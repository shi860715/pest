package com.isoftstone.mapper.main;

import java.util.List;

import com.isoftstone.entity.model.Role;
import com.isoftstone.util.MyMapper;

public interface RoleMapper extends MyMapper<Role> {
    public List<Role> queryRoleListWithSelected(Integer id);
    
    public List<Role> findAllRole();
}