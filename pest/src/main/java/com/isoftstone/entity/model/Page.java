package com.isoftstone.entity.model;

import java.util.Collection;

public class Page {
    /**
     * 当前页码
     */
    private Integer page = 1;
    /**
     * 每页数量
     */
    private Integer limit = 10;
    /**
     * 返回状态码 默认为0
     */
    private Integer code = 0;
    /**
     * 消息
     */
    private String msg;
    /**
     * 记录总条数
     */
    private Long count;
    /**
     * 分页树立
     */
    private Collection<?> data;
    /**
     * @return the page
     */
    public Integer getPage() {
        return page;
    }
    /**
     * @param page the page to set
     */
    public void setPage(Integer page) {
        this.page = page;
    }
    /**
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }
    /**
     * @param limit the limit to set
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }
    /**
     * @param code the code to set
     */
    public void setCode(Integer code) {
        this.code = code;
    }
    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    /**
     * @return the count
     */
    public Long getCount() {
        return count;
    }
    /**
     * @param count the count to set
     */
    public void setCount(Long count) {
        this.count = count;
    }
    /**
     * @return the data
     */
    public Collection<?> getData() {
        return data;
    }
    /**
     * @param data the data to set
     */
    public void setData(Collection<?> data) {
        this.data = data;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Page [page=" + page + ", limit=" + limit + ", code=" + code + ", msg=" + msg + ", count=" + count
                + ", data=" + data +"]";
    }
}
