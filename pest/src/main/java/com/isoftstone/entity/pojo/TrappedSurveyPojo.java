package com.isoftstone.entity.pojo;

import java.sql.Timestamp;

import com.isoftstone.entity.model.TrappedSurvey;

public class TrappedSurveyPojo extends TrappedSurvey{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2884018329946493781L;

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
	 * 调查类型：1，踏查；2，标准地；3，诱捕；4，苗圃（花圃）
	 */
	private Integer type;
	
	/**
	 * 编号:1，当type=1，代表踏查路线编号；2，当type=2，代表标准地编号；3，当type=3，代表诱虫灯编号；4，当type=4，此值为空
	 */
	private String number;
	
	/**
	 * 面积（单位：亩）:1，当type=1，代表踏查面积；2，当type=2，代表标准地面积；3，当type=3，代表林分面积；4，当type=4，苗圃面积
	 */
	private Long acreage;
	
	/**
	 * 所在小班（当type=1,4时，此值为空）
	 */
	private String currentClass;
	
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
	 *林分类型（当type=1,2,4时，此值为空）
	 */
	private String standsType;
	
	/**
	 * 主要树种（当type=1,2,4，此值为空）
	 */
	private String mainSpecies;
	
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
	 * 创建人
	 */
	private Long userId;

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
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the acreage
	 */
	public Long getAcreage() {
		return acreage;
	}

	/**
	 * @param acreage the acreage to set
	 */
	public void setAcreage(Long acreage) {
		this.acreage = acreage;
	}

	/**
	 * @return the currentClass
	 */
	public String getCurrentClass() {
		return currentClass;
	}

	/**
	 * @param currentClass the currentClass to set
	 */
	public void setCurrentClass(String currentClass) {
		this.currentClass = currentClass;
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
	 * @return the standsType
	 */
	public String getStandsType() {
		return standsType;
	}

	/**
	 * @param standsType the standsType to set
	 */
	public void setStandsType(String standsType) {
		this.standsType = standsType;
	}

	/**
	 * @return the mainSpecies
	 */
	public String getMainSpecies() {
		return mainSpecies;
	}

	/**
	 * @param mainSpecies the mainSpecies to set
	 */
	public void setMainSpecies(String mainSpecies) {
		this.mainSpecies = mainSpecies;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrappedSurveyPojo [fieldName=" + fieldName + ", districtId=" + districtId + ", districtName=" + districtName
				+ ", districtCode=" + districtCode + ", townshipName=" + townshipName + ", townshipCode=" + townshipCode
				+ ", type=" + type + ", number=" + number + ", acreage=" + acreage + ", currentClass=" + currentClass
				+ ", lon=" + lon + ", lat=" + lat + ", altitude=" + altitude + ", standsType=" + standsType
				+ ", mainSpecies=" + mainSpecies + ", investigator=" + investigator + ", companion=" + companion
				+ ", surveyTime=" + surveyTime + ", userId=" + userId + "]";
	}
	
}
