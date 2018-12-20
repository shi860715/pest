package com.isoftstone.entity.pojo;

public class BasicPage {
    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * @return the currPage
     */
    public Integer getPageNum() {
        return pageNum;
    }
    /**
     * @param currPage the currPage to set
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }
    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BasicPage [currPage=" + pageNum + ", pageSize=" + pageSize + "]";
    }
}
