package com.isoftstone.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.RemoteDiagnosis;
import com.isoftstone.entity.pojo.RemoteDiagnosisPojo;

public interface RemoteDiagnosisService extends IService<RemoteDiagnosis>{

	PageInfo<RemoteDiagnosisPojo> findPage(RemoteDiagnosisPojo remoteDiagnosisPojo, int start, int length);
	
	PageInfo<RemoteDiagnosisPojo> findRemoteDiagnosis(RemoteDiagnosisPojo remoteDiagnosisPojo, Integer roleId, int start, int length);
	
	int delDiagnosis(List<Long> list);
	
	int addDiagnosis(RemoteDiagnosis remoteDiagnosis);
	
	int editDiagnosis(RemoteDiagnosis remoteDiagnosis);
	
	int diagnosis(RemoteDiagnosis remoteDiagnosis);
}
