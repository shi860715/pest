package com.isoftstone.entity.model;

import java.io.Serializable;

import javax.persistence.*;

import com.isoftstone.entity.pojo.BasicPage;

@Table(name = "t_trapped_survey")
public class TrappedSurvey extends BasicPage implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 2455567727249954523L;

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
	 * 昆虫名称
	 */
	@Column(name = "insect_name")
    private String insectName;
	
	/**
	 * 合计诱虫数量（单位：头）
	 */
	@Column(name = "total_count")
    private Integer totalCount;
	
	/**
	 * 雌虫数量（单位：头）
	 */
	@Column(name = "female_count")
    private Integer femaleCount;
	
	/**
	 * 雄虫数量（单位：头）
	 */
	@Column(name = "male_count")
    private Integer maleCount;
	
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
	 * 发生（危害）程度：1，轻度以下；2，轻；3，中；4，重
	 */
	@Column(name = "harm_degree")
    private Integer harmDegree;
	
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrappedSurvey [id=" + id + ", fieldId=" + fieldId + ", images=" + images + ", insectName=" + insectName
				+ ", totalCount=" + totalCount + ", femaleCount=" + femaleCount + ", maleCount=" + maleCount
				+ ", remark=" + remark + ", insectStatus=" + insectStatus + ", harmDegree=" + harmDegree + "]";
	}

}
