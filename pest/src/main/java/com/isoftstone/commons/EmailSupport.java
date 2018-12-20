/**
 * EmailSupport.java 2016年11月11日
 * 
 * Copyright 2001-2016 iSoftStone All rights reserved.
 * iSoftStone PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.isoftstone.commons;

import java.util.Arrays;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Component;

import com.isoftstone.commons.Constants.Commons;
import com.isoftstone.commons.Constants.EmailConfig;
import com.isoftstone.entity.pojo.MessageMailPojo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 邮件发送支持
 * 
 * @author ylmiaoa
 * @since 2016年11月11日
 *
 */
@Component
public class EmailSupport {

    /**
     * logger
     */
    private Log logger = LogFactory.getLog(EmailSupport.class);
    /**
     * jedis池
     */
    @Resource
    private JedisPool  jedisPool;
    
    /**
     * 发送邮件
     * 异步
     *
     * @author ylmiaoa
     * @param title
     *            标题
     * @param content
     *            内容
     * @param receivers
     *            收件人
     * @since 2016年11月11日
     */
    public void send(String title, String content, String... receivers) {
        Runnable run = new Runnable()
        {
            @Override
            public void run() {
                sendSync(title, content, receivers);
            }
        };

        Thread trd = new Thread(run, "Thread-Email-Sender");
        trd.start();
    }

    /**
     * 发送邮件
     * 异步
     *
     * @author zoe(zhongpanc@isoftstone.com)
     * @param mail
     *            邮件内容
     * @param receivers
     *            接收人列表
     * @since 2017年4月7日
     */
    public void send(MessageMailPojo mail, Set<String> receivers) {
        String[] addresses = receivers.toArray(new String[receivers.size()]);

        this.send(mail.getTitle(), mail.getContent(), addresses);
    }

    /**
     * 发送邮件
     * 同步
     *
     * @author ylmiaoa
     * @param title
     *            标题
     * @param content
     *            内容
     * @param receivers
     *            收件人
     * @since 2016年11月11日
     */
    public void sendSync(String title, String content, String... receivers) {
        // 重试计数
        int retry = 0;
        while (retry < 5) {
            retry++;
            Jedis redis = jedisPool.getResource();
            try {
                // 查询邮件服务配置信息

                // Email对象
                HtmlEmail email = new HtmlEmail();
                email.setCharset(Commons.CHARACTER_SET);

                // smtp host 
                email.setHostName(redis.get(EmailConfig.MAIL_SERVER));

                // smtp ssl
                String sslPort = redis.get(EmailConfig.MAIL_SSL);
                if (StringUtils.isNotBlank(sslPort)) {
                    email.setSslSmtpPort(sslPort);
                    email.setSSLOnConnect(true);
                }

                // 登陆邮件服务器的用户名和密码
                email.setAuthentication(redis.get(EmailConfig.MAIL_USER),
                        redis.get(EmailConfig.MAIL_PASSWORD));

                // 发送人
                email.setFrom(redis.get(EmailConfig.MAIL_ADDRESSER), "生兴防治");

                // 接收人
                email.addTo(receivers);

                // 标题
                email.setSubject(title);

                // 邮件内容
                email.setHtmlMsg(content);

                email.send();

                logger.info("【" + title + "】邮件，发送给：" + Arrays.toString(receivers) + "成功。");
                retry = 999;
            }
            catch (Exception e) {
                logger.error("EmailSupport sendSync error.", e);

                logger.error("【" + title + "】邮件，发送给：" + Arrays.toString(receivers) + "失败。发送次数：" + retry);

                try {
                    Thread.sleep(1800000L);
                }
                catch (InterruptedException e1) {
                    Thread.currentThread().interrupt();
                    logger.error("EmailSupport retry sendSync Email Thread sleep error.", e1);
                }
            }finally {
                redis.close();
            }
        }
    }
}
