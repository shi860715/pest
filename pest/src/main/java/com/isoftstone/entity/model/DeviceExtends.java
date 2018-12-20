package com.isoftstone.entity.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_dev_info")
public class DeviceExtends implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 2084870596125025957L;

	/**
	 * 设备管理ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	/**
	 * 设备照片
	 */
    private String image;
	
	/**
     * 设备名称
     */
    private String devName;
	
	/**
     * 省
     */
    private Long province;
	
    /**
     * 市
     */
    private Long city;
    
    /**
     * 区
     */
    private Long district;
    
    /**
     * 所属地域
     */
    private Long belongArea;
    
    /**
     * 所属地域
     */
    private String areaName;
    
    /**
     * 设备购买时间
     */
    private Long buyTime;
    /**
     * 购买类型：1，购买，2，租赁
     */
    private Integer buyType;
    /**
     * 到期时间(当设备购买类型为2时，此字段才有效)
     */
    private Long expireTime;
    /**
     * 归属方
     */
    private String owner;
    /**
     * 使用方
     */
    private String user;
    
    /**
     * 删除状态  0正常 1删除
     */
    private Integer isdelete;
    
    /**
     * 到期剩余天数
     */
    private Integer diffDay;
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
	 * @return the province
	 */
	public Long getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(Long province) {
		this.province = province;
	}
	/**
	 * @return the city
	 */
	public Long getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(Long city) {
		this.city = city;
	}
	/**
	 * @return the district
	 */
	public Long getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(Long district) {
		this.district = district;
	}
	/**
	 * @return the belongArea
	 */
	public Long getBelongArea() {
		return belongArea;
	}
	/**
	 * @param belongArea the belongArea to set
	 */
	public void setBelongArea(Long belongArea) {
		this.belongArea = belongArea;
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
	 * @return the buyTime
	 */
	public Long getBuyTime() {
		return buyTime;
	}
	/**
	 * @param buyTime the buyTime to set
	 */
	public void setBuyTime(Long buyTime) {
		this.buyTime = buyTime;
	}
	/**
	 * @return the buyType
	 */
	public Integer getBuyType() {
		return buyType;
	}
	/**
	 * @param buyType the buyType to set
	 */
	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}
	/**
	 * @return the expireTime
	 */
	public Long getExpireTime() {
		return expireTime;
	}
	/**
	 * @param expireTime the expireTime to set
	 */
	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the isdelete
	 */
	public Integer getIsdelete() {
		return isdelete;
	}
	/**
	 * @param isdelete the isdelete to set
	 */
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	/**
     * @return the diffDay
     */
    public Integer getDiffDay() {
        return diffDay;
    }
    /**
     * @param diffDay the diffDay to set
     */
    public void setDiffDay(Integer diffDay) {
        this.diffDay = diffDay;
    }
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeviceExtends [id=" + id + ", image=" + image + ", devName=" + devName + ", province=" + province + ", city=" + city
				+ ", district=" + district + ", belongArea=" + belongArea + ", areaName=" + areaName + ", buyTime="
				+ buyTime + ", buyType=" + buyType + ", expireTime=" + expireTime + ", owner=" + owner + ", user="
				+ user + ", diffDay=" + diffDay + "]";
	}
   
}