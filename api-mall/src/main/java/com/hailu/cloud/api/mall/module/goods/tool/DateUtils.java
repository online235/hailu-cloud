/**
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
package com.hailu.cloud.api.mall.module.goods.tool;

import com.hailu.cloud.api.mall.module.common.enums.DateTimeType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
     * 根据预设格式返回当前日期
     *
     * @return
     */
    public static String getNow() {
        return format(new Date());
    }

    /**
     * 根据用户格式返回当前日期
     *
     * @param pattern
     * @return
     */
    public static String getNow(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 使用预设格式格式化日期
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, null);
    }

    /**
     * 使用用户格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */
    public static String format(Date date, String pattern) {
        DateTime dateTime = convertDateToDateTime(date);
        if (StringUtils.isBlank(pattern)) {
            pattern = DATE_TIME_PATTERN_FORMAT_LONG;
        }
        return dateTime.toString(pattern);
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param dateStr 日期字符串
     * @return
     */
    public static Date parse(String dateStr) {
        return parse(dateStr, null);
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = DATE_TIME_PATTERN_FORMAT_LONG;
        }
        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        DateTime dateTime = DateTime.parse(strDate, format);
        return convertDateTimeToDate(dateTime);
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(Date date, String pattern) {
        String dateStr = format(date, pattern);
        return parse(dateStr, pattern);
    }

    /**
     * 在日期上增加数个整月
     *
     * @param date 日期
     * @param n    要增加的月数
     * @return
     */
    public static Date addMonth(Date date, int n) {
        return add(date, n, DateTimeType.MONTHS);
    }

    /**
     * 在日期上增加天数
     *
     * @param date 日期
     * @param n    要增加的天数
     * @return
     */
    public static Date addDay(Date date, int n) {
        return add(date, n, DateTimeType.DAYS);
    }

    /**
     * 在日期上减去天数
     *
     * @param date 日期
     * @param n    要减去的天数
     * @return
     */
    public static Date subDay(Date date, int n) {
        return add(date, 0 - n, DateTimeType.DAYS);
    }

    /**
     * 在日期上增加小时
     *
     * @param date 日期
     * @param n    要增加的小时
     * @return
     */
    public static Date addHour(Date date, int n) {
        return add(date, n, DateTimeType.HOURS);
    }

    /**
     * 日期时间运算操作
     *
     * @param date
     * @param n
     * @param type
     * @return Date
     */
    public static Date add(Date date, int n, DateTimeType type) {
        if (date == null || n == 0) {
            return date;
        }
        DateTime dateTime = convertDateToDateTime(date);
        switch (type) {
            case YEARS:
                dateTime = dateTime.plusYears(n);
                break;
            case MONTHS:
                dateTime = dateTime.plusMonths(n);
                break;
            case DAYS:
                dateTime = dateTime.plusDays(n);
                break;
            case HOURS:
                dateTime = dateTime.plusHours(n);
                break;
            default:
        }
        return dateTime.toDate();
    }

    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        DateTime times = new DateTime();
        return times.toString(DATE_TIME_PATTERN_FORMAT_FULL);
    }

    /**
     * 获取日期年份
     *
     * @param date 日期
     * @return int
     */
    public static int getYear(Date date) {
        return getDateField(date, DateTimeType.YEARS);
    }
    public static int getMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return (calendar.get(2) + 1);
    }

    public static int getDay(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(5);
    }

    /**
     * 获取日期部分值
     * <p>
     * <pre>
     * 默认返回日期年份
     * </pre>
     *
     * @param date
     * @param type
     * @return int
     */
    public static int getDateField(Date date, DateTimeType type) {
        DateTime dateTime = convertDateToDateTime(date);
        LocalDate localDate = dateTime.toLocalDate();
        if (type == null) {
            type = DateTimeType.YEARS;
        }
        int value = 0;
        switch (type) {
            case YEARS:
                value = localDate.getYear();
                break;
            case MONTHS:
                value = localDate.getMonthOfYear();
                break;
            case DAYS:
                value = localDate.getDayOfMonth();
                break;
            case HOURS:
                value = dateTime.getHourOfDay();
                break;

            default:
                break;
        }
        return value;
    }

    /**
     * 按用户格式字符串距离今天的天数
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @return
     */
    public static int countDays(String date, String format) {
        Date date1 = parse(date, format);
        return countDays(date1, new Date());
    }

    /**
     * 获取两个时间间隔年数
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static int countYears(Date date1, Date date2) {
        return countDates(date1, date2, DateTimeType.YEARS);
    }

    /**
     * 获取两个时间间隔月数
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static int countMonths(Date date1, Date date2) {
        return countDates(date1, date2, DateTimeType.MONTHS);
    }

    /**
     * 获取两个时间间隔天数
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static int countDays(Date date1, Date date2) {
        return countDates(date1, date2, DateTimeType.DAYS);
    }

    /**
     * 获取两个时间间隔小时数
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static int countHours(Date date1, Date date2) {
        return countDates(date1, date2, DateTimeType.HOURS);
    }

    /**
     * 获取两个时间间隔值,默认返回天数
     * <p>
     * <pre>
     *    如果时间1 大于 时间2 返回的为负值
     * </pre>
     *
     * @param date1
     * @param date2
     * @param type
     * @return int
     */
    public static int countDates(Date date1, Date date2, DateTimeType type) {
        DateTime dateTime1 = convertDateToDateTime(date1);
        DateTime dateTime2 = convertDateToDateTime(date2);
        if (type == null) {
            return Days.daysBetween(dateTime1, dateTime2).getDays();
        }
        int count = 0;
        switch (type) {
            case YEARS:
                count = Years.yearsBetween(dateTime1, dateTime2).getYears();
                break;
            case MONTHS:
                count = Months.monthsBetween(dateTime1, dateTime2).getMonths();
                break;
            case DAYS:
                count = Days.daysBetween(dateTime1, dateTime2).getDays();
                break;
            case HOURS:
                count = Hours.hoursBetween(dateTime1, dateTime2).getHours();
                break;
            default:
        }
        return count;
    }

    /**
     * 比较两个日期时间的大小
     * <p>
     * <pre>
     * 		1. date1 等于 date2 返回 0
     * 		2. date1 大于 date2 返回 1
     * 		3. date1 小于 date2 返回 -1
     * </pre>
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static int compareDate(Date date1, Date date2) {
        if (date1 == null || null == date2) {
            throw new IllegalArgumentException("Compare date must not be null");
        }
        if (date1.getTime() == date2.getTime()) {
            return 0;
        }
        if (date1.getTime() > date2.getTime()) {
            return 1;
        }
        return -1;
    }

    /**
     * JDK 时间对象 转换成 JODA 时间对象
     *
     * @param date JDK 时间对象
     * @return DateTime
     */
    public static DateTime convertDateToDateTime(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null");
        }
        return new DateTime(date);
    }

    /**
     * JODA 时间对象 转换成 JDK 时间对象
     *
     * @param dateTime JODA 时间对象
     * @return Date
     */
    public static Date convertDateTimeToDate(DateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("DateTime must not be null");
        }
        return dateTime.toDate();
    }

    /**
     * 获取每个星期的第几天
     *
     * @return int
     */
    public static int getDayOfWeek() {
        DateTime date = new DateTime();
        return date.getDayOfWeek();
    }

    /**
     * 获取指定日期的每个星期的第几天
     *
     * @return int
     */
    public static int getDateDayOfWeek(Date date) {
        DateTime dateTime = convertDateToDateTime(date);
        return dateTime.getDayOfWeek();
    }

    /**
     * 获取指定日期的小时数
     *
     * @return int
     */
    public static int getDateHourOfDay(Date date) {
        DateTime dateTime = convertDateToDateTime(date);
        return dateTime.getHourOfDay();
    }

    /**
     * 获取指定日期的小时的分钟数
     *
     * @return int
     */
    public static int getDateMinuteOfHour(Date date) {
        DateTime dateTime = convertDateToDateTime(date);
        return dateTime.getMinuteOfHour();
    }

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