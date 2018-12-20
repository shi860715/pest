package com.isoftstone.controller;

import java.util.ArrayList;
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
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.model.RemoteMonitore;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.RemoteMonitorePojo;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.RemoteMonitoreService;

@RestController
@RequestMapping("/monitorPage")
public class RemoteMonitoreController {
	protected static final Logger logger = LoggerFactory.getLogger(RemoteMonitoreController.class);
	/**
	 * 远程监控设备service
	 */
	@Resource
    private RemoteMonitoreService remoteMonitoreService;
	
	@Resource
    private AreaService areaService;
	
	/**
	 * 查询远程监控设备信息
	 * @param Pojo
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/findPage")
    public Page findPage(RemoteMonitore remoteMonitore, Page page) {
    	int start =  (page.getPage()-1) * page.getLimit();
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	PageInfo<RemoteMonitorePojo> pageInfo = remoteMonitoreService.findPage(remoteMonitore, roleId, areaList, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
        return page;

    }
	
	/**
	 * 批量删除远程监控设备信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delRemoteMonitore")
    public String delRemoteMonitore(@RequestParam(value = "ids") String ids) {
		AjaxResponse ajax = new AjaxResponse(true);
		try{
    		String[] id = ids.split(",");
    		List<Long> list = new ArrayList<Long>();
    		for(String i : id){
    			list.add(Long.valueOf(i));
            }
    		remoteMonitoreService.delRemoteMonitore(list);
    		ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【RemoteMonitoreController.delRemoteMonitore】 error:" + e.getMessage());
        	ajax.setCode(80001);
			ajax.setMessage("删除远程监控设备失败");
        }
		return ajax.toJSONString();

    }
	
	/**
	 * 添加远程监控设备信息
	 * @param RemoteDiagnosis
	 * @return
	 */
	@RequestMapping(value = "/addRemoteMonitore")
    public String addRemoteMonitore(RemoteMonitore remoteMonitore) {
		AjaxResponse ajax = new AjaxResponse(true);
	    try {
	    	remoteMonitoreService.addRemoteMonitore(remoteMonitore);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【RemoteMonitoreController.addRemoteMonitore】 error:" + e.getMessage());
	    	ajax.setCode(80002);
			ajax.setMessage("添加远程监控设备失败");
        }
		return ajax.toJSONString();
    }
	
	/**
	 * 编辑远程监控设备信息
	 * @param remoteDiagnosis
	 * @return
	 */
	@RequestMapping(value = "/editRemoteMonitore")
    public String editRemoteMonitore(RemoteMonitore remoteMonitore) {
		AjaxResponse ajax = new AjaxResponse(true);
	    try {
	    	remoteMonitoreService.editRemoteMonitore(remoteMonitore);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【RemoteMonitoreController.editRemoteMonitore】 error:" + e.getMessage());
	    	ajax.setCode(80003);
			ajax.setMessage("修改远程监控设备失败");
        }
		return ajax.toJSONString();
    }
}
