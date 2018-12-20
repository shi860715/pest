package com.isoftstone.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isoftstone.entity.model.Message;
import com.isoftstone.mapper.main.MessageMapper;
import com.isoftstone.service.MessageService;

/**
 * 地区service实现类
 * @author lufei
 *
 */
@Service("messageService")
public class MessageServiceImpl extends BaseService<Message> implements MessageService {
    /**
     * 消息mapper
     */
    @Resource
    private MessageMapper messageMapper;
    
    @Override
    public Integer getUnreadCount(Long userId) {
        int count = messageMapper.getUnreadCount(userId);
        return count;
    }

    @Override
    public List<Message> queryTop4Message(Long userId) {
        List<Message> msgList = messageMapper.queryTop4Message(userId);
        return msgList;
    }
}
