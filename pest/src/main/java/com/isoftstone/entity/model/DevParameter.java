package com.isoftstone.entity.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.isoftstone.entity.pojo.BasicPage;

@Table(name = "tb_dev_info")
public class DevParameter extends BasicPage implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1797377721286010618L;

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
	 * 设备编号
	 */
	private String devCode;
	
	/**
	 * 设备名称
	 */
	private String devName;
	
	/**
	 * 键
	 */
	private String parameterName;
	
	/**
	 * 值
	 */
	private String parameterValue;
	
	/**
	 * 更新时间
	 */
	private String updateTime;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	private Long userId;
	
	private Long areaId;

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
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the areaId
	 */
	public Long getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
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
	 * @return the deviceId
	 */
	public Long getDeviceId() {
		return deviceId;
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
	 * @return the parameterName
	 */
	public String getParameterName() {
		return parameterName;
	}

	/**
	 * @param parameterName the parameterName to set
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	/**
	 * @return the parameterValue
	 */
	public String getParameterValue() {
		return parameterValue;
	}

	/**
	 * @param parameterValue the parameterValue to set
	 */
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
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
		return "DevParameter [id=" + id + ", deviceId=" + deviceId + ", devCode=" + devCode + ", parameterName="
				+ parameterName + ", parameterValue=" + parameterValue + ", updateTime=" + updateTime + ", status="
				+ status + "]";
	}
	
}
