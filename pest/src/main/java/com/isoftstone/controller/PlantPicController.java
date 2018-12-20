package com.isoftstone.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.isoftstone.entity.model.Page;
import com.isoftstone.entity.model.Picture;
import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.PicturePojo;
import com.isoftstone.service.PictureService;

import tk.mybatis.mapper.util.StringUtil;

/**
 * Created by jnjua on 2017/11/24.
 */
@RestController
@RequestMapping("/plantPicPage")
public class PlantPicController {
	protected static final Logger logger = LoggerFactory.getLogger(PlantPicController.class);
	@Resource
	private PictureService pictureService;

	/**
	 * 方法描述：图片信息分页查询
	 * <p>
	 * POST / picture/findPicByMac
	 *
	 * @author jnjua
	 * @param picture
	 *            图片信息模型
	 * @param
	 * @return 图片信息列表分袂
	 * @since 2017年11月24日
	 * @see
	 */
	@RequestMapping(value = "/findPicByMac")
	public Page findPicByMac(Picture picture, Page page) {

		int start = (page.getPage() - 1) * page.getLimit();
		PageInfo<List<PicturePojo>> pageUsers = pictureService.findPicByMac(picture, start, page.getLimit());
		page.setData(pageUsers.getList());
		page.setCount(pageUsers.getTotal());
		return page;

	}

	/**
	 * 删除图片信息
	 * <p>
	 * POST /picture/delPic
	 *
	 * @author jnjua
	 * @param picture
	 *            图片模型
	 * @param userSession
	 *            会话模型
	 * @return 持久化操作码
	 * @since 2017年11月24日
	 */
	@RequestMapping(value = "/delPic")
	public String delPic(@RequestParam(value = "ids") String ids) {
		AjaxResponse ajax = new AjaxResponse(true);
		try {
			String[] id = ids.split(",");
			List<Long> idList = new ArrayList<Long>();
			for (String i : id) {
				idList.add(Long.valueOf(i));
			}
			pictureService.delPic(idList);
			ajax.setCode(1);
		} catch (Exception e) {
			logger.error("【PlantPicController.delPic】 error:" + e.getMessage());
			ajax.setCode(30009);
			ajax.setMessage("删除照片失败");
		}
		return ajax.toJSONString();
	}

	@RequestMapping(value = "/viewImage")
	public void viewImage(String imgPath, HttpServletResponse response) {
		if (StringUtil.isEmpty(imgPath)){
			return;
		}
		
		File file = new File(imgPath);
		if (null == file || !file.exists() || !file.isFile()) {
			return;
		}
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType(getContentType(imgPath.substring(imgPath.lastIndexOf(".") + 1)));

		ServletOutputStream out = null;
		FileInputStream ips = null;
		try {
			out = response.getOutputStream();
			ips = new FileInputStream(file);
			int i = 0;
			byte[] buffer = new byte[1024];
			while ((i = ips.read(buffer)) != -1) {
				out.write(buffer, 0, i);
			}
			out.flush();
		} catch (Exception e) {
			logger.error("【PlantPicController.viewImage】 error:" + e.getMessage());
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (Exception e) {
					logger.error("【PlantPicController.viewImage】 error:" + e.getMessage());
				}
			}
			if (null != ips) {
				try {
					ips.close();
				} catch (Exception e) {
					logger.error("【PlantPicController.viewImage】 error:" + e.getMessage());
				}
			}
		}
	}

	private String getContentType(String imgType) {
		String contentType = "image/jpeg";
		if ("gif".equalsIgnoreCase(imgType)) {
			contentType = "image/gif";
		} else if ("png".equalsIgnoreCase(imgType)) {
			contentType = "image/png";
		} else if ("bmp".equalsIgnoreCase(imgType)) {
			contentType = "image/bmp";
		}
		return contentType;
	}

}
