package com.isoftstone.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.github.pagehelper.PageInfo;
import com.isoftstone.commons.Constants;
import com.isoftstone.entity.model.Resources;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.pojo.DevicePojo;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.entity.pojo.WarningDataPojo;
import com.isoftstone.service.DeviceService;
import com.isoftstone.service.ResourcesService;
import com.isoftstone.service.UserService;
import com.isoftstone.service.WarningDataService;

/**
 * Created by yangqj on 2017/4/21.
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private ResourcesService resourcesService;

    @Resource
    private DeviceService deviceService;
    
    @Resource
    private WarningDataService warningDataService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user= (User) SecurityUtils.getSubject().getPrincipal();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userid",user.getId());
        List<Resources> resourcesList = resourcesService.loadUserResources(map);
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for(Resources resources: resourcesList){
            info.addStringPermission(resources.getResurl());
        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken)authcToken;
        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        User user = userService.selectByUsername(username);
        if(user==null){ 
            throw new UnknownAccountException();
        }
        // 增加判断验证码逻辑
        String captcha = token.getCaptcha();
        if(!captcha.equals("手机登录")){
            String exitCode = (String) session.getAttribute(Constants.Commons.VERIFY_CODE);
            if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
                throw new CaptchaException("验证码错误");
            }
        }
        if (0==user.getEnable()) {
            throw new LockedAccountException(); // 帐号锁定
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户
                user.getPassword(), //密码
                ByteSource.Util.bytes(username),
                getName()  //realm name
        );
        if(null==session.getAttribute("nCount")){
            if(null!=user.getManageArea()){
                PageInfo<DevicePojo> dpList = deviceService.queryExpireDevices(user.getManageArea());
                PageInfo<WarningDataPojo> wdList = warningDataService.getByArea(user.getManageArea());
                session.setAttribute("nCount", null!=dpList?dpList.getTotal():0);
                session.setAttribute("dpList", null!=dpList?dpList.getList():null);
                session.setAttribute("mCount", null!=wdList?wdList.getTotal():0);
                session.setAttribute("wdList", null!=wdList?wdList.getList():null);
            }else{
                throw new CaptchaException("该用户未分配管理区域，请联系管理分配后进行登录！");
            }
        }
        UserPojo up = userService.getUser(user.getId());
        session.setAttribute(Constants.Commons.SESSION_USER, up);
        return authenticationInfo;
    }

    /**
     * 指定principalCollection 清除
     */
  /*  public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {

        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
*/
}
