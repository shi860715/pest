package com.isoftstone.mapper.main;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.WarningData;
import com.isoftstone.entity.pojo.WarningDataPojo;
import com.isoftstone.util.MyMapper;

/**
 * 预警数据mapper
 * @author llmaoa
 *
 */
public interface WarningDataMapper extends MyMapper<WarningData> {
    
    /**
     * 定时扫描数据库，更行预警数据
     * @author llmaoa
     * @since 2017年11月30日
     * @see 
     */
    public void taskWarningData();


    /**
     * 获取当前需要通知的预警数据
     * @author llmaoa
     * @return
     * @since 2017年11月30日
     * @see 
     */
    public List<WarningData> queryWaringData();
    
	/**
	 * 根据区域获取预警数据
	 * @author llmaoa
	 * @param key
	 * @return
	 * @since 2017年11月29日
	 * @see 
	 */
	public List<WarningDataPojo> getByArea(@Param(value="idList") List<Long> idList);
	
	/**
	 * 分页获取预警数据
	 * @author llmaoa
	 * @param warningData
	 * @return
	 * @since 2017年11月30日
	 * @see 
	 */
	public List<WarningDataPojo> findPage(WarningData warningData);
	
	/**
	 * 根据ID集合批量删除预警数据
	 * @author llmaoa
	 * @param idList
	 * @return
	 * @since 2017年12月1日
	 * @see 
	 */
	public int delWarningDatas(@Param(value="idList")List<Long> idList);
	
	/**
	 * 根据ID获取预警信息
	 * @author llmaoa
	 * @param id
	 * @return
	 * @since 2017年12月1日
	 * @see 
	 */
	public WarningDataPojo selectById(@Param(value="id")Long id);
	
	/**
     * 查询虫情告警数据
     * @param 
     * @return
     */
    public List<WarningDataPojo> getWarningData(Map<String,Object> map);
}
