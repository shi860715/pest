package com.isoftstone.mapper.main;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.Device;
import com.isoftstone.entity.model.DeviceExtends;
import com.isoftstone.entity.pojo.DevicePojo;
import com.isoftstone.util.MyMapper;

public interface DeviceExtendsMapper extends MyMapper<DeviceExtends> {

	
	/**
     * 
     * 分页查询设备信息
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 设备分页列表
     * @since 207年11月13日
     */
    public List<DeviceExtends> findPageOne(@Param(value="deviceList")List<Device> deviceList);
    
    /**
     * 
     * 分页查询设备信息
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 设备分页列表
     * @since 207年11月13日
     */
    public List<DeviceExtends> findPage(Map<String,Object> map);
    
    /**
     * 添加设备
     *
     * @author jnjua
     * @param device
     *            设备模型
     * @return 持久化操作码
     * @since 207年11月13日
     */
    public int addDevice(DevicePojo devicePojo);
    
    /**
     * 删除设备信息
     *
     * @author jnjua
     * @param id
     *            用户ID
     * @return 持久化操作码
     * @since 207年11月13日
     */
    public int deleteDeviceExtends(@Param(value="idList") List<Long> idList);
    
    /**
     * 更新设备信息
     *
     * @author jnjua
     * @param device
     *            用户模型
     * @return 持久化操作码
     * @since 207年11月13日
     */
    public int updateDevice(DevicePojo devicePojo);
    
    /**
     * 获取到期的设备列表
     * @author llmaoa
     * @return
     * @since 2017年11月28日
     * @see 
     */
    public List<DeviceExtends> queryExpireDeviceByArea(@Param(value="idList") List<Long> idList);
    
    /**
     * 获取到期的设备列表
     * @author llmaoa
     * @return
     * @since 2017年11月28日
     * @see 
     */
    public List<DeviceExtends> queryExpireDevice();
    
    /**
     * 查询设备总数
     * @param user
     * @return
     */
    public int getDevNum(Map<String,Object> map);
    
    /**
     * 查询到期设备总数
     * @param user
     * @return
     */
    public int getExpireDevNum(Map<String,Object> map);
    
    /**
     * 查询到期设备详情
     * @param user
     * @return
     */
    public List<DeviceExtends> getExpireDev(Map<String,Object> map);

}