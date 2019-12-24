package com.hailu.cloud.common.utils;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: QiuFeng:WANG
 * @Description: 店铺工具类型
 * @Date: 2019/11/27 0027
 */
public class StoreUtil {

    /**
     *
     * @param closeDate    18:00
     * @param openDate     09:00
     * @param weekDay      1,2,3
     * @return
     * @throws ParseException
     */
    public static boolean storeStatus(String openDate, String closeDate, String weekDay) throws ParseException {

        //获取当前日期、星期一、星期二
        int datet = DateUtil.dayOfWeek(new Date());
        //字符串分割
        String[] s1=weekDay.split(",");
        boolean wee = false;
        for (String s2 : s1){
            int i = Integer.parseInt(s2);
            if (datet == i){
                wee = true;
                break;
            }
        }
        if (dateCompare(openDate) && !dateCompare(closeDate) && wee){
            return true;
        }
        return false;
    }


    /**
     *
     * @param businessTime  "06:00-12：00,14:00-16：00"
     * @param weekDay  1,2,3
     * @return
     * @throws ParseException
     */
    public static boolean storeBusinessTimeStatus(String businessTime, String weekDay) throws ParseException {
        //获取当前日期、星期一、星期二
        int datet = DateUtil.dayOfWeek(new Date());
        //字符串分割
        String[] s1=weekDay.split(",");
        boolean wee = false;
        for (String s2 : s1){
            int i = Integer.parseInt(s2);
            if (datet == i){
                wee = true;
                break;
            }
        }
        if (businessTimeStatus(businessTime) && wee){
            return true;
        }
        return false;
    }


    public static boolean dateCompare(String date) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        Date time = null;
        Date now = null;
        boolean boo = false;
            time= sdf.parse(date);
            now = sdf.parse(sdf.format(new Date()));
            if (time.before(now)) { //time时间是否在当前时间之前
                boo = true;
            }
        return boo;
    }


    /**
     * 经营时间判断
     * @param businessTime
     * @return
     * @throws ParseException
     */
    public static boolean businessTimeStatus(String businessTime) throws ParseException{
        if(StringUtils.isBlank(businessTime)){
            return false;
        }
        String[] businessTimeStr = businessTime.split(",");
        boolean result = false;
        for(int i = 0;i<businessTimeStr.length;i++){
            result = timeSlotStatus(businessTimeStr[i]);
            if(result){
                return result;
            }
        }
        return result;
    }


    /**
     * 该时间段在不在当前时间内
     * @param timeSlot
     * @return
     * @throws ParseException
     */
    public static boolean timeSlotStatus(String timeSlot)throws ParseException{

        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        String[] timeStr = timeSlot.split("-");
        String frontTime = timeStr[0];
        String afterTime = timeStr[1];
        boolean result = false;
        boolean frontResult = dateCompare(frontTime);
        boolean afterResult = dateCompare(afterTime);
        if(frontResult && !afterResult){
            result = true;
        }
        return result;
    }



    public static void main(String[] args) throws ParseException {
        System.out.println(DateUtil.dayOfWeek(new Date()));
        System.out.println(dateCompare("9:00"));
    }
}
