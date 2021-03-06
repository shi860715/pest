package com.isoftstone.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.Resources;

/**
 * Created by yangqj on 2017/4/25.
 */
public interface ResourcesService extends IService<Resources> {
    PageInfo<Resources> selectByPage(Resources resources, int start, int length);

    public List<Resources> queryAll();

    public List<Resources> loadUserResources(Map<String,Object> map);

    public List<Resources> queryResourcesListWithSelected(Integer rid);
    
    public int delResources(String ids);
}
