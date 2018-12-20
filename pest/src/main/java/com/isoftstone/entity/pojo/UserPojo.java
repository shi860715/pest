package com.isoftstone.entity.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <summary>
 * <description>
 * 
 * @author llmaoa
 * @since 2017年11月7日
 * @see [Class/Method]
 *
 */
public class UserPojo implements Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -1983639809312188500L;

    /**
     * 序列号
     */
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
     * 旧密码
     */
    private String oldPassword;
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
     * 创建时间格式化
     */
    private Date createTimeFormat;
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
     * 记录是否有效0无效1有效 默认为1
     */
    private Integer status;
    
    /*****************扩展属性*****************/
    
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 省份名称
     */
    private String provinceName;
    /**
     * 市级名称
     */
    private String cityName;
    /**
     * 区级名称
     */
    private String areaName;
    /**
     * 管理区域名称
     */
    private String manageAreaName;
    
    /**
	 * @return the createTimeFormat
	 */
	public Date getCreateTimeFormat() {
		return createTimeFormat;
	}

	/**
	 * @param createTimeFormat the createTimeFormat to set
	 */
	public void setCreateTimeFormat(Date createTimeFormat) {
		this.createTimeFormat = createTimeFormat;
	}

	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

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
     * @return the roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param provinceName the provinceName to set
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return the areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName the areaName to set
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * @return the manageAreaName
     */
    public String getManageAreaName() {
        return manageAreaName;
    }

    /**
     * @param manageAreaName the manageAreaName to set
     */
    public void setManageAreaName(String manageAreaName) {
        this.manageAreaName = manageAreaName;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserPojo [id=" + id + ", username=" + username + ", realname=" + realname + ", password=" + password
                + ", telphone=" + telphone + ", province=" + province + ", city=" + city + ", area=" + area
                + ", address=" + address + ", manageArea=" + manageArea + ", enable=" + enable + ", remark=" + remark
                + ", createTime=" + createTime + ", status=" + status + ", roleId=" + roleId + ", roleName=" + roleName
                + ", provinceName=" + provinceName + ", cityName=" + cityName + ", areaName=" + areaName
                + ", manageAreaName=" + manageAreaName + "]";
    }
}
