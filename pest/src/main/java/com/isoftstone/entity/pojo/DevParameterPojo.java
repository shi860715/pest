package com.isoftstone.entity.pojo;

import com.isoftstone.entity.model.DevParameter;

public class DevParameterPojo extends DevParameter {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2211920644984163967L;
	
	/**
	 * 光照
	 */
	private String sunshineTime;
	
	/**
	 * 运行开始时间
	 */
	private String runStart;
	
	/**
	 * 运行结束时间
	 */
	private String runEnd;
	
	/**
	 * 工作周期
	 */
	private String workTime;
	
	/**
	 * 回传周期
	 */
	private String backTime;
	
	/**
	 * 加热温度
	 */
	private String heat;
	
	/**
	 * 烘烤时间
	 */
	private String roastTime;
	
	/**
	 * 清虫时间
	 */
	private String cleanTime;
	
	/**
	 * 休眠开始时间
	 */
	private String sleepStart;
	
	/**
	 * 休眠结束时间
	 */
	private String sleepEnd;

	/**
	 * @return the sunshineTime
	 */
	public String getSunshineTime() {
		return sunshineTime;
	}

	/**
	 * @param sunshineTime the sunshineTime to set
	 */
	public void setSunshineTime(String sunshineTime) {
		this.sunshineTime = sunshineTime;
	}

	/**
	 * @return the runStart
	 */
	public String getRunStart() {
		return runStart;
	}

	/**
	 * @param runStart the runStart to set
	 */
	public void setRunStart(String runStart) {
		this.runStart = runStart;
	}

	/**
	 * @return the runEnd
	 */
	public String getRunEnd() {
		return runEnd;
	}

	/**
	 * @param runEnd the runEnd to set
	 */
	public void setRunEnd(String runEnd) {
		this.runEnd = runEnd;
	}

	/**
	 * @return the workTime
	 */
	public String getWorkTime() {
		return workTime;
	}

	/**
	 * @param workTime the workTime to set
	 */
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	/**
	 * @return the backTime
	 */
	public String getBackTime() {
		return backTime;
	}

	/**
	 * @param backTime the backTime to set
	 */
	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}

	/**
	 * @return the heat
	 */
	public String getHeat() {
		return heat;
	}

	/**
	 * @param heat the heat to set
	 */
	public void setHeat(String heat) {
		this.heat = heat;
	}

	/**
	 * @return the roastTime
	 */
	public String getRoastTime() {
		return roastTime;
	}

	/**
	 * @param roastTime the roastTime to set
	 */
	public void setRoastTime(String roastTime) {
		this.roastTime = roastTime;
	}

	/**
	 * @return the cleanTime
	 */
	public String getCleanTime() {
		return cleanTime;
	}

	/**
	 * @param cleanTime the cleanTime to set
	 */
	public void setCleanTime(String cleanTime) {
		this.cleanTime = cleanTime;
	}

	/**
	 * @return the sleepStart
	 */
	public String getSleepStart() {
		return sleepStart;
	}

	/**
	 * @param sleepStart the sleepStart to set
	 */
	public void setSleepStart(String sleepStart) {
		this.sleepStart = sleepStart;
	}

	/**
	 * @return the sleepEnd
	 */
	public String getSleepEnd() {
		return sleepEnd;
	}

	/**
	 * @param sleepEnd the sleepEnd to set
	 */
	public void setSleepEnd(String sleepEnd) {
		this.sleepEnd = sleepEnd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DevParameterPojo [sunshineTime=" + sunshineTime + ", runStart=" + runStart + ", runEnd=" + runEnd
				+ ", workTime=" + workTime + ", backTime=" + backTime + ", heat=" + heat + ", roastTime=" + roastTime
				+ ", cleanTime=" + cleanTime + ", sleepStart=" + sleepStart + ", sleepEnd=" + sleepEnd + "]";
	}
	
}
