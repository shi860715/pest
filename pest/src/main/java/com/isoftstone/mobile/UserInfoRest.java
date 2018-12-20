package com.isoftstone.mobile;

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

import com.isoftstone.entity.model.User;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.RawFilePojo;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.service.UserService;
import com.isoftstone.service.WarningDataService;
import com.isoftstone.util.FileUtil;
import com.isoftstone.util.PasswordHelper;

@Controller
@RequestMapping("/m/user/info")
public class UserInfoRest {

	protected static final Logger logger = LoggerFactory.getLogger(UserInfoRest.class);
	
    @Resource
    private UserService userService;
    
    @Resource
    private WarningDataService warningDataService;
    
    //获取配置文件中图片的路径
    @Value("${upload.uploadPath}")
    private String uploadPath;
    
    /**
     * 手机端用户查询个人信息
     * post /m/user/info/get
     * @author jnjua
     * @param user
     * @return
     * @since 2017年12月18日
     * @see 
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public String get(@RequestBody User user, HttpServletRequest request) {
        AjaxResponse ajax = new AjaxResponse(true);

        UserPojo up = userService.getUser(user.getId());
	    ajax.setCode(1);
	    ajax.setMessage("用户查找个人信息成功！");
	    ajax.setData(up);
        return ajax.toJSONString();
    }
    
    /**
     * 手机端用户修改个人信息
     * post /m/user/info/updateUser
     * @author jnjua
     * @param user
     * @return
     * @since 2017年12月18日
     * @see 
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(@RequestBody User user) {
        AjaxResponse ajax = new AjaxResponse(true);
        int u = userService.selectByUsername(user);
        int t = userService.selectByTelphone(user);
    	    if(u != 0 ){
    	    	logger.error("【UserInfoRest.updateUser】 error.");
                ajax.toError();
                ajax.setMessage("添加用户失败，用户名已存在【90026】");
    	    }else if(t != 0){
    	    	logger.error("【UserInfoRest.updateUser】 error.");
                ajax.toError();
                ajax.setMessage("添加用户失败，手机号码已存在【90027】");
    	    }
    	    else{
    	    	try {
    		        userService.updateUserInfo(user);
    		        ajax.setCode(1);
    		        ajax.setMessage("用户修改个人信息成功！");    
    		    	} catch (Exception e) {
    		    		logger.error("【UserInfoRest.updateUser】 error:" + e.getMessage());
    		            ajax.toError();
    		            ajax.setMessage("添加用户失败【90028】");
    		             
    		    	}
    	    }  
    	    return ajax.toJSONString();
    }
    
    /**
     * 手机端用户修改密码信息
     * post /m/user/info/updatePassword
     * @author jnjua
     * @param user
     * @return
     * @since 2017年12月18日
     * @see 
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(@RequestBody UserPojo user) {
        AjaxResponse ajax = new AjaxResponse(true);
        
        try {
        	UserPojo userPojo = userService.getUser(user.getId());
        	PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptOldPassword(user);
	        	if(!user.getOldPassword().equals(userPojo.getPassword())){
	        		logger.error("【UserInfoRest.updatePassword】 error.");
	        		ajax.toError();
	    	        ajax.setMessage("用户原始密码错误【90029】");
	        	}else{
	        		User newUser = new User();
	        		newUser.setId(user.getId());
	        		newUser.setUsername(user.getUsername());
	        		newUser.setPassword(user.getPassword());
	        		userService.updatePwd(newUser);
	    	        ajax.setCode(1);
	    	        ajax.setMessage("用户修改密码信息成功！");    
	        	}
	    	} catch (Exception e) {
	    		logger.error("【UserInfoRest.updatePassword】 error.");
        		ajax.toError();
    	        ajax.setMessage("用户修改密码信息失败【90030】");
	    	}
        return ajax.toJSONString();
    }
    
    /**
     * 上传头像
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, "profile");
            
            if (CollectionUtils.isNotEmpty(rfList)) {
                ajax.setData(rfList.get(0));
            }
        }
        catch (Exception e) {
        	logger.error("【UserInfoRest.upload】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
    

}
