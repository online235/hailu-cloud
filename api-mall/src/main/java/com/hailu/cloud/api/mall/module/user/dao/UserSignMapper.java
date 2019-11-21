package com.hailu.cloud.api.mall.module.user.dao;

import com.hailu.cloud.api.mall.module.user.vo.UserSignVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 签到
 * Created by somebody on 2016/6/17.
 */
@Mapper
public interface UserSignMapper {
    /**
     * 签到
     *
     * @param userSignVO
     * @return
     */
    int sign(UserSignVO userSignVO);

    /**
     * 第一次签到
     *
     * @param userSignVO
     * @return
     */
    int firstSign(UserSignVO userSignVO);

    /**
     * 获取某天签到记录
     *
     * @param userSign
     * @return
     */
    UserSignVO getLastDaySign(UserSignVO userSign);
}
