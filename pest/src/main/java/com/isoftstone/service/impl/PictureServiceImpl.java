package com.isoftstone.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.isoftstone.mapper.slave.PictureCenterMapper;
import com.isoftstone.mapper.slave.DeviceMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.commons.Common;
import com.isoftstone.entity.model.Device;
import com.isoftstone.entity.model.Picture;
import com.isoftstone.entity.pojo.PicturePojo;
import com.isoftstone.service.PictureService;

/**
 * Created by jnjua on 2017/11/23.
 */
@Service("pictureService")
public class PictureServiceImpl extends BaseService<Picture> implements PictureService{
    
	private Log logger = LogFactory.getLog(PictureServiceImpl.class);
	
	@Resource
    private PictureCenterMapper pictureCenterMapper;
    
    @Resource
    private DeviceMapper deviceMapper;
    
    
    /**
     * 方法描述：根据设备ID查询所有图片信息
     *
     * @author jnjua
     * @param 
     * @return 图片信息列表
     * @since 2017年11月23日
     */
    @Override
    public PageInfo<List<PicturePojo>> findPicByMac(Picture picture, int start, int length) {
        
    	 int page = start/length+1;
    	
    	Device device = deviceMapper.findMacById(picture.getDevId());
    	picture.setDevMac(device.getDevMac());
        //分页查询
        PageHelper.startPage(page, length);
        List<Picture> pictureList = pictureCenterMapper.findPicByMac(picture);
        PageInfo<Picture> picPage = new PageInfo<Picture>(pictureList);
        List<PicturePojo> jpgList = new ArrayList<PicturePojo>();
        
        List<List<PicturePojo>> picturePojoList = new ArrayList<List<PicturePojo>>();
        
        String[] jpg = {"up-1.jpg","up-2.jpg","up-3.jpg","dn-1.jpg","dn-2.jpg","dn-3.jpg"};

        try {
        
	        for(Picture p : pictureList){
	        	String upName = device.getDevCode() + "-" + p.getDevMac().replaceAll("-", "") + "-" + 
	        			Common.getTimeLongToString(p.getCaptureTime(),"yyyy-MM-dd HH:mm").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") + "-";
	        	
	        	for(String j : jpg){
	        		String imgPath = p.getPicPath() + "/" + upName + j;
	        		String imgName = upName + j;
	        		PicturePojo picturePojo = new PicturePojo();
	        		picturePojo.setImgPath(imgPath);
	            	picturePojo.setImgName(imgName);
	            	picturePojo.setDevId(device.getId());
	            	picturePojo.setDevMac(p.getDevMac());
	            	picturePojo.setPicPath(p.getPicPath());
	            	picturePojo.setCaptureTime(p.getCaptureTime()*1000);
	            	picturePojo.setDeviceCode(device.getDevCode());
	            	picturePojo.setDeviceName(device.getDevName());
	            	picturePojo.setId(p.getPathId());
	            	jpgList.add(picturePojo);
	        	}
	        	
	        	picturePojoList.add(jpgList);
	        	jpgList = new ArrayList<PicturePojo>();
	        	      	
	        }
	        PageInfo<List<PicturePojo>> picList = new PageInfo<List<PicturePojo>>(picturePojoList);
	        picList.setTotal(picPage.getTotal());
	        return picList;
	        
        } catch (Exception ex) {
        	logger.error("TeacherService.findPicByMac error.", ex);
            return null;
        }
    }
    
    /**
     * 方法描述：删除图片信息
     *
     * @author jnjua
     * @param pathId
     *            图片ID
     * @return 持久化操作码
     * @since 2017年11月23日
     */
    public int delPic(List<Long> idList) {

    	int result = pictureCenterMapper.delPic(idList);

        return result;
    }

}
