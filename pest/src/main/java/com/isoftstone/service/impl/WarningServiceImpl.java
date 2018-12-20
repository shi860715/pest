package com.isoftstone.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.mapper.main.WarningMapper;
import com.isoftstone.entity.model.Warning;
import com.isoftstone.service.WarningService;

/**
 * Created by jnjua on 2017/11/15.
 */
@Service("warningService")
public class WarningServiceImpl extends BaseService<Warning> implements WarningService{
    @Resource
    private WarningMapper warningMapper;
    
    /**
     * 方法描述：分页查询预警信息列表
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 预警信息列表
     * @since 2017年11月15日
     */
    @Override
    public PageInfo<Warning> findPage(Warning warning, int start, int length) {
        
        int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        List<Warning> warningList = warningMapper.findPage(warning);
        return new PageInfo<Warning>(warningList);
    }
    
    /**
     * 方法描述：添加预警信息
     *
     * @author jnjua	
     * @param warning
     *            预警信息
     * @return 持久化操作码
     * @since 2017年11月15日
     * @see
     */
    public int addWarning(Warning warning) {

        int result = warningMapper.addWarning(warning);

        return result;
    }
    
    /**
     * 方法描述：更新预警信息
     *
     * @author jnjua
     * @param warning
     *            预警信息模型
     * @return 持久化操作码
     * @since 2017年11月15日
     */
    public int updateWarning(Warning warning) {

        int result = warningMapper.updateWarning(warning);

        return result;

    }

    /**
     * 方法描述：删除预警信息
     *
     * @author jnjua
     * @param id
     *            用户ID
     * @return 持久化操作码
     * @since 2017年11月15日
     */
    public int delWarning(List<Long> uList) {

    	int result = warningMapper.delWarning(uList);

        return result;
    }

}
