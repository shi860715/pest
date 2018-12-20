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
import com.isoftstone.entity.model.Area;
import com.isoftstone.entity.model.Collect;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.CollectPojo;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.CollectService;

/**
 * Created by jnjua on 2017/11/24.
 */
@RestController
@RequestMapping("/statisticsPage")
public class StatisticsController {
    protected static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);
    @Resource
    private CollectService collectService;
    
    @Resource
    private AreaService areaService;
    
    /**
     * 查询虫类统计图
     * <p>
     * POST / collect/findPage
     *
     * @author jnjua
     * @param collect
     *            自动虫情信息模型
     * @param page
     *            分页模型
     * @return 自动虫情信息列表分袂
     * @since 2017年11月24日
     * @see
     */
    @RequestMapping(value = "/findInsectStatisticsPage")
    public String findInsectStatisticsPage(Collect collect, Long areaId) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1 || (areaId != null && areaId != 0)){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(null != areaId ? areaId : manageArea);
    	}
    	List<Collect> list = collectService.findInsectStatisticsPage(collect, roleId, areaId, areaList);
    	ajax.setData(list);
        return ajax.toJSONString();

    }
    
    /**
     * 查询虫害折线图
     * @param collect
     * @param areaId
     * @return
     */
    @RequestMapping(value = "/findPestPolylinePage")
    public String findPestPolylinePage(Collect collect, Integer type, Long areaId) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1 || (areaId != null && areaId != 0)){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(null != areaId ? areaId : manageArea);
    	}
    	List<CollectPojo> list = collectService.findPestPolylinePage(collect, type, roleId, areaId, areaList);
    	ajax.setData(list);
        return ajax.toJSONString();
    }
    
    /**
     * 
     * @param collect
     * @param type
     * @return
     */
    @RequestMapping(value = "/findPestColumnarPage")
    public String findPestColumnarPage(Collect collect, Integer type, Long insectId1, Long insectId2) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Area> areaList = null;
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryArea(manageArea,null);
    	}
    	else{
    		areaList = areaService.queryArea(null,1);
    	}
    	List<CollectPojo> cpList = collectService.findPestColumnarPage(collect, type, areaList ,insectId1 ,insectId2);
    	List<Object> objList = new ArrayList<Object>();
    	objList.add(areaList);
    	objList.add(cpList);
    	ajax.setData(objList);
        return ajax.toJSONString();
    }
    
}
