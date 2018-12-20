package com.isoftstone.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.mapper.main.CollectMapper;
import com.isoftstone.entity.model.Area;
import com.isoftstone.entity.model.Collect;
import com.isoftstone.entity.pojo.CollectPojo;
import com.isoftstone.entity.pojo.PestPolylinePojo;
import com.isoftstone.service.CollectService;

/**
 * Created by jnjua on 2017/11/24.
 */
@Service("collectService")
public class CollectServiceImpl extends BaseService<Collect> implements CollectService{
    @Resource
    private CollectMapper collectMapper;
    
    /**
     * 方法描述：分页查询自动虫情信息列表
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 自动虫情信息列表
     * @since 2017年11月24日
     */
    @Override
    public PageInfo<Collect> findPage(Collect collect, Integer roleId, List<Long> areaList, int start, int length) {
        
        int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        Timestamp afterTime = collect.getAfterTime();
        Timestamp beforeTime = collect.getBeforeTime();
        String insectName = collect.getInsectName();
        String deviceName = collect.getDeviceName();
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("afterTime", afterTime);
		map.put("beforeTime", beforeTime);
		map.put("insectName", insectName);
		map.put("deviceName", deviceName);
		map.put("areaList", areaList);
		map.put("roleId", roleId);

        List<Collect> collectList = collectMapper.findPage(map);
        return new PageInfo<Collect>(collectList);
    }
    
    /**
     * 方法描述：添加自动虫情信息
     *
     * @author jnjua	
     * @param CollectPojo
     *            自动虫情信息
     * @return 持久化操作码
     * @since 2017年11月24日
     * @see
     */
    public int addCollect(Collect collect) {

        int result = collectMapper.addCollect(collect);

        return result;
    }
    
    /**
     * 方法描述：修改自动虫情信息
     *
     * @author jnjua
     * @param CollectPojo
     *            自动虫情信息模型
     * @return 持久化操作码
     * @since 2017年11月24日
     */
    public int updateCollectById(Collect collect) {

        int result = collectMapper.updateCollectById(collect);

        return result;

    }
    
    /**
     * 方法描述：更新自动虫情信息
     *
     * @author jnjua
     * @param CollectPojo
     *            自动虫情信息模型
     * @return 持久化操作码
     * @since 2017年11月24日
     */
    public int updateCollect(Collect collect) {

        int result = collectMapper.updateCollect(collect);

        return result;

    }

    /**
     * 方法描述：删除自动虫情信息
     *
     * @author jnjua
     * @param id
     *            自动虫情ID
     * @return 持久化操作码
     * @since 2017年11月24日
     */
    public int delCollect(List<Long> uList) {

    	int result = collectMapper.delCollect(uList);

        return result;
    }
    
    /**
     * 方法描述：判断是否需要新增还是修改自动虫情信息
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 自动虫情信息列表
     * @since 2017年11月24日
     */
    @Override
    public int findOne(Collect collect) {

    	int result = collectMapper.findOne(collect);
        return result;
    }

    /**
     * 导入虫情数据
     */
    @Override
    public int importCollects(List<Collect> collectList){
    	int result = collectMapper.importCollects(collectList);
        return result;
    }
    
    /**
     * 查询虫情数据
     * @param collect
     * @return
     */
    @Override
    public List<Collect> queryCollects(Collect collect, Integer roleId, List<Long> areaList){
    	Timestamp afterTime = collect.getAfterTime();
        Timestamp beforeTime = collect.getBeforeTime();
        String insectName = collect.getInsectName();
        String deviceName = collect.getDeviceName();
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("afterTime", afterTime);
		map.put("beforeTime", beforeTime);
		map.put("insectName", insectName);
		map.put("deviceName", deviceName);
		map.put("areaList", areaList);
		map.put("roleId", roleId);
    	List<Collect> list = collectMapper.findPage(map);
        return list;
    }
    
    /**
     * 查询虫情数据
     * @param collect
     * @return
     */
    @Override
    public List<Collect> findInsectStatisticsPage(Collect collect, Integer roleId, Long areaId, List<Long> areaList){
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("captureTime", collect.getCaptureTime());
		map.put("roleId", roleId);
		map.put("areaId", areaId);
		map.put("areaList", areaList);
    	List<Collect> list = collectMapper.findInsectStatisticsPage(map);
        return list;
    }
    
    /**
     * 查询折线图虫情统计数据
     */
    @Override
    public List<CollectPojo> findPestPolylinePage(Collect collect, Integer type, Integer roleId, Long areaId, List<Long> areaList){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("insectId", collect.getInsectId());
		map.put("captureTime", collect.getCaptureTime());
		map.put("type", type);
		map.put("roleId", roleId);
		map.put("areaId", areaId);
		map.put("areaList", areaList);
		
		List<CollectPojo> collectPojoList = collectMapper.queryInsectNames(map);
		map.put("collectPojoList", collectPojoList);
		
		if(null != collectPojoList && collectPojoList.size() > 0 ){
			List<PestPolylinePojo> collectList = null;
			if(null == type || type == 1){
				collectList = collectMapper.findPestPolylinePageByMonth(map);
			}
			else{
				collectList = collectMapper.findPestPolylinePageByYear(map);
			}
			
			for(CollectPojo cp : collectPojoList){
				List<PestPolylinePojo> temp = new ArrayList<PestPolylinePojo>();
				for(PestPolylinePojo c : collectList){
					if(cp.getInsectName().equals(c.getInsectName())){
						temp.add(c);
					}
				}
				cp.setList(temp);
			}
		}
        return collectPojoList;
    }
    
    /**
     * 查询虫害柱状图数据
     */
    @Override
    public List<CollectPojo> findPestColumnarPage(Collect collect, Integer type, List<Area> areaList, Long insectId1, Long insectId2){
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("captureTime", collect.getCaptureTime());
		map.put("type", type);
		map.put("areaList", areaList);
		map.put("insectId1", insectId1);
		map.put("insectId2", insectId2);
		List<CollectPojo> list = collectMapper.findPestColumnarPage(map);
		return list;
    }
}
