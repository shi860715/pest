package com.isoftstone.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.isoftstone.commons.Constants;
import com.isoftstone.entity.model.Area;
import com.isoftstone.entity.model.Dictionary;
import com.isoftstone.entity.pojo.AreaPojo;
import com.isoftstone.service.AreaService;
import com.isoftstone.service.DictionaryService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class LoadDataConfig implements CommandLineRunner {
    /**
     * 日志系统
     */
    private Log logger = LogFactory.getLog(LoadDataConfig.class);

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private AreaService areaService;

    /**
     * jedis池
     */
    @Resource
    private JedisPool jedisPool;

    @Override
    public void run(String... arg0) throws Exception {
        //加载邮件配置项
        List<Dictionary> dList = dictionaryService.fuzzyQueryByKey("mail.");
        Map<String, String> map = new HashMap<String, String>();
        for (Dictionary d : dList) {
            map.put(d.getKey(), d.getValue());
        }

        //加载地域信息
        List<Area> areaList = areaService.getAllArea();
        List<Area> pList = areaService.getProvince();

        Map<Long, List<Area>> areaMap = new HashMap<Long, List<Area>>();
        for (Area area : areaList) {
            List<Area> aList = null;
            if (null != areaMap.get(area.getParentCode())) {
                aList = areaMap.get(area.getParentCode());
                aList.add(area);
            }
            else {
                aList = new ArrayList<Area>();
                aList.add(area);
            }
            areaMap.put(area.getParentCode(), aList);
        }
        List<AreaPojo> apList = modelToPojo(pList);
        handleData(apList, areaMap);
        
        logger.debug("AreaTreeList is " + JSONArray.toJSON(apList));
        Jedis redis = jedisPool.getResource();
        //将树形地域数据放入缓存
        redis.set(Constants.AreaConfig.AREA_TREE_LIST, JSONArray.toJSONString(apList));
        //将邮件发送配置项放入缓存
        redis.set(Constants.EmailConfig.MAIL_SERVER, map.get(Constants.EmailConfig.MAIL_SERVER));
        redis.set(Constants.EmailConfig.MAIL_USER, map.get(Constants.EmailConfig.MAIL_USER));
        redis.set(Constants.EmailConfig.MAIL_PASSWORD, map.get(Constants.EmailConfig.MAIL_PASSWORD));
        redis.set(Constants.EmailConfig.MAIL_ADDRESSER, map.get(Constants.EmailConfig.MAIL_ADDRESSER));
        redis.set(Constants.EmailConfig.MAIL_SSL, map.get(Constants.EmailConfig.MAIL_SSL));

        redis.close();
    }

    /**
     * 递归省市区数据
     * 
     * @author llmaoa
     * @param pList
     * @param areaMap
     * @return
     * @since 2017年12月5日
     * @see
     */
    private void handleData(List<AreaPojo> pList, Map<Long, List<Area>> areaMap) {
        for (AreaPojo ap : pList) {
            if (null != ap.getCode()) {
                List<AreaPojo> aList = modelToPojo(areaMap.get(ap.getCode()));
                if (null != aList) {
                    ap.setApList(aList);
                    handleData(aList, areaMap);
                }
            }
        }
    }

    /**
     * model转为Pojo
     * 
     * @author llmaoa
     * @param area
     * @return
     * @since 2017年12月5日
     * @see
     */
    private List<AreaPojo> modelToPojo(List<Area> pList) {
        if (null != pList) {
            List<AreaPojo> apList = new ArrayList<AreaPojo>();
            AreaPojo ap = null;
            for (Area area : pList) {
                ap = new AreaPojo();
                ap.setId(area.getId());
                ap.setCode(area.getCode());
                ap.setLevel(area.getLevel());
                ap.setName(area.getName());
                ap.setParentCode(area.getParentCode());
                ap.setStatus(area.getStatus());
                apList.add(ap);
            }
            return apList;
        }
        return null;
    }
}
