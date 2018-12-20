package com.isoftstone.entity.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.isoftstone.entity.pojo.BasicPage;

@Table(name = "tb_dev_info")
public class Device extends BasicPage implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 6846652924466915872L;

	/**
	 * 设备管理ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Device [id=" + id + ", devCode=" + devCode + ", devName=" + devName + ", devMac=" + devMac + ", devLon="
				+ devLon + ", devLat=" + devLat + ", altitude=" + altitude + ", constituent=" + constituent
				+ ", groupName=" + groupName + ", areaCode=" + areaCode + ", devAddr=" + devAddr + ", devStatus="
				+ devStatus + ", factoryCode=" + factoryCode + ", createUserid=" + createUserid + ", createTime="
				+ createTime + ", updateUserid=" + updateUserid + ", updateTime=" + updateTime + ", siteCode="
				+ siteCode + ", devType=" + devType + ", devModel=" + devModel + ", sendPeriod=" + sendPeriod
				+ ", installTime=" + installTime + ", isRemove=" + isRemove + ", isDelete=" + isDelete + ", sync="
				+ sync + "]";
	}
    
}