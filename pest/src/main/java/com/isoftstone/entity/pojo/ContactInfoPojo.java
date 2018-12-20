package com.isoftstone.entity.pojo;

import java.io.Serializable;

import com.isoftstone.entity.model.ContactInfo;

public class ContactInfoPojo extends ContactInfo implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 6558845604481745721L;
	
	/**
	 * 省市区点名称
	 */
    private String areaName;

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

}
