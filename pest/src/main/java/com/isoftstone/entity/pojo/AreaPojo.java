package com.isoftstone.entity.pojo;

import java.util.List;

import com.isoftstone.entity.model.Area;

public class AreaPojo extends Area {
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = -6297316798436573420L;
    
    private List<AreaPojo> apList;

    /**
     * @return the apList
     */
    public List<AreaPojo> getApList() {
        return apList;
    }

    /**
     * @param apList the apList to set
     */
    public void setApList(List<AreaPojo> apList) {
        this.apList = apList;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AreaPojo [apList=" + apList + "]";
    }
}
