package com.isoftstone.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.isoftstone.commons.Constants;
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.pojo.SetParameterPojo;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.SetParameterService;

@RestController
@RequestMapping("/paramPage")
public class SetParameterController {

	/**
	 * 远程诊断service
	 */
	@Resource
    private SetParameterService setParameterService;
	
	@Resource
    private AreaService areaService;
	
	/**
	 * 分页查询参数设置列表
	 * @param Pojo
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/findPage")
    public Page findPage(String devName, Page page) {
    	int start =  (page.getPage()-1) * page.getLimit();
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	PageInfo<SetParameterPojo> pageInfo = setParameterService.findPage(devName, roleId, areaList, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
        return page;

    }
}
