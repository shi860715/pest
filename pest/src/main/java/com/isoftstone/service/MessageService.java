package com.isoftstone.service;

import java.util.List;

import com.isoftstone.entity.model.Message;

/**
 * 消息service
 * @author lufei
 *
 */
public interface MessageService extends IService<Message> {
    
    /**
     * 获取未读消息数量
     * @return
     */
    public Integer getUnreadCount(Long userId);
    
    /**
     * 获取未读前4条消息
     * @param area
     * @return
     */
    public List<Message> queryTop4Message(Long userId);
}
