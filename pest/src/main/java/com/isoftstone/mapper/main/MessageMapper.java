package com.isoftstone.mapper.main;

import java.util.List;

import com.isoftstone.entity.model.Message;
import com.isoftstone.util.MyMapper;

/**
 * 消息mapper
 * @author lufei
 *
 */
public interface MessageMapper extends MyMapper<Message> {
	
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
