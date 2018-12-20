package com.isoftstone.mapper.main;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.RemoteDiagnosis;
import com.isoftstone.entity.pojo.RemoteDiagnosisPojo;
import com.isoftstone.util.MyMapper;

public interface RemoteDiagnosisMapper extends MyMapper<RemoteDiagnosis> {

	/**
     * 
     * 分页查询用户信息
     *
     * @author feilua
     * @param page
     *            分页模型
     * @return 用户分页列表
     * @since 207年11月06日
     */
    public List<RemoteDiagnosisPojo> findPage(Map<String,Object> map);
    
    /**
     * 批量删除远程诊断信息
     * @param list
     * @return
     */
    public int delDiagnosis(@Param(value="list") List<Long> list);
    
    /**
     * 添加远程诊断信息
     * @param list
     * @return
     */
    public int addDiagnosis(RemoteDiagnosis remoteDiagnosis);
    
    /**
     * 编辑远程诊断信息表
     * @param remoteDiagnosis
     * @return
     */
    public int editDiagnosis(RemoteDiagnosis remoteDiagnosis);
    
    /**
     * 诊断
     * @param remoteDiagnosis
     * @return
     */
    public int diagnosis(RemoteDiagnosis remoteDiagnosis);
}
