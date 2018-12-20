package com.isoftstone.mapper.main;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.Collect;
import com.isoftstone.entity.pojo.CollectPojo;
import com.isoftstone.entity.pojo.PestPolylinePojo;
import com.isoftstone.util.MyMapper;

public interface CollectMapper extends MyMapper<Collect> {

	
	/**
     * 
     * 分页查询自动虫情信息
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 自动虫情分页列表
     * @since 207年11月24日
     */
    public List<Collect> findPage(Map<String,Object> map);

    /**
     * 添加自动虫情
     *
     * @author jnjua
     * @param CollectPojo
     *            自动虫情模型
     * @return 持久化操作码
     * @since 207年11月24日
     */
    public int addCollect(Collect collect);
    
    /**
     * 修改自动虫情信息
     *
     * @author jnjua
     * @param CollectPojo
     *            自动虫情模型
     * @return 持久化操作码
     * @since 207年11月24日
     */
    public int updateCollectById(Collect collect);

    /**
     * 更新自动虫情信息
     *
     * @author jnjua
     * @param CollectPojo
     *            自动虫情模型
     * @return 持久化操作码
     * @since 207年11月24日
     */
    public int updateCollect(Collect collect);

    /**
     * 删除自动虫情信息
     *
     * @author jnjua
     * @param CollectPojo
     *            自动虫情模型
     * @return 持久化操作码
     * @since 207年11月24日
     */
    public int delCollect(@Param("idList") List<Long> idList);
    
    /**
     * 
     * 判断是否需要新增还是修改自动虫情信息
     *
     * @author jnjua
     * @param 
     *            分页模型
     * @return 自动虫情分页列表
     * @since 207年11月24日
     */
    public int findOne(Collect collect);
    
    /**
     * 导入虫情数据
     * @param collectList
     * @return
     */
    int importCollects(@Param(value="collectList") List<Collect> collectList);
    
    /**
     * 查询虫情统计数据
     * @param map
     * @return
     */
    public List<Collect> findInsectStatisticsPage(Map<String,Object> map);
    
    /**
     * 查询折线图需要展示的冲集合
     * @param map
     * @return
     */
    public List<CollectPojo> queryInsectNames(Map<String,Object> map);
    
    /**
     * 查询月份折线图虫情统计数据
     * @param map
     * @return
     */
    public List<PestPolylinePojo> findPestPolylinePageByMonth(Map<String,Object> map);
    
    /**
     * 查询年份折线图虫情统计数据
     * @param map
     * @return
     */
    public List<PestPolylinePojo> findPestPolylinePageByYear(Map<String,Object> map);
    
    /**
     * 查询虫害柱状图数据
     * @param map
     * @return
     */
    public List<CollectPojo> findPestColumnarPage(Map<String,Object> map);
}