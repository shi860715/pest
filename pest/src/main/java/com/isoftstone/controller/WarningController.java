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
import com.isoftstone.entity.model.Insect;
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.model.Warning;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.service.InsectService;
import com.isoftstone.service.WarningService;


/**
 * Created by jnjua on 2017/11/15.
 */
@RestController
@RequestMapping("/warningPage")
public class WarningController {
	protected static final Logger logger = LoggerFactory.getLogger(WarningController.class);
    @Resource
    private WarningService warningService;
    
    @Resource
    private InsectService insectService;

    /**
     * 方法描述：预警信息分页查询
     * <p>
     * POST / warning/findPage
     *
     * @author jnjua
     * @param warning
     *            预警信息模型
     * @param page
     *            分页模型
     * @return 预警信息列表分袂
     * @since 2017年11月15日
     * @see
     */
    @RequestMapping(value = "/findPage")
    public Page findPage(Warning warning, Page page) {

    	int start =  (page.getPage()-1) * page.getLimit();
    	PageInfo<Warning> pageUsers = warningService.findPage(warning, start, page.getLimit());
    	page.setData(pageUsers.getList());
    	page.setCount(pageUsers.getTotal());
        return page;

    }
    
    /**
     * 删除预警信息
     * <p>
     * POST /warning/delWarning
     *
     * @author jnjua
     * @param warning
     *            预警模型
     * @param userSession
     *            会话模型
     * @return 持久化操作码
     * @since 2017年11月15日
     */
    @RequestMapping(value = "/delWarning")
    public String delWarning(@RequestParam(value = "ids") String ids) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	try{
    		String[] id = ids.split(",");
    		List<Long> uList = new ArrayList<Long>();
    		for(String i : id){
    			uList.add(Long.valueOf(i));
            }
    		warningService.delWarning(uList);
    		ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【WarningController.delWarning】 error:" + e.getMessage());
        	ajax.setCode(70004);
			ajax.setMessage("删除预警阀值失败");
	    }
	    return ajax.toJSONString();
    }
    
    /**
     * 方法描述：新增预警信息
     * <p>
     * POST /warning/addWarning
     *
     * @author jnjua
     * @param warning
     *            预警信息模型
     * @param userSession.
     *            用户会话数据
     * @return 持久化操作码
     * @since 2017年11月15日
     */
    @RequestMapping(value = "/addWarning")
    public String addWarning(Warning warning) {
    	AjaxResponse ajax = new AjaxResponse(true);
	    try {
	    	if(warning.getInsectId() == -1){
	    		Insect insect = new Insect();
	    		User user = (User) SecurityUtils.getSubject().getPrincipal();
		    	insect.setCreater(user.getId());
		    	insect.setName(warning.getInsectName());
		    	insectService.addInsect(insect);
		    	warning.setInsectId(insect.getId());
		    	warning.setCreater(user.getId());
		    	warningService.addWarning(warning);	
	    	}else{
	    		User user = (User) SecurityUtils.getSubject().getPrincipal();
		    	warning.setCreater(user.getId());
		    	warningService.addWarning(warning);
		    	ajax.setCode(1);
	    	}
	    	} catch (Exception e) {
	    		logger.error("【WarningController.addWarning】 error:" + e.getMessage());
	    		ajax.setCode(70005);
				ajax.setMessage("添加预警阀值失败");
		    }
		    return ajax.toJSONString();
	}
    
    /**
     * 方法描述：修改预警信息
     * <p>
     * POST /warning/updateWarning
     *
     * @author jnjua
     * @param warning
     * @param userSession
     * @return
     * @since 2017年11月15日
     * @see
     */
    @RequestMapping(value = "/updateWarning")
    public String updateWarning(Warning warning) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	try {
	    		if(warning.getInsectId() == -1){
		    		Insect insect = new Insect();
		    		User user = (User) SecurityUtils.getSubject().getPrincipal();
			    	insect.setCreater(user.getId());
			    	insect.setName(warning.getInsectName());
			    	insectService.addInsect(insect);
			    	warning.setInsectId(insect.getId());
			    	warning.setCreater(user.getId());
			    	warningService.updateWarning(warning);	
		    	}else{
		    		User user = (User) SecurityUtils.getSubject().getPrincipal();
		    		warning.setCreater(user.getId());
		    		warningService.updateWarning(warning);
		    		ajax.setCode(1);
		    	}
	    	} catch (Exception e) {
	    		logger.error("【WarningController.updateWarning】 error:" + e.getMessage());
	    		ajax.setCode(70006);
				ajax.setMessage("修改预警阀值失败");
		    }
		    return ajax.toJSONString();
	    
    }

}
