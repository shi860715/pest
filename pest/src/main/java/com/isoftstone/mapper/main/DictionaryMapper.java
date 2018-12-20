package com.isoftstone.mapper.main;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.Dictionary;
import com.isoftstone.util.MyMapper;

/**
 * 字典表mapper
 * @author llmaoa
 *
 */
public interface DictionaryMapper extends MyMapper<Dictionary> {
	
	/**
	 * 根据key获取相应的value值
	 * @author llmaoa
	 * @param key
	 * @return
	 * @since 2017年11月29日
	 * @see 
	 */
	public Dictionary getByKey(@Param(value="key") String key);

    /**
     * 模糊查询
     * @author llmaoa
     * @param key
     * @return
     * @since 2017年11月29日
     * @see 
     */
    public List<Dictionary> fuzzyQueryByKey(@Param(value="key") String key);
}
