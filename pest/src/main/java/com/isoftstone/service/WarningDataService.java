package com.isoftstone.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.WarningData;
import com.isoftstone.entity.pojo.WarningDataPojo;

/**
 * 地区service
 * @author lufei
 *
 */
public interface WarningDataService extends IService<WarningData> {
    
    /**
     * 定时扫描数据库，更行预警数据
     * @author llmaoa
     * @since 2017年11月30日
     * @see 
     */
    public void taskWarningData();
    
    /**
     * 发送短信给对应的消息接收人
     * @author llmaoa
     * @since 2017年11月30日
     * @see 
     */
    public void taskSendWarningMsg();
    
    /**
     * 根据区域获取预警数据
     * @author llmaoa
     * @param key
     * @return
     * @since 2017年11月29日
     * @see 
     */
    public PageInfo<WarningDataPojo> getByArea(Long area);
    
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
    PageInfo<WarningDataPojo> findPage(WarningData warningData, int start, int length);
    
    /**
     * 根据ID集合批量删除预警数据
     * @author llmaoa
     * @param ids
     * @return
     * @since 2017年12月1日
     * @see 
     */
    public int delWarningDatas(String ids);

    /**
     * 发送预警邮件给联系人
     * @author llmaoa
     * @param id
     * @return
     * @since 2017年12月1日
     * @see 
     */
    public boolean sendEmail(Long id);
    
    /**
     * 发送预警短信给联系人
     * @author llmaoa
     * @param id
     * @return
     * @since 2017年12月1日
     * @see 
     */
    public boolean sendSms(Long id);
    
    List<WarningDataPojo> getWarningData(Integer roleId,List<Long> areaList);
}
