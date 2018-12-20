package com.isoftstone.entity.pojo;

import com.isoftstone.entity.model.RemoteMonitore;

public class RemoteMonitorePojo extends RemoteMonitore {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -7182495373707493771L;
	
	/**
     * 省名称
     */
    private String provinceName;
	
    /**
     * 市名称
     */
    private String cityName;
    
    /**
     * 区名称
     */
    private String districtName;
    
    /**
     * 所属地域名称
     */
    private String belongAreaName;

	/**
	 * @return the provinceName
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 * @param provinceName the provinceName to set
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}

	/**
	 * @param districtName the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	/**
	 * @return the belongAreaName
	 */
	public String getBelongAreaName() {
		return belongAreaName;
	}

	/**
	 * @param belongAreaName the belongAreaName to set
	 */
	public void setBelongAreaName(String belongAreaName) {
		this.belongAreaName = belongAreaName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RemoteMonitorePojo [provinceName=" + provinceName + ", cityName=" + cityName + ", districtName="
				+ districtName + ", belongAreaName=" + belongAreaName + "]";
	}

}
