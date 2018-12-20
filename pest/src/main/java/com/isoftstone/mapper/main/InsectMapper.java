package com.isoftstone.mapper.main;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.Insect;
import com.isoftstone.util.MyMapper;

public interface InsectMapper extends MyMapper<Insect> {

	
	/**
     * 
     * 分页查询虫类信息
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 虫类分页列表
     * @since 207年11月14日
     */
    public List<Insect> findPage(Insect insect);

    /**
     * 添加虫类
     *
     * @author jnjua
     * @param Insect
     *            虫类模型
     * @return 持久化操作码
     * @since 207年11月14日
     */
    public int addInsect(Insect insect);

    /**
     * 更新虫类信息
     *
     * @author jnjua
     * @param Insect
     *            虫类模型
     * @return 持久化操作码
     * @since 207年11月14日
     */
    public int updateInsect(Insect insect);
    
    /**
	 * 查询所有虫类信息
	 * @return
	 */
	public List<Insect> getAllInsect();
	
	public Insect selectByName(@Param(value="name")String name);

}