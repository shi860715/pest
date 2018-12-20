package com.isoftstone.service.impl;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.commons.EmailSupport;
import com.isoftstone.entity.model.ContactInfo;
import com.isoftstone.entity.model.DevParameter;
import com.isoftstone.entity.model.Device;
import com.isoftstone.entity.model.DeviceExtends;
import com.isoftstone.entity.model.Dictionary;
import com.isoftstone.entity.pojo.DevParameterPojo;
import com.isoftstone.entity.pojo.DevicePojo;
import com.isoftstone.mapper.main.AreaMapper;
import com.isoftstone.mapper.main.DeviceExtendsMapper;
import com.isoftstone.mapper.main.DictionaryMapper;
import com.isoftstone.mapper.slave.DeviceMapper;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.DeviceService;
import com.isoftstone.util.DateTimeUtil;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Created by yangqj on 2017/4/21.
 */
@Service("deviceService")
public class DeviceServiceImpl extends BaseService<Device> implements DeviceService {
    /**
     * 日志系统
     */
    private Log logger = LogFactory.getLog(DeviceServiceImpl.class);

    @Resource
    private DeviceMapper deviceMapper;

    @Resource
    private DeviceExtendsMapper deviceExtendsMapper;

    @Resource
    private AreaMapper areaMapper;

    @Resource
    private DictionaryMapper dictionaryMapper;

    @Resource
    private EmailSupport emailSupport;
    
    @Resource
    private AreaService areaService;

    /**
     * 方法描述：分页查询设备信息列表(先从设备表查数据)
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 设备信息列表
     * @since 2017年11月13日
     */
    @Override
    public PageInfo<DevicePojo> findPageOne(Device device, int start, int length) {
        int page = start / length + 1;
        //分页查询
        PageHelper.startPage(page, length);
        List<Device> deviceList = deviceMapper.findPageOne(device);

        PageInfo<Device> pageDevices = new PageInfo<Device>(deviceList);
        List<DeviceExtends> deviceExtendsList = new ArrayList<DeviceExtends>();
        if (deviceList.size() > 0) {
            deviceExtendsList = deviceExtendsMapper.findPageOne(deviceList);
        }
        List<DevicePojo> devicePojoList = new ArrayList<DevicePojo>();
        Map<Long, DeviceExtends> map = new HashMap<Long, DeviceExtends>();
        for (DeviceExtends de : deviceExtendsList) {
            map.put(de.getId(), de);
        }
        //从两个库中取出的对象根据id关联组装pojo对象
        for (Device d : deviceList) {
            DevicePojo dp = new DevicePojo();
            dp.setId(d.getId());
            dp.setDevCode(d.getDevCode());
            dp.setDevName(d.getDevName());
            dp.setDevMac(d.getDevMac());
            dp.setDevLon(d.getDevLon());
            dp.setDevLat(d.getDevLat());
            dp.setAltitude(d.getAltitude());
            dp.setConstituent(d.getConstituent());
            dp.setGroupName(d.getGroupName());
            dp.setAreaCode(d.getAreaCode());
            dp.setDevAddr(d.getDevAddr());
            dp.setDevStatus(d.getDevStatus());
            dp.setFactoryCode(d.getFactoryCode());
            dp.setCreateUserid(d.getCreateUserid());
            if (null != d.getCreateTime()) {
                dp.setCreateTime(d.getCreateTime() * 1000);
            }
            dp.setUpdateUserid(d.getUpdateUserid());
            if (null != d.getUpdateTime()) {
                dp.setUpdateTime(d.getUpdateTime() * 1000);
            }
            dp.setSiteCode(d.getSiteCode());
            dp.setDevType(d.getDevType());
            dp.setDevModel(d.getDevModel());
            dp.setSendPeriod(d.getSendPeriod());
            if (null != d.getInstallTime()) {
                dp.setInstallTime(d.getInstallTime() * 1000);
            }
            dp.setIsRemove(d.getIsRemove());
            dp.setIsDelete(d.getIsDelete());
            dp.setSync(d.getSync());
            DeviceExtends deviceExtends = map.get(d.getId());
            if (null != deviceExtends) {
                dp.setProvince(deviceExtends.getProvince());
                dp.setCity(deviceExtends.getCity());
                dp.setDistrict(deviceExtends.getDistrict());
                dp.setBelongArea(deviceExtends.getBelongArea());
                dp.setAreaName(deviceExtends.getAreaName());
                if (null != deviceExtends.getBuyTime()) {
                    dp.setBuyTime(deviceExtends.getBuyTime() * 1000);
                }
                dp.setBuyType(deviceExtends.getBuyType());
                if (null != deviceExtends.getExpireTime()) {
                    dp.setExpireTime(deviceExtends.getExpireTime() * 1000);
                }
                dp.setOwner(deviceExtends.getOwner());
                dp.setUser(deviceExtends.getUser());
            }
            devicePojoList.add(dp);
        }

        PageInfo<DevicePojo> pageDevicePojos = new PageInfo<DevicePojo>(devicePojoList);
        pageDevicePojos.setTotal(pageDevices.getTotal());
        return pageDevicePojos;
    }
    
    /**
     * 方法描述：分页查询设备信息列表(先从设备扩展表查数据)
     *
     * @author jnjua
     * @param page
     *            分页模型
     * @return 设备信息列表
     * @since 2017年11月13日
     */
    @Override
    public PageInfo<DevicePojo> findPage(Device device,Integer roleId,List<Long> areaList, int start, int length) {
        int page = start / length + 1;
        //分页查询
        PageHelper.startPage(page, length);
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("devName", device.getDevName());
        paramMap.put("roleId", roleId);
        paramMap.put("areaList", areaList);
        List<DeviceExtends> deviceExtendsList = deviceExtendsMapper.findPage(paramMap);
        
        PageInfo<DeviceExtends> pageDeviceExtends = new PageInfo<DeviceExtends>(deviceExtendsList);
        
        List<Device> deviceList = new ArrayList<Device>();
        if (deviceExtendsList.size() > 0) {
        	deviceList = deviceMapper.findPage(deviceExtendsList);
        }
        
        List<DevicePojo> devicePojoList = new ArrayList<DevicePojo>();
        Map<Long, DeviceExtends> map = new HashMap<Long, DeviceExtends>();
        for (DeviceExtends de : deviceExtendsList) {
            map.put(de.getId(), de);
        }
        //从两个库中取出的对象根据id关联组装pojo对象
        for (Device d : deviceList) {
            DevicePojo dp = new DevicePojo();
            dp.setId(d.getId());
            dp.setDevCode(d.getDevCode());
            dp.setDevName(d.getDevName());
            dp.setDevMac(d.getDevMac());
            dp.setDevLon(d.getDevLon());
            dp.setDevLat(d.getDevLat());
            dp.setAltitude(d.getAltitude());
            dp.setConstituent(d.getConstituent());
            dp.setGroupName(d.getGroupName());
            dp.setAreaCode(d.getAreaCode());
            dp.setDevAddr(d.getDevAddr());
            dp.setDevStatus(d.getDevStatus());
            dp.setFactoryCode(d.getFactoryCode());
            dp.setCreateUserid(d.getCreateUserid());
            if (null != d.getCreateTime()) {
                dp.setCreateTime(d.getCreateTime() * 1000);
            }
            dp.setUpdateUserid(d.getUpdateUserid());
            if (null != d.getUpdateTime()) {
                dp.setUpdateTime(d.getUpdateTime() * 1000);
            }
            dp.setSiteCode(d.getSiteCode());
            dp.setDevType(d.getDevType());
            dp.setDevModel(d.getDevModel());
            dp.setSendPeriod(d.getSendPeriod());
            if (null != d.getInstallTime()) {
                dp.setInstallTime(d.getInstallTime() * 1000);
            }
            dp.setIsRemove(d.getIsRemove());
            dp.setIsDelete(d.getIsDelete());
            dp.setSync(d.getSync());
            DeviceExtends deviceExtends = map.get(d.getId());
            if (null != deviceExtends) {
            	dp.setImage(deviceExtends.getImage());
                dp.setProvince(deviceExtends.getProvince());
                dp.setCity(deviceExtends.getCity());
                dp.setDistrict(deviceExtends.getDistrict());
                dp.setBelongArea(deviceExtends.getBelongArea());
                dp.setAreaName(deviceExtends.getAreaName());
                if (null != deviceExtends.getBuyTime()) {
                    dp.setBuyTime(deviceExtends.getBuyTime() * 1000);
                }
                dp.setBuyType(deviceExtends.getBuyType());
                if (null != deviceExtends.getExpireTime()) {
                    dp.setExpireTime(deviceExtends.getExpireTime() * 1000);
                }
                dp.setOwner(deviceExtends.getOwner());
                dp.setUser(deviceExtends.getUser());
            }
            devicePojoList.add(dp);
        }

        PageInfo<DevicePojo> pageDevicePojos = new PageInfo<DevicePojo>(devicePojoList);
        pageDevicePojos.setTotal(pageDeviceExtends.getTotal());
        return pageDevicePojos;
    }
    

    /**
     * 方法描述：添加设备信息
     *
     * @author jnjua
     * @param user
     *            设备信息
     * @return 持久化操作码
     * @since 2017年11月13日
     * @see
     */
    public int addDevice(DevicePojo devicePojo) {

        int result = deviceMapper.addDevice(devicePojo);

        result = deviceExtendsMapper.addDevice(devicePojo);

        return result;
    }

    /**
     * 方法描述：更新设备信息
     *
     * @author jnjua
     * @param user
     *            设备信息模型
     * @return 持久化操作码
     * @since 2017年11月13日
     */
    public int updateDevice(DevicePojo devicePojo) {

        int result = deviceMapper.updateDevice(devicePojo);
        result = deviceExtendsMapper.updateDevice(devicePojo);
        return result;

    }

    /**
     * 方法描述：删除设备信息
     *
     * @author jnjua
     * @param id
     *            设备ID
     * @return 持久化操作码
     * @since 2017年11月13日
     */
    public int deleteDevice(List<Long> idList) {

    	int result = deviceExtendsMapper.deleteDeviceExtends(idList);
        result = deviceMapper.deleteDevice(idList);
        return result;
    }

    /**
     * 根据设备id与编码查询设备参数设置
     * 
     * @param param
     * @return
     */
    @Override
    public List<DevParameter> queryParam(DevParameter param) {
        List<DevParameter> list = deviceMapper.queryParam(param);
        return list;
    }

    /**
     * 更新设备参数设置
     * 
     * @param pojo
     * @return
     */
    @Override
    public int updateParam(DevParameterPojo pojo) {
        int result = deviceMapper.delParam(pojo);
        List<DevParameter> paramList = new ArrayList<DevParameter>();
        //光照度
        DevParameter dp = new DevParameter();
        dp.setDeviceId(pojo.getDeviceId());
        dp.setDevCode(pojo.getDevCode());
        dp.setUpdateTime(pojo.getUpdateTime());
        dp.setStatus(1);
        dp.setParameterName("lm_th");
        dp.setParameterValue(pojo.getSunshineTime());
        paramList.add(dp);
        //运行时间
        DevParameter dp1 = new DevParameter();
        dp1.setDeviceId(pojo.getDeviceId());
        dp1.setDevCode(pojo.getDevCode());
        dp1.setUpdateTime(pojo.getUpdateTime());
        dp1.setStatus(1);
        dp1.setParameterName("run_tm");
        dp1.setParameterValue(pojo.getRunStart() + "," + pojo.getRunEnd());
        paramList.add(dp1);
        //工作周期
        DevParameter dp2 = new DevParameter();
        dp2.setDeviceId(pojo.getDeviceId());
        dp2.setDevCode(pojo.getDevCode());
        dp2.setUpdateTime(pojo.getUpdateTime());
        dp2.setStatus(1);
        dp2.setParameterName("wk_invl");
        dp2.setParameterValue(pojo.getWorkTime());
        paramList.add(dp2);
        //图片回传周期
        DevParameter dp3 = new DevParameter();
        dp3.setDeviceId(pojo.getDeviceId());
        dp3.setDevCode(pojo.getDevCode());
        dp3.setUpdateTime(pojo.getUpdateTime());
        dp3.setStatus(1);
        dp3.setParameterName("pic_up_invl");
        dp3.setParameterValue(pojo.getBackTime());
        paramList.add(dp3);
        //加热温度
        DevParameter dp4 = new DevParameter();
        dp4.setDeviceId(pojo.getDeviceId());
        dp4.setDevCode(pojo.getDevCode());
        dp4.setUpdateTime(pojo.getUpdateTime());
        dp4.setStatus(1);
        dp4.setParameterName("heat_temp");
        dp4.setParameterValue(pojo.getHeat());
        paramList.add(dp4);
        //烘烤时间
        DevParameter dp5 = new DevParameter();
        dp5.setDeviceId(pojo.getDeviceId());
        dp5.setDevCode(pojo.getDevCode());
        dp5.setUpdateTime(pojo.getUpdateTime());
        dp5.setStatus(1);
        dp5.setParameterName("heat_tm");
        dp5.setParameterValue(pojo.getRoastTime());
        paramList.add(dp5);
        //清虫周期
        DevParameter dp6 = new DevParameter();
        dp6.setDeviceId(pojo.getDeviceId());
        dp6.setDevCode(pojo.getDevCode());
        dp6.setUpdateTime(pojo.getUpdateTime());
        dp6.setStatus(1);
        dp6.setParameterName("clear_invl");
        dp6.setParameterValue(pojo.getCleanTime());
        paramList.add(dp6);
        //休眠时间
        DevParameter dp7 = new DevParameter();
        dp7.setDeviceId(pojo.getDeviceId());
        dp7.setDevCode(pojo.getDevCode());
        dp7.setUpdateTime(pojo.getUpdateTime());
        dp7.setStatus(1);
        dp7.setParameterName("sleep_tm");
        dp7.setParameterValue(pojo.getSleepStart() + "," + pojo.getSleepEnd());
        paramList.add(dp7);
        result = deviceMapper.addParam(paramList);
        return result;
    }

    /**
     * 获取过期设备信息列表
     *
     * @author llmaoa
     * @param id
     *            设备ID
     * @return 持久化操作码
     * @since 2017年11月28日
     */
    @Override
    public PageInfo<DevicePojo> queryExpireDevices(Long manageArea) {
        List<Long> idList = areaService.queryChildrenIds(manageArea);
        PageHelper.startPage(1, 4);
        List<DeviceExtends> deList = deviceExtendsMapper.queryExpireDeviceByArea(idList);
        if (deList != null && deList.size() > 0) {
            List<Device> deviceList = deviceMapper.queryDeviceByList(deList);
            List<DevicePojo> devicePojoList = new ArrayList<DevicePojo>();
            Map<Long, DeviceExtends> map = new HashMap<Long, DeviceExtends>();
            for (DeviceExtends de : deList) {
                map.put(de.getId(), de);
            }

            //从两个库中取出的对象根据id关联组装pojo对象
            for (Device d : deviceList) {
                DevicePojo dp = new DevicePojo();
                dp.setId(d.getId());
                dp.setDevCode(d.getDevCode());
                dp.setDevName(d.getDevName());
                dp.setDevMac(d.getDevMac());
                dp.setDevLon(d.getDevLon());
                dp.setDevLat(d.getDevLat());
                dp.setAltitude(d.getAltitude());
                dp.setConstituent(d.getConstituent());
                dp.setGroupName(d.getGroupName());
                dp.setAreaCode(d.getAreaCode());
                dp.setDevAddr(d.getDevAddr());
                dp.setDevStatus(d.getDevStatus());
                dp.setFactoryCode(d.getFactoryCode());
                dp.setCreateUserid(d.getCreateUserid());
                if (null != d.getCreateTime()) {
                    dp.setCreateTime(d.getCreateTime() * 1000);
                }
                dp.setUpdateUserid(d.getUpdateUserid());
                if (null != d.getUpdateTime()) {
                    dp.setUpdateTime(d.getUpdateTime() * 1000);
                }
                dp.setSiteCode(d.getSiteCode());
                dp.setDevType(d.getDevType());
                dp.setDevModel(d.getDevModel());
                dp.setSendPeriod(d.getSendPeriod());
                if (null != d.getInstallTime()) {
                    dp.setInstallTime(d.getInstallTime() * 1000);
                }
                dp.setIsRemove(d.getIsRemove());
                dp.setIsDelete(d.getIsDelete());
                dp.setSync(d.getSync());
                DeviceExtends deviceExtends = map.get(d.getId());
                if (null != deviceExtends) {
                    dp.setProvince(deviceExtends.getProvince());
                    dp.setCity(deviceExtends.getCity());
                    dp.setDistrict(deviceExtends.getDistrict());
                    dp.setBelongArea(deviceExtends.getBelongArea());
                    dp.setAreaName(deviceExtends.getAreaName());
                    if (null != deviceExtends.getBuyTime()) {
                        dp.setBuyTime(deviceExtends.getBuyTime() * 1000);
                    }
                    dp.setBuyType(deviceExtends.getBuyType());
                    if (null != deviceExtends.getExpireTime()) {
                        dp.setExpireTime(deviceExtends.getExpireTime() * 1000);
                    }
                    dp.setOwner(deviceExtends.getOwner());
                    dp.setUser(deviceExtends.getUser());
                }
                devicePojoList.add(dp);
            }

            return new PageInfo<>(devicePojoList);
        }
        else {
            return null;
        }
    }

    /**
     * 获取过期设备并插入通知
     *
     * @author llmaoa
     * @param id
     *            设备ID
     * @return 持久化操作码
     * @since 2017年11月28日
     */
    @Override
    public void taskExpireDevice() {
        List<DeviceExtends> deList = deviceExtendsMapper.queryExpireDevice();
        if (deList != null && deList.size() > 0) {
            List<Device> deviceList = deviceMapper.queryDeviceByList(deList);
            Map<Long, Device> dMap = new HashMap<Long, Device>();
            for (Device device : deviceList) {
                dMap.put(device.getId(), device);
            }

            //按到期天数分
            Map<Integer, List<DeviceExtends>> deMap = new HashMap<Integer, List<DeviceExtends>>();
            for (DeviceExtends de : deList) {
                if (null != deMap.get(de.getDiffDay())) {
                    deMap.get(de.getDiffDay()).add(de);
                }
                else {
                    List<DeviceExtends> des = new ArrayList<DeviceExtends>();
                    des.add(de);
                    deMap.put(de.getDiffDay(), des);
                }
            }
            //短信模板
            Dictionary smsTemp = dictionaryMapper.getByKey("expire_sms_temp");
            String now = DateTimeUtil.getTodayChar8ByFormat("yyyy-MM-dd");
            for (Integer key : deMap.keySet()) {
                Long area = null;
                String devices = "";
                for (DeviceExtends de : deMap.get(key)) {
                    if (area == null) {
                        area = de.getBelongArea();
                    }
                    Device d = dMap.get(de.getId());
                    devices += "," + d.getDevCode();
                }
                String expireDate = DateTimeUtil.getIncreaseDT(now, key);

                List<ContactInfo> ciList = areaMapper.queryContact(area);
                //调用短信和邮件发送接口
                for (ContactInfo ci : ciList) {
                	logger.info("消息内容：姓名：" + ci.getName() + "，手机号：" + ci.getTelephone() + "，邮箱地址："
                            + ci.getEmail() + ";" + smsTemp.getValue().replace("EXPIREDATE", expireDate)
                                    .replace("DEVICES", devices.substring(1)));
                    // 发送消息邮件
                    Dictionary emailTemp = dictionaryMapper.getByKey("expire_mail_temp");
                    try {
                        // 获取邮箱信息模板内容
                        String title = "设备到期提醒";
                        String content = emailTemp.getValue();
                        // 内容变量替换
                        Map<String, String> map = new HashMap<>();
                        map.put("EXPIREDATE", expireDate);
                        map.put("DEVICES", devices.substring(1));

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
                        logger.error("TeacherService.add sendEmail error.", e);
                    }
                }
            }
        }
    }
    
    /**
     * 根据设备编码与名称查询设备基本信息
     */
    @Override
    public Device selectByCodeAndName(String deviceCode, String deviceName){
    	
    	Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("deviceCode", deviceCode);
        paramMap.put("deviceName", deviceName);
        return deviceMapper.selectByCodeAndName(paramMap);
    }
    
    /**
     * 方法描述：查询设备总数
     *
     * @author jnjua
     * @param page
     * @return 设备总数
     * @since 2018年01月09日
     */
    @Override
    public int getDevNum(Integer roleId,List<Long> areaList) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("roleId", roleId);
        paramMap.put("areaList", areaList);
        int count = deviceExtendsMapper.getDevNum(paramMap);
        return count;
    }
    
    /**
     * 方法描述：查询到期设备总数
     *
     * @author jnjua
     * @param page
     * @return 到期设备总数
     * @since 2018年01月09日
     */
    @Override
    public int getExpireDevNum(Integer roleId,List<Long> areaList) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("roleId", roleId);
        paramMap.put("areaList", areaList);
        int count = deviceExtendsMapper.getExpireDevNum(paramMap);
        return count;
    }
    
    /**
     * 方法描述：查询到期设备详情
     *
     * @author jnjua
     * @param page
     * @return 到期设备总数
     * @since 2018年01月09日
     */
    @Override
    public List<DeviceExtends> getExpireDev(Integer roleId,List<Long> areaList) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("roleId", roleId);
        paramMap.put("areaList", areaList);
        List<DeviceExtends> count = deviceExtendsMapper.getExpireDev(paramMap);
        return count;
    }
}
