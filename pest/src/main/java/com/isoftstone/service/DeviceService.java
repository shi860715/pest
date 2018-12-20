package com.isoftstone.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.DevParameter;
import com.isoftstone.entity.model.Device;
import com.isoftstone.entity.model.DeviceExtends;
import com.isoftstone.entity.pojo.DevParameterPojo;
import com.isoftstone.entity.pojo.DevicePojo;

/**
 * Created by jnjua on 2017/11/13.
 */
public interface DeviceService extends IService<Device>{

    PageInfo<DevicePojo> findPageOne(Device device, int start, int length);
    
    PageInfo<DevicePojo> findPage(Device device,Integer roleId,List<Long> areaList, int start, int length);
    
    int addDevice(DevicePojo devicePojo);
    
    int updateDevice(DevicePojo devicePojo);
    
    int deleteDevice(List<Long> idList);
    
    List<DevParameter> queryParam(DevParameter param);
    
    int updateParam(DevParameterPojo pojo);
    
    PageInfo<DevicePojo> queryExpireDevices(Long manageArea);
    
    void taskExpireDevice();
    
    Device selectByCodeAndName(String deviceCode, String deviceName);
    
    int getDevNum(Integer roleId,List<Long> areaList);
    
    int getExpireDevNum(Integer roleId,List<Long> areaList);
    
    List<DeviceExtends> getExpireDev(Integer roleId,List<Long> areaList);
}