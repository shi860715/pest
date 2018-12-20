package com.isoftstone.entity.imports;

import java.util.Date;

import com.isoftstone.commons.Template;

@Template("虫情数据")
public class CollectTemp {
    /**
     * 数据行号
     */
    private Long row;
    @Template("设备编号")
    private String deviceCode;
    @Template("设备名称")
    private String deviceName;
    @Template("抓捕时间")
    private Date captureTime;
    @Template("昆虫名称")
    private String insectName;
    @Template("雌虫数量")
    private Integer femaleCount;
    @Template("雄虫数量")
    private Integer maleCount;
    @Template("总数量")
    private Integer totalCount;
	/**
	 * @return the row
	 */
	public Long getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(Long row) {
		this.row = row;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CollectTemp [row=" + row + ", deviceCode=" + deviceCode + ", deviceName=" + deviceName
				+ ", captureTime=" + captureTime + ", insectName=" + insectName + ", femaleCount=" + femaleCount
				+ ", maleCount=" + maleCount + ", totalCount=" + totalCount + "]";
	}
	
}
