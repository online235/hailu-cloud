/**
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
package com.hailu.cloud.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 项目名称：common
 * <p>
 * 类名称：DateUtils
 * <p>
 * 类描述： 日期工具类
 * <p>
 * <pre>
 * 		此类关于日期时间的操作基于 joda time组件
 * </pre>
 * <p>
 * 创建人： Liuzhudong
 * <p>
 * 创建时间：2015年7月29日
 */
@Slf4j
public class DateUtils {

    /**
     * 日期时间设置
     */
    public static final String DATE_TIME_PATTERN_FORMAT_SHORT = "yyyy-MM-dd";

    public static final String DATE_TIME_YYYYMMDDHH = "yyyy-MM-dd HH";

    public static final String DATE_TIME_PATTERN_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIME_PATTERN_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    public static final String DATE_TIME_PATTERN_FORMAT_SHORT_CN = "yyyy年MM月dd";

    public static final String DATE_TIME_PATTERN_FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    public static final String DATE_TIME_PATTERN_FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    /**
     * @Author HuangL
     * @Description long 转换成string
     * @Date 2018-10-11_19:24
     */
    public static String longToString(Long time, String sdf) {
        String timeTo = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sdf);
            Date date = new Date(time);
            timeTo = simpleDateFormat.format(date);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return timeTo;
    }

    /**
     * @Author HuangL
     * @Description 转换为string
     * @Date 2018-10-13_15:34
     */
    public static String longToString(Long time) {
        return longToString(time, DATE_TIME_PATTERN_FORMAT_SHORT);
    }

    /**
     * String 转 long
     *
     * @param date
     * @param sdf
     * @return
     */
    public static Long stringToLong(String date, String sdf) {
        Long timeTo = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sdf);
            Date parse = simpleDateFormat.parse(date);
            timeTo = parse.getTime();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return timeTo;
    }


    /**
     * 获取当天开始时间
     *
     * @return
     */
    public static Long getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

    /**
     * 获取当天结束时间
     *
     * @return
     */
    public static Long getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }

    /**
     * 获取指定时间的timestamp
     * @param time
     * @return
     */
    public static Timestamp getTimestampByLong(long time){

        return new Timestamp(time);
    }
}