package com.isoftstone.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.isoftstone.commons.Constants;
import com.isoftstone.entity.imports.UserTemp;
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.model.UserRole;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.service.UserRoleService;
import com.isoftstone.service.UserService;
import com.isoftstone.util.DateTimeUtil;
import com.isoftstone.util.ExcelUtil;
import com.isoftstone.util.PasswordHelper;

import tk.mybatis.mapper.util.StringUtil;

/**
 * Created by liup on 2017/9/7.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;

    @RequestMapping
    public Page getAll(User user, String draw, Page page) {
        int start = (page.getPage() - 1) * page.getLimit();
        PageInfo<User> pageInfo = userService.selectByPage(user, start, page.getLimit());
        page.setData(pageInfo.getList());
        page.setCount(pageInfo.getTotal());
        return page;
    }

    /**
     * 保存用户角色
     * 
     * @param userRole
     *            用户角色
     *            此处获取的参数的角色id是以 “,” 分隔的字符串
     * @return
     */
    @RequestMapping("/saveUserRoles")
    public String saveUserRoles(UserRole userRole) {
        if (StringUtils.isEmpty(userRole.getUserid()))
            return "error";
        try {
            userRoleService.addUserRole(userRole);
            return "success";
        }
        catch (Exception e) {
            logger.error("【UserController.saveUserRoles】 error:" + e.getMessage());
            return "fail";
        }
    }

    @RequestMapping(value = "/add")
    public String add(User user) {
        User u = userService.selectByUsername(user.getUsername());
        if (u != null)
            return "error";
        try {
            user.setEnable(1);
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user);
            userService.save(user);
            return "success";
        }
        catch (Exception e) {
            logger.error("【UserController.add】 error:" + e.getMessage());
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id) {
        try {
            userService.delUser(id);
            return "success";
        }
        catch (Exception e) {
            logger.error("【UserController.delete】 error:" + e.getMessage());
            return "fail";
        }
    }

    /*******************************************************/
    /**
     * 方法描述：用户信息分页查询
     * <p>
     * POST / user/findPage
     *
     * @author jnjua
     * @param user
     *            用户信息模型
     * @param page
     *            分页模型
     * @return 用户信息列表分袂
     * @since 2017年11月07日
     * @see
     */
    @RequestMapping(value = "/findPage")
    public Page findPage(UserPojo user, Page page) {

        int start = (page.getPage() - 1) * page.getLimit();
        PageInfo<UserPojo> pageUsers = userService.findPage(user, start, page.getLimit());
        page.setData(pageUsers.getList());
        page.setCount(pageUsers.getTotal());
        return page;

    }

    /**
     * 删除用户信息
     * <p>
     * POST/user/deleteUser
     *
     * @author jnjua
     * @param user
     *            用户模型
     * @param userSession
     *            会话模型
     * @return 持久化操作码
     * @since 2017年11月08日
     */
    @RequestMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam(value = "ids") String ids) {
    	AjaxResponse ajax = new AjaxResponse(true);
        try {
            String[] id = ids.split(",");
            List<Long> uList = new ArrayList<Long>();
            for (String i : id) {
                uList.add(Long.valueOf(i));
            }
            userService.deleteUser(uList);
            ajax.setCode(1);
        }
        catch (Exception e) {
            logger.error("【UserController.deleteUser】 error:" + e.getMessage());
            ajax.setCode(10001);
            ajax.setMessage("删除用户失败");
        }
        return ajax.toJSONString();
    }

    /**
     * 启用用户
     * <p>
     * POST/user/enableUser
     *
     * @author jnjua
     * @param user
     *            用户模型
     * @param userSession
     *            会话模型
     * @return 持久化操作码
     * @since 2017年11月08日
     */
    @RequestMapping(value = "/enableUser")
    public String enableUser(@RequestParam(value = "ids") String ids) {
    	AjaxResponse ajax = new AjaxResponse(true);
        try {
            String[] id = ids.split(",");
            List<Long> uList = new ArrayList<Long>();
            for (String i : id) {
                uList.add(Long.valueOf(i));
            }
            userService.enableUser(uList);
            ajax.setCode(1);
        }
        catch (Exception e) {
            logger.error("【UserController.enableUser】 error:" + e.getMessage());
            ajax.setCode(10002);
            ajax.setMessage("启用用户失败");
        }
        return ajax.toJSONString();
    }

    /**
     * 禁用用户
     * <p>
     * POST/user/unableUser
     *
     * @author jnjua
     * @param user
     *            用户模型
     * @param userSession
     *            会话模型
     * @return 持久化操作码
     * @since 2017年11月08日
     */
    @RequestMapping(value = "/unableUser")
    public String unableUser(@RequestParam(value = "ids") String ids) {
    	AjaxResponse ajax = new AjaxResponse(true);
        try {
            String[] id = ids.split(",");
            List<Long> uList = new ArrayList<Long>();
            for (String i : id) {
                uList.add(Long.valueOf(i));
            }
            userService.unableUser(uList);
            ajax.setCode(1);
        }
        catch (Exception e) {
            logger.error("【UserController.unableUser】 error:" + e.getMessage());
            ajax.setCode(10003);
            ajax.setMessage("禁用用户失败");
        }
        return ajax.toJSONString();
    }

    /**
     * 方法描述：新增用户信息
     * <p>
     * POST / user / addUser
     *
     * @author jnjua
     * @param user
     *            用户信息模型
     * @param userSession.
     *            用户会话数据
     * @return 持久化操作码
     * @since 2017年11月08日
     */
    @RequestMapping(value = "/addUser")
    public String addUser(User user, String roleId) {
    	AjaxResponse ajax = new AjaxResponse(true);
        User u = userService.selectByUsername(user.getUsername());
        User t = userService.selectByTelphone(user.getTelphone());
        if (u != null ) {
        	ajax.setCode(10004);
        	ajax.setMessage("添加用户失败，用户名已存在");
        }
        else if(t != null){
        	ajax.setCode(10005);
        	ajax.setMessage("添加用户失败，手机号码已存在");
        }
        else {
            try {
                user.setEnable(1);
                user.setStatus(1);
                PasswordHelper passwordHelper = new PasswordHelper();
                passwordHelper.encryptPassword(user);
                userService.addUser(user, roleId);
                ajax.setCode(1);
            }
            catch (Exception e) {
                logger.error("【UserController.addUser】 error:" + e.getMessage());
                ajax.setCode(10006);
            	ajax.setMessage("添加用户失败");
            }
        }
        return ajax.toJSONString();

    }

    /**
     * 方法描述：修改用户信息
     * <p>
     * POST / user/updateUser
     *
     * @author jnjua
     * @param user
     * @param userSession
     * @return
     * @since 2017年11月10日
     * @see
     */
    @RequestMapping(value = "/updateUser")
    public String update(User user, String roleId) {
    	AjaxResponse ajax = new AjaxResponse(true);
        int u = userService.selectByUsername(user);
        int t = userService.selectByTelphone(user);
        if (u != 0) {
        	ajax.setCode(10007);
        	ajax.setMessage("修改用户失败，用户名已存在");
        }
        else if(t != 0){
        	ajax.setCode(10008);
        	ajax.setMessage("修改用户失败，手机号码已存在");
        }
        else {
            try {
                userService.updateUser(user, roleId);
                ajax.setCode(1);
            }
            catch (Exception e) {
                logger.error("【UserController.update】 error:" + e.getMessage());
                ajax.setCode(10009);
            	ajax.setMessage("修改用户失败");
            }
        }
        return ajax.toJSONString();
    }

    /**
     * 导出用户列表
     * 
     * @author llmaoa
     * @return
     * @since 2017年12月18日
     * @see
     */
    @RequestMapping(value = "/exportUsers")
    public String exportUsers(User user, String columns, String columnsName, HttpServletResponse response) {
        List<String> cList = Arrays.asList(columns.split(","));
        List<String> cnList = Arrays.asList(columnsName.split(","));
        List<UserPojo> upList = userService.queryUsers(user);

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("用户信息");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        row1.setHeight((short) 700);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("用户信息列表");

        HSSFCellStyle headerStyle = (HSSFCellStyle) wb.createCellStyle();// 创建标题样式  
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中  
        HSSFFont headerFont = (HSSFFont) wb.createFont(); //创建字体样式  
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
        headerFont.setFontName("宋体");  //设置字体类型  
        headerFont.setFontHeightInPoints((short) 12);    //设置字体大小  
        headerStyle.setFont(headerFont);    //为标题样式设置字体样式  
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

        cell.setCellStyle(headerStyle);
        //如果选中了全部
        int index = 0, reduce = 0;
        if (cList.get(0).equals("all")) {
            index = 1;
            reduce = 1;
            //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cList.size() - 2));
        }
        else {
            index = 0;
            reduce = 0;
            //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cList.size() - 1));
        }

        try {
            //在sheet里创建第二行
            HSSFRow rowTitle = sheet.createRow(1);
            rowTitle.setHeight((short) 500);
            //创建单元格并设置单元格内容
            for (int i = index; i < cnList.size(); i++) {
                HSSFCell titleCell = rowTitle.createCell(i - reduce);
                titleCell.setCellValue(cnList.get(i));
                titleCell.setCellStyle(headerStyle);    //单元格引用样式  
                sheet.setColumnWidth((short) i - reduce, (short) 3600);// 设置列宽   
            }

            HSSFCellStyle dataStyle = (HSSFCellStyle) wb.createCellStyle();// 创建标题样式  
            dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    //设置垂直居中  
            dataStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);   //设置水平居中  
            dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
            dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
            dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
            dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
            HSSFFont dataFont = (HSSFFont) wb.createFont(); //创建字体样式  
            dataFont.setFontName("宋体");  //设置字体类型  
            dataFont.setFontHeightInPoints((short) 10);    //设置字体大小  
            dataStyle.setFont(dataFont);    //为标题样式设置字体样式  
            dataStyle.setWrapText(true);
            int num = 2;
            for (UserPojo up : upList) {
                HSSFRow rowData = sheet.createRow(num);
                for (int j = index; j < cList.size(); j++) {
                    String name = cList.get(j);
                    // 将属性的首字符大写，方便构造get，set方法
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method m = up.getClass().getMethod("get" + name);
                    String value = String.valueOf(m.invoke(up));

                    HSSFCell dataCell = rowData.createCell(j - reduce);
                    dataCell.setCellStyle(dataStyle);    //单元格引用样式  
                    if (name.equalsIgnoreCase("enable")) {
                        String str = value.equals("1") ? "启用" : "禁用";
                        dataCell.setCellValue(str);
                    }
                    else {
                        if (StringUtil.isNotEmpty(value) && !"null".equalsIgnoreCase(value)) {
                            dataCell.setCellValue(value);
                        }
                        else {
                            dataCell.setCellValue("");
                        }
                    }
                }
                num++;
            }
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            String fileName = "用户列表" + DateTimeUtil.getTodayChar14() + ".xls";
            response.reset();
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            response.setContentType("application/msexcel");
            wb.write(output);
            output.close();
            return null;
        }
        catch (IOException e) {
            logger.error("【UserController.exportUsers】 error:" + e.getMessage());
        }
        catch (NoSuchMethodException e) {
            logger.error("【UserController.exportUsers】 error:" + e.getMessage());
        }
        catch (SecurityException e) {
            logger.error("【UserController.exportUsers】 error:" + e.getMessage());
        }
        catch (IllegalAccessException e) {
            logger.error("【UserController.exportUsers】 error:" + e.getMessage());
        }
        catch (IllegalArgumentException e) {
            logger.error("【UserController.exportUsers】 error:" + e.getMessage());
        }
        catch (InvocationTargetException e) {
            logger.error("【UserController.exportUsers】 error:" + e.getMessage());
        }
        finally{
        	if(null != wb){
        		try {
					wb.close();
				} catch (IOException e) {
					logger.error("【UserController.exportUsers】 error:" + e.getMessage());
				}
        	}
        }
        return "";
    }

    /**
     * 导出用户列表
     * 
     * @author llmaoa
     * @return
     * @since 2017年12月18日
     * @see
     */
    @RequestMapping(value = "/importUsers")
    public String importUsers(HttpServletRequest request) {
        AjaxResponse ajax = new AjaxResponse(true);
        InputStream is = null;
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> itor = multiRequest.getFileNames();
            while (itor.hasNext()) {
                is = multiRequest.getFile(itor.next()).getInputStream();
            }
            Workbook workbook = WorkbookFactory.create(is);
            List<UserTemp> userList = ExcelUtil.parse(workbook, UserTemp.class);
            String message = "";
            boolean flag = true;
            for (int i = 0; i < userList.size(); i++) {
                String username = userList.get(i).getUsername();
                String telphone = userList.get(i).getTelphone();
                String rolename = userList.get(i).getRolename();
                User u = userService.selectByUsername(username);
                if (null != u) {
                    flag = false;
                    message += "第" + (i + 1) + "行的用户名【" + username + "】已经存在";
                }
                if (!StringUtils.isEmpty(telphone)) {
                    User u1 = userService.selectByTelphone(telphone);
                    if (null != u1) {
                        flag = false;
                        message += ",第" + (i + 1) + "行的手机号【" + telphone + "】已经存在";
                    }
                }
                if(!StringUtils.isEmpty(rolename)){
                	Long role = userService.selectRoleByRolename(rolename);
                    if (null == role) {
                        flag = false;
                        message += ",第" + (i + 1) + "行的角色【" + rolename + "】不存在";
                    }
                }
                else{
                	flag = false;
                	message += ",第" + (i + 1) + "行的角色【" + rolename + "】未填写";
                }
                if (flag) {
                    String password = userList.get(i).getPassword();
                    User up = new User();
                    up.setUsername(username);
                    up.setPassword(password);
                    PasswordHelper passwordHelper = new PasswordHelper();
                    passwordHelper.encryptPassword(up);
                    userList.get(i).setPassword(up.getPassword());
                }
                else {
                    message += "\n";
                }
            }
            if (StringUtils.isEmpty(message)) {
                int count = userService.importUsers(userList);
                ajax.setMessage("成功导入" + count + "条数据！");
            }
            else {
                ajax.setCode(10010);
                ajax.setMessage(message.substring(1));
            }
        }
        catch (IOException e) {
            IOUtils.closeQuietly(is);
            logger.error("【UserController.importUsers】 error:" + e.getMessage());
            ajax.setCode(10010);
            ajax.setMessage("导入用户失败");
        }
        catch (EncryptedDocumentException e) {
            logger.error("【UserController.importUsers】 error:" + e.getMessage());
            ajax.setCode(10010);
            ajax.setMessage("导入用户失败");
        }
        catch (InvalidFormatException e) {
            logger.error("【UserController.importUsers】 error:" + e.getMessage());
            ajax.setCode(10010);
            ajax.setMessage("导入用户失败");
        }
        catch (ReflectiveOperationException e) {
            logger.error("【UserController.importUsers】 error:" + e.getMessage());
            ajax.setCode(10010);
            ajax.setMessage("导入用户失败");
        }
       catch (Exception e) {
    	   logger.error("【UserController.importUsers】 error:" + e.getMessage());
           ajax.setCode(10010);
           ajax.setMessage("导入用户失败");
	}
        return ajax.toJSONString();
    }

    /**
     * 删除用户信息
     * <p>
     * POST/user/deleteUser
     *
     * @author jnjua
     * @param user
     *            用户模型
     * @param userSession
     *            会话模型
     * @return 持久化操作码
     * @since 2017年11月08日
     */
    @RequestMapping(value = "/updateManageArea")
    public String updateManageArea(User user) {
    	AjaxResponse ajax = new AjaxResponse(true);
        try {
            userService.updateManageArea(user);
            ajax.setCode(1);
        }
        catch (Exception e) {
            logger.error("【UserController.updateManageArea】 error:" + e.getMessage());
            ajax.setCode(10011);
            ajax.setMessage("修改用户管理区域失败");
        }
        return ajax.toJSONString();
    }

    /**
     * 保存用户信息
     * 
     * @author llmaoa
     * @param user
     * @return
     * @since 2018年1月9日
     * @see
     */
    @RequestMapping(value = "/saveUserInfo")
    public String saveUserInfo(User user) {
        AjaxResponse ajax = new AjaxResponse(true);
        //获取登录用户
        User us = (User) SecurityUtils.getSubject().getPrincipal();
        user.setId(us.getId());
        int r = userService.saveUserInfo(user);
        if (r <= 0) {
            ajax.setCode(10012);
            ajax.setMessage("用户信息更新失败");
        }
        else {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            UserPojo up = (UserPojo) session.getAttribute(Constants.Commons.SESSION_USER);
            if (StringUtil.isNotEmpty(user.getRealname())) {
                up.setRealname(user.getRealname());
            }
            if (null != user.getSex()) {
                up.setSex(user.getSex());
            }
            if (StringUtil.isNotEmpty(user.getPicPath())) {
                up.setPicPath(user.getPicPath());
            }
            session.setAttribute(Constants.Commons.SESSION_USER, up);
        }
        return ajax.toJSONString();
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public String updatePassword(UserPojo user) {
        AjaxResponse ajax = new AjaxResponse(true);
        try {
            UserPojo userPojo = userService.getUser(user.getId());
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptOldPassword(user);
            if (!user.getOldPassword().equals(userPojo.getPassword())) {
                ajax.setCode(10013);
                ajax.setMessage("旧密码错误");
            }
            else {
                User newUser = new User();
                newUser.setId(user.getId());
                newUser.setUsername(user.getUsername());
                newUser.setPassword(user.getPassword());
                userService.updatePwd(newUser);
                ajax.setCode(1);
                ajax.setMessage("用户密码修改成功");
            }
        }
        catch (Exception e) {
            ajax.setCode(10014);
            ajax.setMessage("用户密码修改失败");
            logger.error("【UserController.updatePassword】 error:" + e.getMessage());
        }
        return ajax.toJSONString();
    }
    
    @RequestMapping(value = "/setPassword", method = RequestMethod.POST)
    public String setPassword(UserPojo user) {
        AjaxResponse ajax = new AjaxResponse(true);
        try {
                User newUser = new User();
                newUser.setId(user.getId());
                newUser.setUsername(user.getUsername());
                newUser.setPassword("123456");
                userService.updatePwd(newUser);
                ajax.setCode(1);
                ajax.setMessage("用户密码重置成功");
        }
        catch (Exception e) {
            ajax.setCode(10014);
            ajax.setMessage("用户密码重置失败");
            logger.error("【UserController.setPassword】 error:" + e.getMessage());
        }
        return ajax.toJSONString();
    }
    
    @RequestMapping(value = "/updateStatus")
    public String updateStatus(User user) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	
    	int count = userService.selectUserByTel(user);
    	if(count > 0 ){
    		userService.updateStatus(user);
    		ajax.setCode(1);
            ajax.setMessage("用户恢复成功");
    	}
    	else{
    		ajax.setCode(10015);
            ajax.setMessage("未找到手机号码为：" + user.getTelphone() + "的删除用户");
    	}
        return ajax.toJSONString();
    }
}
