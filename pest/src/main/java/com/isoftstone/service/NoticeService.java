package com.isoftstone.service;

import java.util.List;

import com.isoftstone.entity.model.Notice;

/**
 * 通知service
 * @author lufei
 *
 */
public interface NoticeService extends IService<Notice> {
    
    /**
     * 获取未读通知数量
     * @return
     */
    public Integer getUnreadCount(Long userId);
    
    /**
     * 获取未读前4条通知
     * @param area
     * @return
     */
    public List<Notice> queryTop4Notice(Long userId);
}
