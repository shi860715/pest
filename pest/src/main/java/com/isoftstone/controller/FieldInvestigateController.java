package com.isoftstone.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.isoftstone.commons.Constants;
import com.isoftstone.entity.model.Area;
import com.isoftstone.entity.model.FieldSurvey;
import com.isoftstone.entity.model.FruitSurvey;
import com.isoftstone.entity.model.NurserySurvey;
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.model.StandardSurvey;
import com.isoftstone.entity.model.StepInvestigation;
import com.isoftstone.entity.model.TrappedSurvey;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.FruitSurveyPojo;
import com.isoftstone.entity.pojo.NurserySurveyPojo;
import com.isoftstone.entity.pojo.StandardSurveyPojo;
import com.isoftstone.entity.pojo.StepInvestigationPojo;
import com.isoftstone.entity.pojo.TrappedSurveyPojo;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.FieldInvestigateService;
import com.isoftstone.util.DateTimeUtil;

import tk.mybatis.mapper.util.StringUtil;

@RestController
@RequestMapping("/fieldPage")
public class FieldInvestigateController {
    
	protected static final Logger logger = LoggerFactory.getLogger(FieldInvestigateController.class);
    /**
     * 野外调查service
     */
    @Resource
    private FieldInvestigateService fieldInvestigateService;
    

    @Resource
    private AreaService areaService;
    
    /**
     * 查询野外调查记录
     * @param fieldSurvey
     * @return
     */
    @RequestMapping(value = "/findPage")
    public Page findPage(FieldSurvey fieldSurvey, Page page){
    	int start =  (page.getPage()-1) * page.getLimit();
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	PageInfo<FieldSurvey> pageInfo = fieldInvestigateService.findPage(fieldSurvey,user.getId(), roleId, areaList, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
    	return page;
    }
    
    /**
     * 根据区域编码查询区域
     * @param code
     * @return
     */
    @RequestMapping(value = "/queryAreaByCode")
    public String queryAreaByCode(String code){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            Area area = areaService.queryAreaByCode(code);
            ajax.setData(area);
        }
        catch (Exception e) {
        	logger.error("【FieldInvestigateController.queryAreaByCode】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
    
    /**
     * 添加野外调查记录
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/addFieldSurvey")
    public String addFieldSurvey(FieldSurvey fieldSurvey){
    	AjaxResponse ajax = new AjaxResponse(true);
        try{
        	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
        	fieldSurvey.setUserId(user.getId());
        	fieldInvestigateService.addFieldSurvey(fieldSurvey);
        	ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【FieldInvestigateController.addFieldSurvey】 error:" + e.getMessage());
        	ajax.setCode(40001);
			ajax.setMessage("添加野外调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 更新野外调查记录
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/updateFieldSurvey")
    public String updateFieldSurvey(FieldSurvey fieldSurvey){
    	AjaxResponse ajax = new AjaxResponse(true);
        try{
        	fieldInvestigateService.updateFieldSurvey(fieldSurvey);
        	ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【FieldInvestigateController.updateFieldSurvey】 error:" + e.getMessage());
        	ajax.setCode(40002);
			ajax.setMessage("修改野外调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 删除调查
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteFieldSurvey")
    public String deleteFieldSurvey(String ids){
    	AjaxResponse ajax = new AjaxResponse(true);
    	try{
    		String[] id = ids.split(",");
    		List<Long> list = new ArrayList<Long>();
    		for(String i : id){
    			list.add(Long.valueOf(i));
            }
    		fieldInvestigateService.deleteFieldSurvey(list);
    		ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【FieldInvestigateController.deleteFieldSurvey】 error:" + e.getMessage());
        	ajax.setCode(40003);
			ajax.setMessage("删除野外调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 查询踏查记录
     * @param stepInvestigation
     * @param page
     * @return
     */
    @RequestMapping(value = "/findStepInvestigationPage")
    public Page findStepInvestigationPage(StepInvestigation stepInvestigation, Page page) {
    	int start =  (page.getPage()-1) * page.getLimit();
    	PageInfo<StepInvestigation> pageInfo = fieldInvestigateService.findStepInvestigationPage(stepInvestigation, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
        return page;

    }
    
    /**
     * 添加踏查记录
     * @param stepInvestigation
     * @return
     */
    @RequestMapping(value = "/addStepInvestigation")
    public String addStepInvestigation(StepInvestigation stepInvestigation) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	List<StepInvestigation> list = new ArrayList<StepInvestigation>();
        list.add(stepInvestigation);
	    try {
	    	fieldInvestigateService.addStepInvestigation(list);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【FieldInvestigateController.addStepInvestigation】 error:" + e.getMessage());
	    	ajax.setCode(40004);
			ajax.setMessage("添加踏查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 编辑踏查记录
     * @param stepInvestigation
     * @return
     */
    @RequestMapping(value = "/editStepInvestigation")
    public String editStepInvestigation(StepInvestigation stepInvestigation) {
    	AjaxResponse ajax = new AjaxResponse(true);
	    try {
	    	fieldInvestigateService.editStepInvestigation(stepInvestigation);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【FieldInvestigateController.editStepInvestigation】 error:" + e.getMessage());
	    	ajax.setCode(40005);
			ajax.setMessage("修改踏查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 删除踏查
     * @param id
     * @return
     */
    @RequestMapping(value = "/delStepInvestigation")
    public String delStepInvestigation(@RequestParam(value = "ids") String ids){
    	AjaxResponse ajax = new AjaxResponse(true);
    	try{
    		String[] id = ids.split(",");
    		List<Long> list = new ArrayList<Long>();
    		for(String i : id){
    			list.add(Long.valueOf(i));
            }
    		fieldInvestigateService.delStepInvestigation(list);
    		ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【FieldInvestigateController.delStepInvestigation】 error:" + e.getMessage());
        	ajax.setCode(40006);
			ajax.setMessage("删除踏查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 查询标准地调查记录
     * @param stepInvestigation
     * @param page
     * @return
     */
    @RequestMapping(value = "/findStandardSurveyPage")
    public Page findStandardSurveyPage(StandardSurvey standardSurvey, Page page) {
    	int start =  (page.getPage()-1) * page.getLimit();
    	PageInfo<StandardSurvey> pageInfo = fieldInvestigateService.findStandardSurveyPage(standardSurvey, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
        return page;

    }
    
    /**
     * 添加标准地调查记录
     * @param stepInvestigation
     * @return
     */
    @RequestMapping(value = "/addStandardSurvey")
    public String addStandardSurvey(StandardSurvey standardSurvey) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	List<StandardSurvey> list = new ArrayList<StandardSurvey>();
        list.add(standardSurvey);
	    try {
	    	fieldInvestigateService.addStandardSurvey(list);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【FieldInvestigateController.addStandardSurvey】 error:" + e.getMessage());
	    	ajax.setCode(40007);
			ajax.setMessage("添加标准地调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 编辑标准地调查记录
     * @param stepInvestigation
     * @return
     */
    @RequestMapping(value = "/editStandardSurvey")
    public String editStandardSurvey(StandardSurvey standardSurvey) {
    	AjaxResponse ajax = new AjaxResponse(true);
	    try {
	    	fieldInvestigateService.editStandardSurvey(standardSurvey);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【FieldInvestigateController.editStandardSurvey】 error:" + e.getMessage());
	    	ajax.setCode(40008);
			ajax.setMessage("修改标准地调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 删除标准地调查记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/delStandardSurvey")
    public String delStandardSurvey(@RequestParam(value = "ids") String ids){
    	AjaxResponse ajax = new AjaxResponse(true);
    	try{
    		String[] id = ids.split(",");
    		List<Long> list = new ArrayList<Long>();
    		for(String i : id){
    			list.add(Long.valueOf(i));
            }
    		fieldInvestigateService.delStandardSurvey(list);
    		ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【FieldInvestigateController.delStandardSurvey】 error:" + e.getMessage());
        	ajax.setCode(40009);
			ajax.setMessage("删除标准地调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 查询诱捕记录
     * @param stepInvestigation
     * @param page
     * @return
     */
    @RequestMapping(value = "/findTrappedSurveyPage")
    public Page findTrappedSurveyPage(TrappedSurvey trappedSurvey, Page page) {
    	int start =  (page.getPage()-1) * page.getLimit();
    	PageInfo<TrappedSurvey> pageInfo = fieldInvestigateService.findTrappedSurveyPage(trappedSurvey, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
        return page;

    }
    
    /**
     * 添加诱捕记录
     * @param stepInvestigation
     * @return
     */
    @RequestMapping(value = "/addTrappedSurvey")
    public String addTrappedSurvey(TrappedSurvey trappedSurvey) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	List<TrappedSurvey> list = new ArrayList<TrappedSurvey>();
        list.add(trappedSurvey);
	    try {
	    	fieldInvestigateService.addTrappedSurvey(list);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【FieldInvestigateController.addTrappedSurvey】 error:" + e.getMessage());
	    	ajax.setCode(40010);
			ajax.setMessage("添加诱捕调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 编辑诱捕记录
     * @param stepInvestigation
     * @return
     */
    @RequestMapping(value = "/editTrappedSurvey")
    public String editTrappedSurvey(TrappedSurvey trappedSurvey) {
    	AjaxResponse ajax = new AjaxResponse(true);
	    try {
	    	fieldInvestigateService.editTrappedSurvey(trappedSurvey);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【FieldInvestigateController.editTrappedSurvey】 error:" + e.getMessage());
	    	ajax.setCode(40011);
			ajax.setMessage("修改诱捕调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 删除诱捕记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/delTrappedSurvey")
    public String delTrappedSurvey(@RequestParam(value = "ids") String ids){
    	AjaxResponse ajax = new AjaxResponse(true);
    	try{
    		String[] id = ids.split(",");
    		List<Long> list = new ArrayList<Long>();
    		for(String i : id){
    			list.add(Long.valueOf(i));
            }
    		fieldInvestigateService.delTrappedSurvey(list);
    		ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【FieldInvestigateController.delTrappedSurvey】 error:" + e.getMessage());
        	ajax.setCode(40012);
			ajax.setMessage("删除诱捕调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 查询花圃记录
     * @param stepInvestigation
     * @param page
     * @return
     */
    @RequestMapping(value = "/findNurserySurveyPage")
    public Page findNurserySurveyPage(NurserySurvey nurserySurvey, Page page) {
    	int start =  (page.getPage()-1) * page.getLimit();
    	PageInfo<NurserySurvey> pageInfo = fieldInvestigateService.findNurserySurveyPage(nurserySurvey, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
        return page;

    }
    
    /**
     * 添加花圃记录
     * @param stepInvestigation
     * @return
     */
    @RequestMapping(value = "/addNurserySurvey")
    public String addNurserySurvey(NurserySurvey nurserySurvey) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	List<NurserySurvey> list = new ArrayList<NurserySurvey>();
        list.add(nurserySurvey);
	    try {
	    	fieldInvestigateService.addNurserySurvey(list);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【FieldInvestigateController.addNurserySurvey】 error:" + e.getMessage());
	    	ajax.setCode(40013);
			ajax.setMessage("添加花圃花木调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 编辑花圃记录
     * @param stepInvestigation
     * @return
     */
    @RequestMapping(value = "/editNurserySurvey")
    public String editNurserySurvey(NurserySurvey nurserySurvey) {
    	AjaxResponse ajax = new AjaxResponse(true);
	    try {
	    	fieldInvestigateService.editNurserySurvey(nurserySurvey);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【FieldInvestigateController.editNurserySurvey】 error:" + e.getMessage());
	    	ajax.setCode(40014);
			ajax.setMessage("修改花圃花木调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 删除花圃记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/delNurserySurvey")
    public String delNurserySurvey(@RequestParam(value = "ids") String ids){
    	AjaxResponse ajax = new AjaxResponse(true);
    	try{
    		String[] id = ids.split(",");
    		List<Long> list = new ArrayList<Long>();
    		for(String i : id){
    			list.add(Long.valueOf(i));
            }
    		fieldInvestigateService.delNurserySurvey(list);
    		ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【FieldInvestigateController.delNurserySurvey】 error:" + e.getMessage());
        	ajax.setCode(40015);
			ajax.setMessage("删除花圃花木调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 查询种果记录
     * @param stepInvestigation
     * @param page
     * @return
     */
    @RequestMapping(value = "/findFruitSurveyPage")
    public Page findFruitSurveyPage(FruitSurvey fruitSurvey, Page page) {
    	int start =  (page.getPage()-1) * page.getLimit();
    	PageInfo<FruitSurvey> pageInfo = fieldInvestigateService.findFruitSurveyPage(fruitSurvey, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
        return page;

    }
    
    /**
     * 添加种果记录
     * @param stepInvestigation
     * @return
     */
    @RequestMapping(value = "/addFruitSurvey")
    public String addFruitSurvey(FruitSurvey fruitSurvey) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	List<FruitSurvey> list = new ArrayList<FruitSurvey>();
        list.add(fruitSurvey);
	    try {
	    	fieldInvestigateService.addFruitSurvey(list);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【FieldInvestigateController.addFruitSurvey】 error:" + e.getMessage());
	    	ajax.setCode(40016);
			ajax.setMessage("添加种果花木调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 编辑种果记录
     * @param stepInvestigation
     * @return
     */
    @RequestMapping(value = "/editFruitSurvey")
    public String editFruitSurvey(FruitSurvey fruitSurvey) {
    	AjaxResponse ajax = new AjaxResponse(true);
	    try {
	    	fieldInvestigateService.editFruitSurvey(fruitSurvey);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【FieldInvestigateController.editFruitSurvey】 error:" + e.getMessage());
	    	ajax.setCode(40017);
			ajax.setMessage("修改种果花木调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 删除种果记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/delFruitSurvey")
    public String delFruitSurvey(@RequestParam(value = "ids") String ids){
    	AjaxResponse ajax = new AjaxResponse(true);
    	try{
    		String[] id = ids.split(",");
    		List<Long> list = new ArrayList<Long>();
    		for(String i : id){
    			list.add(Long.valueOf(i));
            }
    		fieldInvestigateService.delFruitSurvey(list);
    		ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【FieldInvestigateController.delFruitSurvey】 error:" + e.getMessage());
        	ajax.setCode(40018);
			ajax.setMessage("删除种果花木调查失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 查询踏查数据统计信息
     * @param fieldSurvey
     * @return
     */
    @RequestMapping(value = "/findStepDataPage")
    public Page findStepDataPage(FieldSurvey fieldSurvey, Page page){
    	int start =  (page.getPage()-1) * page.getLimit();
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	PageInfo<StepInvestigationPojo> pageInfo = fieldInvestigateService.findStepDataPage(fieldSurvey,user.getId(), roleId, areaList, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
    	return page;
    }
    
    /**
     * 查询踏查数据统计信息
     * @param fieldSurvey
     * @return
     */
    @RequestMapping(value = "/findStandardDataPage")
    public Page findStandardDataPage(FieldSurvey fieldSurvey, Page page){
    	int start =  (page.getPage()-1) * page.getLimit();
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	PageInfo<StandardSurveyPojo> pageInfo = fieldInvestigateService.findStandardDataPage(fieldSurvey,user.getId(), roleId, areaList, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
    	return page;
    }
    
    /**
     * 查询踏查数据统计信息
     * @param fieldSurvey
     * @return
     */
    @RequestMapping(value = "/findTrappedDataPage")
    public Page findTrappedDataPage(FieldSurvey fieldSurvey, Page page){
    	int start =  (page.getPage()-1) * page.getLimit();
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	PageInfo<TrappedSurveyPojo> pageInfo = fieldInvestigateService.findTrappedDataPage(fieldSurvey,user.getId(), roleId, areaList, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
    	return page;
    }
    
    /**
     * 查询踏查数据统计信息
     * @param fieldSurvey
     * @return
     */
    @RequestMapping(value = "/findNurseryDataPage")
    public Page findNurseryDataPage(FieldSurvey fieldSurvey, Page page){
    	int start =  (page.getPage()-1) * page.getLimit();
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	PageInfo<NurserySurveyPojo> pageInfo = fieldInvestigateService.findNurseryDataPage(fieldSurvey,user.getId(), roleId, areaList, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
    	return page;
    }
    
    /**
     * 查询踏查数据统计信息
     * @param fieldSurvey
     * @return
     */
    @RequestMapping(value = "/findFruitDataPage")
    public Page findFruitDataPage(FieldSurvey fieldSurvey, Page page){
    	int start =  (page.getPage()-1) * page.getLimit();
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	PageInfo<FruitSurveyPojo> pageInfo = fieldInvestigateService.findFruitDataPage(fieldSurvey,user.getId(), roleId, areaList, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
    	return page;
    }
    
    /**
     * 导出踏查数据列表
     * @author feilua
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/exportStepInvestigations")
    public String exportStepInvestigations(FieldSurvey fieldSurvey, String time, String columns, String columnsName,  HttpServletResponse response){
        List<String> cList = Arrays.asList(columns.split(","));
        List<String> cnList = Arrays.asList(columnsName.split(","));
        
        UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	if(null != time && !"".equals(time)){
    		
    		fieldSurvey.setSurveyTime(Timestamp.valueOf(time));
    	}
        List<StepInvestigationPojo> stepInvestigationList = fieldInvestigateService.queryStepInvestigation(fieldSurvey, roleId, areaList);
        
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("踏查数据");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        row1.setHeight((short)700);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("踏查数据列表");
        
        HSSFCellStyle headerStyle = (HSSFCellStyle) wb.createCellStyle();// 创建标题样式  
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中  
        HSSFFont headerFont = (HSSFFont) wb.createFont(); //创建字体样式  
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
        headerFont.setFontName("宋体");  //设置字体类型  
        headerFont.setFontHeightInPoints((short) 12);    //设置字体大小  
        headerStyle.setFont(headerFont);    //为标题样式设置字体样式  
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        
        cell.setCellStyle(headerStyle);
        int index = 0, reduce = 0;
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,cList.size()-1));
        
        try {
            //在sheet里创建第二行
            HSSFRow rowTitle = sheet.createRow(1);    
            rowTitle.setHeight((short)500);
            //创建单元格并设置单元格内容
            for(int i=index; i<cnList.size(); i++){
                HSSFCell titleCell = rowTitle.createCell(i-reduce);
                titleCell.setCellValue(cnList.get(i));
                titleCell.setCellStyle(headerStyle);    //单元格引用样式  
                sheet.setColumnWidth((short) i-reduce, (short) 3600);// 设置列宽   
            }
            
            HSSFCellStyle dataStyle = (HSSFCellStyle) wb.createCellStyle();// 创建标题样式  
            dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
            dataStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);   //设置水平居中  
            dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
            dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
            dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
            dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
            HSSFFont dataFont = (HSSFFont) wb.createFont(); //创建字体样式  
            dataFont.setFontName("宋体");  //设置字体类型  
            dataFont.setFontHeightInPoints((short) 10);    //设置字体大小  
            dataStyle.setFont(dataFont);    //为标题样式设置字体样式  
            dataStyle.setWrapText(true);
            int num = 2;
            for(StepInvestigationPojo s : stepInvestigationList){
                HSSFRow rowData = sheet.createRow(num);
                for(int j=index; j<cList.size(); j++){
                    String name = cList.get(j);
                    // 将属性的首字符大写，方便构造get，set方法
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method m = s.getClass().getMethod("get" + name);
                    String value = String.valueOf(m.invoke(s));
                    if("surveyTime".equalsIgnoreCase(name)){
                    	value = value.substring(0, value.length()-2);
                    }
                    HSSFCell dataCell = rowData.createCell(j-reduce);
                    dataCell.setCellStyle(dataStyle);    //单元格引用样式  
                    if(name.equalsIgnoreCase("isSetStandard")){
                        String str = value.equals("1")?"是":"否";
                        dataCell.setCellValue(str);
                    }else if(name.equalsIgnoreCase("harmDegree")){
                        String str = "";
                        if(value.equals("1")){
                        	str = "轻度以下";
                        }else if(value.equals("2")){
                        	str = "轻";
                        }else if(value.equals("3")){
                        	str = "中";
                        }else if(value.equals("4")){
                        	str = "重";
                        }else{
                        	str = "";
                        }
                        dataCell.setCellValue(str);
                    }else if(name.equalsIgnoreCase("insectStatus")){
                        String str = "";
                        if(value.equals("1")){
                        	str = "成虫";
                        }else if(value.equals("2")){
                        	str = "卵";
                        }else if(value.equals("3")){
                        	str = "幼虫";
                        }else if(value.equals("4")){
                        	str = "若虫";
                        }else if(value.equals("5")){
                        	str = "蛹";
                        }else{
                        	str = "";
                        }
                        dataCell.setCellValue(str);
                    }else{
                        if(StringUtil.isNotEmpty(value) && !"null".equalsIgnoreCase(value)){
                            dataCell.setCellValue(value);
                        }else{
                            dataCell.setCellValue("");
                        }
                    }
                }
                num++;
            }
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            String fileName = "踏查数据列表"+DateTimeUtil.getTodayChar14()+".xls";
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" )); 
            response.setContentType("application/msexcel");        
            wb.write(output);
            output.close();
            return null;
        }
        catch (IOException e) {
        	logger.error("【FieldInvestigateController.exportStepInvestigations】 error:" + e.getMessage());
        }
        catch (NoSuchMethodException e) {
        	logger.error("【FieldInvestigateController.exportStepInvestigations】 error:" + e.getMessage());
        }
        catch (SecurityException e) {
        	logger.error("【FieldInvestigateController.exportStepInvestigations】 error:" + e.getMessage());
        }
        catch (IllegalAccessException e) {
        	logger.error("【FieldInvestigateController.exportStepInvestigations】 error:" + e.getMessage());
        }
        catch (IllegalArgumentException e) {
        	logger.error("【FieldInvestigateController.exportStepInvestigations】 error:" + e.getMessage());
        }
        catch (InvocationTargetException e) {
        	logger.error("【FieldInvestigateController.exportStepInvestigations】 error:" + e.getMessage());
        }
        finally{
        	if(null != wb){
        		try {
					wb.close();
				} catch (IOException e) {
					logger.error("【FieldInvestigateController.exportStepInvestigations】 error:" + e.getMessage());
				}
        	}
        }
        return "";
    }
    
    /**
     * 导出标准地调查数据列表
     * @author feilua
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/exportStandardSurveys")
    public String exportStandardSurveys(FieldSurvey fieldSurvey, String time, String columns, String columnsName,  HttpServletResponse response){
        List<String> cList = Arrays.asList(columns.split(","));
        List<String> cnList = Arrays.asList(columnsName.split(","));
        
        UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	if(null != time && !"".equals(time)){
    		
    		fieldSurvey.setSurveyTime(Timestamp.valueOf(time));
    	}
        List<StandardSurveyPojo> standardSurveyList = fieldInvestigateService.queryStandardSurvey(fieldSurvey, roleId, areaList);
        
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("标准地调查数据");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        row1.setHeight((short)700);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("标准地调查数据列表");
        
        HSSFCellStyle headerStyle = (HSSFCellStyle) wb.createCellStyle();// 创建标题样式  
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中  
        HSSFFont headerFont = (HSSFFont) wb.createFont(); //创建字体样式  
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
        headerFont.setFontName("宋体");  //设置字体类型  
        headerFont.setFontHeightInPoints((short) 12);    //设置字体大小  
        headerStyle.setFont(headerFont);    //为标题样式设置字体样式  
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        
        cell.setCellStyle(headerStyle);
        int index = 0, reduce = 0;
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,cList.size()-1));
        
        try {
            //在sheet里创建第二行
            HSSFRow rowTitle = sheet.createRow(1);    
            rowTitle.setHeight((short)500);
            //创建单元格并设置单元格内容
            for(int i=index; i<cnList.size(); i++){
                HSSFCell titleCell = rowTitle.createCell(i-reduce);
                titleCell.setCellValue(cnList.get(i));
                titleCell.setCellStyle(headerStyle);    //单元格引用样式  
                sheet.setColumnWidth((short) i-reduce, (short) 3600);// 设置列宽   
            }
            
            HSSFCellStyle dataStyle = (HSSFCellStyle) wb.createCellStyle();// 创建标题样式  
            dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
            dataStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);   //设置水平居中  
            dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
            dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
            dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
            dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
            HSSFFont dataFont = (HSSFFont) wb.createFont(); //创建字体样式  
            dataFont.setFontName("宋体");  //设置字体类型  
            dataFont.setFontHeightInPoints((short) 10);    //设置字体大小  
            dataStyle.setFont(dataFont);    //为标题样式设置字体样式  
            dataStyle.setWrapText(true);
            int num = 2;
            for(StandardSurveyPojo s : standardSurveyList){
                HSSFRow rowData = sheet.createRow(num);
                for(int j=index; j<cList.size(); j++){
                    String name = cList.get(j);
                    // 将属性的首字符大写，方便构造get，set方法
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method m = s.getClass().getMethod("get" + name);
                    String value = String.valueOf(m.invoke(s));

                    if("surveyTime".equalsIgnoreCase(name)){
                    	value = value.substring(0, value.length()-2);
                    }
                    HSSFCell dataCell = rowData.createCell(j-reduce);
                    dataCell.setCellStyle(dataStyle);    //单元格引用样式  
                    if(name.equalsIgnoreCase("harmDegree")){
                        String str = "";
                        if(value.equals("1")){
                        	str = "轻度以下";
                        }else if(value.equals("2")){
                        	str = "轻";
                        }else if(value.equals("3")){
                        	str = "中";
                        }else if(value.equals("4")){
                        	str = "重";
                        }else{
                        	str = "";
                        }
                        dataCell.setCellValue(str);
                    }else if(name.equalsIgnoreCase("insectStatus")){
                        String str = "";
                        if(value.equals("1")){
                        	str = "成虫";
                        }else if(value.equals("2")){
                        	str = "卵";
                        }else if(value.equals("3")){
                        	str = "幼虫";
                        }else if(value.equals("4")){
                        	str = "若虫";
                        }else if(value.equals("5")){
                        	str = "蛹";
                        }else{
                        	str = "";
                        }
                        dataCell.setCellValue(str);
                    }else if(name.equalsIgnoreCase("isDisaster")){
                    	String str = value.equals("1")?"是":"否";
                        dataCell.setCellValue(str);
                    }else{
                        if(StringUtil.isNotEmpty(value) && !"null".equalsIgnoreCase(value)){
                            dataCell.setCellValue(value);
                        }else{
                            dataCell.setCellValue("");
                        }
                    }
                }
                num++;
            }
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            String fileName = "标准地调查数据列表"+DateTimeUtil.getTodayChar14()+".xls";
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" )); 
            response.setContentType("application/msexcel");        
            wb.write(output);
            output.close();
            return null;
        }
        catch (IOException e) {
        	logger.error("【FieldInvestigateController.exportStandardSurveys】 error:" + e.getMessage());
        }
        catch (NoSuchMethodException e) {
        	logger.error("【FieldInvestigateController.exportStandardSurveys】 error:" + e.getMessage());
        }
        catch (SecurityException e) {
        	logger.error("【FieldInvestigateController.exportStandardSurveys】 error:" + e.getMessage());
        }
        catch (IllegalAccessException e) {
        	logger.error("【FieldInvestigateController.exportStandardSurveys】 error:" + e.getMessage());
        }
        catch (IllegalArgumentException e) {
        	logger.error("【FieldInvestigateController.exportStandardSurveys】 error:" + e.getMessage());
        }
        catch (InvocationTargetException e) {
        	logger.error("【FieldInvestigateController.exportStandardSurveys】 error:" + e.getMessage());
        }
        finally{
        	if(null != wb){
        		try {
					wb.close();
				} catch (IOException e) {
					logger.error("【FieldInvestigateController.exportStandardSurveys】 error:" + e.getMessage());
				}
        	}
        }
        return "";
    }
    
    /**
     * 导出诱捕调查数据列表
     * @author feilua
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/exportTrappedSurveys")
    public String exportTrappedSurveys(FieldSurvey fieldSurvey, String time, String columns, String columnsName,  HttpServletResponse response){
        List<String> cList = Arrays.asList(columns.split(","));
        List<String> cnList = Arrays.asList(columnsName.split(","));
        
        UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	if(null != time && !"".equals(time)){
    		
    		fieldSurvey.setSurveyTime(Timestamp.valueOf(time));
    	}
        List<TrappedSurveyPojo> trappedSurveyList = fieldInvestigateService.queryTrappedSurvey(fieldSurvey, roleId, areaList);
        
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("诱捕调查数据");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        row1.setHeight((short)700);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("诱捕调查数据列表");
        
        HSSFCellStyle headerStyle = (HSSFCellStyle) wb.createCellStyle();// 创建标题样式  
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中  
        HSSFFont headerFont = (HSSFFont) wb.createFont(); //创建字体样式  
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
        headerFont.setFontName("宋体");  //设置字体类型  
        headerFont.setFontHeightInPoints((short) 12);    //设置字体大小  
        headerStyle.setFont(headerFont);    //为标题样式设置字体样式  
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        
        cell.setCellStyle(headerStyle);
        int index = 0, reduce = 0;
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,cList.size()-1));
        
        try {
            //在sheet里创建第二行
            HSSFRow rowTitle = sheet.createRow(1);    
            rowTitle.setHeight((short)500);
            //创建单元格并设置单元格内容
            for(int i=index; i<cnList.size(); i++){
                HSSFCell titleCell = rowTitle.createCell(i-reduce);
                titleCell.setCellValue(cnList.get(i));
                titleCell.setCellStyle(headerStyle);    //单元格引用样式  
                sheet.setColumnWidth((short) i-reduce, (short) 3600);// 设置列宽   
            }
            
            HSSFCellStyle dataStyle = (HSSFCellStyle) wb.createCellStyle();// 创建标题样式  
            dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
            dataStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);   //设置水平居中  
            dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
            dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
            dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
            dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
            HSSFFont dataFont = (HSSFFont) wb.createFont(); //创建字体样式  
            dataFont.setFontName("宋体");  //设置字体类型  
            dataFont.setFontHeightInPoints((short) 10);    //设置字体大小  
            dataStyle.setFont(dataFont);    //为标题样式设置字体样式  
            dataStyle.setWrapText(true);
            int num = 2;
            for(TrappedSurveyPojo t : trappedSurveyList){
                HSSFRow rowData = sheet.createRow(num);
                for(int j=index; j<cList.size(); j++){
                    String name = cList.get(j);
                    // 将属性的首字符大写，方便构造get，set方法
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method m = t.getClass().getMethod("get" + name);
                    String value = String.valueOf(m.invoke(t));
                    if("surveyTime".equalsIgnoreCase(name)){
                    	value = value.substring(0, value.length()-2);
                    }
                    HSSFCell dataCell = rowData.createCell(j-reduce);
                    dataCell.setCellStyle(dataStyle);    //单元格引用样式  
                    if(name.equalsIgnoreCase("insectStatus")){
                        String str = "";
                        if(value.equals("1")){
                        	str = "成虫";
                        }else if(value.equals("2")){
                        	str = "卵";
                        }else if(value.equals("3")){
                        	str = "幼虫";
                        }else if(value.equals("4")){
                        	str = "若虫";
                        }else if(value.equals("5")){
                        	str = "蛹";
                        }else{
                        	str = "";
                        }
                        dataCell.setCellValue(str);
                    }else if(name.equalsIgnoreCase("harmDegree")){
                        String str = "";
                        if(value.equals("1")){
                        	str = "轻度以下";
                        }else if(value.equals("2")){
                        	str = "轻";
                        }else if(value.equals("3")){
                        	str = "中";
                        }else if(value.equals("4")){
                        	str = "重";
                        }else{
                        	str = "";
                        }
                        dataCell.setCellValue(str);
                    }else{
                    	if(StringUtil.isNotEmpty(value) && !"null".equalsIgnoreCase(value)){
                    		dataCell.setCellValue(value);
                    	}else{
                    		dataCell.setCellValue("");
                    	}
                    }
                }
                num++;
            }
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            String fileName = "诱捕调查数据列表"+DateTimeUtil.getTodayChar14()+".xls";
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" )); 
            response.setContentType("application/msexcel");        
            wb.write(output);
            output.close();
            return null;
        }
        catch (IOException e) {
        	logger.error("【FieldInvestigateController.exportTrappedSurveys】 error:" + e.getMessage());
        }
        catch (NoSuchMethodException e) {
        	logger.error("【FieldInvestigateController.exportTrappedSurveys】 error:" + e.getMessage());
        }
        catch (SecurityException e) {
        	logger.error("【FieldInvestigateController.exportTrappedSurveys】 error:" + e.getMessage());
        }
        catch (IllegalAccessException e) {
        	logger.error("【FieldInvestigateController.exportTrappedSurveys】 error:" + e.getMessage());
        }
        catch (IllegalArgumentException e) {
        	logger.error("【FieldInvestigateController.exportTrappedSurveys】 error:" + e.getMessage());
        }
        catch (InvocationTargetException e) {
        	logger.error("【FieldInvestigateController.exportTrappedSurveys】 error:" + e.getMessage());
        }
        finally{
        	if(null != wb){
        		try {
					wb.close();
				} catch (IOException e) {
					logger.error("【FieldInvestigateController.exportTrappedSurveys】 error:" + e.getMessage());
				}
        	}
        }
        return "";
    }
    
    /**
     * 导出苗圃花木调查数据列表
     * @author feilua
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/exportNurserySurveys")
    public String exportNurserySurveys(FieldSurvey fieldSurvey, String time, String columns, String columnsName,  HttpServletResponse response){
        List<String> cList = Arrays.asList(columns.split(","));
        List<String> cnList = Arrays.asList(columnsName.split(","));
        
        UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	if(null != time && !"".equals(time)){
    		
    		fieldSurvey.setSurveyTime(Timestamp.valueOf(time));
    	}
        List<NurserySurveyPojo> nurserySurveyList = fieldInvestigateService.queryNurserySurvey(fieldSurvey, roleId, areaList);
        
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("苗圃花木调查数据");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        row1.setHeight((short)700);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("苗圃花木调查数据列表");
        
        HSSFCellStyle headerStyle = (HSSFCellStyle) wb.createCellStyle();// 创建标题样式  
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中  
        HSSFFont headerFont = (HSSFFont) wb.createFont(); //创建字体样式  
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
        headerFont.setFontName("宋体");  //设置字体类型  
        headerFont.setFontHeightInPoints((short) 12);    //设置字体大小  
        headerStyle.setFont(headerFont);    //为标题样式设置字体样式  
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        
        cell.setCellStyle(headerStyle);
        int index = 0, reduce = 0;
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,cList.size()-1));
        
        try {
            //在sheet里创建第二行
            HSSFRow rowTitle = sheet.createRow(1);    
            rowTitle.setHeight((short)500);
            //创建单元格并设置单元格内容
            for(int i=index; i<cnList.size(); i++){
                HSSFCell titleCell = rowTitle.createCell(i-reduce);
                titleCell.setCellValue(cnList.get(i));
                titleCell.setCellStyle(headerStyle);    //单元格引用样式  
                sheet.setColumnWidth((short) i-reduce, (short) 3600);// 设置列宽   
            }
            
            HSSFCellStyle dataStyle = (HSSFCellStyle) wb.createCellStyle();// 创建标题样式  
            dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
            dataStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);   //设置水平居中  
            dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
            dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
            dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
            dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
            HSSFFont dataFont = (HSSFFont) wb.createFont(); //创建字体样式  
            dataFont.setFontName("宋体");  //设置字体类型  
            dataFont.setFontHeightInPoints((short) 10);    //设置字体大小  
            dataStyle.setFont(dataFont);    //为标题样式设置字体样式  
            dataStyle.setWrapText(true);
            int num = 2;
            for(NurserySurveyPojo n : nurserySurveyList){
                HSSFRow rowData = sheet.createRow(num);
                for(int j=index; j<cList.size(); j++){
                    String name = cList.get(j);
                    // 将属性的首字符大写，方便构造get，set方法
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method m = n.getClass().getMethod("get" + name);
                    String value = String.valueOf(m.invoke(n));
                    if("surveyTime".equalsIgnoreCase(name)){
                    	value = value.substring(0, value.length()-2);
                    }
                    HSSFCell dataCell = rowData.createCell(j-reduce);
                    dataCell.setCellStyle(dataStyle);    //单元格引用样式  
                    if(name.equalsIgnoreCase("harmDegree")){
                        String str = "";
                        if(value.equals("1")){
                        	str = "轻度以下";
                        }else if(value.equals("2")){
                        	str = "轻";
                        }else if(value.equals("3")){
                        	str = "中";
                        }else if(value.equals("4")){
                        	str = "重";
                        }else{
                        	str = "";
                        }
                        dataCell.setCellValue(str);
                    }else if(name.equalsIgnoreCase("insectStatus")){
                        String str = "";
                        if(value.equals("1")){
                        	str = "成虫";
                        }else if(value.equals("2")){
                        	str = "卵";
                        }else if(value.equals("3")){
                        	str = "幼虫";
                        }else if(value.equals("4")){
                        	str = "若虫";
                        }else if(value.equals("5")){
                        	str = "蛹";
                        }else{
                        	str = "";
                        }
                        dataCell.setCellValue(str);
                    }else if(name.equalsIgnoreCase("isDisaster")){
                    	String str = value.equals("1")?"是":"否";
                        dataCell.setCellValue(str);
                    }else{
                        if(StringUtil.isNotEmpty(value) && !"null".equalsIgnoreCase(value)){
                            dataCell.setCellValue(value);
                        }else{
                            dataCell.setCellValue("");
                        }
                    }
                }
                num++;
            }
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            String fileName = "苗圃花木调查数据列表"+DateTimeUtil.getTodayChar14()+".xls";
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" )); 
            response.setContentType("application/msexcel");        
            wb.write(output);
            output.close();
            return null;
        }
        catch (IOException e) {
        	logger.error("【FieldInvestigateController.exportNurserySurveys】 error:" + e.getMessage());
        }
        catch (NoSuchMethodException e) {
        	logger.error("【FieldInvestigateController.exportNurserySurveys】 error:" + e.getMessage());
        }
        catch (SecurityException e) {
        	logger.error("【FieldInvestigateController.exportNurserySurveys】 error:" + e.getMessage());
        }
        catch (IllegalAccessException e) {
        	logger.error("【FieldInvestigateController.exportNurserySurveys】 error:" + e.getMessage());
        }
        catch (IllegalArgumentException e) {
        	logger.error("【FieldInvestigateController.exportNurserySurveys】 error:" + e.getMessage());
        }
        catch (InvocationTargetException e) {
        	logger.error("【FieldInvestigateController.exportNurserySurveys】 error:" + e.getMessage());
        }
        finally{
        	if(null != wb){
        		try {
					wb.close();
				} catch (IOException e) {
					logger.error("【FieldInvestigateController.exportNurserySurveys】 error:" + e.getMessage());
				}
        	}
        }
        return "";
    }
    
    /**
     * 导出种果花木调查数据列表
     * @author feilua
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/exportFruitSurveys")
    public String exportFruitSurveys(FieldSurvey fieldSurvey, String time, String columns, String columnsName,  HttpServletResponse response){
        List<String> cList = Arrays.asList(columns.split(","));
        List<String> cnList = Arrays.asList(columnsName.split(","));
        
        UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	if(null != time && !"".equals(time)){
    		
    		fieldSurvey.setSurveyTime(Timestamp.valueOf(time));
    	}
        List<FruitSurveyPojo> fruitSurveyList = fieldInvestigateService.queryFruitSurvey(fieldSurvey, roleId, areaList);
        
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("种果花木调查数据");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        row1.setHeight((short)700);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("种果花木调查数据列表");
        
        HSSFCellStyle headerStyle = (HSSFCellStyle) wb.createCellStyle();// 创建标题样式  
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中  
        HSSFFont headerFont = (HSSFFont) wb.createFont(); //创建字体样式  
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
        headerFont.setFontName("宋体");  //设置字体类型  
        headerFont.setFontHeightInPoints((short) 12);    //设置字体大小  
        headerStyle.setFont(headerFont);    //为标题样式设置字体样式  
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        
        cell.setCellStyle(headerStyle);
        int index = 0, reduce = 0;
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,cList.size()-1));
        
        try {
            //在sheet里创建第二行
            HSSFRow rowTitle = sheet.createRow(1);    
            rowTitle.setHeight((short)500);
            //创建单元格并设置单元格内容
            for(int i=index; i<cnList.size(); i++){
                HSSFCell titleCell = rowTitle.createCell(i-reduce);
                titleCell.setCellValue(cnList.get(i));
                titleCell.setCellStyle(headerStyle);    //单元格引用样式  
                sheet.setColumnWidth((short) i-reduce, (short) 3600);// 设置列宽   
            }
            
            HSSFCellStyle dataStyle = (HSSFCellStyle) wb.createCellStyle();// 创建标题样式  
            dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
            dataStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);   //设置水平居中  
            dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
            dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
            dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
            dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
            HSSFFont dataFont = (HSSFFont) wb.createFont(); //创建字体样式  
            dataFont.setFontName("宋体");  //设置字体类型  
            dataFont.setFontHeightInPoints((short) 10);    //设置字体大小  
            dataStyle.setFont(dataFont);    //为标题样式设置字体样式  
            dataStyle.setWrapText(true);
            int num = 2;
            for(FruitSurveyPojo f : fruitSurveyList){
                HSSFRow rowData = sheet.createRow(num);
                for(int j=index; j<cList.size(); j++){
                    String name = cList.get(j);
                    // 将属性的首字符大写，方便构造get，set方法
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method m = f.getClass().getMethod("get" + name);
                    String value = String.valueOf(m.invoke(f));
                    if("surveyTime".equalsIgnoreCase(name)){
                    	value = value.substring(0, value.length()-2);
                    }
                    HSSFCell dataCell = rowData.createCell(j-reduce);
                    dataCell.setCellStyle(dataStyle);    //单元格引用样式  
                    if(name.equalsIgnoreCase("harmDegree")){
                        String str = "";
                        if(value.equals("1")){
                        	str = "轻度以下";
                        }else if(value.equals("2")){
                        	str = "轻";
                        }else if(value.equals("3")){
                        	str = "中";
                        }else if(value.equals("4")){
                        	str = "重";
                        }else{
                        	str = "";
                        }
                        dataCell.setCellValue(str);
                    }else if(name.equalsIgnoreCase("insectStatus")){
                        String str = "";
                        if(value.equals("1")){
                        	str = "成虫";
                        }else if(value.equals("2")){
                        	str = "卵";
                        }else if(value.equals("3")){
                        	str = "幼虫";
                        }else if(value.equals("4")){
                        	str = "若虫";
                        }else if(value.equals("5")){
                        	str = "蛹";
                        }else{
                        	str = "";
                        }
                        dataCell.setCellValue(str);
                    }else{
                        if(StringUtil.isNotEmpty(value) && !"null".equalsIgnoreCase(value)){
                            dataCell.setCellValue(value);
                        }else{
                            dataCell.setCellValue("");
                        }
                    }
                }
                num++;
            }
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            String fileName = "种果花木调查数据列表"+DateTimeUtil.getTodayChar14()+".xls";
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" )); 
            response.setContentType("application/msexcel");        
            wb.write(output);
            output.close();
            return null;
        }
        catch (IOException e) {
        	logger.error("【FieldInvestigateController.exportFruitSurveys】 error:" + e.getMessage());
        }
        catch (NoSuchMethodException e) {
        	logger.error("【FieldInvestigateController.exportFruitSurveys】 error:" + e.getMessage());
        }
        catch (SecurityException e) {
        	logger.error("【FieldInvestigateController.exportFruitSurveys】 error:" + e.getMessage());
        }
        catch (IllegalAccessException e) {
        	logger.error("【FieldInvestigateController.exportFruitSurveys】 error:" + e.getMessage());
        }
        catch (IllegalArgumentException e) {
        	logger.error("【FieldInvestigateController.exportFruitSurveys】 error:" + e.getMessage());
        }
        catch (InvocationTargetException e) {
        	logger.error("【FieldInvestigateController.exportFruitSurveys】 error:" + e.getMessage());
        }
        finally{
        	if(null != wb){
        		try {
					wb.close();
				} catch (IOException e) {
					logger.error("【FieldInvestigateController.exportFruitSurveys】 error:" + e.getMessage());
				}
        	}
        }
        return "";
    }
    
}
