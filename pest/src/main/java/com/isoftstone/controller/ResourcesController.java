package com.isoftstone.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.model.Resources;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.RawFilePojo;
import com.isoftstone.service.ResourcesService;
import com.isoftstone.shiro.ShiroService;
import com.isoftstone.util.FileUtil;

/**
 * Created by liup on 2017/9/8.
 */
@RestController
@RequestMapping("/resources")
public class ResourcesController {
	protected static final Logger logger = LoggerFactory.getLogger(PlantPicController.class);
    @Resource
    private ResourcesService resourcesService;
    
    @Resource
    private ShiroService shiroService;

    //获取配置文件中图片的路径
   @Value("${upload.uploadPath}")
   private String uploadPath;
    
    /**
     * 分页获取菜单信息列表
     * @author llmaoa
     * @param resources
     * @param draw
     * @param page
     * @return
     * @since 2017年11月7日
     * @see 
     */
    @RequestMapping
    public Page getAll(Resources resources, Page page){
        int start =  (page.getPage()-1) * page.getLimit();
        PageInfo<Resources> pageInfo = resourcesService.selectByPage(resources, start, page.getLimit());
        page.setData(pageInfo.getList());
        page.setCount(pageInfo.getTotal());
        return page;
    }

    @RequestMapping("/queryAll")
    public List<Resources> queryAll(){
        return resourcesService.queryAll();
    }
    
    @RequestMapping("/resourcesWithSelected")
    public List<Resources> resourcesWithSelected(Integer rid){
        return resourcesService.queryResourcesListWithSelected(rid);
    }

    @RequestMapping("/loadMenu")
    public List<Resources> loadMenu(){
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("type",1);
        map.put("userid",user.getId());
        List<Resources> resourcesList = resourcesService.loadUserResources(map);
        return resourcesList;
    }

    @CacheEvict(cacheNames="resources", allEntries=true)
    @RequestMapping(value = "/add")
    public String add(Resources resources){
    	AjaxResponse ajax = new AjaxResponse(true);
        try{
            resourcesService.save(resources);
            //更新权限
            shiroService.updatePermission();
            ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【ResourcesController.add】 error:" + e.getMessage());
        	ajax.setCode(20001);
            ajax.setMessage("新增菜单失败");
        }
        return ajax.toJSONString();
    }
    
    @CacheEvict(cacheNames="resources", allEntries=true)
    @RequestMapping(value = "/edit")
    public String edit(Resources resources){
    	AjaxResponse ajax = new AjaxResponse(true);
        try{
            resourcesService.updateNotNull(resources);
            //更新权限
            shiroService.updatePermission();
            ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【ResourcesController.edit】 error:" + e.getMessage());
        	ajax.setCode(20002);
            ajax.setMessage("修改菜单失败");
        }
        return ajax.toJSONString();
    }
    
    @CacheEvict(cacheNames="resources", allEntries=true)
    @RequestMapping(value = "/delete")
    public String delete(String ids){
    	AjaxResponse ajax = new AjaxResponse(true);
        try{
            resourcesService.delResources(ids);
            //更新权限
            shiroService.updatePermission();
            ajax.setCode(1);
        }catch (Exception e){
        	logger.error("【ResourcesController.delete】 error:" + e.getMessage());
        	ajax.setCode(20003);
            ajax.setMessage("删除菜单失败");
        }
        return ajax.toJSONString();
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(HttpServletRequest request, String path){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, path);
            
            if (CollectionUtils.isNotEmpty(rfList)) {
                ajax.setData(rfList.get(0));
            }
        }
        catch (Exception e) {
        	logger.error("【ResourcesController.upload】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
    
    @RequestMapping(value = "/uploadMore")
    @ResponseBody
    public String uploadMore(HttpServletRequest request, String path){
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, path);
            ajax.setData(rfList);
        }
        catch (Exception e) {
        	logger.error("【ResourcesController.uploadMore】 error:" + e.getMessage());
            ajax.toError();
        }
        return ajax.toJSONString();
    }
}
