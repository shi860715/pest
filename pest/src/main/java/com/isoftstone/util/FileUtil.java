package com.isoftstone.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.isoftstone.entity.pojo.RawFilePojo;

/**
 * 文件处理类 <description>
 * 
 * @author llmaoa
 * @since 2017年11月10日
 * @see [Class/Method]
 *
 */
public class FileUtil {
	protected static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 单文件上传
	 * 
	 * @author llmaoa
	 * @param file 文件
     * @param filePath 上传根目录
     * @param uploadPath 上传子目录
	 * @return
	 * @throws Exception
	 * @since 2017年11月13日
	 * @see
	 */
	public static List<RawFilePojo> uploadFile(HttpServletRequest request, String filePath, String uploadPath) {

		List<RawFilePojo> rfList = new ArrayList<RawFilePojo>();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		// 取得request中的所有文件名
		Iterator<String> itor = multiRequest.getFileNames();
		List<MultipartFile> fileList = null;
		BufferedOutputStream out = null;
		FileOutputStream fos = null;
		try {
			while (itor.hasNext()) {
			    //判断文件夹是否存在 没有则创建
			    File fp = new File(filePath + uploadPath);
                if(!fp.exists()){
                    fp.mkdirs();
                }
				// 取得上传文件
				fileList = multiRequest.getFiles(itor.next());
				if (fileList != null && !fileList.isEmpty()) {
					for (MultipartFile file : fileList) {
						RawFilePojo rfp = new RawFilePojo();
						String originName = file.getOriginalFilename();
						logger.info("[FileUtil.uploadFile] originName:" + originName);
						String newName = EncryptUtil.md5(originName + String.valueOf(UUID.randomUUID()));
						// 后缀名
						String suffix = originName.substring(originName.lastIndexOf("."));

						rfp.setOriginName(originName);
						rfp.setName(newName + suffix);
                        rfp.setPath("/imgView/"+uploadPath+"/"+rfp.getName());
						fos = new FileOutputStream(new File(filePath + File.separator + uploadPath + File.separator + rfp.getName()));
						out = new BufferedOutputStream(fos);
						out.write(file.getBytes());
						rfList.add(rfp);
					}
				}
			}
		}
		catch (FileNotFoundException e) {
			logger.error("【FileUtil.uploadFile】 error:" + e.getMessage());
		}
		catch (IOException ioe) {
			logger.error("【FileUtil.uploadFile】 error:" + ioe.getMessage());
		}
		finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("【FileUtil.uploadFile】 error:" + e.getMessage());
				}
			}
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.error("【FileUtil.uploadFile】 error:" + e.getMessage());
				}
			}
		}
		return rfList;
	}
}
