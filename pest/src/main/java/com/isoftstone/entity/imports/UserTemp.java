package com.isoftstone.entity.imports;

import com.isoftstone.commons.Template;

@Template("用户信息")
public class UserTemp {
    /**
     * 数据行号
     */
    private Long row;
    @Template("用户名")
    private String username;
    @Template("密码")
    private String password;
    @Template("真实姓名")
    private String realname;
    @Template("角色")
    private String rolename;
    @Template("联系电话")
    private String telphone;
    @Template("省份")
    private String provinceName;
    @Template("市级")
    private String cityName;
    @Template("区级")
    private String areaName;
    @Template("管理区域")
    private String manageAreaName;
    @Template("详细地址")
    private String address;
    @Template("是否启用")
    private String enable;
    @Template("备注")
    private String remark;
    /**
     * @return the row
     */
    public Long getRow() {
        return row;
    }
    /**
     * @param row the row to set
     */
    public void setRow(Long row) {
        this.row = row;
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
     * @return the rolename
     */
    public String getRolename() {
        return rolename;
    }
    /**
     * @param rolename the rolename to set
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
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
     * @return the enable
     */
    public String getEnable() {
        return enable;
    }
    /**
     * @param ebable the ebable to set
     */
    public void setEnable(String enable) {
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
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserTemp [row=" + row + ", username=" + username + ", password=" + password + ", realname=" + realname
                + ", rolename=" + rolename + ", telphone=" + telphone + ", provineName=" + provinceName + ", cityName="
                + cityName + ", areaName=" + areaName + ", manageAreaName=" + manageAreaName + ", address=" + address
                + ", enable=" + enable + ", remark=" + remark + "]";
    }
}
