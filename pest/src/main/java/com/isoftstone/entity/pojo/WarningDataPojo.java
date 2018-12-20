package com.isoftstone.entity.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_warning_data")
public class WarningDataPojo implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 5384521190881697096L;

    /**
	 * 字典表
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * 设备ID
	 */
	@Column(name = "device_id")
	private Long deviceId;
	
	/**
	 * 区域ID
	 */
	@Column(name = "area_id")
    private Long areaId;
	
	/**
	 * 地址
	 */
	@Column(name = "address")
    private String address;

    /**
     * 昆虫种类ID
     */
    @Column(name = "insect_id")
	private Long insectId;
    
    private String insectName;

    /**
     * 预警类型
     */
    @Column(name = "type")
	private Integer type;

    /**
     * 预警数值
     */
    @Column(name = "warning_num")
	private Integer warningNum;

    /**
     * 预警时间
     */
    @Column(name = "create_time")
	private Date createTime;

    /**
     * 数据状态0有效1无效
     */
    @Column(name = "status")
	private Integer status;
    
    /**
     * 捕获时间
     */
    @Column(name = "capture_time")
    private Date captureTime;
    /**
     * 创建时间
     */
    @Column(name = "insert_time")
    private Date insertTime;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
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

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return the warningNum
     */
    public Integer getWarningNum() {
        return warningNum;
    }

    /**
     * @param warningNum the warningNum to set
     */
    public void setWarningNum(Integer warningNum) {
        this.warningNum = warningNum;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    /**
     * @return the captureTime
     */
    public Date getCaptureTime() {
        return captureTime;
    }

    /**
     * @param captureTime the captureTime to set
     */
    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }

    /**
     * @return the insertTime
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * @param insertTime the insertTime to set
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WarningDataPojo [id=" + id + ", deviceId=" + deviceId + ", areaId=" + areaId + ", address=" + address
                + ", insectId=" + insectId + ", insectName=" + insectName + ", type=" + type + ", warningNum="
                + warningNum + ", createTime=" + createTime + ", status=" + status + ", captureTime=" + captureTime
                + ", insertTime=" + insertTime + "]";
    }
}
