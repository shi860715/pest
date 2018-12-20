package com.isoftstone.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.Dictionary;

/**
 * 地区service
 * @author lufei
 *
 */
public interface DictionaryService extends IService<Dictionary> {
    
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
     * 根据key值模糊查询
     * @author llmaoa
     * @param key
     * @return
     * @since 2017年11月29日
     * @see 
     */
    public List<Dictionary> fuzzyQueryByKey(@Param(value="key") String key);
}
