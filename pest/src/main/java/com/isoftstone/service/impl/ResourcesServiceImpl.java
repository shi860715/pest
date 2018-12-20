package com.isoftstone.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.Resources;
import com.isoftstone.mapper.main.ResourcesMapper;
import com.isoftstone.service.ResourcesService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

/**
 * Created by yangqj on 2017/4/25.
 */
@Service("resourcesService")
public class ResourcesServiceImpl extends BaseService<Resources> implements ResourcesService {
   @Resource
    private ResourcesMapper resourcesMapper;

    @Override
    public PageInfo<Resources> selectByPage(Resources resources, int start, int length) {
        int page = start/length+1;
        Example example = new Example(Resources.class);
        example.setOrderByClause("sort");

        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(resources.getName())) {
            criteria.andLike("name", "%" + resources.getName() + "%");
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<Resources> userList = selectByExample(example);
        return new PageInfo<>(userList);
    }

    @Override
    public List<Resources> queryAll(){
        return resourcesMapper.queryAll();
    }

    @Override
    @Cacheable(cacheNames="resources",key="#map['userid'].toString()+#map['type']")
    public List<Resources> loadUserResources(Map<String, Object> map) {
        return resourcesMapper.loadUserResources(map);
    }

    @Override
    public List<Resources> queryResourcesListWithSelected(Integer rid) {
        return resourcesMapper.queryResourcesListWithSelected(rid);
    }

    @Override
    public int delResources(String ids) {
        String[] id = ids.split(",");
        List<Long> idList = new ArrayList<Long>();
        for(String i : id){
            idList.add(Long.valueOf(i));
        }
        return resourcesMapper.delResources(idList);
    }
}
