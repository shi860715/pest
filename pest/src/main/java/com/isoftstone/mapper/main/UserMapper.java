package com.isoftstone.mapper.main;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.imports.UserTemp;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.util.MyMapper;

public interface UserMapper extends MyMapper<User> {

	
	/**
     * 
     * 分页查询用户信息
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 用户分页列表
     * @since 207年11月06日
     */
    public List<UserPojo> findPage(UserPojo user);

    /**
     * 添加用户
     *
     * @author jnjua
     * @param user
     *            用户模型
     * @return 持久化操作码
     * @since 207年11月06日
     */
    public int addUser(User user);

    /**
     * 更新用户信息
     *
     * @author jnjua
     * @param user
     *            用户模型
     * @return 持久化操作码
     * @since 207年11月06日
     */
    public int updateUser(User user);

    /**
     * 根据ID查询用户信息
     *
     * @author jnjua
     * @param id
     *            用户ID
     * @return 用户信息
     * @since 207年11月06日
     */
    public UserPojo getUser(Long id);

    /**
     * 删除用户信息
     *
     * @author jnjua
     * @param id
     *            用户ID
     * @return 持久化操作码
     * @since 207年11月06日
     */
    public int deleteUser(@Param(value="uList") List<Long> uList);

    /**
     * 启用用户
     *
     * @author jnjua
     * @param user
     *            用户模型
     * @return 持久化操作码
     * @since 207年11月06日
     */
    public int enableUser(@Param(value="uList") List<Long> uList);
    
    /**
     * 禁用用户
     *
     * @author jnjua
     * @param user
     *            用户模型
     * @return 持久化操作码
     * @since 207年11月06日
     */
    public int unableUser(@Param(value="uList") List<Long> uList);


    public UserPojo login(String username);
    
    /**
     * 根据管理区域获得用户信息
     * @author llmaoa
     * @param area
     * @return
     * @since 2017年11月28日
     * @see 
     */
    public User queryByManageArea(Integer area); 
    

    /**
     * 根据查询条件搜索所有用户
     * @author llmaoa
     * @param user
     * @return
     * @since 2017年12月19日
     * @see 
     */
    public List<UserPojo> queryUsers(User user);    
    /***********手机端接口************/
    /**
     * 方法描述：手机端用户登录
     *
     * @author llmaoa
     * @param user
     *            
     * @since 2016年12月07日
     */
    public UserPojo loginMobile(User user);
    
    /**
     * 重置密码
     *
     * @author jnjua
     * @param user
     *            用户模型
     * @return 持久化操作码
     * @since 2017年12月18日
     */
    public int updatePwd(User user);
    
    /**
     * 导入用户
     * @author llmaoa
     * @param userList
     * @return
     * @since 2017年12月20日
     * @see 
     */
    int importUsers(@Param(value="userList") List<UserTemp> userList);
    
    /**
     * 根据管理区域获得用户信息
     * @author llmaoa
     * @param area
     * @return
     * @since 2017年11月28日
     * @see 
     */
    public int updateManageArea(@Param(value="manageArea")Long manageArea); 
    
    /**
     * 更新管理区域
     * @param user
     * @return
     */
    public int updateManageArea(User user);
    
    /**
     * 查询用户名是否有重复
     * @param user
     * @return
     */
    public int selectByUsername(User user);
    
    /**
     * 查询手机号是否有重复
     * @param user
     * @return
     */
    public int selectByTelphone(User user);

    public UserPojo loginByPhone(String telphone);
    
    /**
     * 查询用户数量
     * @param user
     * @return
     */
    public int getUserSum(Map<String,Object> map);
    
    public int saveUserInfo(User user);
    
    public Long selectRoleByRolename(String rolename);

    int addRole(@Param(value="userList") List<UserTemp> userList);
    
    public int selectUserByTel(User user);    
    
    public int updateStatus(User user);
    
}