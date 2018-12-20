package com.isoftstone.entity.model;

import java.io.Serializable;

import javax.persistence.*;

import com.isoftstone.entity.pojo.BasicPage;

@Table(name = "t_fruit_survey")
public class FruitSurvey extends BasicPage implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6687498229169368220L;

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
	 * 寄主植物
	 */
	@Column(name = "host_plant")
    private String hostPlant;
	
	/**
	 * 寄主类型
	 */
	@Column(name = "host_type")
    private String hostType;
	
	/**
	 * 危害数量（m³，件，张，kg，株等）
	 */
	@Column(name = "harm_count")
    private String harmCount;
	
	/**
	 * 代表数量（m³，件，张，kg，株等）
	 */
	@Column(name = "deputy_count")
    private String deputyCount;
	
	/**
	 * 发生（危害）程度：1，轻度以下；2，轻；3，中；4，重
	 */
	@Column(name = "harm_degree")
    private Integer harmDegree;
	
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
	 * @return the hostType
	 */
	public String getHostType() {
		return hostType;
	}

	/**
	 * @param hostType the hostType to set
	 */
	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	/**
	 * @return the harmCount
	 */
	public String getHarmCount() {
		return harmCount;
	}

	/**
	 * @param harmCount the harmCount to set
	 */
	public void setHarmCount(String harmCount) {
		this.harmCount = harmCount;
	}

	/**
	 * @return the deputyCount
	 */
	public String getDeputyCount() {
		return deputyCount;
	}

	/**
	 * @param deputyCount the deputyCount to set
	 */
	public void setDeputyCount(String deputyCount) {
		this.deputyCount = deputyCount;
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
		return "FruitSurvey [id=" + id + ", fieldId=" + fieldId + ", images=" + images + ", pest=" + pest
				+ ", hostPlant=" + hostPlant + ", hostType=" + hostType + ", harmCount=" + harmCount + ", deputyCount="
				+ deputyCount + ", harmDegree=" + harmDegree + ", remark=" + remark + ", insectStatus=" + insectStatus
				+ "]";
	}
	
}
