package com.hailu.cloud.api.mall.module.user.service.impl;

import com.hailu.cloud.api.mall.module.common.enums.BusinessCode;
import com.hailu.cloud.api.mall.module.user.dao.UserSignMapper;
import com.hailu.cloud.api.mall.module.user.service.IUserSignService;
import com.hailu.cloud.api.mall.module.user.vo.UserSignVO;
import com.hailu.cloud.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

@Service
public class UserSignServiceImpl implements IUserSignService {

    @Resource
    private UserSignMapper userSignDao;

    /**
     * 达到最高积分的连续签到天数
     */
    @Value("${user.maxIntegral.day}")
    private String maxIntegralDayStr;

    /**
     * 签到
     *
     * @param userSignVO
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)//加事物
    public Boolean addSign(UserSignVO userSignVO) throws Exception {
        int maxIntegralDay = Integer.parseInt(maxIntegralDayStr);
        int todayIntegral = 0;
        boolean isSignSuccess = false;
        boolean flag = true;
        int getIntegral = 1;
        //获取最后一次签到
        UserSignVO lastDaySign = userSignDao.getLastDaySign(userSignVO);
        if (null == lastDaySign) {
            //人生第一次签到
            userSignVO.setSerialSignDay(1);
            userSignVO.setIntegral(todayIntegral);
            userSignVO.setLastSignTime(new Date());
        } else if (null != lastDaySign && isSameDate(lastDaySign.getLastSignTime(), new Date())) {
            return false;
        } else {

            Date lastSignDay = lastDaySign.getLastSignTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lastSignDay);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
            Date lastSingDayAdd = calendar.getTime();
            //最后一次签到是否是昨天
            if (isSameDate(lastSingDayAdd, new Date())) {//将昨天+1天,是否是今天
                //最后一次签到是昨天
                int serialDay = lastDaySign.getSerialSignDay();
                //判断是否达到最高天数
                if (serialDay < maxIntegralDay) {
                    //未达到最高天数
                    //todayIntegral = (serialDay+1)*baseSignIntegral;
                    todayIntegral = getIntegral;
                    userSignVO.setIntegral(todayIntegral);
                    userSignVO.setLastSignTime(new Date());
                    userSignVO.setSerialSignDay(serialDay + 1);
                    isSignSuccess = 1 == userSignDao.sign(userSignVO);
                } else {
                    userSignVO.setLastSignTime(new Date());
                    userSignVO.setSerialSignDay(1);
                }
            } else {
                //最后一次签到不是昨天
                userSignVO.setIntegral(todayIntegral);
                userSignVO.setLastSignTime(new Date());
                userSignVO.setSerialSignDay(1);
            }
        }
        return flag;
    }

    /**
     * 今天是否可以签到
     * true 表示可以签到 (即今天还未签到)
     *
     * @param userSignVO
     * @return
     * @throws Exception
     */
    @Override
    public Boolean isCanSign(UserSignVO userSignVO) throws Exception {

        //获取签到记录
        UserSignVO lastDaySign = userSignDao.getLastDaySign(userSignVO);
        Boolean flag = true;
        //已签到
        if (null != lastDaySign && isSameDate(lastDaySign.getLastSignTime(), new Date())) {
            throw new BusinessException(BusinessCode.USER_ALREADY_SIGN.getDescription());
        }
        //未签到
        return true;
    }

    /**
     * 判断是否同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    private static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    @Override
    public UserSignVO getLastDaySign(UserSignVO userSign) {
        return userSignDao.getLastDaySign(userSign);
    }
}