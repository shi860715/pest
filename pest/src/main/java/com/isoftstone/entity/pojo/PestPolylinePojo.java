package com.isoftstone.entity.pojo;

import java.io.Serializable;

public class PestPolylinePojo implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 47160231040925539L;

	private String insectName;
	
	/**
	 * 折线图展示时间
	 */
	private String time;
	
	/**
	 * 折线图展示数量
	 */
	private Long totalCount;

	/**
	 * @return the insectName
	 */
	public String getInsectName() {
		return insectName;
	}

	/**
	 * @param insectName the insectName to set
	 */
	public void setInsectName(String insectName) {
		this.insectName = insectName;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PestPolylinePojo [insectName=" + insectName + ", time=" + time + ", totalCount=" + totalCount + "]";
	}
	
}
