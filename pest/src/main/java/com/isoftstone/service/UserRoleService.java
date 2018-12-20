package com.isoftstone.service;

import com.isoftstone.entity.model.UserRole;

/**
 * Created by yangqj on 2017/4/26.
 */
public interface UserRoleService extends IService<UserRole> {

    public void addUserRole(UserRole userRole);
    
    public UserRole getUserRole(Long id);

}
