package com.isoftstone.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.isoftstone.commons.Constants;
import com.isoftstone.entity.model.Area;
import com.isoftstone.entity.model.ContactInfo;
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.ContactInfoPojo;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.service.AreaService;

/**
 * 地区control
 * @author lufei
 *
 */
@RestController
@RequestMapping("/areasPage")
public class AreaController {
	
	protected static final Logger logger = LoggerFactory.getLogger(AreaController.class);
	
	@Resource
    private AreaService areaService;
	
	/**
	 * 查询所有省市区点
	 */
	@RequestMapping("/getAllArea")
    public List<Area> getAllArea(){
        List<Area> list = areaService.getAllArea();
        return list;
    }
	
	/**
	 * 添加点
	 */
	@RequestMapping("/addArea")
	public String addArea(Area area){
		AjaxResponse ajax = new AjaxResponse(true);
		try {
			areaService.addArea(area);
			ajax.setCode(1);
			ajax.setData(area);
		} catch (Exception e) {
			logger.error("【AreaController.addArea】 error:" + e.getMessage());
			ajax.setCode(20004);
            ajax.setMessage("添加点区域失败");
		}
        return ajax.toJSONString();
    }
	
	/**
	 * 编辑点区域
	 */
	@RequestMapping("/editArea")
	public String editArea(Area area){
		AjaxResponse ajax = new AjaxResponse(true);
		try {
			areaService.editArea(area);
			ajax.setCode(1);
		} catch (Exception e) {
			logger.error("【AreaController.editArea】 error:" + e.getMessage());
			ajax.setCode(20005);
            ajax.setMessage("修改点区域失败");
		}
		return ajax.toJSONString();
    }
	
	/**
	 * 删除点区域
	 */
	@RequestMapping("/delArea")
	public String delArea(Long id){
		AjaxResponse ajax = new AjaxResponse(true);
		try {
			int result = areaService.delArea(id);
			ajax.setCode(result);
		} catch (Exception e) {
			logger.error("【AreaController.delArea】 error:" + e.getMessage());
			ajax.setCode(20006);
            ajax.setMessage("删除点区域失败");
		}
		return ajax.toJSONString();
    }
	
	/**
	 * 查询所有省级
	 */
	@RequestMapping("/getProvince")
    public String getProvince(){
		AjaxResponse ajax = new AjaxResponse(true);
        List<Area> list = areaService.getProvince();
        ajax.setData(list);
        return ajax.toJSONString();
    }
	
	/**
	 * 查询区域联系人
	 */
	@RequestMapping("/queryContact")
	public String queryContact(Long areaId){
		AjaxResponse ajax = new AjaxResponse(true);
		List<ContactInfo> list = areaService.queryContact(areaId);
		ajax.setData(list);
        return ajax.toJSONString();
    }
	
	/**
	 * 保存区域联系人
	 */
	@RequestMapping("/saveContact")
	public String saveContact(Long areaId,String names,String telephones,String emails){
		AjaxResponse ajax = new AjaxResponse(true);
		try {
			String[] nameArray = names.split(",");
			String[] telephoneArray = telephones.split(",");
			String[] emailArray = emails.split(",");
			List<ContactInfo> contactList = new ArrayList<ContactInfo>();
			for(int i =0;i<nameArray.length;i++){
				ContactInfo c = new ContactInfo();
				c.setAreaId(areaId);
				c.setName(nameArray[i]);
				c.setTelephone(telephoneArray[i]);
				c.setEmail(emailArray[i]);
				contactList.add(c);
			}
			areaService.saveContact(areaId, contactList);
			ajax.setCode(1);
		} catch (Exception e) {
			logger.error("【AreaController.saveContact】 error:" + e.getMessage());
			ajax.setCode(20007);
            ajax.setMessage("添加联系人失败");
		}
        return ajax.toJSONString();
    }
	
	/**
	 * 根据parentID与级别查询区域
	 * @param id
	 * @param level
	 * @return
	 */
	@RequestMapping("/queryArea")
	public String queryArea(Long id,Integer level){
		AjaxResponse ajax = new AjaxResponse(true);
		List<Area> list = areaService.queryArea(id,level);
		ajax.setData(list);
        return ajax.toJSONString();
    }
	
	
	/**
	 * 根据parentID查询区域(包含自己)
	 * @param id
	 * @param level
	 * @return
	 */
	@RequestMapping("/queryAreaByUser")
	public String queryAreaByUser(){
		AjaxResponse ajax = new AjaxResponse(true);
		UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Area> list;
    	if(roleId == 1){
    		list = areaService.getProvince();
    		Area area = new Area();
    		area.setName("全国");
    		area.setId(0L);
    		list.add(0, area);
    	}else{
    		list = areaService.queryAreaByUser(user.getManageArea());
    	}
		ajax.setData(list);
        return ajax.toJSONString();
    }
	
	/**
     * 方法描述：分页查询地域联系人信息
     * <p>
     * POST /areasPage/getAllContact
     *
     * @author jnjua
     * @param contactInfoPojo
     *            地域联系人信息模型
     * @param page
     *            分页模型
     * @return 地域联系人信息列表分袂
     * @since 2018年02月05日
     * @see
     */
    @RequestMapping(value = "/getAllContact")
    public Page getAllContact(ContactInfoPojo contactInfoPojo, Page page) {

    	int start =  (page.getPage()-1) * page.getLimit();
    	
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1 && roleId != 5){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	PageInfo<ContactInfoPojo> pageUsers = areaService.getAllContact(contactInfoPojo, roleId, areaList, start, page.getLimit());
    	page.setData(pageUsers.getList());
    	page.setCount(pageUsers.getTotal());
        return page;

    }
    
    /**
     * 方法描述：修改地域联系人信息
     * <p>
     * POST /areasPage/updateContact
     *
     * @author jnjua
     * @param ContactInfoPojo
     * @param userSession
     * @return
     * @since 2018年02月05日
     * @see
     */
    @RequestMapping(value = "/updateContact")
    public String updateContact(ContactInfoPojo contactInfoPojo) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	try{
    		areaService.updateContact(contactInfoPojo);
    		ajax.setCode(1);
    	}catch (Exception e){
            logger.error("【AreaController.updateContact】 error:" + e.getMessage());
            ajax.setCode(20008);
        	ajax.setMessage("修改地域联系人失败");
        }
    	return ajax.toJSONString();
    }
    
    /**
     * 删除地域联系人信息
     * <p>
     * POST /areasPage/deleteContact
     *
     * @author jnjua
     * @param ContactInfoPojo
     *            地域联系人模型
     * @param userSession
     *            会话模型
     * @return 持久化操作码
     * @since 2018年02月05日
     */
    @RequestMapping(value = "/deleteContact")
    public String deleteContact(ContactInfoPojo contactInfoPojo) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	try{
    		areaService.deleteContact(contactInfoPojo);
    		ajax.setCode(1);
        }catch (Exception e){
            logger.error("【AreaController.deleteContact】 error:" + e.getMessage());
            ajax.setCode(20009);
        	ajax.setMessage("删除地域联系人失败");
        }
    	return ajax.toJSONString();
    }
    
}
