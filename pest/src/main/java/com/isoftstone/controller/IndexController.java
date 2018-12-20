package com.isoftstone.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isoftstone.commons.Constants;
import com.isoftstone.entity.model.DeviceExtends;
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.entity.pojo.WarningDataPojo;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.DeviceService;
import com.isoftstone.service.UserService;
import com.isoftstone.service.WarningDataService;

/**
 * Created by liup on 2017/9/7.
 */
@RestController
@RequestMapping("/index")
public class IndexController {
	protected static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Resource
    private UserService userService;
    @Resource
    private AreaService areaService;
    @Resource
    private DeviceService deviceService;
    @Resource
    private WarningDataService warningDataService;

    /**
     * 查询用户数量
     * 
     * @param user
     *            用户
     * @return
     */
    @RequestMapping("/GetUserSum")
    public int GetUserSum() {
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	int count = userService.getUserSum(roleId, areaList);
    	return count;
    }

    /**
     * 查询设备总数
     * 
     * @param dev
     *            设备
     * @return
     */
    @RequestMapping(value = "/getDevNum")
    public int getDevNum() {
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	int count = deviceService.getDevNum(roleId, areaList);
    	return count;
    }

    /**
     * 查询到期设备总数
     * 
     * @param dev
     *            设备
     * @return
     */
    @RequestMapping(value = "/getExpireDevNum")
    public int getExpireDevNum() {
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	int count = deviceService.getExpireDevNum(roleId, areaList);
    	return count;
    }
    
    /**
     * 查询虫情告警数据
     * 
     * @param warningData
     *            虫情告警
     * @return
     */
    @RequestMapping(value = "/getWarningData")
    public Page getWarningData(Page page) {
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	List<WarningDataPojo> count = warningDataService.getWarningData(roleId, areaList);
    	page.setData(count);
    	return page;
    }
    
    /**
     * 查询过期设备数据
     * 
     * @param dev
     *            设备
     * @return
     */
    @RequestMapping(value = "/getExpireDev")
    public Page getExpireDev(Page page) {
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	List<DeviceExtends> count = deviceService.getExpireDev(roleId, areaList);
    	page.setData(count);
    	return page;
    }

}
