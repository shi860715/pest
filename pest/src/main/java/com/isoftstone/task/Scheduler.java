package com.isoftstone.task;

import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.isoftstone.commons.Constants;
import com.isoftstone.service.DeviceService;
import com.isoftstone.service.WarningDataService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class Scheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private DeviceService deviceService;

    @Resource
    private WarningDataService warningDataService;
    
    /**
     * jedis池
     */
    @Resource
    private JedisPool jedisPool;
    
    /**
     * 设备到期定时器+短信邮件发送
     * 每半小时钟执行一次  
     * @author llmaoa
     * @since 2017年11月30日
     * @see 
     */
    @Scheduled(cron="0 0,30 * * * ?")
    public void expireDviceTask() {
        logger.debug("开始进入设备到期扫描 start...");
        deviceService.taskExpireDevice();
        logger.debug("设备到期扫描 end.");
    }
  
    /**
     * 预警数据定时器
     * 每天早上9点发送一次消息
     * @author llmaoa
     * @since 2017年11月30日
     * @see 
     */
    @Scheduled(cron="0 0 9 * * ?")
    public void warningDataTask(){
        logger.debug("预警数据扫面 start...");
        warningDataService.taskWarningData();
        logger.debug("预警数据扫面 end.");
    }
    
    /**
     * 获取区域定时器
     * 每天早上2点发送一次消息
     * @author llmaoa
     * @since 2017年11月30日
     * @see 
     */
    @Scheduled(cron="0 0 2 * * ?")
    public void areaDataTask(){
        logger.debug("地域数据扫描 start...");
        Jedis redis = jedisPool.getResource();
		String str = redis.get(Constants.AreaConfig.AREA_TREE_LIST);
        FileWriter writer = null;
        try {
            writer = new FileWriter("\\dev\\pest\\pest-1.0\\WEB-INF\\classes\\static\\js\\city.json");
            writer.write(str);
            writer.flush();
        } catch (IOException e) {
        	logger.error("【Scheduler.areaDataTask】 error:" + e.getMessage());
        }
        finally{
            try {
            	if( null != writer){
            		writer.close();
            	}
			} catch (IOException e) {
				logger.error("【Scheduler.areaDataTask】 error:" + e.getMessage());
			}
        }
        logger.debug("地域数据扫描 end.");
    }

   /* @Scheduled(fixedRate=20000)  
    public void testTasks() {      
        logger.info("每20秒执行一次。开始……");  
        //statusTask.healthCheck();  
        logger.info("每20秒执行一次。结束。");  
    }*/
}
