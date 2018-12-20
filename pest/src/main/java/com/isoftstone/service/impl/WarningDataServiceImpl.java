package com.isoftstone.service.impl;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.commons.EmailSupport;
import com.isoftstone.entity.model.ContactInfo;
import com.isoftstone.entity.model.Dictionary;
import com.isoftstone.entity.model.WarningData;
import com.isoftstone.entity.pojo.WarningDataPojo;
import com.isoftstone.mapper.main.AreaMapper;
import com.isoftstone.mapper.main.DictionaryMapper;
import com.isoftstone.mapper.main.WarningDataMapper;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.WarningDataService;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 预警数据service实现类
 * @author lufei
 *
 */
@Service("warningDataService")
public class WarningDataServiceImpl extends BaseService<WarningData> implements WarningDataService {
    /**
     * 日志系统
     */
    private Log logger = LogFactory.getLog(WarningDataServiceImpl.class);
	
	/**
	 * 预警数据mapper
	 */
	@Resource
	private WarningDataMapper  warningDataMapper;
	
	/**
	 * 地区Mapper
	 */
    @Resource
    private AreaMapper areaMapper;

    /**
     * 字典表Mapper
     */
    @Resource
    private DictionaryMapper dictionaryMapper;
    /**
     * 邮件发送类
     */
    @Resource
    private EmailSupport emailSupport;

    @Resource
    private AreaService areaService;
    
    /**
     * 定时扫描数据库，更行预警数据
     * @author llmaoa
     * @since 2017年11月30日
     * @see 
     */
    public void taskWarningData(){
        warningDataMapper.taskWarningData();
    }
    
    public void taskSendWarningMsg(){
        List<WarningData> wdList =  warningDataMapper.queryWaringData();

//        Dictionary smsTemp = dictionaryMapper.getByKey("warning_sms_temp");
//        String now = DateTimeUtil.getTodayChar8ByFormat("yyyy-MM-dd");
        for(WarningData wd : wdList){
            List<ContactInfo> ciList = areaMapper.queryContact(Long.valueOf(wd.getAreaId()));
            //调用短信和邮件发送接口
            for (ContactInfo ci : ciList) {
            	logger.info("消息内容：姓名：" + ci.getName() + "，手机号：" + ci.getTelephone() + "，邮箱地址："
                        + ci.getEmail() + ";" + wd.getAddress());
                // 发送消息邮件
                Dictionary emailTemp = dictionaryMapper.getByKey("expire_mail_temp");
                try {
                    // 获取邮箱信息模板内容
                    String title = "设备到期提醒";
                    String content = emailTemp.getValue();
                    // 内容变量替换
                    Map<String, String> map = new HashMap<>();
                    map.put("ADDRESS", wd.getAddress());

                    StringTemplateLoader stl = new StringTemplateLoader();
                    stl.putTemplate("content", content);

                    Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
                    cfg.setTemplateLoader(stl);

                    Template template = cfg.getTemplate("content");

                    StringWriter writer = new StringWriter();
                    template.process(map, writer);
                    content = writer.toString();

                    emailSupport.send(title, content, ci.getEmail());

                }
                catch (Exception e) {
                    logger.error("WarningDataServiceImpl taskSendWarningMsg error.", e);
                }
            }
        }
    }
    /**
     * 根据区域获取预警数据
     * @author llmaoa
     * @param key
     * @return
     * @since 2017年11月29日
     * @see 
     */
    public PageInfo<WarningDataPojo> getByArea(Long area){
        List<Long> idList = areaService.queryChildrenIds(area);
        PageHelper.startPage(1, 4);
        List<WarningDataPojo> wdList = warningDataMapper.getByArea(idList);
        return new PageInfo<>(wdList);
    }
    
    /**
     * 分页查询预警数据
     * @author llmaoa
     * @param warningData
     * @param start
     * @param length
     * @return
     * @since 2017年11月30日
     * @see 
     */
    @Override
    public PageInfo<WarningDataPojo> findPage(WarningData warningData, int start, int length) {
        int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        List<WarningDataPojo> wdList = warningDataMapper.findPage(warningData);
        return new PageInfo<WarningDataPojo>(wdList);
    }
    
    /**
     * 根据ID集合批量删除预警数据
     * @author llmaoa
     * @param ids
     * @return
     * @since 2017年12月1日
     * @see 
     */
    @Override
    public int delWarningDatas(String ids){
        String[] id = ids.split(",");
        List<Long> idList = new ArrayList<Long>();
        for(String i : id){
            idList.add(Long.valueOf(i));
        }
        int count = warningDataMapper.delWarningDatas(idList);
        return count;
    }
    
    /**
     * 发送预警邮件给联系人
     * @author llmaoa
     * @param id
     * @return
     * @since 2017年12月1日
     * @see 
     */
    public boolean sendEmail(Long id){
        WarningDataPojo wd = warningDataMapper.selectById(id);
        List<ContactInfo> ciList = areaMapper.queryContact(Long.valueOf(wd.getAreaId()));
        //调用短信和邮件发送接口
        for (ContactInfo ci : ciList) {
        	logger.info("消息内容：姓名：" + ci.getName() + "，手机号：" + ci.getTelephone() + "，邮箱地址："
                    + ci.getEmail() + ";" + wd.getAddress());
            // 发送消息邮件
            Dictionary emailTemp = dictionaryMapper.getByKey("warning_mail_temp");
            try {
                // 获取邮箱信息模板内容
                String title = "防治预警通知";
                String content = emailTemp.getValue();
                String captureTime = DateFormatUtils.format(wd.getCaptureTime(), "yyyy-MM-dd HH:mm:ss");
                // 内容变量替换
                Map<String, String> map = new HashMap<>();
                map.put("ADDRESS", wd.getAddress());
                map.put("CAPTURETIME", captureTime);
                map.put("INSECT", wd.getInsectName());

                StringTemplateLoader stl = new StringTemplateLoader();
                stl.putTemplate("content", content);

                Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
                cfg.setTemplateLoader(stl);

                Template template = cfg.getTemplate("content");

                StringWriter writer = new StringWriter();
                template.process(map, writer);
                content = writer.toString();

                emailSupport.send(title, content, ci.getEmail());

            }
            catch (Exception e) {
                logger.error("WarningDataServiceImpl sendEmail error.", e);
                return false;
            }
        }
        return true;
    }
    
    /**
     * 发送预警短信给联系人
     * @author llmaoa
     * @param id
     * @return
     * @since 2017年12月1日
     * @see 
     */
    public boolean sendSms(Long id){
        WarningDataPojo wd = warningDataMapper.selectById(id);
        List<ContactInfo> ciList = areaMapper.queryContact(Long.valueOf(wd.getAreaId()));
        //调用短信和邮件发送接口
        for (ContactInfo ci : ciList) {
            String captureTime = DateFormatUtils.format(wd.getCaptureTime(), "yyyy-MM-dd HH:mm:ss");
            logger.info("消息内容：姓名：" + ci.getName() + "，手机号：" + ci.getTelephone() + "，邮箱地址："
                    + ci.getEmail() + ";" + wd.getAddress()+";"+captureTime+";"+wd.getInsectName());
            // 发送消息邮件
//            Dictionary emailTemp = dictionaryMapper.getByKey("warning_sms_temp");
        }
        return true;
    }
    
    /**
     * 方法描述：查询虫情告警数据
     *
     * @author jnjua
     * @param 
     * @return 虫情告警数据
     * @since 2018年01月10日
     */
    @Override
    public List<WarningDataPojo> getWarningData(Integer roleId,List<Long> areaList) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("roleId", roleId);
        paramMap.put("areaList", areaList);
        List<WarningDataPojo> count = warningDataMapper.getWarningData(paramMap);
        return count;
    }
}
