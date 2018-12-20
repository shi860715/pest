package com.isoftstone.entity.pojo;

import com.isoftstone.entity.model.Picture;

/**
 * <summary>
 * <description>
 * 
 * @author jnjua
 * @since 2017年11月23日
 * @see [Class/Method]
 *
 */
public class PicturePojo extends Picture {  
    /**
	 * 
	 */
	private static final long serialVersionUID = -3045248598823914949L;
	/**
     * 图片路径
     */
    private String imgPath;
    /**
     * 图片名称
     */
    private String imgName;
    /**
     * 设备编码
     */
    private String deviceCode;
    /**
     * 设备名称
     */
    private String deviceName;
	/**
	 * @return the deviceCode
	 */
	public String getDeviceCode() {
		return deviceCode;
	}
	/**
	 * @param deviceCode the deviceCode to set
	 */
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * @return the imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}
	/**
	 * @param imgPath the imgPath to set
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	/**
	 * @return the imgName
	 */
	public String getImgName() {
		return imgName;
	}
	/**
	 * @param imgName the imgName to set
	 */
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
 
}
