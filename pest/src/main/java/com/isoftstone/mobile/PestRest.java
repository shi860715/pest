package com.isoftstone.mobile;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.Collect;
import com.isoftstone.entity.model.DevParameter;
import com.isoftstone.entity.model.Picture;
import com.isoftstone.entity.model.UserRole;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.CollectPojo;
import com.isoftstone.entity.pojo.DevParameterPojo;
import com.isoftstone.entity.pojo.DevicePojo;
import com.isoftstone.entity.pojo.PicturePojo;
import com.isoftstone.entity.pojo.SetParameterPojo;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.CollectService;
import com.isoftstone.service.DeviceService;
import com.isoftstone.service.PictureService;
import com.isoftstone.service.SetParameterService;
import com.isoftstone.service.UserRoleService;

import tk.mybatis.mapper.util.StringUtil;

@Controller
@RequestMapping("/m/pest")
public class PestRest {
    
	protected static final Logger logger = LoggerFactory.getLogger(PestRest.class);
	
    @Resource
    private CollectService collectService;
    
    @Resource
    private UserRoleService userRoleService;
    
    @Resource
    private AreaService areaService;
    
    @Resource
    private DeviceService deviceService;
    
    @Resource
    private PictureService pictureService;
    
    @Resource
    private SetParameterService setParameterService;
    /**
     * 手机端获取自动虫情数据列表
     * post /m/pest/collectPage
     * @author jnjua
     * @param collect
     * @return
     * @since 2017年12月18日
     * @see 
     */
    @RequestMapping(value = "/collectPage", method = RequestMethod.POST)
    @ResponseBody
    public String collectPage(@RequestBody CollectPojo collectPojo) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            int start =  (collectPojo.getPageNum()-1) * collectPojo.getPageSize();
            //获取登录用户
            UserRole userRole = userRoleService.getUserRole(collectPojo.getUserId());
            List<Long> areaList = new ArrayList<Long>();
            Integer roleId = Integer.valueOf(userRole.getRoleid());
            if(roleId != 1){
        		Long manageArea = collectPojo.getAreaId();
        		areaList = areaService.queryChildrenIds(manageArea);
        	}
            PageInfo<Collect> pageUsers = collectService.findPage(collectPojo, roleId, areaList, start, collectPojo.getPageSize());
            ajax.setMessage("自动虫情分页查询列表成功！");
            ajax.setData(pageUsers);
        }
        catch (Exception e) {
        	logger.error("【PestRest.collectPage】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage(e.getMessage());
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端获取设备参数列表
     * post /m/pest/queryParam
     * @author jnjua
     * @param queryParam
     * @return
     * @since 2017年12月18日
     * @see 
     */
    @RequestMapping(value = "/queryParam", method = RequestMethod.POST)
    @ResponseBody
    public String queryParam(@RequestBody DevParameter param) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	 int start =  (param.getPageNum()-1) * param.getPageSize();
             //获取登录用户
             UserRole userRole = userRoleService.getUserRole(param.getUserId());
             List<Long> areaList = new ArrayList<Long>();
             Integer roleId = Integer.valueOf(userRole.getRoleid());
             if(roleId != 1){
         		Long manageArea = param.getAreaId();
         		areaList = areaService.queryChildrenIds(manageArea);
         	}
            PageInfo<SetParameterPojo> pageInfo = setParameterService.findPage(param.getDevName(), roleId, areaList, start, param.getPageSize());
            List<SetParameterPojo> list = pageInfo.getList();
            for(int i = 0;i < list.size(); i++){
            	String sleepTm = list.get(i).getSleepTm();
            	String startTime = sleepTm.split(",")[0];
            	String endTime = sleepTm.split(",")[1];
            	list.get(i).setSleepTm((Long.parseLong(startTime)*1000)+","+(Long.parseLong(endTime)*1000));
            }
             
            pageInfo.setList(list);
            ajax.setMessage("设备参数查询列表成功！");
            ajax.setData(pageInfo);
        }
        catch (Exception e) {
        	logger.error("【FieldRest.queryParam】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage(e.getMessage());
        }
        return ajax.toJSONString();
    }

    /**
     * 手机端修改设备参数
     * post /m/pest/updateParam
     * @author jnjua
     * @param queryParam
     * @return
     * @since 2017年12月18日
     * @see 
     */
    @RequestMapping(value = "/updateParam", method = RequestMethod.POST)
    @ResponseBody
    public String updateParam(@RequestBody DevParameterPojo pojo) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	pojo.setUpdateTime(System.currentTimeMillis()/1000+"");
    		pojo.setWorkTime((Long.parseLong(pojo.getWorkTime())*3600)+"");
    		pojo.setBackTime((Long.parseLong(pojo.getBackTime())*3600)+"");
    		pojo.setRoastTime((Long.parseLong(pojo.getRoastTime())*60)+"");
    		pojo.setCleanTime((Long.parseLong(pojo.getCleanTime())*86400)+"");
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		pojo.setSleepStart(((sdf.parse(pojo.getSleepStart()).getTime())/1000)+"");
    		pojo.setSleepEnd(((sdf.parse(pojo.getSleepEnd()).getTime())/1000)+"");
    		
    		deviceService.updateParam(pojo);
    		ajax.setCode(1);
            ajax.setMessage("修改设备参数成功！");
            return ajax.toJSONString();
        }
        catch (Exception e) {
        	logger.error("【FieldRest.updateParam】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("修改设备参数失败【90024】");
            return ajax.toJSONString();
        }
    }
    
    /**
     * 手机端获取设备管理列表
     * post /m/pest/devicePage
     * @author jnjua
     * @param device
     * @return
     * @since 2017年12月18日
     * @see 
     */
    @RequestMapping(value = "/devicePage", method = RequestMethod.POST)
    @ResponseBody
    public String devicePage(@RequestBody DevicePojo devicePojo) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	int start =  (devicePojo.getPageNum()-1) * devicePojo.getPageSize();
            //获取登录用户
            UserRole userRole = userRoleService.getUserRole(devicePojo.getUserId());
            List<Long> areaList = new ArrayList<Long>();
            Integer roleId = Integer.valueOf(userRole.getRoleid());
            if(roleId != 1){
        		Long manageArea = devicePojo.getAreaId();
        		areaList = areaService.queryChildrenIds(manageArea);
        	}
            PageInfo<DevicePojo> pageDevices = deviceService.findPage(devicePojo, roleId, areaList, start, devicePojo.getPageSize());
            ajax.setMessage("获取设备管理列表成功！");
            ajax.setData(pageDevices);
            return ajax.toJSONString();
        }
        catch (Exception e) {
        	logger.error("【FieldRest.devicePage】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage(e.getMessage());
            return ajax.toJSONString();
        }
    }
    
    /**
     * 手机端获取图片采集列表
     * post /m/pest/findPicByMac
     * @author jnjua
     * @param device
     * @return
     * @since 2017年12月18日
     * @see 
     */
    @RequestMapping(value = "/findPicByMac", method = RequestMethod.POST)
    @ResponseBody
    public String findPicByMac(@RequestBody Picture picture) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	int start =  (picture.getPageNum()-1) * picture.getPageSize();
            
            PageInfo<List<PicturePojo>> pageDevices = pictureService.findPicByMac(picture, start, picture.getPageSize());
            
            for(int i = 0; i < pageDevices.getList().size(); i++){
            	for(int j = 0; j<pageDevices.getList().get(i).size(); j++){
            		String imgPath = pageDevices.getList().get(i).get(j).getImgPath();
            		pageDevices.getList().get(i).get(j).setImgPath("/m/pest/viewImage?imgPath=" + imgPath);
            	}
            }
            
            ajax.setMessage("获取图片采集列表成功！");
            ajax.setData(pageDevices);
            return ajax.toJSONString();
        }
        catch (Exception e) {
        	logger.error("【FieldRest.findPicByMac】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage(e.getMessage());
            return ajax.toJSONString();
        }
    }
    
    /**
     * 手机端添加虫情数据
     * post /m/pest/updateParam
     * @author jnjua
     * @param queryParam
     * @return
     * @since 2017年12月18日
     * @see 
     */
    @RequestMapping(value = "/addCollect", method = RequestMethod.POST)
    @ResponseBody
    public String addCollect(@RequestBody Collect collect) {
        
        AjaxResponse ajax = new AjaxResponse(true);
        try {
        	if(collectService.findOne(collect)!= 0){
	    		collectService.updateCollectById(collect);
	    	}else{
	    		collectService.addCollect(collect);
	    	}
    		ajax.setCode(1);
            ajax.setMessage("添加虫情数据成功！");
            return ajax.toJSONString();
        }
        catch (Exception e) {
        	logger.error("【FieldRest.addCollect】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("添加虫情数据失败【90025】");
        }
        return ajax.toJSONString();
    }
    
    @RequestMapping(value = "/viewImage")
	public void viewImage(String imgPath, HttpServletResponse response) {
		if (StringUtil.isEmpty(imgPath)){
			return;
		}
		
		File file = new File(imgPath);
		if (null == file || !file.exists() || !file.isFile()) {
			return;
		}
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType(getContentType(imgPath.substring(imgPath.lastIndexOf(".") + 1)));

		ServletOutputStream out = null;
		FileInputStream ips = null;
		try {
			out = response.getOutputStream();
			ips = new FileInputStream(file);
			int i = 0;
			byte[] buffer = new byte[1024];
			while ((i = ips.read(buffer)) != -1) {
				out.write(buffer, 0, i);
			}
			out.flush();
		} catch (Exception e) {
			logger.error("【FieldRest.viewImage】 error:" + e.getMessage());
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (Exception e) {
					logger.error("【FieldRest.viewImage】 error:" + e.getMessage());
				}
			}
			if (null != ips) {
				try {
					ips.close();
				} catch (Exception e) {
					logger.error("【FieldRest.viewImage】 error:" + e.getMessage());
				}
			}
		}
	}

	private String getContentType(String imgType) {
		String contentType = "image/jpeg";
		if ("gif".equalsIgnoreCase(imgType)) {
			contentType = "image/gif";
		} else if ("png".equalsIgnoreCase(imgType)) {
			contentType = "image/png";
		} else if ("bmp".equalsIgnoreCase(imgType)) {
			contentType = "image/bmp";
		}
		return contentType;
	}

}
