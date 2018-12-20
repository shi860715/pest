package com.isoftstone.entity.pojo;

import java.io.Serializable;
import java.util.List;

import com.isoftstone.entity.model.Collect;

public class CollectPojo extends Collect implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6012646022395298748L;

	private Long userId;
	
	private Long areaId;

	private String areaName;
	
	private List<PestPolylinePojo> list;

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
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @return the list
	 */
	public List<PestPolylinePojo> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<PestPolylinePojo> list) {
		this.list = list;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CollectPojo [userId=" + userId + ", areaId=" + areaId + ", areaName=" + areaName + ", list=" + list
				+ "]";
	}
	
}