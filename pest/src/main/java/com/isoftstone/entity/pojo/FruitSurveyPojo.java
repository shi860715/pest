package com.isoftstone.entity.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;

import com.isoftstone.entity.model.FruitSurvey;

public class FruitSurveyPojo extends FruitSurvey {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5196081790943874406L;

	/**
	 * 野外调查名称
	 */
	private String fieldName;
	
	/**
	 * 县名称
	 */
	private String districtId;
	
	/**
	 * 县名称
	 */
	private String districtName;
	
	/**
	 * 县代码
	 */
	private String districtCode;
	
	/**
	 * 乡镇名称
	 */
	private String townshipName;
	
	/**
	 * 乡镇代码
	 */
	private String townshipCode;
	
	/**
	 * 场所名称
	 */
	private String placeName;
	
	/**
	 * 调查类型：1，踏查；2，标准地；3，诱捕；4，苗圃（花圃）
	 */
	private Integer type;
	
	/**
	 * 经度（当type=1时，此值为空）
	 */
	private String lon;
	
	/**
	 * 纬度（当type=1时，此值为空）
	 */
	private String lat;
	
	/**
	 * 海拔（单位：m，当type=1时，此值为空）
	 */
	private String altitude;
	
	/**
	 * 调查人
	 */
	private String investigator;
	
	/**
	 * 陪同人员
	 */
	private String companion;
	
	/**
	 * 调查时间
	 */
	private Timestamp surveyTime;
	
	/**
	 * 库存（m³，件，张，kg，株等）
	 */
	private String stock;
	
	/**
	 * 抽样数量（m³，件，张，kg，株等）
	 */
	private String sampleNumber;
	
	/**
	 * 创建人
	 */
	private Long userId;
	
	/**
	 * 库存单位（m³，件，张，kg，株等）
	 */
	@Column(name = "stock_unit")
	private String stockUnit;
	
	/**
	 * 抽样数量单位（m³，件，张，kg，株等）
	 */
	@Column(name = "sample_number_unit")
	private String sampleNumberUnit;

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the districtId
	 */
	public String getDistrictId() {
		return districtId;
	}

	/**
	 * @param districtId the districtId to set
	 */
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
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
	 * @return the districtCode
	 */
	public String getDistrictCode() {
		return districtCode;
	}

	/**
	 * @param districtCode the districtCode to set
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	/**
	 * @return the townshipName
	 */
	public String getTownshipName() {
		return townshipName;
	}

	/**
	 * @param townshipName the townshipName to set
	 */
	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}

	/**
	 * @return the townshipCode
	 */
	public String getTownshipCode() {
		return townshipCode;
	}

	/**
	 * @param townshipCode the townshipCode to set
	 */
	public void setTownshipCode(String townshipCode) {
		this.townshipCode = townshipCode;
	}

	/**
	 * @return the placeName
	 */
	public String getPlaceName() {
		return placeName;
	}

	/**
	 * @param placeName the placeName to set
	 */
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
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
	 * @return the lon
	 */
	public String getLon() {
		return lon;
	}

	/**
	 * @param lon the lon to set
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}

	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
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
	 * @return the investigator
	 */
	public String getInvestigator() {
		return investigator;
	}

	/**
	 * @param investigator the investigator to set
	 */
	public void setInvestigator(String investigator) {
		this.investigator = investigator;
	}

	/**
	 * @return the companion
	 */
	public String getCompanion() {
		return companion;
	}

	/**
	 * @param companion the companion to set
	 */
	public void setCompanion(String companion) {
		this.companion = companion;
	}

	/**
	 * @return the surveyTime
	 */
	public Timestamp getSurveyTime() {
		return surveyTime;
	}

	/**
	 * @param surveyTime the surveyTime to set
	 */
	public void setSurveyTime(Timestamp surveyTime) {
		this.surveyTime = surveyTime;
	}

	/**
	 * @return the stock
	 */
	public String getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(String stock) {
		this.stock = stock;
	}

	/**
	 * @return the sampleNumber
	 */
	public String getSampleNumber() {
		return sampleNumber;
	}

	/**
	 * @param sampleNumber the sampleNumber to set
	 */
	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

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
	 * @return the stockUnit
	 */
	public String getStockUnit() {
		return stockUnit;
	}

	/**
	 * @param stockUnit the stockUnit to set
	 */
	public void setStockUnit(String stockUnit) {
		this.stockUnit = stockUnit;
	}

	/**
	 * @return the sampleNumberUnit
	 */
	public String getSampleNumberUnit() {
		return sampleNumberUnit;
	}

	/**
	 * @param sampleNumberUnit the sampleNumberUnit to set
	 */
	public void setSampleNumberUnit(String sampleNumberUnit) {
		this.sampleNumberUnit = sampleNumberUnit;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FruitSurveyPojo [fieldName=" + fieldName + ", districtId=" + districtId + ", districtName="
				+ districtName + ", districtCode=" + districtCode + ", townshipName=" + townshipName + ", townshipCode="
				+ townshipCode + ", placeName=" + placeName + ", type=" + type + ", lon=" + lon + ", lat=" + lat
				+ ", altitude=" + altitude + ", investigator=" + investigator + ", companion=" + companion
				+ ", surveyTime=" + surveyTime + ", stock=" + stock + ", sampleNumber=" + sampleNumber + ", userId="
				+ userId + ", stockUnit=" + stockUnit + ", sampleNumberUnit=" + sampleNumberUnit + "]";
	}

}
