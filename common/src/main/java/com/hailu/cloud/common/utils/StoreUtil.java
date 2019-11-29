package com.hailu.cloud.common.utils;

import cn.hutool.core.date.DateUtil;

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
        /*if (wee){
            int compareTo = new Date().compareTo(date);
            int compareToTwo = new Date().compareTo(dates);
            if (compareTo == 1 && compareToTwo == -1){
                return true;
            }
        }*/
        if (dateCompare(openDate) && !dateCompare(closeDate) && wee){
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
            if (time.before(now)) { //time时间是否在当前时间之前，为true则time时间已过
                boo = true;
            }
        return boo;
    }
}
