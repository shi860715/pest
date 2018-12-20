package com.isoftstone.mapper.main;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isoftstone.entity.model.Picture;

public interface PictureMapper {

	
	/**
     * 
     * 查询所有图片信息
     *
     * @author jnjua
     * @param 
     * @return 图片列表
     * @since 2017年11月23日
     */
    public List<Picture> findPicByMac(Picture picture);

    /**
     * 删除图片信息
     *
     * @author jnjua
     * @param Picture
     *            图片模型
     * @return 持久化操作码
     * @since 2017年11月23日
     */
    public int delPic(@Param("idList") List<Long> idList);
}