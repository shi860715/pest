package com.isoftstone.entity.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.isoftstone.entity.pojo.BasicPage;

@Table(name = "t_remote_diagnosis")
public class RemoteDiagnosis extends BasicPage implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6039206495179947067L;
	
	/**
	 * 远程诊断ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * 图片地址
	 */
	@Column(name = "image")
	private String image;
	
	/**
	 * 录入用户ID
	 */
	@Column(name = "user_id")
    private Long userId;
	
	/**
	 * 拍摄时间
	 */
	@Column(name = "capture_time")
    private Timestamp captureTime;
	
	/**
	 * 文字描述:1，病害；2，防治；3：其他林业病虫害事项；
	 */
	@Column(name = "description")
    private String description;
	
	/**
	 * 诊断状态:1：待诊断；2，已诊断;3：信息不全；
	 */
	@Column(name = "diagnostic_status")
    private Integer diagnosticStatus;

	/**
	 * 诊断人
	 */
	@Column(name = "diagnostic_person")
    private String diagnosticPerson;
	
	/**
	 * 诊断结果
	 */
	@Column(name = "diagnostic_result")
    private String diagnosticResult;
	
	/**
	 * 状态：0：删除；1，正常
	 */
	@Column(name = "status")
    private Integer status;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
    private Timestamp createTime;

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
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the diagnosticStatus
	 */
	public Integer getDiagnosticStatus() {
		return diagnosticStatus;
	}

	/**
	 * @param diagnosticStatus the diagnosticStatus to set
	 */
	public void setDiagnosticStatus(Integer diagnosticStatus) {
		this.diagnosticStatus = diagnosticStatus;
	}

	/**
	 * @return the diagnosticPerson
	 */
	public String getDiagnosticPerson() {
		return diagnosticPerson;
	}

	/**
	 * @param diagnosticPerson the diagnosticPerson to set
	 */
	public void setDiagnosticPerson(String diagnosticPerson) {
		this.diagnosticPerson = diagnosticPerson;
	}

	/**
	 * @return the diagnosticResult
	 */
	public String getDiagnosticResult() {
		return diagnosticResult;
	}

	/**
	 * @param diagnosticResult the diagnosticResult to set
	 */
	public void setDiagnosticResult(String diagnosticResult) {
		this.diagnosticResult = diagnosticResult;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RemoteDiagnosis [id=" + id + ", image=" + image + ", userId=" + userId + ", captureTime=" + captureTime
				+ ", description=" + description + ", diagnosticStatus=" + diagnosticStatus + ", diagnosticPerson=" + diagnosticPerson
				+ ", diagnosticResult=" + diagnosticResult + ", status=" + status + ", createTime=" + createTime + "]";
	}
	
}
