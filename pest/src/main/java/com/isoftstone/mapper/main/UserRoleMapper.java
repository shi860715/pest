package com.isoftstone.mapper.main;

import java.util.List;

import com.isoftstone.entity.model.UserRole;
import com.isoftstone.util.MyMapper;

public interface UserRoleMapper extends MyMapper<UserRole> {
    public List<Integer> findUserIdByRoleId(Integer roleId);
    
    public int addUserRole(UserRole userRole);
    
    public int deleteUserRole(Long userid);
    
    public UserRole getUserRole(Long id);
}