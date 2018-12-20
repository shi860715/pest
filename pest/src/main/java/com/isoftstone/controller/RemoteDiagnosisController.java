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
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.model.RemoteDiagnosis;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.RemoteDiagnosisPojo;
import com.isoftstone.service.RemoteDiagnosisService;

@RestController
@RequestMapping("/diagnosisPage")
public class RemoteDiagnosisController {

	protected static final Logger logger = LoggerFactory.getLogger(RemoteDiagnosisController.class);
	
	/**
	 * 远程诊断service
	 */
	@Resource
    private RemoteDiagnosisService remoteDiagnosisService;
	
	/**
	 * 查询远程诊断数据
	 * @param Pojo
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/findPage")
    public Page findPage(RemoteDiagnosisPojo Pojo, Page page) {
    	int start =  (page.getPage()-1) * page.getLimit();
    	PageInfo<RemoteDiagnosisPojo> pageInfo = remoteDiagnosisService.findPage(Pojo, start, page.getLimit());
    	page.setData(pageInfo.getList());
    	page.setCount(pageInfo.getTotal());
        return page;

    }
	
	/**
	 * 批量删除远程诊断信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delDiagnosis")
    public String delDiagnosis(@RequestParam(value = "ids") String ids) {
		AjaxResponse ajax = new AjaxResponse(true);
		try{
    		String[] id = ids.split(",");
    		List<Long> list = new ArrayList<Long>();
    		for(String i : id){
    			list.add(Long.valueOf(i));
            }
    		remoteDiagnosisService.delDiagnosis(list);
    		ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【RemoteDiagnosisController.delDiagnosis】 error:" + e.getMessage());
        	ajax.setCode(50001);
			ajax.setMessage("删除远程诊断失败");
        }
		return ajax.toJSONString();
    }
	
	/**
	 * 添加远程诊断信息
	 * @param RemoteDiagnosis
	 * @return
	 */
	@RequestMapping(value = "/addDiagnosis")
    public String addDiagnosis(RemoteDiagnosis remoteDiagnosis) {
		AjaxResponse ajax = new AjaxResponse(true);
	    try {
	    	 User user = (User) SecurityUtils.getSubject().getPrincipal();
	    	remoteDiagnosis.setUserId(user.getId());
	    	remoteDiagnosisService.addDiagnosis(remoteDiagnosis);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【RemoteDiagnosisController.addDiagnosis】 error:" + e.getMessage());
	    	ajax.setCode(50002);
			ajax.setMessage("添加远程诊断失败");
	    }
	    return ajax.toJSONString();
    }
	
	/**
	 * 编辑诊断信息
	 * @param remoteDiagnosis
	 * @return
	 */
	@RequestMapping(value = "/editDiagnosis")
    public String editDiagnosis(RemoteDiagnosis remoteDiagnosis) {
		AjaxResponse ajax = new AjaxResponse(true);
	    try {
	    	remoteDiagnosisService.editDiagnosis(remoteDiagnosis);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【RemoteDiagnosisController.editDiagnosis】 error:" + e.getMessage());
	    	ajax.setCode(50003);
			ajax.setMessage("修改远程诊断失败");
	    }
	    return ajax.toJSONString();
    }
	
	/**
	 * 诊断
	 * @param remoteDiagnosis
	 * @return
	 */
	@RequestMapping(value = "/diagnosis")
    public String diagnosis(RemoteDiagnosis remoteDiagnosis) {
		AjaxResponse ajax = new AjaxResponse(true);
	    try {
	    	remoteDiagnosisService.diagnosis(remoteDiagnosis);
	    	ajax.setCode(1);
	    } catch (Exception e) {
	    	logger.error("【RemoteDiagnosisController.diagnosis】 error:" + e.getMessage());
	        ajax.setCode(50004);
			ajax.setMessage("诊断失败");
	    }
	    return ajax.toJSONString();
    }
}
