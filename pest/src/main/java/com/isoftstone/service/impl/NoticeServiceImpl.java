package com.isoftstone.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isoftstone.entity.model.Notice;
import com.isoftstone.mapper.main.NoticeMapper;
import com.isoftstone.service.NoticeService;

/**
 * 通知service实现类
 * @author lufei
 *
 */
@Service("noticeService")
public class NoticeServiceImpl extends BaseService<Notice> implements NoticeService {
    /**
     * 地区mapper
     */
    @Resource
    private NoticeMapper  noticeMapper;
    
    @Override
    public Integer getUnreadCount(Long userId) {
        int count = noticeMapper.getUnreadCount(userId);
        return count;
    }

    @Override
    public List<Notice> queryTop4Notice(Long userId) {
        List<Notice> noticeList = noticeMapper.queryTop4Notice(userId);
        return noticeList;
    }
}
