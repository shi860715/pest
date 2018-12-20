package com.isoftstone.entity.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.isoftstone.entity.pojo.BasicPage;

@Table(name = "t_collect")
public class Collect extends BasicPage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6012646022395298748L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 设备编号
     */
    private String deviceCode;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 捕获时间
     */
    private Timestamp captureTime;
    /**
     * 昆虫种类
     */
    private Long insectId;
    /**
     * 昆虫名称
     */
    private String insectName;
    /**
     * 雌性数量
     */
    private Integer femaleCount;
    /**
     * 雄性数量
     */
    private Integer maleCount;
    /**
     * 总数量
     */
    private Integer totalCount;
    /**
     * 照片ID
     */
    private Integer pictureId;
    /**
     * 创建时间
     */
    private Timestamp insertTime;
    /**
     * 前捕获时间
     */
    private Timestamp beforeTime;
    /**
     * 后捕获时间
     */
    private Timestamp afterTime;
	/**
	 * @return the beforeTime
	 */
	public Timestamp getBeforeTime() {
		return beforeTime;
	}
	/**
	 * @param beforeTime the beforeTime to set
	 */
	public void setBeforeTime(Timestamp beforeTime) {
		this.beforeTime = beforeTime;
	}
	/**
	 * @return the afterTime
	 */
	public Timestamp getAfterTime() {
		return afterTime;
	}
	/**
	 * @param afterTime the afterTime to set
	 */
	public void setAfterTime(Timestamp afterTime) {
		this.afterTime = afterTime;
	}
	/**
	 * @return the deviceCode
	 */
	public String getDeviceCode() {
		return deviceCode;
	}
	/**
	 * @param deviceCode the deviceCode to set
	 */
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
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
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * @return the captureTime
	 */
	public Timestamp getCaptureTime() {
		return captureTime;
	}
	/**
	 * @param captureTime the captureTime to set
	 */
	public void setCaptureTime(Timestamp captureTime) {
		this.captureTime = captureTime;
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
	 * @return the femaleCount
	 */
	public Integer getFemaleCount() {
		return femaleCount;
	}
	/**
	 * @param femaleCount the femaleCount to set
	 */
	public void setFemaleCount(Integer femaleCount) {
		this.femaleCount = femaleCount;
	}
	/**
	 * @return the maleCount
	 */
	public Integer getMaleCount() {
		return maleCount;
	}
	/**
	 * @param maleCount the maleCount to set
	 */
	public void setMaleCount(Integer maleCount) {
		this.maleCount = maleCount;
	}
	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the pictureId
	 */
	public Integer getPictureId() {
		return pictureId;
	}
	/**
	 * @param pictureId the pictureId to set
	 */
	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}
	/**
	 * @return the insertTime
	 */
	public Timestamp getInsertTime() {
		return insertTime;
	}
	/**
	 * @param insertTime the insertTime to set
	 */
	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Collect [id=" + id + ", deviceId=" + deviceId + ", deviceName=" + deviceName + ",captureTime=" + captureTime + ", insectId="
				+ insectId + ", femaleCount=" + femaleCount + ", maleCount=" + maleCount + ", totalCount=" + totalCount
				+ ", pictureId=" + pictureId + ", insertTime=" + insertTime + "]";
	}

}