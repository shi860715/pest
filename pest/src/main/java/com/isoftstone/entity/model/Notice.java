package com.isoftstone.entity.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_notice")
public class Notice implements Serializable {

	/**
     * 序列号
     */
    private static final long serialVersionUID = -1463571505430257912L;

    /**
	 * 通知id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * 标题
	 */
	@Column(name = "title")
	private String title;
	
	/**
	 * 内容
	 */
	@Column(name = "content")
    private String content;
	
	/**
	 * 发送人：-1为系统发送
	 */
	@Column(name = "send_user")
    private Long sendUser;
	
	/**
	 * 接收人
	 */
	@Column(name = "receive_user")
    private Long receiveUser;

    /**
     * 类型 1设备到期提醒 2其他（待定）
     */
    @Column(name = "type")
	private Integer type;
    /**
     * 是否已读：0未读 1已读
     */
    @Column(name = "state")
	private Integer state;
    /**
     * 阅读时间
     */
    @Column(name = "read_time")
	private Date readTime;
    /**
     * 发送时间
     */
    @Column(name = "create_time")
	private Date createTime;
	
	/**
	 * 状态：0：删除；1正常
	 */
	@Column(name = "status")
    private Integer status;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the sendUser
     */
    public Long getSendUser() {
        return sendUser;
    }

    /**
     * @param sendUser the sendUser to set
     */
    public void setSendUser(Long sendUser) {
        this.sendUser = sendUser;
    }

    /**
     * @return the receiveUser
     */
    public Long getReceiveUser() {
        return receiveUser;
    }

    /**
     * @param receiveUser the receiveUser to set
     */
    public void setReceiveUser(Long receiveUser) {
        this.receiveUser = receiveUser;
    }

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return the state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return the readTime
     */
    public Date getReadTime() {
        return readTime;
    }

    /**
     * @param readTime the readTime to set
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Notice [id=" + id + ", title=" + title + ", content=" + content + ", sendUser=" + sendUser
                + ", receiveUser=" + receiveUser + ", type=" + type + ", state=" + state + ", readTime=" + readTime
                + ", createTime=" + createTime + ", status=" + status + "]";
    }
}
