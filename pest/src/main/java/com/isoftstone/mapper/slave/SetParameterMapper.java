package com.isoftstone.mapper.slave;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.DeviceExtends;
import com.isoftstone.entity.pojo.SetParameterPojo;
import com.isoftstone.util.MyMapper;

/**
 * 参数设置mapper
 * @author lufei
 *
 */
public interface SetParameterMapper extends MyMapper<SetParameterPojo> {
	
	/**
	 * 分页查询参数设置列表
	 * @return
	 */
	public List<SetParameterPojo> findPage(@Param(value="devList") List<DeviceExtends> devList);
	

}
