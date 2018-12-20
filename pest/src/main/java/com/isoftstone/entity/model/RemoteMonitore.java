package com.isoftstone.entity.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import com.isoftstone.entity.pojo.BasicPage;

@Table(name = "t_remote_monitore")
public class RemoteMonitore extends BasicPage implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 6008886605919411887L;
	
	/**
	 * 主键id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	/**
	 * 远程监控设备名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * IP地址
	 */
	@Column(name = "loginip")
	private String loginip;
	
	/**
	 * 端口号
	 */
	@Column(name = "port")
	private String port;
	
	/**
	 * 设备序列号
	 */
	@Column(name = "deviceSerial")
	private String deviceSerial;
	
	/**
	 * 设备通道号
	 */
	@Column(name = "cameraNo")
	private String cameraNo;
	
	/**
	 * 视频流畅地址
	 */
	@Column(name = "ezopen")
	private String ezopen;
	
	/**
	 * 视频高清地址
	 */
	@Column(name = "ezopen_hd")
	private String ezopenHd;
	
	/**
	 * 视频流畅地址
	 */
	@Column(name = "rtmp")
	private String rtmp;
	
	/**
	 * 视频高清地址
	 */
	@Column(name = "rtmp_hd")
	private String rtmpHd;
	
	/**
	 * 视频流畅地址
	 */
	@Column(name = "hls")
	private String hls;
	
	/**
	 * 视频高清地址
	 */
	@Column(name = "hls_hd")
	private String hlsHd;
	
	/**
	 * 远程监控设备编号
	 */
	@Column(name = "code")
	private String code;
	
	/**
	 * 地点
	 */
	@Column(name = "address")
	private String address;
	
	/**
     * 省
     */
	@Column(name = "province")
    private Long province;
	
    /**
     * 市
     */
	@Column(name = "city")
    private Long city;
    
    /**
     * 区
     */
	@Column(name = "district")
    private Long district;
    
    /**
     * 所属地域
     */
	@Column(name = "belong_area")
    private Long belongArea;
	
	/**
	 * 使用方
	 */
	@Column(name = "user")
	private String user;
	
	/**
     * 状态：0：删除，1：正常
     */
	@Column(name = "status")
    private Long status;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	private Long userId;
	
	private Long areaId;
	
	/**
	 * @return the loginip
	 */
	public String getLoginip() {
		return loginip;
	}

	/**
	 * @param loginip the loginip to set
	 */
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the deviceSerial
	 */
	public String getDeviceSerial() {
		return deviceSerial;
	}

	/**
	 * @param deviceSerial the deviceSerial to set
	 */
	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	/**
	 * @return the cameraNo
	 */
	public String getCameraNo() {
		return cameraNo;
	}

	/**
	 * @param cameraNo the cameraNo to set
	 */
	public void setCameraNo(String cameraNo) {
		this.cameraNo = cameraNo;
	}

	/**
	 * @return the rtmp
	 */
	public String getRtmp() {
		return rtmp;
	}

	/**
	 * @param rtmp the rtmp to set
	 */
	public void setRtmp(String rtmp) {
		this.rtmp = rtmp;
	}

	/**
	 * @return the rtmpHd
	 */
	public String getRtmpHd() {
		return rtmpHd;
	}

	/**
	 * @param rtmpHd the rtmpHd to set
	 */
	public void setRtmpHd(String rtmpHd) {
		this.rtmpHd = rtmpHd;
	}

	/**
	 * @return the hls
	 */
	public String getHls() {
		return hls;
	}

	/**
	 * @param hls the hls to set
	 */
	public void setHls(String hls) {
		this.hls = hls;
	}

	/**
	 * @return the hlsHd
	 */
	public String getHlsHd() {
		return hlsHd;
	}

	/**
	 * @param hlsHd the hlsHd to set
	 */
	public void setHlsHd(String hlsHd) {
		this.hlsHd = hlsHd;
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
	
	/**
	 * @return the ezopen
	 */
	public String getEzopen() {
		return ezopen;
	}

	/**
	 * @param ezopen the ezopen to set
	 */
	public void setEzopen(String ezopen) {
		this.ezopen = ezopen;
	}

	/**
	 * @return the ezopenHd
	 */
	public String getEzopenHd() {
		return ezopenHd;
	}

	/**
	 * @param ezopenHd the ezopenHd to set
	 */
	public void setEzopenHd(String ezopenHd) {
		this.ezopenHd = ezopenHd;
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
	 * @return the province
	 */
	public Long getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(Long province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public Long getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(Long city) {
		this.city = city;
	}

	/**
	 * @return the district
	 */
	public Long getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(Long district) {
		this.district = district;
	}

	/**
	 * @return the belongArea
	 */
	public Long getBelongArea() {
		return belongArea;
	}

	/**
	 * @param belongArea the belongArea to set
	 */
	public void setBelongArea(Long belongArea) {
		this.belongArea = belongArea;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the status
	 */
	public Long getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Long status) {
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
		return "RemoteMonitore [id=" + id + ", name=" + name + ", code=" + code + ", address=" + address + ", province="
				+ province + ", city=" + city + ", district=" + district + ", belongArea=" + belongArea + ", user="
				+ user + ", status=" + status + ", createTime=" + createTime + "]";
	}

}
