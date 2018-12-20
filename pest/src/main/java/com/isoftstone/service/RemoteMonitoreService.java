package com.isoftstone.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.RemoteMonitore;
import com.isoftstone.entity.pojo.RemoteMonitorePojo;

public interface RemoteMonitoreService extends IService<RemoteMonitore>{

	PageInfo<RemoteMonitorePojo> findPage(RemoteMonitore remoteMonitore,Integer roleId, List<Long> areaList, int start, int length);
	
	int delRemoteMonitore(List<Long> list);

	int addRemoteMonitore(RemoteMonitore remoteMonitore);
	
	int editRemoteMonitore(RemoteMonitore remoteMonitore);
	
	RemoteMonitorePojo findHdById(Long id);
}
