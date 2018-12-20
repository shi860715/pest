package com.isoftstone.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.isoftstone.entity.model.UserRole;
import com.isoftstone.mapper.main.UserRoleMapper;
import com.isoftstone.service.UserRoleService;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by yangqj on 2017/4/26.
 */
@Service("serRoleService")
public class UserRoleServiceImpl extends BaseService<UserRole> implements UserRoleService {

	@Resource
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void addUserRole(UserRole userRole) {
        //删除
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid",userRole.getUserid());
        mapper.deleteByExample(example);
        //添加
        String[] roleids = userRole.getRoleid().split(",");
        for (String roleId : roleids) {
            UserRole u = new UserRole();
            u.setUserid(userRole.getUserid());
            u.setRoleid(roleId);
            mapper.insert(u);
        }

    }
    
    public UserRole getUserRole(Long id) {
    	UserRole result = userRoleMapper.getUserRole(id);
    	return result;

    }
}
