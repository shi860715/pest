package com.isoftstone.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.Warning;

/**
 * Created by jnjua on 2017/11/15.
 */
public interface WarningService extends IService<Warning>{

    PageInfo<Warning> findPage(Warning warning, int start, int length);
    
    int addWarning(Warning warning);
    
    int updateWarning(Warning warning);
    
    int delWarning(List<Long> uList);
}
