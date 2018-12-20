package com.isoftstone.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.Insect;
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.service.InsectService;
import com.isoftstone.service.UserRoleService;


/**
 * Created by jnjua on 2017/11/14.
 */
@RestController
@RequestMapping("/insectPage")
public class InsectController {
	protected static final Logger logger = LoggerFactory.getLogger(InsectController.class);
    @Resource
    private InsectService insectService;

    @Resource
    private UserRoleService userRoleService;

    /**
     * 方法描述：虫类信息分页查询
     * <p>
     * POST / insect/findPage
     *
     * @author jnjua
     * @param insect
     *            虫类信息模型
     * @param page
     *            分页模型
     * @return 虫类信息列表分袂
     * @since 2017年11月14日
     * @see
     */
    @RequestMapping(value = "/findPage")
    public Page findPage(Insect insect, Page page) {

    	int start =  (page.getPage()-1) * page.getLimit();
    	PageInfo<Insect> pageUsers = insectService.findPage(insect, start, page.getLimit());
    	page.setData(pageUsers.getList());
    	page.setCount(pageUsers.getTotal());
        return page;

    }
    
    /**
     * 方法描述：新增虫类信息
     * <p>
     * POST /insect/addInsect
     *
     * @author jnjua
     * @param insect
     *            虫类信息模型
     * @param userSession.
     *            用户会话数据
     * @return 持久化操作码
     * @since 2017年11月14日
     */
    @RequestMapping(value = "/addInsect")
    public String addInsect(Insect insect) {

	    try {
	    	User user = (User) SecurityUtils.getSubject().getPrincipal();
	    	insect.setCreater(user.getId());
	    	insectService.addInsect(insect);
	        return "success";
	    	} catch (Exception e) {
	    		logger.error("【InsectController.addInsect】 error:" + e.getMessage());
	    		return "fail";
	    	}
	    
	}
    
    /**
     * 方法描述：修改虫类信息
     * <p>
     * POST /insect/updateInsect
     *
     * @author jnjua
     * @param insect
     * @param userSession
     * @return
     * @since 2017年11月14日
     * @see
     */
    @RequestMapping(value = "/updateInsect")
    public String updateInsect(Insect insect) {
    	try {
    		User user = (User) SecurityUtils.getSubject().getPrincipal();
    		insect.setCreater(user.getId());
    		insectService.updateInsect(insect);
	        return "success";
	    	} catch (Exception e) {
	    		logger.error("【InsectController.updateInsect】 error:" + e.getMessage());
	    		return "fail";
	    	}
	    
    }


	/**
	 * 查询所有虫类信息
	 */
	@RequestMapping("/getAllInsect")
    public String getAllInsect(){
		AjaxResponse ajax = new AjaxResponse(true);
        List<Insect> list = insectService.getAllInsect();
        ajax.setData(list);
        return ajax.toJSONString();
    }
}
