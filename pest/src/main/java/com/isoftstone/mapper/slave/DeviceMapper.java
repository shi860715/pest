package com.isoftstone.mapper.slave;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.DevParameter;
import com.isoftstone.entity.model.Device;
import com.isoftstone.entity.model.DeviceExtends;
import com.isoftstone.entity.pojo.DevParameterPojo;
import com.isoftstone.entity.pojo.DevicePojo;
import com.isoftstone.util.MyMapper;

public interface DeviceMapper extends MyMapper<Device> {

	
	/**
     * 
     * 分页查询设备信息
     *
     * @author jnjua
     * @return 设备分页列表
     * @since 207年11月13日
     */
    public List<Device> findPageOne(Device device);
    
    /**
     * 
     * 分页查询设备信息
     *
     * @author jnjua
     * @return 设备分页列表
     * @since 207年11月13日
     */
    public List<Device> findPage(@Param(value="deviceExtendsList")List<DeviceExtends> deviceExtendsList);

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
     * 删除设备信息
     *
     * @author jnjua
     * @param id
     *            用户ID
     * @return 持久化操作码
     * @since 207年11月13日
     */
    public int deleteDevice(@Param(value="idList") List<Long> idList);
    
    /**
     * 
     * 根据ID查询设备MAC信息
     *
     * @author jnjua
     * @return 设备列表
     * @since 207年11月23日
     */
    public Device findMacById(Long id);
    
    /**
	 * 根据设备id与编码查询设备参数设置
	 * @param param
	 * @return
	 */
    public List<DevParameter> queryParam(DevParameter param);
    
    /**
     * 删除设备参数设置
     * @param pojo
     * @return
     */
    public int delParam(DevParameterPojo pojo);
    
    /**
     * 增加设备参数设置
     * @param pojo
     * @return
     */
    public int addParam(@Param(value="paramList")List<DevParameter> paramList);
    
    /**
     * 根据ID获取设备信息
     * @author llmaoa
     * @param deList
     * @return
     * @since 2017年11月28日
     * @see 
     */
    public List<Device> queryDeviceByList(@Param(value="deList") List<DeviceExtends> deList);

    /**
     * 根据设备编码与名称查询设备基本信息
     * @param map
     * @return
     */
    public Device selectByCodeAndName(Map<String,Object> map);
}