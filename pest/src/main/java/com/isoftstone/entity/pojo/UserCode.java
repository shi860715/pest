package com.isoftstone.entity.pojo;

public class UserCode {

    /**
     * 电话
     */
    private String telphone;
    /**
     * 手机登录使用短信码
     */
    private String code;
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
     * @return the code
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
}
