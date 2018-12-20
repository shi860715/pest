package com.isoftstone.mobile;

import java.util.LinkedList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.model.WarningData;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.UserCode;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.entity.pojo.WarningDataPojo;
import com.isoftstone.service.UserService;
import com.isoftstone.service.WarningDataService;
import com.isoftstone.shiro.UsernamePasswordCaptchaToken;
import com.isoftstone.util.PasswordHelper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
@RequestMapping("/m/user")
public class UserRest {

	protected static final Logger logger = LoggerFactory.getLogger(UserRest.class);
	
    @Resource
    private UserService userService;
    
    @Resource
    private WarningDataService warningDataService;
    
    /**
     * jedis池
     */
    @Resource
    private JedisPool  jedisPool;
    
    /**
     * 手机端用户登录
     * post /m/user/login
     * @author llmaoa
     * @param user
     * @return
     * @since 2017年12月7日
     * @see 
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody User user, HttpServletRequest request) {
        AjaxResponse ajax = new AjaxResponse(true);
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            ajax.toError();
            ajax.setMessage("用户名或密码不能为空！");
        }else{
            Subject subject = SecurityUtils.getSubject();

            String ip = request.getRemoteAddr();
            UsernamePasswordCaptchaToken token = new UsernamePasswordCaptchaToken(user.getUsername(),
                    user.getPassword().toCharArray(), false, ip, "手机登录");
            try {
                subject.login(token);
            }
            catch (LockedAccountException lae) {
                token.clear();
                ajax.toError();
                ajax.setMessage("用户已经被锁定不能登录，请与管理员联系！");
                logger.error("【UserRest.login】 error:" + lae.getMessage());
                return ajax.toJSONString();
            }
            catch (AuthenticationException e) {
                token.clear();
                ajax.toError();
                ajax.setMessage("用户名或密码不正确！");
                logger.error("【UserRest.login】 error:" + e.getMessage());
                return ajax.toJSONString();
            }
            
            UserPojo up = userService.login(user.getUsername());
            if(null!=up){
                ajax.setMessage("用户登录成功！");
                ajax.setData(up);
            }else{
                ajax.toError();
                ajax.setMessage("用户名或密码不正确！");
            }
        }
        return ajax.toJSONString();
    }
    
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public String findPage(@RequestBody WarningData warningData) {
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            int start =  (warningData.getPageNum()-1) * warningData.getPageSize();
            //获取登录用户
            PageInfo<WarningDataPojo> pageUsers = warningDataService.findPage(warningData, start, warningData.getPageSize());
            ajax.setMessage("分页查询咨询列表成功！");
            ajax.setData(pageUsers);
            return ajax.toJSONString();
        }
        catch (Exception e) {
        	logger.error("【UserRest.findPage】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage(e.getMessage());
            return ajax.toJSONString();
        }
    }

    /**
     * 用户根据手机号和短信码登录
     * @author llmaoa
     * @param user
     * @return
     * @since 2017年12月29日
     * @see 
     */
    @RequestMapping(value = "/loginByPhone", method = RequestMethod.POST)
    @ResponseBody
    public String loginByPhone(@RequestBody UserCode uc){
        AjaxResponse ajax = new AjaxResponse(true);
        if(StringUtils.isEmpty(uc.getTelphone())){
        	logger.error("【UserRest.loginByPhone】 error.");
            ajax.toError();
            ajax.setMessage("登录手机号不能为空");
            return ajax.toJSONString();
        }else if(StringUtils.isEmpty(uc.getCode())){
        	logger.error("【UserRest.loginByPhone】 error.");
            ajax.toError();
            ajax.setMessage("短信验证码不能为空");
            return ajax.toJSONString();
        }
        Jedis redis = jedisPool.getResource();
        String rCode = redis.get(uc.getTelphone());
        if(StringUtils.isEmpty(rCode)){
        	logger.error("【UserRest.loginByPhone】 error.");
            ajax.toError();
            ajax.setMessage("您输入的验证码有误，请重新获取");
        }else{
            if(uc.getCode().equals(rCode)){
                UserPojo up = userService.loginByPhone(uc.getTelphone());
                if(up == null){
                	logger.error("【UserRest.loginByPhone】 error.");
                	ajax.toError();
                	ajax.setMessage("您的手机尚未注册，请先注册");
                }else{
                	ajax.setData(up);
                    ajax.setCode(1);
                }
            }else{
            	logger.error("【UserRest.loginByPhone】 error.");
                ajax.toError();
                ajax.setMessage("您输入的验证码有误，请重新获取");
            }
        }
        return ajax.toJSONString();
    }
    
    /**
     * 用户根据手机号和短信码重置密码
     * @author llmaoa
     * @param user
     * @return
     * @since 2017年12月29日
     * @see 
     */
    @RequestMapping(value = "/updatePwdByPhone", method = RequestMethod.POST)
    @ResponseBody
    public String updatePwdByPhone(User user, String code){
        AjaxResponse ajax = new AjaxResponse(true);
        if(StringUtils.isEmpty(user.getTelphone())){
            ajax.toError();
            ajax.setMessage("登录手机号不能为空");
            return ajax.toJSONString();
        }else if(StringUtils.isEmpty(code)){
            ajax.toError();
            ajax.setMessage("短信验证码不能为空");
            return ajax.toJSONString();
        }
        Jedis redis = jedisPool.getResource();
        String rCode = redis.get("p_"+user.getTelphone());
        if(StringUtils.isEmpty(rCode)){
            ajax.toError();
            ajax.setMessage("您输入的验证码有误，请重新获取");
            return ajax.toJSONString();
        }else{
            if(code.equals(rCode)){
            	UserPojo u = userService.getUser(user.getId());
            	user.setUsername(u.getUsername());
            	PasswordHelper passwordHelper = new PasswordHelper();
		        passwordHelper.encryptPassword(user);
                userService.updatePwd(user);
                ajax.setCode(1);
            }else{
                ajax.toError();
                ajax.setMessage("您输入的验证码有误，请重新获取");
            }
        }
        return ajax.toJSONString();
    }

    /**
     * 发送找回密码短信验证码
     * @author llmaoa
     * @param phone
     * @return
     * @since 2017年12月29日
     * @see 
     */
    @RequestMapping(value = "/sendPwdCode", method = RequestMethod.POST)
    @ResponseBody
    public String sendPwdCode(@RequestBody User user){
        AjaxResponse ajax = new AjaxResponse(true);
        UserPojo up = userService.loginByPhone(user.getTelphone());
        if(up == null){
        	ajax.toError();
        	ajax.setMessage("您的手机号码尚未注册，无法发送短信验证码！");
        }else{
        	String code = getRand();
            /**
             * 将验证码放入redis中 有效期5分钟
             */
            Jedis redis = jedisPool.getResource();
            redis.set("p_"+user.getTelphone(), code);
            redis.expire("p_"+user.getTelphone(), 300);
            redis.close();
            //TODO 
            ajax.setData(code);
            ajax.setMessage("短信验证码发送成功");
        }    
        return ajax.toJSONString();
    }
    
    
    /**
     * 发送登录短信验证码
     * @author llmaoa
     * @param phone
     * @return
     * @since 2017年12月29日
     * @see 
     */
    @RequestMapping(value = "/sendSmsCode", method = RequestMethod.POST)
    @ResponseBody
    public String sendSmsCode(@RequestBody User user){
        AjaxResponse ajax = new AjaxResponse(true);
        
        UserPojo up = userService.loginByPhone(user.getTelphone());
        if(up == null){
        	ajax.toError();
        	ajax.setMessage("您的手机号码尚未注册，无法发送短信验证码！");
        }else{
	        String code = getRand();
	        /**
	         * 将验证码放入redis中 有效期5分钟
	         */
	        Jedis redis = jedisPool.getResource();
	        redis.set(user.getTelphone(), code);
	        redis.expire(user.getTelphone(), 300);
	        redis.close();
	        //TODO 
	        ajax.setData(code);
	        ajax.setMessage("短信验证码发送成功");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 获取随机6位数
     * @author llmaoa
     * @return
     * @since 2017年12月29日
     * @see 
     */
    public String getRand(){
     // 取随机产生的认证码(6位数字)
        String sRand = "";
        for (int i = 0; i < 6; i++) {
            char rand = getChar();
            sRand += rand;
        }
        return sRand;
    }
    /**
     * 方法描述：获取字符
     * 
     * @param
     * @return date:2015-5-11 add by:llmaoa
     */
    private char getChar() {
        char ch = '0';
        LinkedList<String> ls = new LinkedList<String>();
        for (int i = 0; i < 10; i++) {// 0-9
            ls.add(String.valueOf(48 + i));
        }
        int index = (int) (Math.random() * ls.size());
        if (index > (ls.size() - 1)) {
            index = ls.size() - 1;
        }
        ch = (char) Integer.parseInt(String.valueOf(ls.get(index)));
        return ch;
    }
}
