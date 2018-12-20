package com.isoftstone.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource; 

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.isoftstone.commons.Constants;
import com.isoftstone.entity.model.DevParameter;
import com.isoftstone.entity.model.Device;
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.DevParameterPojo;
import com.isoftstone.entity.pojo.DevicePojo;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.DeviceService;


/**
 * Created by jnjua on 2017/11/13.
 */
@RestController
@RequestMapping("/devicePage")
public class DeviceController {
	
	protected static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
	
    @Resource
    private DeviceService deviceService;

    @Resource
    private AreaService areaService;
    
    /**
     * 方法描述：设备信息分页查询
     * <p>
     * POST / device/findPage
     *
     * @author jnjua
     * @param device
     *            设备信息模型
     * @param page
     *            分页模型
     * @return 设备信息列表分袂
     * @since 2017年11月13日
     * @see
     */
    @RequestMapping(value = "/findPage")
    public Page findPage(Device device, Page page) {

    	int start =  (page.getPage()-1) * page.getLimit();
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	PageInfo<DevicePojo> pageDevices = deviceService.findPage(device,roleId,areaList, start, page.getLimit());
    	page.setData(pageDevices.getList());
    	page.setCount(pageDevices.getTotal());
        return page;
    }
    
    /**
     * 删除设备信息
     * <p>
     * POST/device/deleteDevice
     *
     * @author jnjua
     * @param user
     *            设备模型
     * @param userSession
     *            会话模型
     * @return 持久化操作码
     * @since 2017年11月13日
     */
    @RequestMapping(value = "/deleteDevice")
    public String deleteDevice(@RequestParam(value = "ids") String ids) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	try{
    		String[] id = ids.split(",");
    		List<Long> idList = new ArrayList<Long>();
    		for(String i : id){
    			idList.add(Long.valueOf(i));
            }
    		deviceService.deleteDevice(idList);
    		ajax.setCode(1);
        }catch (Exception e){
            logger.error("【DeviceController.deleteDevice】 error:" + e.getMessage());
            ajax.setCode(30005);
        	ajax.setMessage("删除设备失败");
        }
    	return ajax.toJSONString();
    }
    
    /**
     * 方法描述：新增设备信息
     * <p>
     * POST /device/addDevice
     *
     * @author jnjua
     * @param device
     *            设备信息模型
     * @param userSession.
     *            用户会话数据
     * @return 持久化操作码
     * @since 2017年11月13日
     */
    @RequestMapping(value = "/addDevice")
    public String addDevice(DevicePojo devicePojo,String buyTime1,String expireTime1) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
	    	User user = (User) SecurityUtils.getSubject().getPrincipal();
	    	devicePojo.setDevStatus(1);
	    	devicePojo.setIsDelete(0);
	    	devicePojo.setCreateUserid(user.getId());
	    	devicePojo.setCreateTime(System.currentTimeMillis()/1000);
	    	devicePojo.setBuyTime((sdf.parse(buyTime1).getTime())/1000);
	    	devicePojo.setExpireTime((sdf.parse(expireTime1).getTime())/1000);
	    	devicePojo.setIsRemove(0);
	    	deviceService.addDevice(devicePojo);
	    	ajax.setCode(1);
	    } 
	    catch (Exception e) {
	    	logger.error("【DeviceController.addDevice】 error:" + e.getMessage());
	    	ajax.setCode(30006);
	        ajax.setMessage("添加设备失败");
	    }
	    return ajax.toJSONString();
	}
    
    /**
     * 方法描述：修改设备信息
     * <p>
     * POST /device/updateDevice
     *
     * @author jnjua
     * @param user
     * @param userSession
     * @return
     * @since 2017年11月10日
     * @see
     */
    @RequestMapping(value = "/updateDevice")
    public String updateDevice(DevicePojo devicePojo,String buyTime1,String expireTime1) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
    		User user = (User) SecurityUtils.getSubject().getPrincipal();
    		devicePojo.setUpdateUserid(user.getId());
    		devicePojo.setBuyTime((sdf.parse(buyTime1).getTime())/1000);
    		if(devicePojo.getBuyType() == 2){
    			devicePojo.setExpireTime((sdf.parse(expireTime1).getTime())/1000);
    		}
	    	devicePojo.setUpdateTime(System.currentTimeMillis()/1000);
    		deviceService.updateDevice(devicePojo);
    		ajax.setCode(1);
	    } 
    	catch (Exception e) {
	    	logger.error("【DeviceController.updateDevice】 error:" + e.getMessage());
	    	ajax.setCode(30007);
		    ajax.setMessage("修改设备失败");
	    }
    	return ajax.toJSONString();
    }
    
    /**
	 * 根据设备id与编码查询设备参数设置
	 * @param param
	 * @return
	 */
	@RequestMapping("/queryParam")
	public String queryParam(DevParameter param){
		AjaxResponse ajax = new AjaxResponse(true);
		List<DevParameter> list = deviceService.queryParam(param);
		DevParameterPojo dpp = new DevParameterPojo();
		dpp.setDeviceId(param.getDeviceId());
		dpp.setDevCode(param.getDevCode());
		for(DevParameter dp : list){
			if("lm_th".equals(dp.getParameterName())){
				dpp.setSunshineTime(dp.getParameterValue());
			}
			else if("run_tm".equals(dp.getParameterName())){
				String[] value = dp.getParameterValue().split(",");
				dpp.setRunStart(value[0]);
				dpp.setRunEnd(value[1]);
			}
			else if("wk_invl".equals(dp.getParameterName())){
				dpp.setWorkTime((Long.parseLong(dp.getParameterValue())/3600)+"");
			}
			else if("pic_up_invl".equals(dp.getParameterName())){
				dpp.setBackTime((Long.parseLong(dp.getParameterValue())/3600)+"");
			}
			else if("heat_temp".equals(dp.getParameterName())){
				dpp.setHeat(dp.getParameterValue());
			}
			else if("heat_tm".equals(dp.getParameterName())){
				dpp.setRoastTime((Long.parseLong(dp.getParameterValue())/60)+"");
			}
			else if("clear_invl".equals(dp.getParameterName())){
				dpp.setCleanTime((Long.parseLong(dp.getParameterValue())/86400)+"");
			}
			else if("sleep_tm".equals(dp.getParameterName())){
				String[] value = dp.getParameterValue().split(",");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(Long.parseLong(value[0])*1000);
				dpp.setSleepStart(sdf.format(calendar.getTime()));
				calendar.setTimeInMillis(Long.parseLong(value[1])*1000);
				dpp.setSleepEnd(sdf.format(calendar.getTime()));
			}
		}
		ajax.setData(dpp);
        return ajax.toJSONString();
    }
	
	/**
	 * 更新设备参数设置
	 * @param pojo
	 * @return
	 */
	@RequestMapping(value = "/updateParam")
    public String updateParam(DevParameterPojo pojo) {
		AjaxResponse ajax = new AjaxResponse(true);
    	try {
    		pojo.setUpdateTime(System.currentTimeMillis()/1000+"");
    		pojo.setWorkTime((Long.parseLong(pojo.getWorkTime())*3600)+"");
    		pojo.setBackTime((Long.parseLong(pojo.getBackTime())*3600)+"");
    		pojo.setRoastTime((Long.parseLong(pojo.getRoastTime())*60)+"");
    		pojo.setCleanTime((Long.parseLong(pojo.getCleanTime())*86400)+"");
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		pojo.setSleepStart(((sdf.parse(pojo.getSleepStart()).getTime())/1000)+"");
    		pojo.setSleepEnd(((sdf.parse(pojo.getSleepEnd()).getTime())/1000)+"");
    		
    		deviceService.updateParam(pojo);
    		ajax.setCode(1);
	    } 
    	catch (Exception e) {
	    	logger.error("【DeviceController.updateParam】 error:" + e.getMessage());
	    	ajax.setCode(30008);
			ajax.setMessage("参数设定失败，请确保填写的参数正确");
	    }
    	return ajax.toJSONString();
    }
	

}
