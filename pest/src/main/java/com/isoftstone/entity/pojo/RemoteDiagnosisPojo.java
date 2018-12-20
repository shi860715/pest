package com.isoftstone.entity.pojo;

import com.isoftstone.entity.model.RemoteDiagnosis;

public class RemoteDiagnosisPojo extends RemoteDiagnosis {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2698602021251638538L;
	/**
	 * 用户名
	 */
	private String realName;
	
	private Long userId;
	
	private Long areaId;
	
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
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RemoteDiagnosisPojo [realName=" + realName + "]";
	}
	
}
