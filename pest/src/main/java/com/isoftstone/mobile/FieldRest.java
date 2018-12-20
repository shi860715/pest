package com.isoftstone.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.FieldSurvey;
import com.isoftstone.entity.model.FruitSurvey;
import com.isoftstone.entity.model.NurserySurvey;
import com.isoftstone.entity.model.StandardSurvey;
import com.isoftstone.entity.model.StepInvestigation;
import com.isoftstone.entity.model.TrappedSurvey;
import com.isoftstone.entity.model.UserRole;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.RawFilePojo;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.FieldInvestigateService;
import com.isoftstone.service.UserRoleService;
import com.isoftstone.util.FileUtil;

@Controller
@RequestMapping("/m/field")
public class FieldRest {

	protected static final Logger logger = LoggerFactory.getLogger(FieldRest.class);
	
    @Resource
    private AreaService areaService;
    
    @Resource
    private UserRoleService userRoleService;
    
    @Resource
    private FieldInvestigateService fieldInvestigateService;
    
    //获取配置文件中图片的路径
    @Value("${upload.uploadPath}")
    private String uploadPath;
    
    /**
     * 手机端获取野外调查列表
     * post /m/field/queryFieldSurvey
     * @author jnjua
     * @param fieldSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/queryFieldSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String queryFieldSurvey(@RequestBody FieldSurvey fieldSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            int start =  (fieldSurvey.getPageNum()-1) * fieldSurvey.getPageSize();
            //获取登录用户
            UserRole userRole = userRoleService.getUserRole(fieldSurvey.getUserId());
            List<Long> areaList = new ArrayList<Long>();
            Integer roleId = Integer.valueOf(userRole.getRoleid());
            if(roleId != 1){
        		Long manageArea = fieldSurvey.getAreaId();
        		areaList = areaService.queryChildrenIds(manageArea);
        	}
            PageInfo<FieldSurvey> pageInfo = fieldInvestigateService.findPage(fieldSurvey, fieldSurvey.getUserId(), roleId, areaList, start, fieldSurvey.getPageSize());
            ajax.setMessage("野外调查分页查询列表成功！");
            ajax.setData(pageInfo);
        }
        catch (Exception e) {
        	logger.error("【FieldRest.queryFieldSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage(e.getMessage());
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端添加野外调查
     * post /m/field/addFieldSurvey
     * @author jnjua
     * @param fieldSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/addFieldSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String addFieldSurvey(@RequestBody FieldSurvey fieldSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	fieldInvestigateService.addFieldSurvey(fieldSurvey);
        	Map<String,Long> map = new HashMap<String,Long>();
        	map.put("fieldId", fieldSurvey.getId());
        	ajax.setData(map);
    		ajax.setCode(1);
            ajax.setMessage("添加野外调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.addFieldSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("添加野外调查失败【90004】");
        }
        return ajax.toJSONString();
    }
   
    /**
     * 手机端修改野外调查
     * post /m/field/updateFieldSurvey
     * @author jnjua
     * @param fieldSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/updateFieldSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String updateFieldSurvey(@RequestBody FieldSurvey fieldSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	fieldInvestigateService.updateFieldSurvey(fieldSurvey);
    		ajax.setCode(1);
            ajax.setMessage("修改野外调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.updateFieldSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("修改野外调查失败【90005】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端删除野外调查
     * post /m/field/deleteFieldSurvey
     * @author jnjua
     * @param fieldSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/deleteFieldSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFieldSurvey(@RequestBody FieldSurvey fieldSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	List<Long> list = new ArrayList<Long>();
        	list.add(fieldSurvey.getId());
        	fieldInvestigateService.deleteFieldSurvey(list);
    		ajax.setCode(1);
            ajax.setMessage("删除野外调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.deleteFieldSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("删除野外调查失败【90006】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端获取踏查列表
     * post /m/field/queryStepInvestigation
     * @author jnjua
     * @param stepInvestigation
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/queryStepInvestigation", method = RequestMethod.POST)
    @ResponseBody
    public String queryStepInvestigation(@RequestBody StepInvestigation stepInvestigation) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            int start =  (stepInvestigation.getPageNum()-1) * stepInvestigation.getPageSize();
            
            PageInfo<StepInvestigation> pageInfo = fieldInvestigateService.findStepInvestigationPage(stepInvestigation, start, stepInvestigation.getPageSize());
            ajax.setMessage("踏查分页查询列表成功！");
            ajax.setData(pageInfo);
        }
        catch (Exception e) {
        	logger.error("【FieldRest.queryStepInvestigation】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage(e.getMessage());
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端添加踏查
     * post /m/field/addStepInvestigation
     * @author jnjua
     * @param stepInvestigation
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/addStepInvestigation", method = RequestMethod.POST)
    @ResponseBody
    public String addStepInvestigation(@RequestBody StepInvestigation stepInvestigation) {
        AjaxResponse ajax = new AjaxResponse(true);
        List<StepInvestigation> list = new ArrayList<StepInvestigation>();
        list.add(stepInvestigation);
        try {
        	fieldInvestigateService.addStepInvestigation(list);
    		ajax.setCode(1);
            ajax.setMessage("添加踏查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.addStepInvestigation】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("添加踏查失败【90007】");
        }
        return ajax.toJSONString();
    }
   
    /**
     * 手机端批量添加踏查
     * post /m/field/addStepInvestigations
     * @author jnjua
     * @param list
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/addStepInvestigations", method = RequestMethod.POST)
    @ResponseBody
    public String addStepInvestigations(@RequestBody List<StepInvestigation> list) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	fieldInvestigateService.addStepInvestigation(list);
    		ajax.setCode(1);
            ajax.setMessage("批量添加踏查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.addStepInvestigations】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("批量添加踏查失败【90033】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端修改踏查
     * post /m/field/updateStepInvestigation
     * @author jnjua
     * @param stepInvestigation
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/updateStepInvestigation", method = RequestMethod.POST)
    @ResponseBody
    public String updateStepInvestigation(@RequestBody StepInvestigation stepInvestigation) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	fieldInvestigateService.editStepInvestigation(stepInvestigation);
    		ajax.setCode(1);
            ajax.setMessage("修改踏查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.updateStepInvestigation】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("修改踏查失败【90008】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端删除踏查
     * post /m/field/delStepInvestigation
     * @author jnjua
     * @param stepInvestigation
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/delStepInvestigation", method = RequestMethod.POST)
    @ResponseBody
    public String delStepInvestigation(@RequestBody StepInvestigation stepInvestigation) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	List<Long> list = new ArrayList<Long>();
        	list.add(stepInvestigation.getId());
        	fieldInvestigateService.delStepInvestigation(list);
    		ajax.setCode(1);
            ajax.setMessage("删除踏查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.delStepInvestigation】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("删除踏查失败【90009】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端获取标准地调查列表
     * post /m/field/queryStandardSurvey
     * @author jnjua
     * @param standardSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/queryStandardSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String queryStandardSurvey(@RequestBody StandardSurvey standardSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            int start =  (standardSurvey.getPageNum()-1) * standardSurvey.getPageSize();
            
            PageInfo<StandardSurvey> pageInfo = fieldInvestigateService.findStandardSurveyPage(standardSurvey, start, standardSurvey.getPageSize());
            ajax.setMessage("标准地调查分页查询列表成功！");
            ajax.setData(pageInfo);
        }
        catch (Exception e) {
        	logger.error("【FieldRest.queryStandardSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage(e.getMessage());
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端添加标准地调查
     * post /m/field/addStandardSurvey
     * @author jnjua
     * @param standardSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/addStandardSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String addStandardSurvey(@RequestBody StandardSurvey standardSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        List<StandardSurvey> list = new ArrayList<StandardSurvey>();
        list.add(standardSurvey);
        try {
        	fieldInvestigateService.addStandardSurvey(list);
    		ajax.setCode(1);
            ajax.setMessage("添加标准地调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.addStandardSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("添加标准地调查失败【90010】");
        }
        return ajax.toJSONString();
    }
   
    /**
     * 手机端批量添加标准地调查
     * post /m/field/addStandardSurveys
     * @author jnjua
     * @param list
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/addStandardSurveys", method = RequestMethod.POST)
    @ResponseBody
    public String addStandardSurveys(@RequestBody List<StandardSurvey> list) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	fieldInvestigateService.addStandardSurvey(list);
    		ajax.setCode(1);
            ajax.setMessage("批量添加标准地调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.addStandardSurveys】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("批量添加标准地调查失败【90034】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端修改标准地调查
     * post /m/field/updateStandardSurvey
     * @author jnjua
     * @param standardSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/updateStandardSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String updateStandardSurvey(@RequestBody StandardSurvey standardSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	fieldInvestigateService.editStandardSurvey(standardSurvey);
    		ajax.setCode(1);
            ajax.setMessage("修改标准地调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.updateStandardSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("修改标准地调查失败【90011】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端删除标准地调查
     * post /m/field/delStandardSurvey
     * @author jnjua
     * @param standardSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/delStandardSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String delStandardSurvey(@RequestBody StandardSurvey standardSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	List<Long> list = new ArrayList<Long>();
        	list.add(standardSurvey.getId());
        	fieldInvestigateService.delStandardSurvey(list);
    		ajax.setCode(1);
            ajax.setMessage("删除标准地调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.delStandardSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("删除标准地调查失败【90012】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端获取诱捕调查列表
     * post /m/field/queryTrappedSurvey
     * @author jnjua
     * @param trappedSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/queryTrappedSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String queryTrappedSurvey(@RequestBody TrappedSurvey trappedSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            int start =  (trappedSurvey.getPageNum()-1) * trappedSurvey.getPageSize();
            
            PageInfo<TrappedSurvey> pageInfo = fieldInvestigateService.findTrappedSurveyPage(trappedSurvey, start, trappedSurvey.getPageSize());
            ajax.setMessage("诱捕调查分页查询列表成功！");
            ajax.setData(pageInfo);
        }
        catch (Exception e) {
        	logger.error("【FieldRest.queryTrappedSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage(e.getMessage());
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端添加诱捕调查
     * post /m/field/addTrappedSurvey
     * @author jnjua
     * @param trappedSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/addTrappedSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String addTrappedSurvey(@RequestBody TrappedSurvey trappedSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        List<TrappedSurvey> list = new ArrayList<TrappedSurvey>();
        list.add(trappedSurvey);
        try {
        	fieldInvestigateService.addTrappedSurvey(list);
    		ajax.setCode(1);
            ajax.setMessage("添加诱捕调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.addTrappedSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("添加诱捕调查失败【90013】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端批量添加诱捕调查
     * post /m/field/addTrappedSurveys
     * @author jnjua
     * @param list
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/addTrappedSurveys", method = RequestMethod.POST)
    @ResponseBody
    public String addTrappedSurveys(@RequestBody List<TrappedSurvey> list) {
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	fieldInvestigateService.addTrappedSurvey(list);
    		ajax.setCode(1);
            ajax.setMessage("批量添加诱捕调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.addTrappedSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("批量添加诱捕调查失败【90035】");
        }
        return ajax.toJSONString();
    }
   
    /**
     * 手机端修改诱捕调查
     * post /m/field/updateTrappedSurvey
     * @author jnjua
     * @param trappedSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/updateTrappedSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String updateTrappedSurvey(@RequestBody TrappedSurvey trappedSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	fieldInvestigateService.editTrappedSurvey(trappedSurvey);
    		ajax.setCode(1);
            ajax.setMessage("修改诱捕调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.updateTrappedSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("修改诱捕调查失败【90014】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端删除诱捕调查
     * post /m/field/delTrappedSurvey
     * @author jnjua
     * @param trappedSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/delTrappedSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String delTrappedSurvey(@RequestBody TrappedSurvey trappedSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	List<Long> list = new ArrayList<Long>();
        	list.add(trappedSurvey.getId());
        	fieldInvestigateService.delTrappedSurvey(list);
    		ajax.setCode(1);
            ajax.setMessage("删除诱捕调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.delTrappedSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("删除诱捕调查失败【90015】");
        }
        return ajax.toJSONString();
    }
   
    /**
     * 手机端获取苗圃花木调查列表
     * post /m/field/queryNurserySurvey
     * @author jnjua
     * @param nurserySurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/queryNurserySurvey", method = RequestMethod.POST)
    @ResponseBody
    public String queryNurserySurvey(@RequestBody NurserySurvey nurserySurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            int start =  (nurserySurvey.getPageNum()-1) * nurserySurvey.getPageSize();
            
            PageInfo<NurserySurvey> pageInfo = fieldInvestigateService.findNurserySurveyPage(nurserySurvey, start, nurserySurvey.getPageSize());
            ajax.setMessage("苗圃花木调查分页查询列表成功！");
            ajax.setData(pageInfo);
            return ajax.toJSONString();
        }
        catch (Exception e) {
        	logger.error("【FieldRest.queryNurserySurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage(e.getMessage());
            return ajax.toJSONString();
        }
    }
    
    /**
     * 手机端添加苗圃花木调查
     * post /m/field/addNurserySurvey
     * @author jnjua
     * @param nurserySurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/addNurserySurvey", method = RequestMethod.POST)
    @ResponseBody
    public String addNurserySurvey(@RequestBody NurserySurvey nurserySurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        List<NurserySurvey> list = new ArrayList<NurserySurvey>();
        list.add(nurserySurvey);
        try {
        	fieldInvestigateService.addNurserySurvey(list);
    		ajax.setCode(1);
            ajax.setMessage("添加苗圃花木调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.addNurserySurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("添加苗圃花木调查失败【90016】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端批量添加苗圃花木调查
     * post /m/field/addNurserySurveys
     * @author jnjua
     * @param list
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/addNurserySurveys", method = RequestMethod.POST)
    @ResponseBody
    public String addNurserySurveys(@RequestBody List<NurserySurvey> list) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	fieldInvestigateService.addNurserySurvey(list);
    		ajax.setCode(1);
            ajax.setMessage("批量添加苗圃花木调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.addNurserySurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("批量添加苗圃花木调查失败【90036】");
        }
        return ajax.toJSONString();
    }
   
    /**
     * 手机端修改苗圃花木调查
     * post /m/field/updateNurserySurvey
     * @author jnjua
     * @param nurserySurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/updateNurserySurvey", method = RequestMethod.POST)
    @ResponseBody
    public String updateNurserySurvey(@RequestBody NurserySurvey nurserySurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	fieldInvestigateService.editNurserySurvey(nurserySurvey);
    		ajax.setCode(1);
            ajax.setMessage("修改苗圃花木调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.updateNurserySurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("修改苗圃花木调查失败【90017】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端删除苗圃花木调查
     * post /m/field/delNurserySurvey
     * @author jnjua
     * @param nurserySurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/delNurserySurvey", method = RequestMethod.POST)
    @ResponseBody
    public String delNurserySurvey(@RequestBody NurserySurvey nurserySurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	List<Long> list = new ArrayList<Long>();
        	list.add(nurserySurvey.getId());
        	fieldInvestigateService.delNurserySurvey(list);
    		ajax.setCode(1);
            ajax.setMessage("删除苗圃花木调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.delNurserySurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("删除苗圃花木调查失败【90018】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端获取种果花木调查列表
     * post /m/field/queryFruitSurvey
     * @author jnjua
     * @param fruitSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/queryFruitSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String queryFruitSurvey(@RequestBody FruitSurvey fruitSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            int start =  (fruitSurvey.getPageNum()-1) * fruitSurvey.getPageSize();
            
            PageInfo<FruitSurvey> pageInfo = fieldInvestigateService.findFruitSurveyPage(fruitSurvey, start, fruitSurvey.getPageSize());
            ajax.setMessage("种果花木调查分页查询列表成功！");
            ajax.setData(pageInfo);
        }
        catch (Exception e) {
        	logger.error("【FieldRest.queryFruitSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage(e.getMessage());
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端添加种果花木调查
     * post /m/field/addFruitSurvey
     * @author jnjua
     * @param fruitSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/addFruitSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String addFruitSurvey(@RequestBody FruitSurvey fruitSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        List<FruitSurvey> list = new ArrayList<FruitSurvey>();
        list.add(fruitSurvey);
        try {
        	fieldInvestigateService.addFruitSurvey(list);
    		ajax.setCode(1);
            ajax.setMessage("添加种果花木调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.addFruitSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("添加种果花木调查失败【90019】");
        }
        return ajax.toJSONString();
    }
   
    /**
     * 手机端批量添加种果花木调查
     * post /m/field/addFruitSurveys
     * @author jnjua
     * @param list
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/addFruitSurveys", method = RequestMethod.POST)
    @ResponseBody
    public String addFruitSurveys(@RequestBody List<FruitSurvey> list) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	fieldInvestigateService.addFruitSurvey(list);
    		ajax.setCode(1);
            ajax.setMessage("批量添加种果花木调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.addFruitSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("批量添加种果花木调查失败【90037】");
        }
        return ajax.toJSONString();
    }

    
    /**
     * 手机端修改种果花木调查
     * post /m/field/updateFruitSurvey
     * @author jnjua
     * @param fruitSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/updateFruitSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String updateFruitSurvey(@RequestBody FruitSurvey fruitSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	fieldInvestigateService.editFruitSurvey(fruitSurvey);
    		ajax.setCode(1);
            ajax.setMessage("修改种果花木调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.updateFruitSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("修改种果花木调查失败【90020】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端删除种果花木调查
     * post /m/field/delFruitSurvey
     * @author jnjua
     * @param fruitSurvey
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/delFruitSurvey", method = RequestMethod.POST)
    @ResponseBody
    public String delFruitSurvey(@RequestBody FruitSurvey fruitSurvey) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	List<Long> list = new ArrayList<Long>();
        	list.add(fruitSurvey.getId());
        	fieldInvestigateService.delFruitSurvey(list);
    		ajax.setCode(1);
            ajax.setMessage("删除种果花木调查成功！");
        }
        catch (Exception e) {
        	logger.error("【FieldRest.updateFruitSurvey】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("删除种果花木调查失败【90021】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 踏查上传单张图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/stepUpload")
    @ResponseBody
    public String stepUpload(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, "step");
            
            if (CollectionUtils.isNotEmpty(rfList)) {
                ajax.setData(rfList.get(0));
            }
        }
        catch (Exception e) {
        	logger.error("【FieldRest.stepUpload】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
    
    /**
     * 踏查上传多张图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/stepUploadMore")
    @ResponseBody
    public String stepUploadMore(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, "step");
            ajax.setData(rfList);
        }
        catch (Exception e) {
        	logger.error("【FieldRest.stepUploadMore】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
    
    /**
     * 标准地调查上传单张图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/standardUpload")
    @ResponseBody
    public String standardUpload(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, "standard");
            
            if (CollectionUtils.isNotEmpty(rfList)) {
                ajax.setData(rfList.get(0));
            }
        }
        catch (Exception e) {
        	logger.error("【FieldRest.standardUpload】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
    
    /**
     * 标准地调查上传多张图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/standardUploadMore")
    @ResponseBody
    public String standardUploadMore(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, "standard");
            ajax.setData(rfList);
        }
        catch (Exception e) {
        	logger.error("【FieldRest.standardUploadMore】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
    
    /**
     * 诱捕调查上传单张图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/trappedUpload")
    @ResponseBody
    public String trappedUpload(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, "trapped");
            
            if (CollectionUtils.isNotEmpty(rfList)) {
                ajax.setData(rfList.get(0));
            }
        }
        catch (Exception e) {
        	logger.error("【FieldRest.trappedUpload】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
    
    /**
     * 诱捕调查上传多张图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/trappedUploadMore")
    @ResponseBody
    public String trappedUploadMore(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, "trapped");
            ajax.setData(rfList);
        }
        catch (Exception e) {
        	logger.error("【FieldRest.trappedUploadMore】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }

    /**
     * 苗圃花木上传单张图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/nurseryUpload")
    @ResponseBody
    public String nurseryUpload(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, "nursery");
            
            if (CollectionUtils.isNotEmpty(rfList)) {
                ajax.setData(rfList.get(0));
            }
        }
        catch (Exception e) {
        	logger.error("【FieldRest.nurseryUpload】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
    
    /**
     * 苗圃花木上传多张图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/nurseryUploadMore")
    @ResponseBody
    public String nurseryUploadMore(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, "nursery");
            ajax.setData(rfList);
        }
        catch (Exception e) {
        	logger.error("【FieldRest.nurseryUploadMore】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
    
    
    /**
     * 种果花木上传单张图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/fruitUpload")
    @ResponseBody
    public String fruitUpload(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, "fruit");
            
            if (CollectionUtils.isNotEmpty(rfList)) {
                ajax.setData(rfList.get(0));
            }
        }
        catch (Exception e) {
        	logger.error("【FieldRest.fruitUpload】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
    
    /**
     * 种果花木上传多张图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/fruitUploadMore")
    @ResponseBody
    public String fruitUploadMore(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, "fruit");
            ajax.setData(rfList);
        }
        catch (Exception e) {
        	logger.error("【FieldRest.fruitUploadMore】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
}
