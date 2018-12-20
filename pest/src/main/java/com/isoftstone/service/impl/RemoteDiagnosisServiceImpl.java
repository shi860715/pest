package com.isoftstone.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.commons.Constants;
import com.isoftstone.entity.model.RemoteDiagnosis;
import com.isoftstone.entity.pojo.RemoteDiagnosisPojo;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.mapper.main.RemoteDiagnosisMapper;
import com.isoftstone.service.RemoteDiagnosisService;

@Service("remoteDiagnosisService")
public class RemoteDiagnosisServiceImpl extends BaseService<RemoteDiagnosis> implements RemoteDiagnosisService {

	@Resource
    private RemoteDiagnosisMapper remoteDiagnosisMapper;
	
	/**
	 * 分页查询远程诊断列表
	 */
	@Override
	public PageInfo<RemoteDiagnosisPojo> findPage(RemoteDiagnosisPojo remoteDiagnosisPojo, int start, int length) {
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("roleId", user.getRoleId());
        map.put("realName", remoteDiagnosisPojo.getRealName());
        map.put("diagnosticStatus", remoteDiagnosisPojo.getDiagnosticStatus());
        map.put("captureTime", remoteDiagnosisPojo.getCaptureTime());
        map.put("userId", user.getId());
        List<RemoteDiagnosisPojo> pojoList = remoteDiagnosisMapper.findPage(map);
		return new PageInfo<RemoteDiagnosisPojo>(pojoList);
	}
	
	/**
	 * 分页查询远程诊断列表
	 */
	@Override
	public PageInfo<RemoteDiagnosisPojo> findRemoteDiagnosis(RemoteDiagnosisPojo Pojo, Integer roleId, int start, int length) {
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("roleId", roleId);
        map.put("diagnosticStatus", Pojo.getDiagnosticStatus());
        map.put("userId", Pojo.getUserId());
        List<RemoteDiagnosisPojo> pojoList = remoteDiagnosisMapper.findPage(map);
		return new PageInfo<RemoteDiagnosisPojo>(pojoList);
	}

	/**
	 * 批量删除远程诊断信息表
	 */
	@Override
	public int delDiagnosis(List<Long> list) {
		int result = remoteDiagnosisMapper.delDiagnosis(list);
        return result;
	}

	@Override
	public int addDiagnosis(RemoteDiagnosis remoteDiagnosis) {
		int result = remoteDiagnosisMapper.addDiagnosis(remoteDiagnosis);
        return result;
	}
	
	@Override
	public int editDiagnosis(RemoteDiagnosis remoteDiagnosis) {
		int result = remoteDiagnosisMapper.editDiagnosis(remoteDiagnosis);
        return result;
	}

	
	@Override
	public int diagnosis(RemoteDiagnosis remoteDiagnosis) {
		int result = remoteDiagnosisMapper.diagnosis(remoteDiagnosis);
        return result;
	}
}
