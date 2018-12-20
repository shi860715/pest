package com.isoftstone.mapper.main;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.Area;
import com.isoftstone.entity.model.ContactInfo;
import com.isoftstone.entity.pojo.ContactInfoPojo;
import com.isoftstone.util.MyMapper;

/**
 * 地区mapper
 * @author lufei
 *
 */
public interface AreaMapper extends MyMapper<Area> {
	
	/**
	 * 查询所有区域
	 * @return
	 */
	public List<Area> getAllArea();
	
	/**
	 * 新增点区域
	 * @param area
	 * @return
	 */
	public int addArea(Area area);
	
	/**
	 * 编辑点区域
	 * @param area
	 * @return
	 */
	public int editArea(Area area);
	
	/**
	 * 删除点区域
	 * @param id
	 * @return
	 */
	public int delArea(Long id);
	
	/**
	 * 查询省级区域
	 * @return
	 */
	public List<Area> getProvince();
	
	/**
	 * 查询区域联系人
	 * @param areaId
	 * @return
	 */
	public List<ContactInfo> queryContact(Long areaId);
	
	/**
	 * 保存区域联系人
	 * @param contactList
	 * @return
	 */
	public int saveContact(@Param(value="contactList") List<ContactInfo> contactList);
	
	/**
	 * 删除区域联系人
	 * @param areaId
	 * @return
	 */
	public int delContact(Long areaId);
	
	/**
	 * 根据parentID与级别查询区域
	 * @param map
	 * @return
	 */
	public List<Area> queryArea(Map<String,Object> map);
	
	/**
	 * 根据父级ID获取子集ID集合
	 * @author llmaoa
	 * @param id
	 * @return
	 * @since 2017年12月6日
	 * @see 
	 */
	public String queryChildrenIds(@Param(value="id")Long id);
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public Area queryAreaByCode(@Param(value="code")String code);
	
	/**
	 * 根据parentID查询区域(包含自己)
	 * @param map
	 * @return
	 */
	public List<Area> queryAreaByUser(Map<String,Object> map);
	
	
	/**
     * 
     * 分页查询地域联系人信息
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 地域联系人分页列表
     * @since 2018年02月05日
     */
    public List<ContactInfoPojo> getAllContact(Map<String,Object> map);
    
    /**
     * 修改地域联系人信息
     *
     * @author jnjua
     * @param ContactInfoPojo
     *            地域联系人模型
     * @return 持久化操作码
     * @since 2018年02月05日
     */
    public int updateContact(ContactInfoPojo contactInfoPojo);
    
    /**
     * 删除地域联系人信息
     *
     * @author jnjua
     * @param ContactInfoPojo
     *            地域联系人模型
     * @return 持久化操作码
     * @since 2018年02月05日
     */
    public int deleteContact(ContactInfoPojo contactInfoPojo);
}
