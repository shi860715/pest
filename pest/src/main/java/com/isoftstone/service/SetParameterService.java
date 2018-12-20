package com.isoftstone.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.pojo.SetParameterPojo;

public interface SetParameterService extends IService<SetParameterPojo>{

	PageInfo<SetParameterPojo> findPage(String devName, Integer roleId, List<Long> areaList, int start, int length);

}
