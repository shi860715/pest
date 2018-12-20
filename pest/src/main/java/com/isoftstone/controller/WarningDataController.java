package com.isoftstone.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.model.WarningData;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.WarningDataPojo;
import com.isoftstone.service.WarningDataService;

/**
 * 预警数据管理
 * @author llmaoa
 * @since 2017年11月30日
 * @see [Class/Method]
 *
 */
@RestController
@RequestMapping("/warningData")
public class WarningDataController {
	protected static final Logger logger = LoggerFactory.getLogger(WarningDataController.class);
    @Resource
    private WarningDataService warningDataService;

    /**
     * 分页获取预警数据
     * @author llmaoa
     * @param warningData
     * @param page
     * @return
     * @since 2017年11月30日
     * @see 
     */
    @RequestMapping
    public Page findPage(WarningData warningData, Page page) {
    	int start =  (page.getPage()-1) * page.getLimit();
    	//获取登录用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        warningData.setAreaId(user.getManageArea());
    	PageInfo<WarningDataPojo> pageUsers = warningDataService.findPage(warningData, start, page.getLimit());
    	page.setData(pageUsers.getList());
    	page.setCount(pageUsers.getTotal());
        return page;

    }
    
    /**
     * 根据ID集合删除预警数据
     * @author llmaoa
     * @param ids
     * @return
     * @since 2017年12月1日
     * @see 
     */
    @RequestMapping(value = "/delete")
    public String delWarning(@RequestParam(value = "ids") String ids) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	try{
    		warningDataService.delWarningDatas(ids);
    		ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【WarningDataController.delWarning】 error:" + e.getMessage());
        	ajax.setCode(70001);
			ajax.setMessage("删除预警数据失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 发送邮件通知相关联系人
     * @author llmaoa
     * @param id
     * @return
     * @since 2017年12月1日
     * @see 
     */
    @RequestMapping(value = "/sendEmail")
    public String sendEmail(@RequestParam(value="id")Long id){
    	AjaxResponse ajax = new AjaxResponse(true);
        try{
            boolean flag = warningDataService.sendEmail(id);
            if(flag){
            	ajax.setCode(1);
            }
            else{
            	ajax.setCode(70002);
    			ajax.setMessage("发送预警邮件失败");
            }
        }catch (Exception e){
        	logger.error("【WarningDataController.sendEmail】 error:" + e.getMessage());
        	ajax.setCode(70002);
			ajax.setMessage("发送预警邮件失败");
        }
		return ajax.toJSONString();
    }
    
    /**
     * 发送短信通知相关联系人
     * @author llmaoa
     * @param id
     * @return
     * @since 2017年12月1日
     * @see 
     */
    @RequestMapping(value = "/sendSms")
    public String sendSms(@RequestParam(value="id")Long id){
    	AjaxResponse ajax = new AjaxResponse(true);
        try{
            boolean flag = warningDataService.sendSms(id);
            if(flag){
            	ajax.setCode(1);
            }
            else{
            	ajax.setCode(70003);
    			ajax.setMessage("发送预警短信失败");
            }
        }catch (Exception e){
        	logger.error("【WarningDataController.sendSms】 error:" + e.getMessage());
        	ajax.setCode(70003);
			ajax.setMessage("发送预警短信失败");
        }
        return ajax.toJSONString();
    }
}
