package com.isoftstone.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.mapper.main.InsectMapper;
import com.isoftstone.entity.model.Insect;
import com.isoftstone.service.InsectService;

/**
 * Created by jnju on 2017/11/14.
 */
@Service("insectService")
public class InsectServiceImpl extends BaseService<Insect> implements InsectService{
    @Resource
    private InsectMapper insectMapper;
    
    /**
     * 方法描述：分页查询虫类信息列表
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 虫类信息列表
     * @since 2017年11月14日
     */
    @Override
    public PageInfo<Insect> findPage(Insect insect, int start, int length) {
        
        int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        List<Insect> insectList = insectMapper.findPage(insect);
        return new PageInfo<Insect>(insectList);
    }
    
    /**
     * 方法描述：添加虫类信息
     *
     * @author jnjua	
     * @param insect
     *            虫类信息
     * @return 持久化操作码
     * @since 2017年11月14日
     * @see
     */
    public int addInsect(Insect insect) {

        int result = insectMapper.addInsect(insect);

        return result;
    }
    
    /**
     * 方法描述：更新虫类信息
     *
     * @author jnjua
     * @param insect
     *            虫类信息模型
     * @return 持久化操作码
     * @since 2017年11月14日
     */
    public int updateInsect(Insect insect) {

        int result = insectMapper.updateInsect(insect);

        return result;

    }

    /**
	 * 查询所有虫类信息
	 */
	public List<Insect> getAllInsect(){
		List<Insect> list = insectMapper.getAllInsect();
		return list;
	}
	
	public Insect selectByName(String name){
		Insect i = insectMapper.selectByName(name);
		return i;
	}
}
