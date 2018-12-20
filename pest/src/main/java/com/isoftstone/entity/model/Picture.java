package com.isoftstone.entity.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.isoftstone.entity.pojo.BasicPage;

@Table(name = "tb_picture")
public class Picture extends BasicPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1714790527473325371L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 图片路径ID
     */
    private Long pathId;
    /**
     * 设备Mac地址
     */
    private String devMac;
    /**
     * 设备ID
     */
    private Long devId;
    /**
     * 捕获时间
     */
    private Long captureTime;
    /**
     * 图片地址
     */
    private String picPath;
    /**
     * 处理时间
     */
    private Integer dealTime;
    /**
     * 图片状态
     */
    private Integer picStatus;
    /**
     * 前捕获时间
     */
    private Integer beforeTime;
    /**
     * 后捕获时间
     */
    private Integer afterTime;
	/**
	 * @return the beforeTime
	 */
	public Integer getBeforeTime() {
		return beforeTime;
	}
	/**
	 * @param beforeTime the beforeTime to set
	 */
	public void setBeforeTime(Integer beforeTime) {
		this.beforeTime = beforeTime;
	}
	/**
	 * @return the afterTime
	 */
	public Integer getAfterTime() {
		return afterTime;
	}
	/**
	 * @param afterTime the afterTime to set
	 */
	public void setAfterTime(Integer afterTime) {
		this.afterTime = afterTime;
	}
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
	 * @return the pathId
	 */
	public Long getPathId() {
		return pathId;
	}
	/**
	 * @param pathId the pathId to set
	 */
	public void setPathId(Long pathId) {
		this.pathId = pathId;
	}
	/**
	 * @return the devMac
	 */
	public String getDevMac() {
		return devMac;
	}
	/**
	 * @param devMac the devMac to set
	 */
	public void setDevMac(String devMac) {
		this.devMac = devMac;
	}
	/**
	 * @return the devId
	 */
	public Long getDevId() {
		return devId;
	}
	/**
	 * @param devId the devId to set
	 */
	public void setDevId(Long devId) {
		this.devId = devId;
	}
	/**
	 * @return the captureTime
	 */
	public Long getCaptureTime() {
		return captureTime;
	}
	/**
	 * @param captureTime the captureTime to set
	 */
	public void setCaptureTime(Long captureTime) {
		this.captureTime = captureTime;
	}
	/**
	 * @return the picPath
	 */
	public String getPicPath() {
		return picPath;
	}
	/**
	 * @param picPath the picPath to set
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	/**
	 * @return the dealTime
	 */
	public Integer getDealTime() {
		return dealTime;
	}
	/**
	 * @param dealTime the dealTime to set
	 */
	public void setDealTime(Integer dealTime) {
		this.dealTime = dealTime;
	}
	/**
	 * @return the picStatus
	 */
	public Integer getPicStatus() {
		return picStatus;
	}
	/**
	 * @param picStatus the picStatus to set
	 */
	public void setPicStatus(Integer picStatus) {
		this.picStatus = picStatus;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Picture [id=" + id + ", pathId=" + pathId + ", devMac=" + devMac + ", devId=" + devId + ", captureTime="
				+ captureTime + ", picPath=" + picPath + ", dealTime=" + dealTime + ", picStatus=" + picStatus + "]";
	}

}