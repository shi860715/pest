package com.isoftstone.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.imports.UserTemp;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.pojo.UserPojo;

/**
 * Created by yangqj on 2017/4/21.
 */
public interface UserService extends IService<User>{
    PageInfo<User> selectByPage(User user, int start, int length);

    UserPojo login(String username);
    
    User selectByUsername(String username);
    
    User selectByTelphone(String telphone);

    void delUser(Integer userid);

    /*********************************************/
    PageInfo<UserPojo> findPage(UserPojo user, int start, int length);
    
    int addUser(User user, String roleId);
    
    int updateUser(User user, String roleId);
    
    int updateUserInfo(User user);
    
    UserPojo getUser(Long id);
    
    int deleteUser(List<Long> uList);
    
    int enableUser(List<Long> uList);
    
    int unableUser(List<Long> uList);
    
    List<UserPojo> queryUsers(User user);
    
    int updatePwd(User user);
    
    int importUsers(List<UserTemp> userList);
    
    int updateManageArea(User user);
    
    int selectByUsername(User user);
    
    int selectByTelphone(User user);

    UserPojo loginByPhone(String telphone);
    
    int getUserSum(Integer roleId,List<Long> areaList);
    
    int saveUserInfo(User user);
    
    Long selectRoleByRolename(String rolename);
        
    int selectUserByTel(User user);
    
    int updateStatus(User user);

}