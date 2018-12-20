package com.isoftstone.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.DeviceExtends;
import com.isoftstone.entity.pojo.SetParameterPojo;
import com.isoftstone.mapper.main.DeviceExtendsMapper;
import com.isoftstone.mapper.slave.SetParameterMapper;
import com.isoftstone.service.SetParameterService;

/**
 * 参数设置service
 * @author lufei
 *
 */
@Service("setParameterService")
public class SetParameterServiceImpl extends BaseService<SetParameterPojo> implements SetParameterService {

	@Resource
    private SetParameterMapper setParameterMapper;
	
	@Resource
    private DeviceExtendsMapper deviceExtendsMapper;
	
	/**
	 * 分页查询参数设置列表
	 */
	@Override
	public PageInfo<SetParameterPojo> findPage(String devName, Integer roleId, List<Long> areaList, int start, int length) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("devName", devName);
        map.put("roleId", roleId);
        map.put("areaList", areaList);
        List<DeviceExtends> devList = deviceExtendsMapper.findPage(map);
    	int page = start/length+1;
    	//分页查询
    	PageHelper.startPage(page, length);
    	List<SetParameterPojo> pojoList = setParameterMapper.findPage(devList);
    	for(int i = 0; i< pojoList.size(); i++){
    		for(int j = 0; j< devList.size(); j++){
    			if(pojoList.get(i).getDeviceId().equals(devList.get(j).getId())){
    				pojoList.get(i).setImage(devList.get(j).getImage());
    				break;
    			}
    		}
    	}
    	
    	return new PageInfo<SetParameterPojo>(pojoList);
		
	}

}
