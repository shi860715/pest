/**
 * MessageMailPojo.java 2017年4月6日
 * 
 * Copyright 2001-2017 iSoftStone All rights reserved.
 * iSoftStone PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.isoftstone.entity.pojo;

/**
 * 邮件消息
 * 
 * @author zoe(zhongpanc@isoftstone.com)
 * @since 2017年4月6日
 */
public class MessageMailPojo {
    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * Get the title
     * 
     * @return title
     * @see MessageMailPojo#title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title
     * 
     * @param title
     * @see MessageMailPojo#title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the content
     * 
     * @return content
     * @see MessageMailPojo#content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the content
     * 
     * @param content
     * @see MessageMailPojo#content
     */
    public void setContent(String content) {
        this.content = content;
    }
}
