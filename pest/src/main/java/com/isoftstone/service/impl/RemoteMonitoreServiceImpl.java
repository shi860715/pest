package com.isoftstone.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.RemoteMonitore;
import com.isoftstone.entity.pojo.RemoteMonitorePojo;
import com.isoftstone.mapper.main.RemoteMonitoreMapper;
import com.isoftstone.service.RemoteMonitoreService;

@Service("remoteMonitoreService")
public class RemoteMonitoreServiceImpl extends BaseService<RemoteMonitore> implements RemoteMonitoreService {

	@Resource
    private RemoteMonitoreMapper remoteMonitoreMapper;
	
	/**
	 * 分页查询远程监控设备信息
	 */
	@Override
	public PageInfo<RemoteMonitorePojo> findPage(RemoteMonitore remoteMonitore,Integer roleId,List<Long> areaList, int start, int length) {
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", remoteMonitore.getName());
        map.put("roleId", roleId);
        map.put("areaList", areaList);
        List<RemoteMonitorePojo> pojoList = remoteMonitoreMapper.findPage(map);
		return new PageInfo<RemoteMonitorePojo>(pojoList);
	}
	
	/**
	 * 批量删除远程监控信息
	 */
	@Override
	public int delRemoteMonitore(List<Long> list) {
		int result = remoteMonitoreMapper.delRemoteMonitore(list);
        return result;
	}

	@Override
	public int addRemoteMonitore(RemoteMonitore remoteMonitore) {
		int result = remoteMonitoreMapper.addRemoteMonitore(remoteMonitore);
        return result;
	}
	
	@Override
	public int editRemoteMonitore(RemoteMonitore remoteMonitore) {
		int result = remoteMonitoreMapper.editRemoteMonitore(remoteMonitore);
        return result;
	}
	
	@Override
	public RemoteMonitorePojo findHdById(Long id) {
		RemoteMonitorePojo result = remoteMonitoreMapper.findHdById(id);
        return result;
	}
	
}
