package com.hailu.cloud.common.utils;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @Author: QiuFeng:WANG
 * @Description: 店铺工具类型
 * @Date: 2019/11/27 0027
 */
public class StoreUtil {

    public static boolean storeStatus(Date date, Date dates, String weekDay){

        int datet = DateUtil.dayOfWeek(new Date());
        String[] s1=weekDay.split(",");
        boolean wee = false;
        for (String s2 : s1){
            int i = Integer.parseInt(s2);
            if (datet == i){
                wee = true;
                break;
            }
        }
        if (wee){
            int compareTo = new Date().compareTo(date);
            int compareToTwo = new Date().compareTo(dates);
            if (compareTo == 1 && compareToTwo == -1){
                return true;
            }
        }
        return false;
    }
}
