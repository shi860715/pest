package com.isoftstone.entity.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_dictionary")
public class Dictionary implements Serializable {

    /**
	 * 序列号
	 */
    private static final long serialVersionUID = -206608814295112719L;

    /**
	 * 字典表
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * 字典表key
	 */
	@Column(name = "key")
	private String key;
	
	/**
	 * 字典表value
	 */
	@Column(name = "value")
    private String value;
	
	/**
	 * 描述
	 */
	@Column(name = "description")
    private String description;

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
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Dictionary [id=" + id + ", key=" + key + ", value=" + value + ", description=" + description + "]";
    }
}
