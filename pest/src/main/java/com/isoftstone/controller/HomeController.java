package com.isoftstone.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.isoftstone.commons.Constants;
import com.isoftstone.entity.model.Device;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.pojo.DevicePojo;
import com.isoftstone.entity.pojo.RemoteMonitorePojo;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.entity.pojo.WarningDataPojo;
import com.isoftstone.mapper.slave.DeviceMapper;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.DeviceService;
import com.isoftstone.service.FieldInvestigateService;
import com.isoftstone.service.RemoteMonitoreService;
import com.isoftstone.service.UserService;
import com.isoftstone.service.WarningDataService;
import com.isoftstone.shiro.UsernamePasswordCaptchaToken;

/**
 * Created by liup on 2017/9/8.
 */
@Controller
public class HomeController {
	protected static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Resource
    private DeviceService deviceService;
    
    @Resource
    private WarningDataService warningDataService;

    @Resource
    private UserService userService;
    
    @Resource
    private RemoteMonitoreService remoteMonitoreService;
    
    @Resource
    private AreaService areaService;
    
    @Resource
    private FieldInvestigateService fieldInvestigateService;
    
    @Resource
    private DeviceMapper deviceMapper;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, User user, Model model, String captcha, HttpSession httpSession) {
    	
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "login";
        }

        if (StringUtils.isEmpty(captcha)) {
            request.setAttribute("msg", "验证码不能为空！");
            return "login";
        }
        String sessionCode = String.valueOf(httpSession.getAttribute(Constants.Commons.VERIFY_CODE));
        if (!sessionCode.equals(captcha)) {
            request.setAttribute("msg", "验证码错误！");
            return "login";
        }
        String ip = request.getRemoteAddr();
        
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordCaptchaToken token = new UsernamePasswordCaptchaToken(user.getUsername(),
                user.getPassword().toCharArray(), false, ip, captcha);
        try {
            subject.login(token);
            
            user = (User) subject.getPrincipal();
            // 当验证都通过后，把用户信息放在session里
            Session session = subject.getSession();
            UserPojo up = userService.getUser(user.getId());
            session.setAttribute(Constants.Commons.SESSION_USER, up);
            if(null==session.getAttribute("nCount")){
                if(null!=user.getManageArea()){
                    PageInfo<DevicePojo> dpList = deviceService.queryExpireDevices(user.getManageArea());
                    PageInfo<WarningDataPojo> wdList = warningDataService.getByArea(user.getManageArea());
                    session.setAttribute("nCount", null!=dpList?dpList.getTotal():0);
                    session.setAttribute("dpList", null!=dpList?dpList.getList():null);
                    session.setAttribute("mCount", null!=wdList?wdList.getTotal():0);
                    session.setAttribute("wdList", null!=wdList?wdList.getList():null);
                }else{
                    request.setAttribute("msg", "该用户未分配管理区域，请联系管理分配后进行登录！");
                    return "login";
                }
            }
            return "redirect:index";
        }
        catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            logger.error("【HomeController.login】 error:" + lae.getMessage());
            return "login";
        }
        catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", e.getMessage());
            logger.error("【HomeController.login】 error:" + e.getMessage());
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存  
            return "login";
        }
        else {
            return null;
        }
    }

    @RequestMapping("/usersPage")
    public String usersPage() {
        return "user/users";
    }

    @RequestMapping("/rolesPage")
    public String rolesPage() {
        return "role/roles";
    }

    @RequestMapping("/resourcesPage")
    public String resourcesPage() {
        return "resources/resources";
    }

    @RequestMapping("/areasPage")
    public String areasPage() {
        return "area/areaManage";
    }
    
    @RequestMapping("/contactPage")
    public String contactPage() {
        return "area/contactManage";
    }

    @RequestMapping("/diagnosisPage")
    public String diagnosisPage() {
        return "diagnosis/remoteDiagnosis";
    }

    @RequestMapping("/devPage")
    public String devicePage() {
        return "pest/deviceManage";
    }

    @RequestMapping("/warningDataPage")
    public String warningDataPage() {
        return "warning/warningData";
    }

    @RequestMapping("/warningSetPage")
    public String insectPage() {
        return "warning/warningSet";
    }

    @RequestMapping("/devPage/{devId:\\d+}")
    public String plantPicPage(HttpServletRequest request, @PathVariable(value = "devId") Long devId) {
        request.setAttribute("devId", devId);
        Device device = deviceMapper.findMacById(devId);
        request.setAttribute("devName", device.getDevName());
        return "pest/plantPic";
    }

    @RequestMapping("/paramPage")
    public String paramPage() {
        return "pest/setParameter";
    }

    @RequestMapping("/collectPage")
    public String collectPage() {
        return "pest/autoPest";
    }

    @RequestMapping("/fieldListPage")
    public String fieldPage() {
        return "field/fieldInvestigate";
    }

    @RequestMapping("/fieldListPage/toStepInvestigation/{fieldId:\\d+}")
    public String toStepInvestigation(HttpServletRequest request, @PathVariable(value = "fieldId") Long fieldId) {
        request.setAttribute("fieldId", fieldId);
        return "field/toStepInvestigation";
    }

    @RequestMapping("/fieldListPage/toStandardSurvey/{fieldId:\\d+}")
    public String toStandardSurvey(HttpServletRequest request, @PathVariable(value = "fieldId") Long fieldId) {
        request.setAttribute("fieldId", fieldId);
        return "field/toStandardSurvey";
    }

    @RequestMapping("/fieldListPage/toTrappedSurvey/{fieldId:\\d+}")
    public String toTrappedSurvey(HttpServletRequest request, @PathVariable(value = "fieldId") Long fieldId) {
        request.setAttribute("fieldId", fieldId);
        return "field/toTrappedSurvey";
    }

    @RequestMapping("/fieldListPage/toNurserySurvey/{fieldId:\\d+}")
    public String toNurserySurvey(HttpServletRequest request, @PathVariable(value = "fieldId") Long fieldId) {
        request.setAttribute("fieldId", fieldId);
        return "field/toNurserySurvey";
    }

    @RequestMapping("/fieldListPage/toFruitSurvey/{fieldId:\\d+}")
    public String toFruitSurvey(HttpServletRequest request, @PathVariable(value = "fieldId") Long fieldId) {
        request.setAttribute("fieldId", fieldId);
        return "field/toFruitSurvey";
    }
    
    @RequestMapping("/fieldDataPage")
    public String fieldDataPage() {
        return "field/fieldData";
    }
    
    @RequestMapping("/monitorPage")
    public String monitorPage() {
    	return "monitor/remoteMonitore";
    }
    
    @RequestMapping("/insectStatisticsPage")
    public String insectStatisticsPage() {
        return "statistics/insectStatistics";
    }
    
    @RequestMapping("/pestPolylinePage")
    public String pestPolylinePage() {
        return "statistics/pestPolyline";
    }
    
    @RequestMapping("/pestColumnarPage")
    public String pestColumnarPage() {
        return "statistics/pestColumnar";
    }

    @RequestMapping("/iconHelp")
    public String iconHelp() {
        return "iconHelp";
    }
    
    @RequestMapping("/403")
    public String forbidden() {
        return "403";
    }
    
    @RequestMapping("/profile")
    public String profile() {
        return "profile";
    }

    @RequestMapping("/monitorPage/video/{id:\\d+}")
    public String video(HttpServletRequest request, @PathVariable(value = "id") Long id){
        RemoteMonitorePojo pojo = remoteMonitoreService.findHdById(id);
        request.setAttribute("id", id);
        request.setAttribute("pojo", pojo);
        return "monitor/video";
    }
    
    @RequestMapping("/monitorPage/video2/{id:\\d+}")
    public String video2(HttpServletRequest request, @PathVariable(value = "id") Long id){
        RemoteMonitorePojo pojo = remoteMonitoreService.findHdById(id);
        request.setAttribute("id", id);
        request.setAttribute("pojo", pojo);
        return "monitor/video2";
    }
    
    @RequestMapping(value = {"/index", ""})
    public String index(HttpServletRequest request) {
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		if(null!=manageArea){
    		    areaList = areaService.queryChildrenIds(manageArea);
    		}
    	}
    	int userCount = userService.getUserSum(roleId, areaList);
    	int devCount = deviceService.getDevNum(roleId, areaList);
    	int expireDevCount = deviceService.getExpireDevNum(roleId, areaList);
    	int fieldSurveyCount = fieldInvestigateService.getFieldSurvey(roleId, areaList);
    	request.setAttribute("userCount", userCount);
    	request.setAttribute("devCount", devCount);
    	request.setAttribute("expireDevCount", expireDevCount);
    	request.setAttribute("fieldSurveyCount", fieldSurveyCount);
    	return "index";
    }
}
