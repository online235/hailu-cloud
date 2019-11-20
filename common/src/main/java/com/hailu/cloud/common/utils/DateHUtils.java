package com.hailu.cloud.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间相关计算类
 * @AUTHOR HUANGL
 */
@Slf4j
public class DateHUtils {
    public static final String DEFAULT_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * string(DATE)转long 工具方法 例如 date="2018-01-01" , stren="00:00:00"
     * @AUTHOR HUANGL
     * @param date
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String date , String stern) {
        //手动解析字符串转化成时间戳
        long time1=0;
        try {
            date=(stern!=null && stern!="")? date+" "+ stern:date;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time1 = sdf.parse(date).getTime();
        }catch(Exception e){
            log.error(e.getMessage(), e);
        }
        return time1;
    }
    /**
     * @Author: HuangL
     * @Description: 得到对应的
     * @Date:2018-10-22_18:41
     */
    public static long stringToLong(String date) {
        return stringToLong(date,null);
    }
    /**
     * 时间戳转换成string 例如 time = 1517923343212 ,sdf = yyyy-MM-dd HH-mm-ss
     * @AUTHOR HUANGL
     * @return
     */
    public static String longToString(Long time , String sdf){
        String timeTo=null;
        try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sdf);
                //long time1 = time / 1000;
                Date date = new Date(time);
                timeTo = simpleDateFormat.format(date);

        }catch(Exception e){
            log.error(e.getMessage(), e);
        }
        return timeTo;
    }
    /**
     * @Author: HuangL
     * @Email: huangl96@163.com
     * @Description: 根据指定日期得到最后的时间
     * @Date: 2018-11-21 10:21
     */
    public static Long lastLongTime(Date date){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }

    /**
     * @Author: HuangL
     * @Description:
     * @Date:2018-10-19_15:13
     */
    public static String longToString(Long time){
        return longToString(time,DEFAULT_FORMAT_YYYY_MM_DD);
    }



    public static long stringToLong2(String date , String stern) {
        //手动解析字符串转化成时间戳
        long time1=0;
        try {
            date=(stern!=null && stern!="")? date+" "+ stern:date;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            time1 = sdf.parse(date).getTime();
        }catch(Exception e){
            log.error(e.getMessage(), e);
        }
        return time1;
    }

    public static String stringTimeSSS(String dateTime){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
            SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date strToDate = formatter.parse(dateTime);
            String dateFormat = formats.format(strToDate);
            return dateFormat;
        }catch (Exception e){
                log.error(e.getMessage(), e);
            return null;
        }
    }

    public static String stringTimeEEE(String dateTime){
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);	//时区需要写成US，其他自测(CHINESE肯定报错)
        try {
//          TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");		//dateStr 原时区
//          sdf.setTimeZone(tz);
            Date date = sdf.parse(dateTime);			//格式化成日期格式
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//          format1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));		//转换成目标时区
            return format1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }







    /**
     * if(先判断该商品是否是预定){
     * 预定选取规格时+
     * 		if(先判断选中的规格库存activityRepertory>当前选择的数量){
     * 			true 
     * 			if(在判断当前选中数量是否小于 limitNumber){
     * 				true 能点击+可以点击否则不能
     * 			}else{
     * 				false点击+无效
     * 			}
     * 		}else{
     * 			false 加入购物车或立即购买不能点击,
     * 		}
     * }else if(isActivity==1){
     * 		if(limitNumber-当前选择的数量<0){
	     * 		if(先判断库存 activityRepertory>当前选择的数量){
	     * 			true	小于金额取这一件activityPrice 跟以起的相加进行相加
	     * 			}else {
	     * 				false	+号点击无反应,
	     * 			}
	     * 
	     * 		}else if(正常库存specGoodsStorage >limitNumber-当前选择的数量){
	     * 				false 取这一件specGoodsPrice 金额跟以起的相加	
	     * 		}else{
     * 				false	+号点击无反应,
     * 			}
     * }else if(正常库存specGoodsStorage >当前选择的数量){
     * 		取这一件specGoodsPrice 金额跟以起的相加	
     * }else{
     * 		false 加入购物车或立即购买不能点击,
     * }
     */
}
