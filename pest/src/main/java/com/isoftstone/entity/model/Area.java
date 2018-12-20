package com.isoftstone.entity.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_area")
public class Area implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1931930190513538088L;

	/**
	 * 省市区点id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * 省市区点编码
	 */
	@Column(name = "code")
	private Long code;
	
	/**
	 * 省市区点名称
	 */
	@Column(name = "name")
    private String name;
	
	/**
	 * 省市区点级别
	 */
	@Column(name = "level")
    private Integer level;
	
	/**
	 * 省市区点父级id
	 */
	@Column(name = "parent_code")
    private Long parentCode;
	
	/**
	 * 状态：0：删除；1正常
	 */
	@Column(name = "status")
    private Integer status;
    
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
	 * @return the code
	 */
	public Long getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Long code) {
		this.code = code;
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
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the parentCode
	 */
	public Long getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode the parentCode to set
	 */
	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
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

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Area [id=" + id + ", code=" + code + ", name=" + name + ", level=" + level + ", parentCode="
				+ parentCode + ", status=" + status + "]";
	}
}
