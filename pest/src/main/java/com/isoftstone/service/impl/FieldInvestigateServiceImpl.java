package com.isoftstone.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
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
import com.isoftstone.mapper.main.FieldInvestigateMapper;
import com.isoftstone.service.FieldInvestigateService;

/**
 * 野外调查service实现类
 * @author lufei
 *
 */
@Service("fieldInvestigateService")
public class FieldInvestigateServiceImpl extends BaseService<FieldSurvey> implements FieldInvestigateService {
	
	/**
	 * 野外调查mapper
	 */
	@Resource
	private FieldInvestigateMapper  fieldInvestigateMapper;
	
	/**
	 * 查询野外调查记录
	 */
	@Override
	public PageInfo<FieldSurvey> findPage(FieldSurvey fieldSurvey, Long userId,Integer roleId,List<Long> areaList, int start, int length){
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", fieldSurvey.getName());
        map.put("type", fieldSurvey.getType());
        map.put("userId", userId);
        map.put("roleId", roleId);
        map.put("areaList", areaList);
		List<FieldSurvey> list = fieldInvestigateMapper.findPage(map);
		return new PageInfo<FieldSurvey>(list);
	}

	/**
	 * 添加野外调查记录
	 */
	@Override
	public int addFieldSurvey(FieldSurvey fieldSurvey) {
		int result = fieldInvestigateMapper.addFieldSurvey(fieldSurvey);
		return result;
	}
	
	
	/**
	 * 更新野外调查记录
	 */
	@Override
	public int updateFieldSurvey(FieldSurvey fieldSurvey) {
		int result = fieldInvestigateMapper.updateFieldSurvey(fieldSurvey);
		return result;
	}
	
	/**
	 * 拖动调查，更新日期
	 */
	@Override
	public int updateSurveyTime(Long id,Integer day){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("day", day);
		int result = fieldInvestigateMapper.updateSurveyTime(map);
		return result;
	}
	
	/**
	 * 删除调查
	 */
	@Override
	public int deleteFieldSurvey(List<Long> list){
		int result = fieldInvestigateMapper.deleteFieldSurvey(list);
		return result;
	}

	/**
	 * 查询踏查记录
	 */
	@Override
	public PageInfo<StepInvestigation> findStepInvestigationPage(StepInvestigation stepInvestigation, int start, int length) {
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("fieldId", stepInvestigation.getFieldId());
        map.put("pest", stepInvestigation.getPest());
        map.put("hostPlant", stepInvestigation.getHostPlant());
        map.put("harmDegree", stepInvestigation.getHarmDegree());
        List<StepInvestigation> list = fieldInvestigateMapper.findStepInvestigationPage(map);
		return new PageInfo<StepInvestigation>(list);
	}
	
	/**
	 * 删除踏查
	 */
	@Override
	public int delStepInvestigation(List<Long> list){
		int result = fieldInvestigateMapper.delStepInvestigation(list);
		return result;
	}

	/**
	 * 添加踏查记录
	 */
	@Override
	public int addStepInvestigation(List<StepInvestigation> list) {
		int result = fieldInvestigateMapper.addStepInvestigation(list);
		return result;
	}

	/**
	 * 编辑踏查记录
	 */
	@Override
	public int editStepInvestigation(StepInvestigation stepInvestigation) {
		int result = fieldInvestigateMapper.editStepInvestigation(stepInvestigation);
		return result;
	}
	
	/**
	 * 查询标准地调查记录
	 */
	@Override
	public PageInfo<StandardSurvey> findStandardSurveyPage(StandardSurvey standardSurvey, int start, int length) {
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("fieldId", standardSurvey.getFieldId());
        map.put("pest", standardSurvey.getPest());
        map.put("hostPlant", standardSurvey.getHostPlant());
        map.put("harmDegree", standardSurvey.getHarmDegree());
        List<StandardSurvey> list = fieldInvestigateMapper.findStandardSurveyPage(map);
		return new PageInfo<StandardSurvey>(list);
	}
	
	/**
	 * 删除标准地调查记录
	 */
	@Override
	public int delStandardSurvey(List<Long> list){
		int result = fieldInvestigateMapper.delStandardSurvey(list);
		return result;
	}

	/**
	 * 添加标准地调查记录
	 */
	@Override
	public int addStandardSurvey(List<StandardSurvey> list){
		int result = fieldInvestigateMapper.addStandardSurvey(list);
		return result;
	}

	/**
	 * 编辑标准地调查记录
	 */
	@Override
	public int editStandardSurvey(StandardSurvey standardSurvey) {
		int result = fieldInvestigateMapper.editStandardSurvey(standardSurvey);
		return result;
	}
	
	/**
	 * 查询诱捕调查记录
	 */
	@Override
	public PageInfo<TrappedSurvey> findTrappedSurveyPage(TrappedSurvey trappedSurvey, int start, int length) {
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("fieldId", trappedSurvey.getFieldId());
        map.put("insectName", trappedSurvey.getInsectName());
        map.put("harmDegree", trappedSurvey.getHarmDegree());
        List<TrappedSurvey> list = fieldInvestigateMapper.findTrappedSurveyPage(map);
		return new PageInfo<TrappedSurvey>(list);
	}
	
	/**
	 * 删除诱捕调查记录
	 */
	@Override
	public int delTrappedSurvey(List<Long> list){
		int result = fieldInvestigateMapper.delTrappedSurvey(list);
		return result;
	}

	/**
	 * 添加诱捕调查记录
	 */
	@Override
	public int addTrappedSurvey(List<TrappedSurvey> list) {
		int result = fieldInvestigateMapper.addTrappedSurvey(list);
		return result;
	}

	/**
	 * 编辑诱捕调查记录
	 */
	@Override
	public int editTrappedSurvey(TrappedSurvey trappedSurvey) {
		int result = fieldInvestigateMapper.editTrappedSurvey(trappedSurvey);
		return result;
	}
	
	/**
	 * 查询花圃调查记录
	 */
	@Override
	public PageInfo<NurserySurvey> findNurserySurveyPage(NurserySurvey nurserySurvey, int start, int length) {
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("fieldId", nurserySurvey.getFieldId());
        map.put("pest", nurserySurvey.getPest());
        map.put("hostPlant", nurserySurvey.getHostPlant());
        map.put("harmDegree", nurserySurvey.getHarmDegree());
        List<NurserySurvey> list = fieldInvestigateMapper.findNurserySurveyPage(map);
		return new PageInfo<NurserySurvey>(list);
	}
	
	/**
	 * 删除花圃调查记录
	 */
	@Override
	public int delNurserySurvey(List<Long> list){
		int result = fieldInvestigateMapper.delNurserySurvey(list);
		return result;
	}

	/**
	 * 添加花圃调查记录
	 */
	@Override
	public int addNurserySurvey(List<NurserySurvey> list){
		int result = fieldInvestigateMapper.addNurserySurvey(list);
		return result;
	}

	/**
	 * 编辑花圃调查记录
	 */
	@Override
	public int editNurserySurvey(NurserySurvey nurserySurvey) {
		int result = fieldInvestigateMapper.editNurserySurvey(nurserySurvey);
		return result;
	}
	
	/**
	 * 查询种果调查记录
	 */
	@Override
	public PageInfo<FruitSurvey> findFruitSurveyPage(FruitSurvey fruitSurvey, int start, int length) {
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("fieldId", fruitSurvey.getFieldId());
        map.put("pest", fruitSurvey.getPest());
        map.put("hostPlant", fruitSurvey.getHostPlant());
        map.put("harmDegree", fruitSurvey.getHarmDegree());
        List<FruitSurvey> list = fieldInvestigateMapper.findFruitSurveyPage(map);
		return new PageInfo<FruitSurvey>(list);
	}
	
	/**
	 * 删除种果调查记录
	 */
	@Override
	public int delFruitSurvey(List<Long> list){
		int result = fieldInvestigateMapper.delFruitSurvey(list);
		return result;
	}

	/**
	 * 添加种果调查记录
	 */
	@Override
	public int addFruitSurvey(List<FruitSurvey> list){
		int result = fieldInvestigateMapper.addFruitSurvey(list);
		return result;
	}

	/**
	 * 编辑种果调查记录
	 */
	@Override
	public int editFruitSurvey(FruitSurvey fruitSurvey) {
		int result = fieldInvestigateMapper.editFruitSurvey(fruitSurvey);
		return result;
	}
	
	/**
	 * 查询踏查数据统计记录
	 */
	@Override
	public PageInfo<StepInvestigationPojo> findStepDataPage(FieldSurvey fieldSurvey, Long userId,Integer roleId,List<Long> areaList, int start, int length){
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
		Map<String,Object> map = new HashMap<String,Object>();
        map.put("surveyTime", fieldSurvey.getSurveyTime());
        map.put("userId", userId);
        map.put("roleId", roleId);
        map.put("areaList", areaList);
		List<StepInvestigationPojo> list = fieldInvestigateMapper.findStepDataPage(map);
		return new PageInfo<StepInvestigationPojo>(list);
	}
	
	/**
	 * 查询标准地数据统计记录
	 */
	@Override
	public PageInfo<StandardSurveyPojo> findStandardDataPage(FieldSurvey fieldSurvey, Long userId,Integer roleId,List<Long> areaList, int start, int length){
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
		Map<String,Object> map = new HashMap<String,Object>();
        map.put("surveyTime", fieldSurvey.getSurveyTime());
        map.put("userId", userId);
        map.put("roleId", roleId);
        map.put("areaList", areaList);
		List<StandardSurveyPojo> list = fieldInvestigateMapper.findStandardDataPage(map);
		return new PageInfo<StandardSurveyPojo>(list);
	}
	
	/**
	 * 查询诱捕数据统计记录
	 */
	@Override
	public PageInfo<TrappedSurveyPojo> findTrappedDataPage(FieldSurvey fieldSurvey, Long userId,Integer roleId,List<Long> areaList, int start, int length){
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
		Map<String,Object> map = new HashMap<String,Object>();
        map.put("surveyTime", fieldSurvey.getSurveyTime());
        map.put("userId", userId);
        map.put("roleId", roleId);
        map.put("areaList", areaList);
		List<TrappedSurveyPojo> list = fieldInvestigateMapper.findTrappedDataPage(map);
		return new PageInfo<TrappedSurveyPojo>(list);
	}
	
	/**
	 * 查询苗圃花木数据统计记录
	 */
	@Override
	public PageInfo<NurserySurveyPojo> findNurseryDataPage(FieldSurvey fieldSurvey, Long userId,Integer roleId,List<Long> areaList, int start, int length){
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
		Map<String,Object> map = new HashMap<String,Object>();
        map.put("surveyTime", fieldSurvey.getSurveyTime());
        map.put("userId", userId);
        map.put("roleId", roleId);
        map.put("areaList", areaList);
		List<NurserySurveyPojo> list = fieldInvestigateMapper.findNurseryDataPage(map);
		return new PageInfo<NurserySurveyPojo>(list);
	}
	
	/**
	 * 查询种果花木数据统计记录
	 */
	@Override
	public PageInfo<FruitSurveyPojo> findFruitDataPage(FieldSurvey fieldSurvey, Long userId,Integer roleId,List<Long> areaList, int start, int length){
		int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
		Map<String,Object> map = new HashMap<String,Object>();
        map.put("surveyTime", fieldSurvey.getSurveyTime());
        map.put("userId", userId);
        map.put("roleId", roleId);
        map.put("areaList", areaList);
		List<FruitSurveyPojo> list = fieldInvestigateMapper.findFruitDataPage(map);
		return new PageInfo<FruitSurveyPojo>(list);
	}
	
	/**
	 * 查询踏出数据列表
	 * @param fieldSurvey
	 * @param roleId
	 * @param areaList
	 * @return
	 */
	public List<StepInvestigationPojo> queryStepInvestigation(FieldSurvey fieldSurvey, Integer roleId, List<Long> areaList){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("surveyTime", fieldSurvey.getSurveyTime());
		map.put("areaList", areaList);
		map.put("roleId", roleId);
		List<StepInvestigationPojo> list = fieldInvestigateMapper.findStepDataPage(map);
		return list;
	}
	
	/**
	 * 查询标准地调查数据列表
	 * @param fieldSurvey
	 * @param roleId
	 * @param areaList
	 * @return
	 */
	public List<StandardSurveyPojo> queryStandardSurvey(FieldSurvey fieldSurvey, Integer roleId, List<Long> areaList){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("surveyTime", fieldSurvey.getSurveyTime());
		map.put("areaList", areaList);
		map.put("roleId", roleId);
		List<StandardSurveyPojo> list = fieldInvestigateMapper.findStandardDataPage(map);
		return list;
	}
	
	/**
	 * 查询诱捕调查数据列表
	 * @param fieldSurvey
	 * @param roleId
	 * @param areaList
	 * @return
	 */
	public List<TrappedSurveyPojo> queryTrappedSurvey(FieldSurvey fieldSurvey, Integer roleId, List<Long> areaList){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("surveyTime", fieldSurvey.getSurveyTime());
		map.put("areaList", areaList);
		map.put("roleId", roleId);
		List<TrappedSurveyPojo> list = fieldInvestigateMapper.findTrappedDataPage(map);
		return list;
	}
	
	/**
	 * 查询苗圃花木调查数据列表
	 * @param fieldSurvey
	 * @param roleId
	 * @param areaList
	 * @return
	 */
	public List<NurserySurveyPojo> queryNurserySurvey(FieldSurvey fieldSurvey, Integer roleId, List<Long> areaList){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("surveyTime", fieldSurvey.getSurveyTime());
		map.put("areaList", areaList);
		map.put("roleId", roleId);
		List<NurserySurveyPojo> list = fieldInvestigateMapper.findNurseryDataPage(map);
		return list;
	}
	
	/**
	 * 查询种果花木调查数据列表
	 * @param fieldSurvey
	 * @param roleId
	 * @param areaList
	 * @return
	 */
	public List<FruitSurveyPojo> queryFruitSurvey(FieldSurvey fieldSurvey, Integer roleId, List<Long> areaList){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("surveyTime", fieldSurvey.getSurveyTime());
		map.put("areaList", areaList);
		map.put("roleId", roleId);
		List<FruitSurveyPojo> list = fieldInvestigateMapper.findFruitDataPage(map);
		return list;
	}
	
	/**
     * 方法描述：查询野外调查总数
     *
     * @author jnjua
     * @param 
     * @return 野外调查总数
     * @since 2018年01月10日
     */
    @Override
    public int getFieldSurvey(Integer roleId,List<Long> areaList) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("roleId", roleId);
        paramMap.put("areaList", areaList);
        int count = fieldInvestigateMapper.getFieldSurvey(paramMap);
        return count;
    }
}
