package com.isoftstone.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.Picture;
import com.isoftstone.entity.pojo.PicturePojo;

/**
 * Created by jnjua on 2017/11/23.
 */
public interface PictureService extends IService<Picture>{

    PageInfo<List<PicturePojo>> findPicByMac(Picture picture, int start, int length);
    
    int delPic(List<Long> idList);
}
