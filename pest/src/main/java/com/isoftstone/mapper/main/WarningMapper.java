package com.isoftstone.mapper.main;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.Warning;
import com.isoftstone.util.MyMapper;

public interface WarningMapper extends MyMapper<Warning> {

	
	/**
     * 
     * 分页查询预警信息
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 预警分页列表
     * @since 207年11月15日
     */
    public List<Warning> findPage(Warning warning);

    /**
     * 添加预警
     *
     * @author jnjua
     * @param Warning
     *            预警模型
     * @return 持久化操作码
     * @since 207年11月15日
     */
    public int addWarning(Warning warning);

    /**
     * 更新预警信息
     *
     * @author jnjua
     * @param Warning
     *            预警模型
     * @return 持久化操作码
     * @since 207年11月15日
     */
    public int updateWarning(Warning warning);

    /**
     * 删除预警信息
     *
     * @author jnjua
     * @param Warning
     *            预警模型
     * @return 持久化操作码
     * @since 207年11月15日
     */
    public int delWarning(@Param("idList") List<Long> idList);
}