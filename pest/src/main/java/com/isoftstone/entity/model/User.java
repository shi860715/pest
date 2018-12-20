package com.isoftstone.entity.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_user")
public class User implements Serializable{
    private static final long serialVersionUID = -8736616045315083846L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    private String username;
    /**
     * 真实姓名
     */
    private String realname;
    /**
     * 密码
     */
    private String password;
    /**
     * 电话
     */
    private String telphone;
    /**
     * 省
     */
    private Integer province;
    /**
     * 市
     */
    private Integer city;
    /**
     * 区
     */
    private Integer area;
    
    /**
     * 详细地址
     */
    private String address;
    /**
     * 管理区域
     */
    private Long manageArea;
    /**
     * 是否启用
     */
    private Integer enable;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 记录是否有效0无效1有效 默认为1
     */
    private Integer status;
    /**
     * 图片地址
     */
    private String picPath;
    /**
     * 性别 1男 2女
     */
    private Integer sex;
    /**
     * 邮箱
     */
    private String email;
    
	/**
	 * @return the picPath
	 */
	public String getPicPath() {
		return picPath;
	}
	/**
	 * @param picPath the picPath to set
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * @param realname the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the telphone
     */
    public String getTelphone() {
        return telphone;
    }
    /**
     * @param telphone the telphone to set
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
    /**
     * @return the province
     */
    public Integer getProvince() {
        return province;
    }
    /**
     * @param province the province to set
     */
    public void setProvince(Integer province) {
        this.province = province;
    }
    /**
     * @return the city
     */
    public Integer getCity() {
        return city;
    }
    /**
     * @param city the city to set
     */
    public void setCity(Integer city) {
        this.city = city;
    }
    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * @return the manageArea
     */
    public Long getManageArea() {
        return manageArea;
    }
    /**
     * @param manageArea the manageArea to set
     */
    public void setManageArea(Long manageArea) {
        this.manageArea = manageArea;
    }
    /**
     * @return the enable
     */
    public Integer getEnable() {
        return enable;
    }
    /**
     * @param enable the enable to set
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
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
	 * @return the area
	 */
	public Integer getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(Integer area) {
		this.area = area;
	}
	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", telphone=" + telphone
                + ", province=" + province + ", city=" + city + ", area=" + area + ", address=" + address + ", manageArea=" + manageArea
                + ", enable=" + enable + ", remark=" + remark + ", createTime=" + createTime + ", status=" + status
                + "]";
    }
}