package com.isoftstone.entity.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_warning")
public class Warning implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7413868191833195805L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 地区ID
     */
    private Long areaId;
    /**
     * 地区名称
     */
    private String areaName;
    /**
     * 虫类ID
     */
    private Long  insectId;
    /**
     * 虫类名称
     */
    private String insectName;
    /**
     * 创建人
     */
    private Long creater;
    /**
     * 创建时间
     */
    private Timestamp createTime;
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
	 * @return the insectId
	 */
	public Long getInsectId() {
		return insectId;
	}
	/**
	 * @param insectId the insectId to set
	 */
	public void setInsectId(Long insectId) {
		this.insectId = insectId;
	}
	/**
	 * @return the creater
	 */
	public Long getCreater() {
		return creater;
	}
	/**
	 * @param creater the creater to set
	 */
	public void setCreater(Long creater) {
		this.creater = creater;
	}
	/**
	 * @return the createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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
	 * @return the insectName
	 */
	public String getInsectName() {
		return insectName;
	}
	/**
	 * @param insectName the insectName to set
	 */
	public void setInsectName(String insectName) {
		this.insectName = insectName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Warning [id=" + id + ", areaId=" + areaId + ", areaName=" + areaName + ", insectId=" + insectId
				+ ", insectName=" + insectName + ", creater=" + creater + ", createTime=" + createTime + "]";
	}

}