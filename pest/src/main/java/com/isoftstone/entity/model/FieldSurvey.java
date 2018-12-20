package com.isoftstone.entity.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import com.isoftstone.entity.pojo.BasicPage;

@Table(name = "t_field_survey")
public class FieldSurvey extends BasicPage implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8332877453766682068L;

	/**
	 * 主键id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * 野外调查名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 县名称
	 */
	@Column(name = "district_id")
	private String districtId;
	
	/**
	 * 县名称
	 */
	@Column(name = "district_name")
	private String districtName;
	
	/**
	 * 县代码
	 */
	@Column(name = "district_code")
	private String districtCode;
	
	/**
	 * 乡镇名称
	 */
	@Column(name = "township_name")
	private String townshipName;
	
	/**
	 * 乡镇代码
	 */
	@Column(name = "township_code")
	private String townshipCode;
	
	/**
	 * 场所名称
	 */
	@Column(name = "place_name")
	private String placeName;
	
	/**
	 * 调查类型：1，踏查；2，标准地；3，诱捕；4，苗圃（花圃）
	 */
	@Column(name = "type")
	private Integer type;
	
	/**
	 * 编号:1，当type=1，代表踏查路线编号；2，当type=2，代表标准地编号；3，当type=3，代表诱虫灯编号；4，当type=4，此值为空
	 */
	@Column(name = "number")
	private String number;
	
	/**
	 * 面积（单位：亩）:1，当type=1，代表踏查面积；2，当type=2，代表标准地面积；3，当type=3，代表林分面积；4，当type=4，苗圃面积
	 */
	@Column(name = "acreage")
	private Long acreage;
	
	/**
	 * 所在小班（当type=1,4时，此值为空）
	 */
	@Column(name = "current_class")
	private String currentClass;
	
	/**
	 * 代表面积（单位：亩，当type=1,3,4，此值为空）
	 */
	@Column(name = "deputy_acreage")
	private Long deputyAcreage;
	
	/**
	 * 经度（当type=1时，此值为空）
	 */
	@Column(name = "lon")
	private String lon;
	
	/**
	 * 纬度（当type=1时，此值为空）
	 */
	@Column(name = "lat")
	private String lat;
	
	/**
	 * 海拔（单位：m，当type=1时，此值为空）
	 */
	@Column(name = "altitude")
	private String altitude;
	
	/**
	 *林分类型（当type=1,2,4时，此值为空）
	 */
	@Column(name = "stands_type")
	private String standsType;
	
	/**
	 * 主要树种（当type=1,2,4，此值为空）
	 */
	@Column(name = "main_species")
	private String mainSpecies;
	
	/**
	 * 调查人
	 */
	@Column(name = "investigator")
	private String investigator;
	
	/**
	 * 陪同人员
	 */
	@Column(name = "companion")
	private String companion;
	
	/**
	 * 调查时间
	 */
	@Column(name = "survey_time")
	private Timestamp surveyTime;
	
	/**
	 * 库存（m³，件，张，kg，株等）
	 */
	@Column(name = "stock")
	private String stock;
	
	/**
	 * 库存单位（m³，件，张，kg，株等）
	 */
	@Column(name = "stock_unit")
	private String stockUnit;
	
	/**
	 * 抽样数量（m³，件，张，kg，株等）
	 */
	@Column(name = "sample_number")
	private String sampleNumber;
	
	/**
	 * 抽样数量单位（m³，件，张，kg，株等）
	 */
	@Column(name = "sample_number_unit")
	private String sampleNumberUnit;
	
	/**
	 * 创建人
	 */
	@Column(name = "user_id")
	private Long userId;
	
	private Long areaId;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the deputyAcreage
	 */
	public Long getDeputyAcreage() {
		return deputyAcreage;
	}

	/**
	 * @param deputyAcreage the deputyAcreage to set
	 */
	public void setDeputyAcreage(Long deputyAcreage) {
		this.deputyAcreage = deputyAcreage;
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
		return "FieldSurvey [id=" + id + ", name=" + name + ", districtId=" + districtId + ", districtName="
				+ districtName + ", districtCode=" + districtCode + ", townshipName=" + townshipName + ", townshipCode="
				+ townshipCode + ", placeName=" + placeName + ", type=" + type + ", number=" + number + ", acreage="
				+ acreage + ", currentClass=" + currentClass + ", deputyAcreage=" + deputyAcreage + ", lon=" + lon
				+ ", lat=" + lat + ", altitude=" + altitude + ", standsType=" + standsType + ", mainSpecies="
				+ mainSpecies + ", investigator=" + investigator + ", companion=" + companion + ", surveyTime="
				+ surveyTime + ", stock=" + stock + ", sampleNumber=" + sampleNumber + ", userId=" + userId + "]";
	}

}
