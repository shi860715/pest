package com.isoftstone.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
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

/**
 * 野外调查service
 * @author lufei
 *
 */
public interface FieldInvestigateService extends IService<FieldSurvey> {
	
	/**
	 * 查询野外调查记录
	 */
	public PageInfo<FieldSurvey> findPage(FieldSurvey fieldSurvey, Long userId,Integer roleId,List<Long> areaList, int start, int length);
	
	/**
	 * 添加野外调查记录
	 */
	public int addFieldSurvey(FieldSurvey fieldSurvey);
	
	/**
	 * 更新野外调查记录
	 */
	public int updateFieldSurvey(FieldSurvey fieldSurvey);
	
	/**
	 * 拖动调查，更新日期
	 * @param map
	 * @return
	 */
	public int updateSurveyTime(Long id,Integer day);
	
	/**
	 * 删除调查
	 * @param id
	 * @return
	 */
	public int deleteFieldSurvey(List<Long> list);
	
	/**
	 * 查询踏查记录
	 * @param stepInvestigation
	 * @return
	 */
	public PageInfo<StepInvestigation> findStepInvestigationPage(StepInvestigation stepInvestigation, int start, int length);
	
	/**
	 * 删除踏查
	 * @param id
	 * @return
	 */
	public int delStepInvestigation(List<Long> list);
	
	/**
	 * 添加踏查记录
	 * @param stepInvestigation
	 * @return
	 */
	public int addStepInvestigation(List<StepInvestigation> list);
	
	/**
	 * 编辑踏查记录
	 * @param stepInvestigation
	 * @return
	 */
	public int editStepInvestigation(StepInvestigation stepInvestigation);
	
	/**
	 * 查询标准地调查记录
	 * @param stepInvestigation
	 * @return
	 */
	public PageInfo<StandardSurvey> findStandardSurveyPage(StandardSurvey standardSurvey, int start, int length);
	
	/**
	 * 删除标准地调查记录
	 * @param id
	 * @return
	 */
	public int delStandardSurvey(List<Long> list);
	
	/**
	 * 添加标准地调查记录
	 * @param stepInvestigation
	 * @return
	 */
	public int addStandardSurvey(List<StandardSurvey> list);
	
	/**
	 * 编辑标准地调查记录
	 * @param stepInvestigation
	 * @return
	 */
	public int editStandardSurvey(StandardSurvey standardSurvey);
	
	/**
	 * 查询诱捕记录
	 * @param stepInvestigation
	 * @return
	 */
	public PageInfo<TrappedSurvey> findTrappedSurveyPage(TrappedSurvey trappedSurvey, int start, int length);
	
	/**
	 * 删除诱捕记录
	 * @param id
	 * @return
	 */
	public int delTrappedSurvey(List<Long> list);
	
	/**
	 * 添加诱捕记录
	 * @param stepInvestigation
	 * @return
	 */
	public int addTrappedSurvey(List<TrappedSurvey> list);
	
	/**
	 * 编辑诱捕记录
	 * @param stepInvestigation
	 * @return
	 */
	public int editTrappedSurvey(TrappedSurvey trappedSurvey);
	
	/**
	 * 查询花圃调查记录
	 * @param stepInvestigation
	 * @return
	 */
	public PageInfo<NurserySurvey> findNurserySurveyPage(NurserySurvey nurserySurvey, int start, int length);
	
	/**
	 * 删除花圃调查记录
	 * @param id
	 * @return
	 */
	public int delNurserySurvey(List<Long> list);
	
	/**
	 * 添加花圃调查记录
	 * @param stepInvestigation
	 * @return
	 */
	public int addNurserySurvey(List<NurserySurvey> list);
	
	/**
	 * 编辑花圃调查记录
	 * @param stepInvestigation
	 * @return
	 */
	public int editNurserySurvey(NurserySurvey nurserySurvey);
	
	/**
	 * 查询种果调查记录
	 * @param stepInvestigation
	 * @return
	 */
	public PageInfo<FruitSurvey> findFruitSurveyPage(FruitSurvey fruitSurvey, int start, int length);
	
	/**
	 * 删除种果调查记录
	 * @param id
	 * @return
	 */
	public int delFruitSurvey(List<Long> list);
	
	/**
	 * 添加种果调查记录
	 * @param stepInvestigation
	 * @return
	 */
	public int addFruitSurvey(List<FruitSurvey> list);
	
	/**
	 * 编辑种果调查记录
	 * @param stepInvestigation
	 * @return
	 */
	public int editFruitSurvey(FruitSurvey fruitSurvey);
	
	/**
	 * 查询踏查数据统计信息
	 */
	public PageInfo<StepInvestigationPojo> findStepDataPage(FieldSurvey fieldSurvey, Long userId,Integer roleId,List<Long> areaList, int start, int length);
	
	/**
	 * 查询标准地数据统计信息
	 */
	public PageInfo<StandardSurveyPojo> findStandardDataPage(FieldSurvey fieldSurvey, Long userId,Integer roleId,List<Long> areaList, int start, int length);
	
	/**
	 * 查询诱捕数据统计信息
	 */
	public PageInfo<TrappedSurveyPojo> findTrappedDataPage(FieldSurvey fieldSurvey, Long userId,Integer roleId,List<Long> areaList, int start, int length);
	
	/**
	 * 查询苗圃花木数据统计信息
	 */
	public PageInfo<NurserySurveyPojo> findNurseryDataPage(FieldSurvey fieldSurvey, Long userId,Integer roleId,List<Long> areaList, int start, int length);
	
	/**
	 * 查询种果花木数据统计信息
	 */
	public PageInfo<FruitSurveyPojo> findFruitDataPage(FieldSurvey fieldSurvey, Long userId,Integer roleId,List<Long> areaList, int start, int length);
	
	/**
	 * 查询踏出数据列表
	 * @param fieldSurvey
	 * @param roleId
	 * @param areaList
	 * @return
	 */
	public List<StepInvestigationPojo> queryStepInvestigation(FieldSurvey fieldSurvey, Integer roleId, List<Long> areaList);
	
	/**
	 * 查询标准地调查数据列表
	 * @param fieldSurvey
	 * @param roleId
	 * @param areaList
	 * @return
	 */
	public List<StandardSurveyPojo> queryStandardSurvey(FieldSurvey fieldSurvey, Integer roleId, List<Long> areaList);
	
	/**
	 * 查询诱捕调查数据列表
	 * @param fieldSurvey
	 * @param roleId
	 * @param areaList
	 * @return
	 */
	public List<TrappedSurveyPojo> queryTrappedSurvey(FieldSurvey fieldSurvey, Integer roleId, List<Long> areaList);
	
	/**
	 * 查询苗圃花木调查数据列表
	 * @param fieldSurvey
	 * @param roleId
	 * @param areaList
	 * @return
	 */
	public List<NurserySurveyPojo> queryNurserySurvey(FieldSurvey fieldSurvey, Integer roleId, List<Long> areaList);
	
	/**
	 * 查询种果花木调查数据列表
	 * @param fieldSurvey
	 * @param roleId
	 * @param areaList
	 * @return
	 */
	public List<FruitSurveyPojo> queryFruitSurvey(FieldSurvey fieldSurvey, Integer roleId, List<Long> areaList);
	
    int getFieldSurvey(Integer roleId,List<Long> areaList);
}
