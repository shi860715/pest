package com.isoftstone.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isoftstone.entity.model.Role;
import com.isoftstone.entity.model.RoleResources;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.service.RoleResourcesService;
import com.isoftstone.service.RoleService;

/**
 * Created by liup on 2017/9/7.
 */
@RestController
@RequestMapping("/roles")
public class RoleController {
	protected static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Resource
    private RoleService roleService;
    @Resource
    private RoleResourcesService roleResourcesService;

    @RequestMapping
    public List<Role> getAll(Role role){
        List<Role> roleList = roleService.findAllRole();
        return roleList;
    }
    
    @RequestMapping("/getAllRole")
    public String getAllRole(Role role){
    	AjaxResponse ajax = new AjaxResponse(true);
        List<Role> roleList = roleService.findAllRole();
        ajax.setData(roleList);
        return ajax.toJSONString();
    }

    @RequestMapping("/rolesWithSelected")
    public List<Role> rolesWithSelected(Integer uid){
        return roleService.queryRoleListWithSelected(uid);
    }

    //分配角色
    @RequestMapping("/saveRoleResources")
    public String saveRoleResources(RoleResources roleResources){
    	AjaxResponse ajax = new AjaxResponse(true);
        if(StringUtils.isEmpty(roleResources.getRoleid())){
        	ajax.setCode(20008);
        	ajax.setMessage("保存角色权限失败");
        }
        try {
            roleResourcesService.addRoleResources(roleResources);
            ajax.setCode(1);
        } catch (Exception e) {
        	logger.error("【RoleController.saveRoleResources】 error:" + e.getMessage());
        	ajax.setCode(20008);
        	ajax.setMessage("保存角色权限失败");
        }
        return ajax.toJSONString();
    }

    @RequestMapping(value = "/add")
    public String add(Role role) {
    	AjaxResponse ajax = new AjaxResponse(true);
        try {
            roleService.save(role);
            ajax.setCode(1);
        } catch (Exception e) {
        	logger.error("【RoleController.add】 error:" + e.getMessage());
        	ajax.setCode(20009);
        	ajax.setMessage("添加角色失败");
        }
        return ajax.toJSONString();
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id){
    	AjaxResponse ajax = new AjaxResponse(true);
        try{
            roleService.delRole(id);
            ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【RoleController.delete】 error:" + e.getMessage());
        	ajax.setCode(20010);
        	ajax.setMessage("删除角色失败");
        }
        return ajax.toJSONString();
    }



}
