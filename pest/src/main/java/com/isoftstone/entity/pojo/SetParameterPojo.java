package com.isoftstone.entity.pojo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SetParameterPojo implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1651149703753936550L;

	/**
	 * 参数设置ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * 设备id
	 */
	private Long deviceId;
	
	/**
	 * 设备照片
	 */
	private String image;
	
	/**
	 * 设备编号
	 */
	private String devCode;
	
	/**
	 * 设备名称
	 */
	private String devName;
	
	/**
	 * 光照度
	 */
	private String lmTh;
	
	/**
	 * 运行时间
	 */
	private String runTm;
	
	/**
	 * 工作周期
	 */
	private String wkInvl;
	
	/**
	 * 图片回传周期
	 */
	private String picUpInvl;
	
	/**
	 * 加热温度
	 */
	private String heatTemp;
	
	/**
	 * 烘烤时间
	 */
	private String hearTm;
	
	/**
	 * 清虫时间
	 */
	private String clearInvl;
	
	/**
	 * 休眠时间
	 */
	private String sleepTm;
	
	/**
	 * 更新时间
	 */
	private String updateTime;
	
	/**
	 * 状态
	 */
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
	 * @return the deviceId
	 */
	public Long getDeviceId() {
		return deviceId;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the devCode
	 */
	public String getDevCode() {
		return devCode;
	}

	/**
	 * @param devCode the devCode to set
	 */
	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	/**
	 * @return the devName
	 */
	public String getDevName() {
		return devName;
	}

	/**
	 * @param devName the devName to set
	 */
	public void setDevName(String devName) {
		this.devName = devName;
	}

	/**
	 * @return the lmTh
	 */
	public String getLmTh() {
		return lmTh;
	}

	/**
	 * @param lmTh the lmTh to set
	 */
	public void setLmTh(String lmTh) {
		this.lmTh = lmTh;
	}

	/**
	 * @return the runTm
	 */
	public String getRunTm() {
		return runTm;
	}

	/**
	 * @param runTm the runTm to set
	 */
	public void setRunTm(String runTm) {
		this.runTm = runTm;
	}

	/**
	 * @return the wkInvl
	 */
	public String getWkInvl() {
		return wkInvl;
	}

	/**
	 * @param wkInvl the wkInvl to set
	 */
	public void setWkInvl(String wkInvl) {
		this.wkInvl = wkInvl;
	}

	/**
	 * @return the picUpInvl
	 */
	public String getPicUpInvl() {
		return picUpInvl;
	}

	/**
	 * @param picUpInvl the picUpInvl to set
	 */
	public void setPicUpInvl(String picUpInvl) {
		this.picUpInvl = picUpInvl;
	}

	/**
	 * @return the heatTemp
	 */
	public String getHeatTemp() {
		return heatTemp;
	}

	/**
	 * @param heatTemp the heatTemp to set
	 */
	public void setHeatTemp(String heatTemp) {
		this.heatTemp = heatTemp;
	}

	/**
	 * @return the hearTm
	 */
	public String getHearTm() {
		return hearTm;
	}

	/**
	 * @param hearTm the hearTm to set
	 */
	public void setHearTm(String hearTm) {
		this.hearTm = hearTm;
	}

	/**
	 * @return the clearInvl
	 */
	public String getClearInvl() {
		return clearInvl;
	}

	/**
	 * @param clearInvl the clearInvl to set
	 */
	public void setClearInvl(String clearInvl) {
		this.clearInvl = clearInvl;
	}

	/**
	 * @return the sleepTm
	 */
	public String getSleepTm() {
		return sleepTm;
	}

	/**
	 * @param sleepTm the sleepTm to set
	 */
	public void setSleepTm(String sleepTm) {
		this.sleepTm = sleepTm;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
		return "SetParameterPojo [id=" + id + ", deviceId=" + deviceId + ", image=" + image + ", devCode=" + devCode + ", devName=" + devName
				+ ", lmTh=" + lmTh + ", runTm=" + runTm + ", wkInvl=" + wkInvl + ", picUpInvl=" + picUpInvl
				+ ", heatTemp=" + heatTemp + ", hearTm=" + hearTm + ", clearInvl=" + clearInvl + ", sleepTm=" + sleepTm
				+ ", updateTime=" + updateTime + ", status=" + status + "]";
	}
	
}
