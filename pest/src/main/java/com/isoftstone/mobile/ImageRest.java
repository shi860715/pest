package com.isoftstone.mobile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isoftstone.entity.pojo.AjaxResponse;
import com.isoftstone.entity.pojo.RawFilePojo;
import com.isoftstone.util.FileUtil;

@Controller
@RequestMapping("/m/image")
public class ImageRest {
    
	protected static final Logger logger = LoggerFactory.getLogger(ImageRest.class);
	
    //获取配置文件中图片的路径
   @Value("${upload.uploadPath}")
   private String uploadPath;
    
    /**
     * 手机端上传单张图片
     * post /m/image/upload
     * @author jnjua
     * @param request
     * @return
     * @since 2017年12月22日
     * @see 
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request, String path) {
    	AjaxResponse ajax = new AjaxResponse(true);
        try {
            List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, path);
            
            if (CollectionUtils.isNotEmpty(rfList)) {
            	ajax.setCode(1);
            	ajax.setMessage("图片上传成功！");
                ajax.setData(rfList.get(0));
            }
        }
        catch (Exception e) {
        	logger.error("【ImageRest.upload】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("单个图片上传失败【90022】");
        }
        return ajax.toJSONString();
    }
    
    /**
     * 手机端上传多张图片
     * post /m/image/uploadMore
     * @author jnjua
     * @param request
     * @return
     * @since 2017年12月22日
     * @see 
     */
    @RequestMapping(value = "/uploadMore", method = RequestMethod.POST)
    @ResponseBody
    public String uploadMore(HttpServletRequest request, String path) {
    	AjaxResponse ajax = new AjaxResponse(true);
        try {
        	 List<RawFilePojo> rfList = FileUtil.uploadFile(request, uploadPath, path);
             ajax.setData(rfList);
        }
        catch (Exception e) {
        	logger.error("【ImageRest.uploadMore】 error:" + e.getMessage());
            ajax.toError();
            ajax.setMessage("多个图片上传失败【90023】");
        }
        return ajax.toJSONString();
    }
    
}
