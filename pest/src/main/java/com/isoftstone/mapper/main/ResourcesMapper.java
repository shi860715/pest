package com.isoftstone.mapper.main;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.Resources;
import com.isoftstone.util.MyMapper;

public interface ResourcesMapper extends MyMapper<Resources> {

    public List<Resources> queryAll();

    public List<Resources> loadUserResources(Map<String,Object> map);

    public List<Resources> queryResourcesListWithSelected(Integer rid);
    
    public int delResources(@Param("idList") List<Long> idList);
}