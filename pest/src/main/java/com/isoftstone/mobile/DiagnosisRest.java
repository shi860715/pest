package com.isoftstone.mobile;

import java.util.ArrayList;
import java.util.List;

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
import com.isoftstone.entity.model.RemoteDiagnosis;
import com.isoftstone.entity.model.UserRole;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.RawFilePojo;
import com.isoftstone.entity.pojo.RemoteDiagnosisPojo;
import com.isoftstone.service.RemoteDiagnosisService;
import com.isoftstone.service.UserRoleService;
import com.isoftstone.util.FileUtil;

@Controller
@RequestMapping("/m/diagnosis")
public class DiagnosisRest {

	protected static final Logger logger = LoggerFactory.getLogger(DiagnosisRest.class);
	
    @Resource
    private RemoteDiagnosisService remoteDiagnosisService;
    
    @Resource
    private UserRoleService userRoleService;
    
    //获取配置文件中图片的路径
    @Value("${upload.uploadPath}")
    private String uploadPath;
    
    /**
     * 手机端获取远程诊断信息
     * post /m/diagnosis/queryRemoteDiagnosis
     * @author jnjua
     * @param remoteDiagnosis
     * @return
     * @since 2017年12月20日
     * @see 
     */
    @RequestMapping(value = "/queryRemoteDiagnosis", method = RequestMethod.POST)
    @ResponseBody
    public String queryRemoteDiagnosis(@RequestBody RemoteDiagnosisPojo Pojo) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            int start =  (Pojo.getPageNum()-1) * Pojo.getPageSize();
            //获取登录用户
            UserRole userRole = userRoleService.getUserRole(Pojo.getUserId());
            Integer roleId = Integer.valueOf(userRole.getRoleid());
            PageInfo<RemoteDiagnosisPojo> pageInfo = remoteDiagnosisService.findRemoteDiagnosis(Pojo, roleId, start, Pojo.getPageSize());
            ajax.setMessage("远程诊断分页查询列表成功！");
            ajax.setData(pageInfo);
        }
        catch (Exception e) {
        	logger.error("【DiagnosisRest.queryRemoteDiagnosis】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage(e.getMessage());
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端添加远程诊断信息
     * post /m/diagnosis/addRemoteDiagnosis
     * @author jnjua
     * @param remoteDiagnosis
     * @return
     * @since 2017年12月20日
     * @see 
     */
    @RequestMapping(value = "/addRemoteDiagnosis", method = RequestMethod.POST)
    @ResponseBody
    public String addRemoteDiagnosis(@RequestBody RemoteDiagnosis remoteDiagnosis) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {   		
        	remoteDiagnosisService.addDiagnosis(remoteDiagnosis);
    		ajax.setCode(1);
            ajax.setMessage("添加远程诊断信息成功！");
        }
        catch (Exception e) {
        	logger.error("【DiagnosisRest.addRemoteDiagnosis】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("添加远程诊断信息失败【90001】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端删除远程诊断信息
     * post /m/diagnosis/delRemoteDiagnosis
     * @author jnjua
     * @param remoteDiagnosis
     * @return
     * @since 2017年12月20日
     * @see 
     */
    @RequestMapping(value = "/delRemoteDiagnosis", method = RequestMethod.POST)
    @ResponseBody
    public String delRemoteDiagnosis(@RequestBody RemoteDiagnosis remoteDiagnosis) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {   
        	List<Long> list = new ArrayList<Long>();
        	list.add(remoteDiagnosis.getId());
        	remoteDiagnosisService.delDiagnosis(list);
    		ajax.setCode(1);
            ajax.setMessage("删除远程诊断信息成功！");
        }
        catch (Exception e) {
        	logger.error("【DiagnosisRest.delRemoteDiagnosis】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("删除远程诊断信息失败【90002】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端专家诊断信息
     * post /m/diagnosis/diagnosis
     * @author jnjua
     * @param remoteDiagnosis
     * @return
     * @since 2017年12月20日
     * @see 
     */
    @RequestMapping(value = "/diagnosis", method = RequestMethod.POST)
    @ResponseBody
    public String diagnosis(@RequestBody RemoteDiagnosis remoteDiagnosis) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {   
        	remoteDiagnosisService.diagnosis(remoteDiagnosis);
    		ajax.setCode(1);
            ajax.setMessage("专家诊断远程诊断信息成功！");
        }
        catch (Exception e) {
        	logger.error("【DiagnosisRest.diagnosis】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("专家诊断远程诊断信息失败【90003】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 上传单张图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, "diagnosis");
            
            if (CollectionUtils.isNotEmpty(rfList)) {
                ajax.setData(rfList.get(0));
            }
        }
        catch (Exception e) {
        	logger.error("【DiagnosisRest.upload】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
    
    /**
     * 上传多张图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadMore")
    @ResponseBody
    public String uploadMore(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, "diagnosis");
            ajax.setData(rfList);
        }
        catch (Exception e) {
        	logger.error("【DiagnosisRest.uploadMore】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
}
