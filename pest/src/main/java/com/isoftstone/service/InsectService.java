package com.isoftstone.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.Insect;

/**
 * Created by jnjua on 2017/11/14.
 */
public interface InsectService extends IService<Insect>{

    PageInfo<Insect> findPage(Insect insect, int start, int length);
    
    int addInsect(Insect insect);
    
    int updateInsect(Insect insect);
    
    List<Insect> getAllInsect();
    
    Insect selectByName(String name);

}
