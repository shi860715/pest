package com.isoftstone.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.Area;
import com.isoftstone.entity.model.ContactInfo;
import com.isoftstone.entity.pojo.ContactInfoPojo;

/**
 * 地区service
 * @author lufei
 *
 */
public interface AreaService extends IService<Area> {
	
	/**
	 * 查询所有省市区点
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
	 * @param area
	 * @return
	 */
	public int delArea(Long id);
	
	/**
	 * 查询所有省级
	 */
	public List<Area> getProvince();
	
	/**
	 * 查询区域联系人
	 * @param area
	 * @return
	 */
	public List<ContactInfo> queryContact(Long areaId);
	
	/**
	 * 保存区域联系人
	 * @param area
	 * @return
	 */
	public int saveContact(Long areaId, List<ContactInfo> contactList);
	
	/**
	 * 根据parentID与级别查询区域
	 * @param id
	 * @param level
	 * @return
	 */
	public List<Area> queryArea(Long id,Integer level);
	
	/**
	 * 根据ID获取所有子集
	 * @author llmaoa
	 * @param id
	 * @return
	 * @since 2017年12月6日
	 * @see 
	 */
	public List<Long> queryChildrenIds(Long id);
	
	/**
	 * 根据区域编码查询区域
	 * @param code
	 * @return
	 */
	public Area queryAreaByCode(String code);
	
	/**
	 * 根据parentID查询区域(包含自己)
	 * @param id
	 * @param level
	 * @return
	 */
	public List<Area> queryAreaByUser(Long id);
	
	/**
	 * 分页查询地域联系人信息
	 */
	PageInfo<ContactInfoPojo> getAllContact(ContactInfoPojo contactInfoPojo, Integer roleId, List<Long> areaList, int start, int length);

	int updateContact(ContactInfoPojo contactInfoPojo);
    
    int deleteContact(ContactInfoPojo contactInfoPojo);
}
