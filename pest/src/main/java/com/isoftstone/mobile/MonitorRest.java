package com.isoftstone.mobile;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.RemoteMonitore;
import com.isoftstone.entity.model.UserRole;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.RemoteMonitorePojo;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.RemoteMonitoreService;
import com.isoftstone.service.UserRoleService;

@Controller
@RequestMapping("/m/monitor")
public class MonitorRest {
	
	protected static final Logger logger = LoggerFactory.getLogger(MonitorRest.class);

    @Resource
    private AreaService areaService;
    
    @Resource
    private UserRoleService userRoleService;
    
    @Resource
    private RemoteMonitoreService remoteMonitoreService;
    
    /**
     * 手机端获取远程监控设备分页信息列表
     * post /m/monitor/queryRemoteDiagnosis
     * @author jnjua
     * @param remoteMonitore
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/queryRemoteMonitore", method = RequestMethod.POST)
    @ResponseBody
    public String queryRemoteMonitore(@RequestBody RemoteMonitore remoteMonitore) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            int start =  (remoteMonitore.getPageNum()-1) * remoteMonitore.getPageSize();
            //获取登录用户
            UserRole userRole = userRoleService.getUserRole(remoteMonitore.getUserId());
            List<Long> areaList = new ArrayList<Long>();
            Integer roleId = Integer.valueOf(userRole.getRoleid());
            if(roleId != 1){
        		Long manageArea = remoteMonitore.getAreaId();
        		areaList = areaService.queryChildrenIds(manageArea);
        	}
            PageInfo<RemoteMonitorePojo> pageInfo = remoteMonitoreService.findPage(remoteMonitore, roleId, areaList, start, remoteMonitore.getPageSize());
            ajax.setMessage("远程监控设备分页查询列表成功！");
            ajax.setData(pageInfo);
        }
        catch (Exception e) {
        	logger.error("【MonitorRest.queryRemoteMonitore】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage(e.getMessage());
        }
        return ajax.toJSONString();
    }
   
}
