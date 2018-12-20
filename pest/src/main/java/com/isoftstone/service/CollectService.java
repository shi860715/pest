package com.isoftstone.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.Area;
import com.isoftstone.entity.model.Collect;
import com.isoftstone.entity.pojo.CollectPojo;

/**
 * Created by jnjua on 2017/11/24.
 */
public interface CollectService extends IService<Collect>{

    PageInfo<Collect> findPage(Collect collect, Integer roleId, List<Long> areaList, int start, int length);
    
    int addCollect(Collect collect);
    
    int updateCollectById(Collect collect);
    
    int updateCollect(Collect collect);
    
    int delCollect(List<Long> uList);
    
    int findOne(Collect collect);
    
    int importCollects(List<Collect> collectList);
    
    List<Collect> queryCollects(Collect collect, Integer roleId, List<Long> areaList);
    
    List<Collect> findInsectStatisticsPage(Collect collect, Integer roleId, Long areaId, List<Long> areaList);
    
    List<CollectPojo> findPestPolylinePage(Collect collect, Integer type, Integer roleId, Long areaId, List<Long> areaList);
    
    List<CollectPojo> findPestColumnarPage(Collect collect, Integer type, List<Area> areaList, Long insectId1, Long insectId2);
}
