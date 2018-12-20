package com.isoftstone.commons;

/**
 * 业务常量表
 * @author llmaoa
 * @since 2017年11月22日
 * @see [Class/Method]
 *
 */
public class Constants {

    public static final class Commons {

        /**
         * UTF-8 中文字符
         */
        public static final String CHARACTER_SET = "UTF-8";
        
        /**
         * 登录验证码
         */
        public static final String VERIFY_CODE = "verifyCode";
        
        /**
         * WEB用户Session key
         */
        public static final String SESSION_USER = "webUser";
    }
    
    
    
    /**
     * 邮件配置项
     * 
     * @author llmaoa
     * @since 2017年11月29日
     *
     */
    public static final class EmailConfig{
        /**
         * 邮箱服务器
         */
        public static final String MAIL_SERVER = "mail.server";

        /**
         * 用户名
         */
        public static final String MAIL_USER = "mail.user";

        /**
         * 密码
         */
        public static final String MAIL_PASSWORD = "mail.password";

        /**
         * 发件人邮箱
         */
        public static final String MAIL_ADDRESSER = "mail.addresser";

        /**
         * 邮箱服务器ssl端口
         */
        public static final String MAIL_SSL = "mail.ssl";

    }
    
    /**
     * 地域相关参数 
     * @author llmaoa
     * @since 2017年12月6日
     * @see [Class/Method]
     *
     */
    public static final class AreaConfig{
        /**
         * 地域树形结构
         */
        public static final String AREA_TREE_LIST = "areaTreeList";
        
        /**
         * 地域ID子集key
         */
        public static final String AREA_ID = "areaId_";

    }
}
