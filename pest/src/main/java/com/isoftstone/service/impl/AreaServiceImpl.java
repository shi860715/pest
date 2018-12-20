package com.isoftstone.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.commons.Constants;
import com.isoftstone.entity.model.Area;
import com.isoftstone.entity.model.ContactInfo;
import com.isoftstone.entity.pojo.ContactInfoPojo;
import com.isoftstone.mapper.main.AreaMapper;
import com.isoftstone.service.AreaService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 地区service实现类
 * @author lufei
 *
 */
@Service("areaService")
public class AreaServiceImpl extends BaseService<Area> implements AreaService {
	
	/**
	 * 地区mapper
	 */
	@Resource
	private AreaMapper  areaMapper;
	
	/**
	 * jedis池
	 */
	@Resource
	private JedisPool  jedisPool;
	
	/**
	 * 查询所有省市区点
	 */
	@Override
	public List<Area> getAllArea(){
		List<Area> list = null;
		Jedis redis = jedisPool.getResource();
		String result = redis.get("areaList");
		//判断redis缓存中是否有区域
		if(null != result){
			list = JSON.parseArray(result, Area.class);
		}
		else{
			list = areaMapper.getAllArea();
			String json = JSON.toJSONString(list);
			redis.set("areaList", json);
		}
		redis.close();
		return list;
	}
	
	/**
	 * 新增点区域
	 * @param area
	 * @return
	 */
	@Override
	public int addArea(Area area){
		int result = areaMapper.addArea(area);
		//更新区域redis缓存
		updateAreaRedis();
		return result;
	}
	
	/**
	 * 编辑点区域
	 * @param area
	 * @return
	 */
	@Override
	public int editArea(Area area){
		int result = areaMapper.editArea(area);
		//更新区域redis缓存
		updateAreaRedis();
		return result;
	}
	
	/**
	 * 编辑点区域
	 * @param area
	 * @return
	 */
	@Override
	public int delArea(Long id){
		int result = areaMapper.delArea(id);
		//更新区域redis缓存
		updateAreaRedis();
		return result;
	}
	
	/**
	 * 更新区域redis缓存
	 */
	private void updateAreaRedis(){
		Jedis redis = jedisPool.getResource();
		List<Area> list = areaMapper.getAllArea();
		String json = JSONObject.toJSONString(list);
		redis.set("areaList", json);
		redis.close();
	}
	
	/**
	 * 查询所有省级
	 */
	public List<Area> getProvince(){
		List<Area> list = areaMapper.getProvince();
		return list;
	}
	
	
	/**
	 * 查询区域联系人
	 * @param areaId
	 * @return
	 */
	@Override
	public List<ContactInfo> queryContact(Long areaId){
		List<ContactInfo> list = areaMapper.queryContact(areaId);
		return list;
	}

	/**
	 * 保存区域联系人
	 * @param contactList
	 * @return
	 */
	@Override
	public int saveContact(Long areaId, List<ContactInfo> contactList) {
		int result = areaMapper.delContact(areaId);
		result = areaMapper.saveContact(contactList);
		return result;
	}
	
	/**
	 * 根据parentID与级别查询区域
	 * @param id
	 * @param level
	 * @return
	 */
	public List<Area> queryArea(Long id,Integer level){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("level", level);
		List<Area> list = areaMapper.queryArea(map);
		return list;
	}
	

    /**
     * 根据ID获取所有子集
     * @author llmaoa
     * @param id
     * @return
     * @since 2017年12月6日
     * @see 
     */
    public List<Long> queryChildrenIds(Long id){
        Jedis redis = jedisPool.getResource();
        List<Long> idList = new ArrayList<Long>();
        String ids = redis.get(Constants.AreaConfig.AREA_ID+id);

        if(StringUtils.isNotBlank(ids)){
            idList = JSON.parseArray(ids, Long.class);
        }else{
            ids = areaMapper.queryChildrenIds(id);
            if(ids.indexOf("$")!=-1){
                ids = ids.substring(ids.indexOf("$")+2);
            }
            String[] idStr = ids.split(",");
            for(String str: idStr){
                idList.add(Long.valueOf(str));
            }
            redis.set(Constants.AreaConfig.AREA_ID+id, JSON.toJSONString(idList));
        }
        redis.close();
        return idList;
    }
    
    /**
     * 根据区域编码查询区域
     */
    @Override
    public Area queryAreaByCode(String code){
    	return areaMapper.queryAreaByCode(code);
    }
    
    /**
	 * 根据parentID查询区域(包含自己)
	 * @param id
	 * @param level
	 * @return
	 */
	public List<Area> queryAreaByUser(Long id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		List<Area> list = areaMapper.queryAreaByUser(map);
		return list;
	}
	
	 /**
     * 方法描述：分页查询地域联系人信息
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 地域联系人信息列表
     * @since 2018年02月05日
     */
    @Override
    public PageInfo<ContactInfoPojo> getAllContact(ContactInfoPojo contactInfoPojo, Integer roleId, List<Long> areaList, int start, int length) {
        
        int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", contactInfoPojo.getName());
		map.put("areaName", contactInfoPojo.getAreaName());
		map.put("areaList", areaList);
		map.put("roleId", roleId);

        List<ContactInfoPojo> contactInfoList = areaMapper.getAllContact(map);
        return new PageInfo<ContactInfoPojo>(contactInfoList);
    }
    
    /**
     * 方法描述：更新地域联系人信息
     *
     * @author jnjua
     * @param ContactInfoPojo
     *            地域联系人信息模型
     * @return 持久化操作码
     * @since 2018年02月05日
     */
    public int updateContact(ContactInfoPojo contactInfoPojo) {

        int result = areaMapper.updateContact(contactInfoPojo);

        return result;

    }

    /**
     * 方法描述：删除地域联系人信息
     *
     * @author jnjua
     * @param id
     *            地域联系人ID
     * @return 持久化操作码
     * @since 2018年02月05日
     */
    public int deleteContact(ContactInfoPojo contactInfoPojo) {

    	int result = areaMapper.deleteContact(contactInfoPojo);

        return result;
    }
}
