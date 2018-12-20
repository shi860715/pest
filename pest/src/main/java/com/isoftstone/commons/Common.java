package com.isoftstone.commons;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Common {
    
    protected static final Logger logger = LoggerFactory.getLogger(Common.class);
    
    private static final int BUFFER_SIZE = 20 * 1024; // 上传文件时的缓存大小

    /**
     * 将OBJECT对象转换成String，如果obj为NULL,则返回空
     * 
     * @param obj
     * @return String
     */
    public static String obj2Str(Object obj) {
        if (null == obj) {
            return "";
        }
        else {
            return obj.toString();
        }
    }
    
    /**
     * 判断list是否为NULL或者长度为0；
     * 
     * @param list
     * @return boolean
     */
    public static boolean isEmptyList(List<?> list) {
        if (null == list || list.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 判断字符串是否为空或null
     * 
     * @param str
     *            需要判断是否为空的字符串
     * @return 如果字符串为空,则返回true;如果字符串非空,则返回false
     */
    public static boolean isNullStr(String str) {
        if (str == null || "".equals(str.trim()) || "NULL".equals(str.toUpperCase())) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * 验证数字类型(float,int)
     */
    public static boolean isNumber(String str) {
        String reg = "([+-]?)[0-9]{0,10}(\\.?)[0-9]{0,10}";
        Pattern p = Pattern.compile(reg);
        return p.matcher(str).matches();
    }
    
    /**
     * 对null字符串的处理为空
     * 
     * @param str
     *            需要处理的字符串
     * @return 返回结果
     */
    public static String delNull(String str) {
        if (isNullStr(str)) {
            str = "";
        }
        return str.trim();
    }
    
    /**
     * @param str
     *            字符
     * @return 把字符转化为int
     */
    public static int getStr2Int(String str) {
        int res = 0;
        try {
            res = Integer.parseInt(str);
        }
        catch (Exception e) {
            res = 0;
        }
        return res;
    }
    
    /**
     * @param str
     *            字符
     * @return 把字符转化为int
     */
    public static long getStr2Long(String str) {
        Long res = 0L;
        try {
            res = Long.parseLong(str);
        }
        catch (Exception e) {
            res = 0L;
        }
        return res;
    }
    
    /**
     * 小于10的数字前补零
     * 
     * @param i
     *            数字
     * @return 小于10的数字前补零
     */
    public static String strNum(int i) {
        String num = "0";
        if (i < 10) {
            num = "0" + i;
        }
        else {
            num = String.valueOf(i);
        }
        return num;
    }
    
    /**
     * 根据VecMap集合获取json格式字符串。
     * 
     * @param map
     * @return
     */
    public static String getResultJsonByVecMap(Vector<Map<String, String>> list) {
    	StringBuffer result = new StringBuffer("");
        if (list != null && list.size() > 0) {
        	result.append("[");
            for (int i = 0; i < list.size(); i++) {
                if (i != 0) {
                	result.append(",");
                }
                result.append("{");
                result.append("'key':'" + list.get(i).get("KEY") + "'");
                result.append(",");
                result.append("'value':'" + list.get(i).get("VALUE") + "'");
                result.append("}");
            }
            result.append("]");
        }
        return result.toString();
    }
    
    /**
     * 判断IP是否合法
     * 
     * @param ip
     * @return false=不合法，true=合法
     */
    public static boolean isIp(String ipAddress) {
        if (isNullStr(ipAddress)) {
            return false;
        }
        ipAddress = ipAddress.trim();
        String test =
            "(\\d|[0-9]\\d|[01]\\d\\d|[2][0-4]\\d|[2][5][0-5])(\\.(\\d|[0-9]\\d|[01]\\d\\d|[2][0-4]\\d|[2][5][0-5])){3}";
        Pattern pattern = Pattern.compile(test);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }
    
    /**
     * 判断是否是闰年
     * 
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        
        if ((0 == year % 4 && 0 != year % 100) || (0 == year % 4 && 0 == year % 400)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 判断字符串是否为数字 当str为空时返回false
     * 
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        if (Common.isNullStr(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher num = pattern.matcher(str);
        if (!num.matches()) {
            return false;
        }
        return true;
    }
    
    
    
    /**
     * 日期验证
     * 
     * @param str
     *            格式 如2009-10-01
     * @return
     */
    public static boolean isValidDate(String str) {
        Pattern pattern =
            Pattern.compile("^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$");
        Matcher m = pattern.matcher(str);
        if (!m.matches()) {
            return false;
        }
        return true;
    }
    
    /**
     * 验证邮政编码
     * 
     * @param str
     * @return
     */
    public static boolean isPostalcode(String str) {
        if (isNullStr(str)) {
            return false;
        }
        str = str.trim();
        Pattern pattern = Pattern.compile("^[1-9]\\d{5}(?!\\d)$");
        Matcher postalcode = pattern.matcher(str);
        if (!postalcode.matches()) {
            return false;
        }
        return true;
    }
    
    /**
     * 获得当前日期和星期 如：2011年10月12日 星期二
     * 
     * @return String 
     */
    public static String getNowDate() {
        StringBuilder nowDate = new StringBuilder();
        Calendar cl = Calendar.getInstance();
        nowDate.append(cl.get(Calendar.YEAR) + "年");
        nowDate.append((cl.get(Calendar.MONTH) + 1) + "月");
        nowDate.append(cl.get(Calendar.DAY_OF_MONTH) + "日");
        int week = cl.get(Calendar.DAY_OF_WEEK) - 1;
        String[] weekArray = new String[] {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        if (week >= 0 && week < weekArray.length) {
            nowDate.append("  " + weekArray[week]);
        }
        return nowDate.toString();
    }
       
    /**
     * 拼接两个list
     * @param baseList
     * @param addList
     * @return
     */
    public static List<Map<String, Object>> getConnList(List<Map<String, Object>> firstList,
        List<Map<String, Object>> secondList) {
        if (!isEmptyList(firstList) && !isEmptyList(secondList)) {
            for (Map<String, Object> addMap : secondList) {
                firstList.add(addMap);
            }
            
        }
        else if (isEmptyList(firstList) && !isEmptyList(secondList)) {
            firstList = secondList;
        }
        return firstList;
    }
    
    /**
     * 生成指定位数的随机数
     * @param pwd_len
     * @return
     */
    public static synchronized String genRandomNum(int pwd_len) {
        int count = 0;
        char str[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            int i = Math.abs(r.nextInt(10));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }
    
    
    /**
     * 将IP地址中的0去掉。即10.05.010.002转换成10.5.10.2
     * 
     * @param ipStr
     *            字符串类型的IP
     * @return String 去0后的IP
     */
    public static String delIP0(String ipStr) {
        if (null != ipStr && !ipStr.equals("")) {
            String[] ipArray = ipStr.split("\\.");
            if (null != ipArray && ipArray.length == 4) {
                return Integer.parseInt(ipArray[0]) + "." + Integer.parseInt(ipArray[1]) + "."
                    + Integer.parseInt(ipArray[2]) + "." + Integer.parseInt(ipArray[3]);
            }
        }
        return "";
    }
    
    /**
     * 文件拷贝
     * 
     * @param src
     *            原文件
     * @param dst
     *            目标文件
     * @param bufferSize
     *            缓冲区大小
     */
    public static boolean fileCopy(File src, File dst, int bufferSize) {
        // 文件大小为0时直接返回
        if (src.length() == 0 || bufferSize <= 0) {
            return false;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src), bufferSize);
            out = new BufferedOutputStream(new FileOutputStream(dst), bufferSize);
            byte[] buffer = new byte[bufferSize];
            int i;
            while ((i = in.read(buffer)) > 0) {
                out.write(buffer, 0, i);
            }
        }
        catch (Exception e) {
        	logger.error("【Common.fileCopy】 error:"+e.getMessage());
            return false;
        }
        finally {
            if (null != in) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    logger.error("【Common.fileCopy】 error:"+e.getMessage());
                }
            }
            if (null != out) {
                try {
                    out.close();
                }
                catch (IOException e) {
                    logger.error("【Common.fileCopy】 error:"+e.getMessage());
                }
            }
        }
        return true;
    }
    
    /**
     * 文件拷贝,缓冲区大小默认使用20 * 1024
     * 
     * @param src
     *            原文件
     * @param dst
     *            目标文件
     */
    public static boolean fileCopy(File src, File dst) {
        return fileCopy(src, dst, BUFFER_SIZE);
    }
    
    /**
     * 将int型的时间值转换成"xx:xx:xx"格式描述,范围00:00:00-23:59:59。 如：0表示00:00:00,按“h*3600+m*60+s”方式转换
     * 
     * @param time
     * @return String xx:xx:xx
     */
    public static String changeInt2TimeFormat(int time) {
        String timeStr = "00:00:00";
        if (time > 0) {
            if (time > 86399) {
                time = time % 86400;
            }
            int hour = time / 3600;
            int min = (time - hour * 3600) / 60;
            int sec = time % 60;
            String hourStr = (hour < 10) ? ("0" + hour) : (String.valueOf(hour));
            String minStr = (min < 10) ? ("0" + min) : (String.valueOf(min));
            String secStr = sec < 10 ? ("0" + sec) : (String.valueOf(sec));
            timeStr = hourStr + ":" + minStr + ":" + secStr;
        }
        return timeStr;
    }

    /**
     * 判断MAP是否为空
     * 
     * @param map
     * @return boolean
     */
    public static boolean isEmptyMap(Map<?, ?> map) {
        if (null == map || map.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }
 
	/**
	 * 获取当天时间
	 * @param dateformat
	 * @return
	 */
	public static String getNowTime(String dateformat) {
		Date now = new Date();
		if(isNullStr(dateformat)){
			dateformat = "yyyy-MM-dd";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String hehe = dateFormat.format(now);
		return hehe;
	}
	
    /**
     * @param date  long型
     * @param format 时间格式yyyy-MM-dd HH:mm:ss有用户自行制定
     * @return 时间long型按照某个格式转化为时间字符串
     */
    public static String getTimeLongToString(long date, String format) {
        Timestamp t = new Timestamp(date * 1000);
        SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
        return sDateFormat.format(t);
    }
	

    /**
     * 图像类型转换：GIF->JPG、GIF->PNG、PNG->JPG、PNG->GIF(X)、BMP->PNG
     * @param srcImageFile 源图像地址
     * @param formatName 包含格式非正式名称的 String：如JPG、JPEG、GIF等
     * @param destImageFile 目标图像地址
     */
    public final static void convert(String srcImageFile, String formatName, String destImageFile) {
        try {
            File file = new File(srcImageFile);
            boolean canRead = file.canRead();
            boolean canWrite = file.canWrite();
            if(canRead && canWrite){
                BufferedImage src = ImageIO.read(file);
                ImageIO.write(src, formatName, new File(destImageFile));
            }
        } catch (Exception e) {
            logger.error("【Common.convert】 error:"+e.getMessage());
        }
    }

}
