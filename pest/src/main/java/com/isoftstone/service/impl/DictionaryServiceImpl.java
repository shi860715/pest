package com.isoftstone.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.isoftstone.entity.model.Dictionary;
import com.isoftstone.mapper.main.DictionaryMapper;
import com.isoftstone.service.DictionaryService;

/**
 * 地区service实现类
 * @author lufei
 *
 */
@Service("dictionaryService")
public class DictionaryServiceImpl extends BaseService<Dictionary> implements DictionaryService {
	
	/**
	 * 地区mapper
	 */
	@Resource
	private DictionaryMapper  dictionaryMapper;
    /**
     * 根据key获取相应的value值
     * @author llmaoa
     * @param key
     * @return
     * @since 2017年11月29日
     * @see 
     */
    public Dictionary getByKey(@Param(value="key") String key){
        return dictionaryMapper.getByKey(key);
    }

    public List<Dictionary> fuzzyQueryByKey(@Param(value="key") String key){
        return dictionaryMapper.fuzzyQueryByKey(key);
    }
}
