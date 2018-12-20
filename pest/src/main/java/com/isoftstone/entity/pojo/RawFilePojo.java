package com.isoftstone.entity.pojo;

public class RawFilePojo {
    /**
     * 原文件名
     */
    private String originName;
    /**
     * 文件名 MD5（文件名+UUID）摘要
     */
    private String name;
    /**
     * 路径
     */
    private String path;
    /**
     * @return the originName
     */
    public String getOriginName() {
        return originName;
    }
    /**
     * @param originName the originName to set
     */
    public void setOriginName(String originName) {
        this.originName = originName;
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
     * @return the path
     */
    public String getPath() {
        return path;
    }
    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RawFilePojo [originName=" + originName + ", name=" + name + ", path=" + path + "]";
    }
}
