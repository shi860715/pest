package com.isoftstone.mapper.main;

import java.util.List;

import com.isoftstone.entity.model.Notice;
import com.isoftstone.util.MyMapper;

/**
 * 通知mapper
 * @author lufei
 *
 */
public interface NoticeMapper extends MyMapper<Notice> {
	
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
