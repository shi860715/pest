package com.isoftstone.entity.model;

import java.io.Serializable;

import javax.persistence.*;

import com.isoftstone.entity.pojo.BasicPage;

@Table(name = "t_nursery_survey")
public class NurserySurvey extends BasicPage implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1555476001511011986L;

	/**
	 * 主键id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * 野外调查表id
	 */
	@Column(name = "field_id")
	private Long fieldId;
	
	/**
	 * 照片路径（多个路径以","分隔）
	 */
	@Column(name = "images")
	private String images;
	
	/**
	 * 有害生物种类
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
	 * 受害株率（单位：%）
	 */
	@Column(name = "victims_rate")
    private Double victimsRate;
	
	/**
	 * 发生（危害）程度：1，轻度以下；2，轻；3，中；4，重
	 */
	@Column(name = "harm_degree")
    private Integer harmDegree;
	
	/**
	 * 是否成灾：1，是；2，否
	 */
	@Column(name = "is_disaster")
    private Integer isDisaster;
	
	/**
	 * 备注
	 */
	@Column(name = "remark")
    private String remark;

	/**
	 * 调查卡编号
	 */
	@Column(name = "card_number")
    private String cardNumber;

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
	 * @return the victimsRate
	 */
	public Double getVictimsRate() {
		return victimsRate;
	}

	/**
	 * @param victimsRate the victimsRate to set
	 */
	public void setVictimsRate(Double victimsRate) {
		this.victimsRate = victimsRate;
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
	 * @return the isDisaster
	 */
	public Integer getIsDisaster() {
		return isDisaster;
	}

	/**
	 * @param isDisaster the isDisaster to set
	 */
	public void setIsDisaster(Integer isDisaster) {
		this.isDisaster = isDisaster;
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
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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
		return "NurserySurvey [id=" + id + ", fieldId=" + fieldId + ", images=" + images + ", pest=" + pest
				+ ", hostPlant=" + hostPlant + ", harmPart=" + harmPart + ", victimsRate=" + victimsRate
				+ ", harmDegree=" + harmDegree + ", isDisaster=" + isDisaster + ", remark=" + remark + ", cardNumber="
				+ cardNumber + ", insectStatus=" + insectStatus + "]";
	}
	
}
