package com.isoftstone.mapper.main;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.FieldSurvey;
import com.isoftstone.entity.model.FruitSurvey;
import com.isoftstone.entity.model.NurserySurvey;
import com.isoftstone.entity.model.StandardSurvey;
import com.isoftstone.entity.model.StepInvestigation;
import com.isoftstone.entity.model.TrappedSurvey;
import com.isoftstone.entity.pojo.FruitSurveyPojo;
import com.isoftstone.entity.pojo.NurserySurveyPojo;
import com.isoftstone.entity.pojo.StandardSurveyPojo;
import com.isoftstone.entity.pojo.StepInvestigationPojo;
import com.isoftstone.entity.pojo.TrappedSurveyPojo;
import com.isoftstone.util.MyMapper;

public interface FieldInvestigateMapper extends MyMapper<FieldSurvey> {

	public List<FieldSurvey> findPage(Map<String,Object> map);
	
	public int addFieldSurvey(FieldSurvey fieldSurvey);
	
	public int updateFieldSurvey(FieldSurvey fieldSurvey);
	
	public int updateSurveyTime(Map<String,Object> map);
	
	public int deleteFieldSurvey(@Param(value="list") List<Long> list);
	
	public List<StepInvestigation> findStepInvestigationPage(Map<String,Object> map);
	
	public int delStepInvestigation(@Param(value="list") List<Long> list);
	
	public int addStepInvestigation(@Param(value="list") List<StepInvestigation> list);
	
	public int editStepInvestigation(StepInvestigation stepInvestigation);
	
	public List<StandardSurvey> findStandardSurveyPage(Map<String,Object> map);
	
	public int delStandardSurvey(@Param(value="list") List<Long> list);
	
	public int addStandardSurvey(@Param(value="list") List<StandardSurvey> list);
	
	public int editStandardSurvey(StandardSurvey standardSurvey);
	
	public List<TrappedSurvey> findTrappedSurveyPage(Map<String,Object> map);
	
	public int delTrappedSurvey(@Param(value="list") List<Long> list);
	
	public int addTrappedSurvey(@Param(value="list") List<TrappedSurvey> list);
	
	public int editTrappedSurvey(TrappedSurvey trappedSurvey);
	
	public List<NurserySurvey> findNurserySurveyPage(Map<String,Object> map);
	
	public int delNurserySurvey(@Param(value="list") List<Long> list);
	
	public int addNurserySurvey(@Param(value="list") List<NurserySurvey> list);
	
	public int editNurserySurvey(NurserySurvey nurserySurvey);
	
	public List<FruitSurvey> findFruitSurveyPage(Map<String,Object> map);
	
	public int delFruitSurvey(@Param(value="list") List<Long> list);
	
	public int addFruitSurvey(@Param(value="list") List<FruitSurvey> list);
	
	public int editFruitSurvey(FruitSurvey fruitSurvey);
	
	public List<StepInvestigationPojo> findStepDataPage(Map<String,Object> map);
	
	public List<StandardSurveyPojo> findStandardDataPage(Map<String,Object> map);
	
	public List<TrappedSurveyPojo> findTrappedDataPage(Map<String,Object> map);
	
	public List<NurserySurveyPojo> findNurseryDataPage(Map<String,Object> map);
	
	public List<FruitSurveyPojo> findFruitDataPage(Map<String,Object> map);
	
	/**
     * 查询野外调查总数
     * @param user
     * @return
     */
    public int getFieldSurvey(Map<String,Object> map);
}
