package com.isoftstone.mobile;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.WarningData;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.WarningDataPojo;
import com.isoftstone.service.UserRoleService;
import com.isoftstone.service.WarningDataService;

@Controller
@RequestMapping("/m/warning")
public class WarningRest {

	protected static final Logger logger = LoggerFactory.getLogger(WarningRest.class);
	
    @Resource
    private WarningDataService warningDataService;
    
    @Resource
    private UserRoleService userRoleService;
    
    /**
     * 手机端获取防治预警分页信息列表
     * post /m/warning/queryWarningData
     * @author jnjua
     * @param warningData
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/queryWarningData", method = RequestMethod.POST)
    @ResponseBody
    public String queryWarningData(@RequestBody WarningData warningData) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            int start =  (warningData.getPageNum()-1) * warningData.getPageSize();
            //获取登录用户
            PageInfo<WarningDataPojo> pageInfo = warningDataService.findPage(warningData, start, warningData.getPageSize());
            ajax.setMessage("防治预警分页查询列表成功！");
            ajax.setData(pageInfo);
            return ajax.toJSONString();
        }
        catch (Exception e) {
            logger.error("【WarningRest.queryWarningData】 error." );
       	 	ajax.toError();
       	 	ajax.setMessage("发送短信失败【90033】");
       	 	return ajax.toJSONString();
        }
    }
    
    /**
     * 手机端发送邮件通知相关联系人
     * post /m/warning/sendEmail
     * @author jnjua
     * @param warningData
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    @ResponseBody
    public String sendEmail(@RequestBody WarningData warningData) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	 boolean flag = warningDataService.sendEmail(warningData.getId());
             if(flag){
            	 ajax.setCode(1);
            	 ajax.setMessage("发送邮件成功！");
            	 
             }else{
            	 logger.error("【WarningRest.sendEmail】 error." );
            	 ajax.toError();
            	 ajax.setMessage("发送邮件失败【90031】");
             }
        }
        catch (Exception e) {
        	logger.error("【WarningRest.sendEmail】 error." );
       	 	ajax.toError();
       	 	ajax.setMessage("发送邮件失败【90031】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端发送短信通知相关联系人
     * post /m/warning/sendSms
     * @author jnjua
     * @param warningData
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST)
    @ResponseBody
    public String sendSms(@RequestBody WarningData warningData) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	 boolean flag = warningDataService.sendSms(warningData.getId());
             if(flag){
            	 ajax.setCode(1);
            	 ajax.setMessage("发送短信成功！");
            	 
             }else{
            	 logger.error("【WarningRest.sendSms】 error." );
            	 ajax.toError();
            	 ajax.setMessage("发送短信失败【90032】");
             }
        }
        catch (Exception e) {
        	logger.error("【WarningRest.sendSms】 error." );
       	 	ajax.toError();
       	 	ajax.setMessage("发送短信失败【90032】");
        }
        return ajax.toJSONString();
    }
   
}
