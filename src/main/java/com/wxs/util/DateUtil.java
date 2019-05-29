package com.wxs.util;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期通用处理工具类
 *
 * @author wxs
 * @date 2019-05-29 10:18
 */
public class DateUtil extends DateUtils {
    /**
     * 日志
     */
    private static Logger log = LogUtil.get();

    /**
     * 标准日期
     */
    public static final String NORM_DATE_FORMAT_STR = "yyyy-MM-dd";

    /**
     * 标准日期时间
     */
    public static final String NORM_DATETIME_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    /**
     * HTTP日期时间
     */
    public static final String HTTP_DATETIME_FORMAT_STR = "EEE, dd MMM yyyy HH:mm:ss z";

    /**
     * 当前时间，格式 yyyy-MM-dd HH:mm:ss
     *
     * @return 当前时间的标准形式字符串
     */
    public static String now() {
        return formatDateTime(new Date());
    }

    /**
     * 当前日期，格式 yyyy-MM-dd
     *
     * @return 当前日期的标准形式字符串
     */
    public static String today() {
        return formatDate(new Date());
    }


    /**
     * 根据特定格式格式化日期
     *
     * @param date   被格式化的日期
     * @param format 格式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date 被格式化的日期
     * @return 格式化后的日期
     */
    public static String formatDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(NORM_DATETIME_FORMAT_STR, Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * 格式化为Http的标准日期格式
     *
     * @param date 被格式化的日期
     * @return HTTP标准形式日期字符串
     */
    public static String formatHttpDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(HTTP_DATETIME_FORMAT_STR, Locale.US);
        return dateFormat.format(date);
    }

    /**
     * 格式 yyyy-MM-dd
     *
     * @param date 被格式化的日期
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(NORM_DATE_FORMAT_STR, Locale.getDefault());
        return dateFormat.format(date);
    }


    /**
     * 将特定格式的日期转换为Date对象
     *
     * @param dateString 特定格式的日期
     * @param format     格式，例如yyyy-MM-dd
     * @return 日期对象
     */
    public static Date parse(String dateString, String format) {
        try {
            return (new SimpleDateFormat(format)).parse(dateString);
        } catch (ParseException e) {
            log.error("Parse " + dateString + " with format " + format + " error!", e);
        }
        return null;
    }

    /**
     * 格式yyyy-MM-dd HH:mm:ss
     *
     * @param dateString 标准形式的时间字符串
     * @return 日期对象
     */
    public static Date parseDateTime(String dateString) {
        try {
            return getDateFormat(NORM_DATETIME_FORMAT_STR, dateString);
        } catch (Exception e) {
            log.error("Parse " + dateString + " with format " + NORM_DATETIME_FORMAT_STR + " error!", e);
        }
        return null;
    }

    public static Date getDateFormat(String dateFormat, String dateString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 格式yyyy-MM-dd
     *
     * @param dateString 标准形式的日期字符串
     * @return 日期对象
     */
    public static Date parseDate(String dateString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(NORM_DATE_FORMAT_STR, Locale.getDefault());
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            log.error("Parse " + dateString + " with format " + NORM_DATE_FORMAT_STR + " error!", e);
        }
        return null;
    }

    /**
     * 获取指定日期偏移指定时间后的时间
     *
     * @param date          基准日期
     * @param calendarField 偏移的粒度大小（小时、天、月等）使用Calendar中的常数
     * @param offsite       偏移量，正数为向后偏移，负数为向前偏移
     * @return 偏移后的日期
     */
    public static Date getOffsiteDate(Date date, int calendarField, int offsite) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendarField, offsite);
        return cal.getTime();
    }

    /**
     * 判断两个日期相差的时长
     * 返回 minuend - subtrahend 的差
     *
     * @param subtrahend 减数日期
     * @param minuend    被减数日期
     * @param diffField  相差的选项：相差的天、小时
     * @return 日期差
     */
    public static long dateDiff(Date subtrahend, Date minuend, long diffField) {
        long diff = minuend.getTime() - subtrahend.getTime();
        return diff / diffField;
    }

    public static String getStrMillTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        return sdf.format(date.getTime());
    }

    /**
     * 时间转换成时间戳
     *
     * @param dateStr
     * @return
     */
    public static long date2TimeStamp(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(NORM_DATETIME_FORMAT_STR, Locale.getDefault());
            Date date = dateFormat.parse(dateStr);
            long ts = date.getTime() / 1000;
            return ts;
        } catch (ParseException e) {
            return 0;
        }
    }

    public static String timeStamp2Date(Long timeStamp) {
        String dateStr = "";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(NORM_DATETIME_FORMAT_STR, Locale.getDefault());
            Long stamp = timeStamp * 1000;
            dateStr = dateFormat.format(new Date(stamp));
        } catch (Exception e) {
        }
        return dateStr;
    }
}
