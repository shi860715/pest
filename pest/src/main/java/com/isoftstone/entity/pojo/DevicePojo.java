package com.isoftstone.entity.pojo;

import java.io.Serializable;

import com.isoftstone.entity.model.Device;

public class DevicePojo extends Device implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1794819108267097747L;

	/**
	 * 设备管理ID
	 */
    private Long id;
    
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
     * 设备MAC地址
     */
    private String devMac;
    /**
     * 设备经度
     */
    private String devLon;
    /**
     * 设备纬度
     */
    private String devLat;
    /**
     * 海拔
     */
    private String altitude;
    /**
     * 林分组成
     */
    private String constituent;    
    /**
     * 林业小班
     */
    private String groupName;
    /**
     * 所属地区
     */
    private String areaCode;
    /**
     * 设备详细地址
     */
    private String devAddr;
    /**
     * 设备在线状态，0：离线；1：在线
     */
    private Integer devStatus;
    /**
     * 厂商编码
     */
    private String factoryCode;
    /**
     * 创建人ID
     */
    private Long createUserid;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 修改人ID
     */
    private Long updateUserid;
    /**
     * 修改时间
     */
    private Long updateTime;
    /**
     * 场所编号
     */
    private String siteCode;
    /**
     * 设备类型
     */
    private Integer devType;
    /**
     * 设备模型
     */
    private String devModel;
    /**
     * 上传数据间隔时间，单位秒
     */
    private Long sendPeriod;
    /**
     * 安装时间
     */
    private Long installTime;
    /**
     * 拆除状态，0：未拆除；1：已拆除
     */
    private Integer isRemove;
    /**
     * 删除状态 0正常 1删除
     */
    private Integer isDelete;
    /**
     * 是否同步，0：已同步；1：待同步
     */
    private Integer sync;
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
     * 所属地域名称
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
	 * @return the devLon
	 */
	public String getDevLon() {
		return devLon;
	}
	/**
	 * @param devLon the devLon to set
	 */
	public void setDevLon(String devLon) {
		this.devLon = devLon;
	}
	/**
	 * @return the devLat
	 */
	public String getDevLat() {
		return devLat;
	}
	/**
	 * @param devLat the devLat to set
	 */
	public void setDevLat(String devLat) {
		this.devLat = devLat;
	}
	/**
	 * @return the altitude
	 */
	public String getAltitude() {
		return altitude;
	}
	/**
	 * @param altitude the altitude to set
	 */
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}
	/**
	 * @return the constituent
	 */
	public String getConstituent() {
		return constituent;
	}
	/**
	 * @param constituent the constituent to set
	 */
	public void setConstituent(String constituent) {
		this.constituent = constituent;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * @return the devAddr
	 */
	public String getDevAddr() {
		return devAddr;
	}
	/**
	 * @param devAddr the devAddr to set
	 */
	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}
	/**
	 * @return the devStatus
	 */
	public Integer getDevStatus() {
		return devStatus;
	}
	/**
	 * @param devStatus the devStatus to set
	 */
	public void setDevStatus(Integer devStatus) {
		this.devStatus = devStatus;
	}
	/**
	 * @return the factoryCode
	 */
	public String getFactoryCode() {
		return factoryCode;
	}
	/**
	 * @param factoryCode the factoryCode to set
	 */
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	/**
	 * @return the createUserid
	 */
	public Long getCreateUserid() {
		return createUserid;
	}
	/**
	 * @param createUserid the createUserid to set
	 */
	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}
	/**
	 * @return the createTime
	 */
	public Long getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the updateUserid
	 */
	public Long getUpdateUserid() {
		return updateUserid;
	}
	/**
	 * @param updateUserid the updateUserid to set
	 */
	public void setUpdateUserid(Long updateUserid) {
		this.updateUserid = updateUserid;
	}
	/**
	 * @return the updateTime
	 */
	public Long getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the siteCode
	 */
	public String getSiteCode() {
		return siteCode;
	}
	/**
	 * @param siteCode the siteCode to set
	 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	/**
	 * @return the devType
	 */
	public Integer getDevType() {
		return devType;
	}
	/**
	 * @param devType the devType to set
	 */
	public void setDevType(Integer devType) {
		this.devType = devType;
	}
	/**
	 * @return the devModel
	 */
	public String getDevModel() {
		return devModel;
	}
	/**
	 * @param devModel the devModel to set
	 */
	public void setDevModel(String devModel) {
		this.devModel = devModel;
	}
	/**
	 * @return the sendPeriod
	 */
	public Long getSendPeriod() {
		return sendPeriod;
	}
	/**
	 * @param sendPeriod the sendPeriod to set
	 */
	public void setSendPeriod(Long sendPeriod) {
		this.sendPeriod = sendPeriod;
	}
	/**
	 * @return the installTime
	 */
	public Long getInstallTime() {
		return installTime;
	}
	/**
	 * @param installTime the installTime to set
	 */
	public void setInstallTime(Long installTime) {
		this.installTime = installTime;
	}
	/**
	 * @return the isRemove
	 */
	public Integer getIsRemove() {
		return isRemove;
	}
	/**
	 * @param isRemove the isRemove to set
	 */
	public void setIsRemove(Integer isRemove) {
		this.isRemove = isRemove;
	}
	/**
	 * @return the isDelete
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * @param isDelete the isDelete to set
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * @return the sync
	 */
	public Integer getSync() {
		return sync;
	}
	/**
	 * @param sync the sync to set
	 */
	public void setSync(Integer sync) {
		this.sync = sync;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DevicePojo [id=" + id + ", devCode=" + devCode + ", image=" + image + ", devName=" + devName + ", devMac=" + devMac
				+ ", devLon=" + devLon + ", devLat=" + devLat + ", altitude=" + altitude + ", constituent="
				+ constituent + ", groupName=" + groupName + ", areaCode=" + areaCode + ", devAddr=" + devAddr
				+ ", devStatus=" + devStatus + ", factoryCode=" + factoryCode + ", createUserid=" + createUserid
				+ ", createTime=" + createTime + ", updateUserid=" + updateUserid + ", updateTime=" + updateTime
				+ ", siteCode=" + siteCode + ", devType=" + devType + ", devModel=" + devModel + ", sendPeriod="
				+ sendPeriod + ", installTime=" + installTime + ", isRemove=" + isRemove + ", isDelete=" + isDelete
				+ ", sync=" + sync + ", province=" + province + ", city=" + city + ", district=" + district
				+ ", belongArea=" + belongArea + ", areaName=" + areaName + ", buyTime=" + buyTime + ", buyType="
				+ buyType + ", expireTime=" + expireTime + ", owner=" + owner + ", user=" + user + "]";
	}
}