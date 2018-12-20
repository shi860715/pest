package com.isoftstone.entity.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_contact_info")
public class ContactInfo implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 6558845604481745721L;
	
	/**
	 * 联系人id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * 省市区点id
	 */
	@Column(name = "area_id")
    private Long areaId;
	
	/**
	 * 联系人名字
	 */
	@Column(name = "name")
    private String name;
	
	/**
	 * 联系人电话
	 */
	@Column(name = "telephone")
    private String telephone;
	
	/**
	 * 联系人邮箱
	 */
	@Column(name = "email")
    private String email;

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
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContactInfo [id=" + id + ", areaId=" + areaId + ", name=" + name + ", telephone=" + telephone
				+ ", email=" + email + "]";
	}
	
}
