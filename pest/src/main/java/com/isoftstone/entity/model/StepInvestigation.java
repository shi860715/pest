package com.isoftstone.entity.model;

import java.io.Serializable;

import javax.persistence.*;

import com.isoftstone.entity.pojo.BasicPage;

@Table(name = "t_step_investigation")
public class StepInvestigation extends BasicPage implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 243791726956292944L;

	/**
	 * 主键id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * 照片路径（多个路径以","分隔）
	 */
	@Column(name = "images")
	private String images;
	
	/**
	 * 野外调查表id
	 */
	@Column(name = "field_id")
	private Long fieldId;
	
	/**
	 * 踏查点名称
	 */
	@Column(name = "name")
    private String name;
	
	/**
	 * 经度
	 */
	@Column(name = "lon")
    private String lon;
	
	/**
	 * 纬度
	 */
	@Column(name = "lat")
    private String lat;
	
	/**
	 * 海拔（单位：m）
	 */
	@Column(name = "altitude")
    private String altitude;
	
	/**
	 * 林分组成
	 */
	@Column(name = "constituent")
    private String constituent;
	
	/**
	 * 有害生物名称或标本编号
	 */
	@Column(name = "pest")
    private String pest;
	
	/**
	 * 寄主植物名称
	 */
	@Column(name = "host_plant")
    private String hostPlant;
	
	/**
	 * 危害部位
	 */
	@Column(name = "harm_part")
    private String harmPart;
	
	/**
	 * 发生（危害）程度：1，轻度以下；2，轻；3，中；4，重
	 */
	@Column(name = "harm_degree")
    private Integer harmDegree;
	
	/**
	 * 是否需要设置标准地：1：是，2，否
	 */
	@Column(name = "is_set_standard")
    private Long isSetStandard;
	
	/**
	 * 标准地编号
	 */
	@Column(name = "standard_code")
    private String standardCode;
	
	/**
	 * 备注
	 */
	@Column(name = "remark")
    private String remark;
	
	/**
	 * 虫态
	 */
	@Column(name = "insect_status")
    private Integer insectStatus;

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
	 * @return the fieldId
	 */
	public Long getFieldId() {
		return fieldId;
	}

	/**
	 * @param fieldId the fieldId to set
	 */
	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	/**
	 * @return the images
	 */
	public String getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(String images) {
		this.images = images;
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
	 * @return the pest
	 */
	public String getPest() {
		return pest;
	}

	/**
	 * @param pest the pest to set
	 */
	public void setPest(String pest) {
		this.pest = pest;
	}

	/**
	 * @return the hostPlant
	 */
	public String getHostPlant() {
		return hostPlant;
	}

	/**
	 * @param hostPlant the hostPlant to set
	 */
	public void setHostPlant(String hostPlant) {
		this.hostPlant = hostPlant;
	}

	/**
	 * @return the harmPart
	 */
	public String getHarmPart() {
		return harmPart;
	}

	/**
	 * @param harmPart the harmPart to set
	 */
	public void setHarmPart(String harmPart) {
		this.harmPart = harmPart;
	}

	/**
	 * @return the isSetStandard
	 */
	public Long getIsSetStandard() {
		return isSetStandard;
	}

	/**
	 * @param isSetStandard the isSetStandard to set
	 */
	public void setIsSetStandard(Long isSetStandard) {
		this.isSetStandard = isSetStandard;
	}

	/**
	 * @return the standardCode
	 */
	public String getStandardCode() {
		return standardCode;
	}

	/**
	 * @param standardCode the standardCode to set
	 */
	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the harmDegree
	 */
	public Integer getHarmDegree() {
		return harmDegree;
	}

	/**
	 * @param harmDegree the harmDegree to set
	 */
	public void setHarmDegree(Integer harmDegree) {
		this.harmDegree = harmDegree;
	}

	/**
	 * @return the insectStatus
	 */
	public Integer getInsectStatus() {
		return insectStatus;
	}

	/**
	 * @param insectStatus the insectStatus to set
	 */
	public void setInsectStatus(Integer insectStatus) {
		this.insectStatus = insectStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StepInvestigation [id=" + id + ", images=" + images + ", fieldId=" + fieldId + ", name=" + name
				+ ", lon=" + lon + ", lat=" + lat + ", altitude=" + altitude + ", constituent=" + constituent
				+ ", pest=" + pest + ", hostPlant=" + hostPlant + ", harmPart=" + harmPart + ", harmDegree="
				+ harmDegree + ", isSetStandard=" + isSetStandard + ", standardCode=" + standardCode + ", remark="
				+ remark + ", insectStatus=" + insectStatus + "]";
	}

}
