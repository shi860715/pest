package com.isoftstone.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.mapper.main.UserMapper;
import com.isoftstone.mapper.main.UserRoleMapper;
import com.isoftstone.entity.imports.UserTemp;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.entity.model.UserRole;
import com.isoftstone.service.UserService;
import com.isoftstone.util.PasswordHelper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

/**
 * Created by yangqj on 2017/4/21.
 */
@Service("userService")
public class UserServiceImpl extends BaseService<User> implements UserService{
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public PageInfo<User> selectByPage(User user, int start, int length) {
        int page = start/length+1;
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(user.getUsername())) {
            criteria.andLike("username", "%" + user.getUsername() + "%");
        }
        if (user.getId() != null) {
            criteria.andEqualTo("id", user.getId());
        }
        if (user.getEnable() != null) {
            criteria.andEqualTo("enable", user.getEnable());
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<User> userList = selectByExample(example);
        return new PageInfo<>(userList);
    }

    @Override
    public User selectByUsername(String username) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        List<User> userList = selectByExample(example);
        if(userList.size()>0){
            return userList.get(0);
        }
        return null;
    }
    
    @Override
    public User selectByTelphone(String telphone) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("telphone",telphone);
        List<User> userList = selectByExample(example);
        if(userList.size()>0){
            return userList.get(0);
        }
            return null;
    }
    
    @Override
    public UserPojo login(String username){
        return userMapper.login(username);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void delUser(Integer userid) {
        //删除用户表
        mapper.deleteByPrimaryKey(userid);
        //删除用户角色表
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid",userid);
        userRoleMapper.deleteByExample(example);
    }
    
    
    /************************************************/
    
    /**
     * 方法描述：分页查询用户信息列表
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 用户信息列表
     * @since 2017年11月06日
     */
    @Override
    public PageInfo<UserPojo> findPage(UserPojo user, int start, int length) {
        
        int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        List<UserPojo> userList = userMapper.findPage(user);
        return new PageInfo<UserPojo>(userList);
    }
    
    /**
     * 方法描述：添加用户信息
     *
     * @author jnjua	
     * @param user
     *            用户信息
     * @return 持久化操作码
     * @since 2017年11月06日
     * @see
     */
    public int addUser(User user, String roleId) {

        int result = userMapper.addUser(user);

        // 一个用户对应一个角色 to do 
        UserRole userRole = new UserRole();
        userRole.setRoleid(roleId);
        userRole.setUserid(user.getId());
        result = userRoleMapper.addUserRole(userRole);

        return result;
    }
    
    /**
     * 方法描述：更新用户信息
     *
     * @author jnjua
     * @param user
     *            用户信息模型
     * @return 持久化操作码
     * @since 2017年11月06日
     */
    public int updateUser(User user, String roleId) {

        int result = userMapper.updateUser(user);

        //先删除后插入
        userRoleMapper.deleteUserRole(user.getId());
        // 一个用户对应多个用户组to do 
        UserRole userRole = new UserRole();
        userRole.setRoleid(roleId);
        userRole.setUserid(user.getId());
        result = userRoleMapper.addUserRole(userRole);

        return result;

    }
    
    /**
     * 方法描述：更新用户信息
     *
     * @author jnjua
     * @param user
     *            用户信息模型
     * @return 持久化操作码
     * @since 2017年11月06日
     */
    public int updateUserInfo(User user) {

        int result = userMapper.updateUser(user);

        return result;

    }
    
    /**
     * 方法描述：根据ID查询用户信息
     *
     * @author jnjua
     * @param id
     *            用户ID
     * @return 用户信息模型
     * @since 2017年11月06日
     */
    public UserPojo getUser(Long id) {

        UserPojo result = userMapper.getUser(id);

        return result;
    }
    
    /**
     * 方法描述：删除用户信息
     *
     * @author jnjua
     * @param id
     *            用户ID
     * @return 持久化操作码
     * @since 2016年11月18日
     */
    public int deleteUser(List<Long> uList) {

    	int result = userMapper.deleteUser(uList);

        return result;
    }
    
    /**
     * 方法描述：启用用户
     *
     * @author jnjua
     * @param id
     *            用户ID
     * @return 持久化操作码
     * @since 2016年11月18日
     */
    public int enableUser(List<Long> uList) {

    	int result = userMapper.enableUser(uList);

        return result;
    }
    
    /**
     * 方法描述：禁用用户
     *
     * @author jnjua
     * @param id
     *            用户ID
     * @return 持久化操作码
     * @since 2016年11月18日
     */
    public int unableUser(List<Long> uList) {

    	int result = userMapper.unableUser(uList);

        return result;
    }
    
    /**
     * 方法描述：禁用用户
     *
     * @author llmaoa
     * @param User模型
     *            用户ID
     * @return 持久化操作码
     * @since 2016年12月19日
     */
    public List<UserPojo> queryUsers(User user){
        List<UserPojo> up = userMapper.queryUsers(user);
        return up;
    }
    
    /**
     * 重置密码
     *
     * @author jnjua
     * @param user
     *            用户模型
     * @return 持久化操作码
     * @since 2017年12月18日
     */
    public int updatePwd(User user) {
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(user);
        int status = userMapper.updatePwd(user);
        return status;
    }
    
    /**
     * 重置密码
     *
     * @author jnjua
     * @param user
     *            用户模型
     * @return 持久化操作码
     * @since 2017年12月18日
     */
    @Override
    @Transactional
    public int importUsers(List<UserTemp> userList){
        int count = userMapper.importUsers(userList);
        userMapper.addRole(userList);
        return count;
    }
    
    /**
     * 更新管理区域
     * @param user
     * @return
     */
    public int updateManageArea(User user){
    	int result = userMapper.updateManageArea(user);
    	return result;
    }
    
    /**
     * 查询用户名是否有重复
     * @param user
     * @return
     */
    public int selectByUsername(User user){
    	int result = userMapper.selectByUsername(user);
    	return result;
    }
    
    /**
     * 查询手机号是否有重复
     * @param user
     * @return
     */
    public int selectByTelphone(User user){
    	int result = userMapper.selectByTelphone(user);
    	return result;
    }
    
    @Override
    public UserPojo loginByPhone(String telphone){
        return userMapper.loginByPhone(telphone);
    }
    
    /**
     * 方法描述：查询用户数量
     *
     * @author jnjua
     * @param page
     * @return 用户数量
     * @since 2018年01月09日
     */
    @Override
    public int getUserSum(Integer roleId,List<Long> areaList) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("roleId", roleId);
        paramMap.put("areaList", areaList);
        int count = userMapper.getUserSum(paramMap);
        return count;
    }
    
    @Override
    public int saveUserInfo(User user){
    	int result = userMapper.saveUserInfo(user);
    	return result;
    }
    
    @Override
    public Long selectRoleByRolename(String rolename){
    	Long role = userMapper.selectRoleByRolename(rolename);
    	return role;
    }
    
    @Override
    public int selectUserByTel(User user){
    	int result = userMapper.selectUserByTel(user);
    	return result;
    }
    
    @Override
    public int updateStatus(User user){
    	int result = userMapper.updateStatus(user);
    	return result;
    }
    
}
