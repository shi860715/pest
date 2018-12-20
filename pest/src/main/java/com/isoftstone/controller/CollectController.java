package com.isoftstone.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.isoftstone.commons.Constants;
import com.isoftstone.entity.imports.CollectTemp;
import com.isoftstone.entity.model.Collect;
import com.isoftstone.entity.model.Device;
import com.isoftstone.entity.model.Insect;
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.model.User;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.UserPojo;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.CollectService;
import com.isoftstone.service.DeviceService;
import com.isoftstone.service.InsectService;
import com.isoftstone.util.DateTimeUtil;
import com.isoftstone.util.ExcelUtil;

import tk.mybatis.mapper.util.StringUtil;

/**
 * Created by jnjua on 2017/11/24.
 */
@RestController
@RequestMapping("/collectPage")
public class CollectController {
    protected static final Logger logger = LoggerFactory.getLogger(CollectController.class);
    @Resource
    private CollectService collectService;
    
    @Resource
    private AreaService areaService;

    @Resource
    private DeviceService deviceService;
    
    @Resource
    private InsectService insectService;
    
    /**
     * 方法描述：自动虫情信息分页查询
     * <p>
     * POST / collect/findPage
     *
     * @author jnjua
     * @param collect
     *            自动虫情信息模型
     * @param page
     *            分页模型
     * @return 自动虫情信息列表分袂
     * @since 2017年11月24日
     * @see
     */
    @RequestMapping(value = "/findPage")
    public Page findPage(Collect collect, Page page) {

    	int start =  (page.getPage()-1) * page.getLimit();
    	
    	UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	PageInfo<Collect> pageUsers = collectService.findPage(collect, roleId, areaList, start, page.getLimit());
    	page.setData(pageUsers.getList());
    	page.setCount(pageUsers.getTotal());
        return page;

    }
    
    /**
     * 删除自动虫情信息
     * <p>
     * POST /collect/delCollect
     *
     * @author jnjua
     * @param collect
     *            自动虫情模型
     * @param userSession
     *            会话模型
     * @return 持久化操作码
     * @since 2017年11月24日
     */
    @RequestMapping(value = "/delCollect")
    public String delCollect(@RequestParam(value = "ids") String ids) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	try{
    		String[] id = ids.split(",");
    		List<Long> uList = new ArrayList<Long>();
    		for(String i : id){
    			uList.add(Long.valueOf(i));
            }
    		collectService.delCollect(uList);
    		ajax.setCode(1);
        }catch (Exception e){
            logger.error("【CollectController.delCollect】 error:" + e.getMessage());
            ajax.setCode(30001);
        	ajax.setMessage("删除虫情失败");
        }
    	return ajax.toJSONString();
    }
    
    /**
     * 方法描述：新增自动虫情信息
     * <p>
     * POST /collect/addCollect
     *
     * @author jnjua
     * @param collect
     *            自动虫情信息模型
     * @param userSession.
     *            用户会话数据
     * @return 持久化操作码
     * @since 2017年11月24日
     */
    @RequestMapping(value = "/addCollect")
    public String addCollect(Collect collect) {
    	AjaxResponse ajax = new AjaxResponse(true);
	    try {
	    	if(collect.getInsectId() == -1){
	    		Insect insect = new Insect();
	    		User user = (User) SecurityUtils.getSubject().getPrincipal();
		    	insect.setCreater(user.getId());
		    	insect.setName(collect.getInsectName());
		    	insectService.addInsect(insect);
		    	collect.setInsectId(insect.getId());
		    	collectService.addCollect(collect);	
	    	}else{
	    		if(collectService.findOne(collect)!= 0){
		    		collectService.updateCollectById(collect);
		    	}else{
		    		collectService.addCollect(collect);
		    	}
		    	ajax.setCode(1);
	    	}
	    	
	    	} catch (Exception e) {
	            logger.error("【CollectController.addCollect】 error:" + e.getMessage());
	            ajax.setCode(30002);
	        	ajax.setMessage("添加虫情失败");
	    	}
	    return ajax.toJSONString();
	}
    
    /**
     * 方法描述：修改自动虫情信息
     * <p>
     * POST /collect/updateCollect
     *
     * @author jnjua
     * @param collect
     * @param userSession
     * @return
     * @since 2017年11月24日
     * @see
     */
    @RequestMapping(value = "/updateCollect")
    public String updateCollect(Collect collect) {
    	AjaxResponse ajax = new AjaxResponse(true);
    	try {
    		if(collect.getInsectId() == -1){
	    		Insect insect = new Insect();
	    		User user = (User) SecurityUtils.getSubject().getPrincipal();
		    	insect.setCreater(user.getId());
		    	insect.setName(collect.getInsectName());
		    	insectService.addInsect(insect);
		    	collect.setInsectId(insect.getId());
		    	collectService.updateCollect(collect);	
	    	}else{
	    		collectService.updateCollect(collect);
			    ajax.setCode(1);
	    	}  
    	} catch (Exception e) {
            logger.error("【CollectController.updateCollect】 error:" + e.getMessage());
            ajax.setCode(30003);
        	ajax.setMessage("修改虫情失败");
    	}
    	return ajax.toJSONString();
    }
    
    /**
     * 导出用户列表
     * @author feilua
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/importCollects")
    public String importCollects(HttpServletRequest request){
        AjaxResponse ajax = new AjaxResponse(true);
        InputStream is = null;
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> itor = multiRequest.getFileNames();
            while (itor.hasNext()) {
                is = multiRequest.getFile(itor.next()).getInputStream();
            }
            Workbook workbook = WorkbookFactory.create(is);
            List<CollectTemp> collectTempList = ExcelUtil.parse(workbook, CollectTemp.class);
            List<Collect> collectList = new ArrayList<Collect>();
            String message = "";
            boolean flag = true;
            for(int i=0; i<collectTempList.size(); i++){
                String deviceCode = collectTempList.get(i).getDeviceCode();
                String deviceName = collectTempList.get(i).getDeviceName();
                Device d = deviceService.selectByCodeAndName(deviceCode,deviceName);
                Insect insect = null;
                if(null == d){
                    flag = false;
                    message += "，第" + (i+1) + "行设备编码【" + deviceCode + "】设备名称【" + deviceName + "】的设备不存在" ;
                }
                else{
                	insect = insectService.selectByName(collectTempList.get(i).getInsectName());
                	if(null == insect){
                		flag = false;
                        message += "，第" + (i+1) + "行昆虫名称【" + deviceCode + "】的昆虫不存在" ;
                	}
                	else{
                		Collect c = new Collect();
                    	c.setDeviceId(d.getId());
                    	c.setDeviceCode(deviceCode);
                    	c.setDeviceName(deviceName);
                    	c.setInsectId(insect.getId());
                    	
                    	c.setCaptureTime(DateTimeUtil.DateToTimestamp(collectTempList.get(i).getCaptureTime()));
                    	c.setFemaleCount(collectTempList.get(i).getFemaleCount());
                    	c.setMaleCount(collectTempList.get(i).getMaleCount());
                    	c.setTotalCount(collectTempList.get(i).getTotalCount());
                    	c.setPictureId(0);
                    	int count = collectService.findOne(c);
                    	if(count != 0){
                    		flag = false;
                            message += "，第" + (i+1) + "行的虫情数据已经存在" ;
                    	}
                    	else{
                    		collectList.add(c);
                    	}
                	}
                }
                if(!flag){
                    message += "\n";
                }
            }
            if(StringUtils.isEmpty(message)){
                int count = collectService.importCollects(collectList);
                ajax.setMessage("成功导入"+count+"条数据！");
            }else{
            	ajax.setCode(30004);
                ajax.setMessage(message.substring(1));
            }
        }
        catch (IOException e) {
            IOUtils.closeQuietly(is);
            logger.error("【CollectController.importCollects】 error:" + e.getMessage());
            ajax.setCode(30004);
            ajax.setMessage("导入错误，请确认模板是否正确");
        }
        catch (EncryptedDocumentException e) {
            logger.error("【CollectController.importCollects】 error:" + e.getMessage());
            ajax.setCode(30004);
            ajax.setMessage("导入错误，请确认模板是否正确");
        }
        catch (InvalidFormatException e) {
            logger.error("【CollectController.importCollects】 error:" + e.getMessage());
            ajax.setCode(30004);
            ajax.setMessage("导入错误，请确认模板是否正确");
        }
        catch (ReflectiveOperationException e) {
            logger.error("【CollectController.importCollects】 error:" + e.getMessage());
            ajax.setCode(30004);
            ajax.setMessage("导入错误，请确认模板是否正确");
        }
        return ajax.toJSONString();
    }

    /**
     * 导出虫情列表
     * @author feilua
     * @return
     * @since 2017年12月21日
     * @see 
     */
    @RequestMapping(value = "/exportCollects")
    public String exportCollects(Collect collect, String startTime, String endTime, String columns, String columnsName,  HttpServletResponse response){
        List<String> cList = Arrays.asList(columns.split(","));
        List<String> cnList = Arrays.asList(columnsName.split(","));
        
        UserPojo user = (UserPojo)SecurityUtils.getSubject().getSession().getAttribute(Constants.Commons.SESSION_USER);
    	Integer roleId = user.getRoleId();
    	List<Long> areaList = new ArrayList<Long>();
    	if(roleId != 1){
    		Long manageArea = user.getManageArea();
    		areaList = areaService.queryChildrenIds(manageArea);
    	}
    	if(null != startTime && !"".equals(startTime)){
    		
    		collect.setBeforeTime(Timestamp.valueOf(startTime));
    	}
    	if(null != startTime && !"".equals(endTime)){
    		
    		collect.setAfterTime(Timestamp.valueOf(endTime));
    	}
        List<Collect> collectList = collectService.queryCollects(collect, roleId, areaList);
        
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("虫情数据");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        row1.setHeight((short)700);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("虫情数据列表");
        
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
        int index = 0, reduce = 0;
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,cList.size()-1));
        
        try {
            //在sheet里创建第二行
            HSSFRow rowTitle = sheet.createRow(1);    
            rowTitle.setHeight((short)500);
            //创建单元格并设置单元格内容
            for(int i=index; i<cnList.size(); i++){
                HSSFCell titleCell = rowTitle.createCell(i-reduce);
                titleCell.setCellValue(cnList.get(i));
                titleCell.setCellStyle(headerStyle);    //单元格引用样式  
                sheet.setColumnWidth((short) i-reduce, (short) 3600);// 设置列宽   
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
            for(Collect c : collectList){
                HSSFRow rowData = sheet.createRow(num);
                for(int j=index; j<cList.size(); j++){
                    String name = cList.get(j);
                    // 将属性的首字符大写，方便构造get，set方法
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method m = c.getClass().getMethod("get" + name);
                    String value = String.valueOf(m.invoke(c));;
                    if("captureTime".equalsIgnoreCase(name)){
                    	value = value.substring(0, value.length()-2);
                    }
                    
                    HSSFCell dataCell = rowData.createCell(j-reduce);
                    dataCell.setCellStyle(dataStyle);    //单元格引用样式  
                    if(StringUtil.isNotEmpty(value) && !"null".equalsIgnoreCase(value)){
                        dataCell.setCellValue(value);
                    }else{
                        dataCell.setCellValue("");
                    }
                }
                num++;
            }
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            String fileName = "虫情数据列表"+DateTimeUtil.getTodayChar14()+".xls";
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" )); 
            response.setContentType("application/msexcel");        
            wb.write(output);
            output.close();
            return null;
        }
        catch (IOException e) {
            logger.error("【CollectController.exportCollects】 error:" + e.getMessage());
        }
        catch (NoSuchMethodException e) {
            logger.error("【CollectController.exportCollects】 error:" + e.getMessage());
        }
        catch (SecurityException e) {
            logger.error("【CollectController.exportCollects】 error:" + e.getMessage());
        }
        catch (IllegalAccessException e) {
            logger.error("【CollectController.exportCollects】 error:" + e.getMessage());
        }
        catch (IllegalArgumentException e) {
            logger.error("【CollectController.exportCollects】 error:" + e.getMessage());
        }
        catch (InvocationTargetException e) {
        	logger.error("【CollectController.exportCollects】 error:" + e.getMessage());
        }
        finally{
        	if(null != wb){
        		try {
					wb.close();
				} catch (IOException e) {
					logger.error("【CollectController.exportCollects】 error:" + e.getMessage());
				}
        	}
        }
        return "";
    }
    
}
