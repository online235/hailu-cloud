package com.hailu.cloud.api.mall.module.user.service;


import com.hailu.cloud.api.mall.module.user.vo.UserSignVO;

/**
 * 签到
 * Created by somebody on 2016/6/17.
 */
public interface IUserSignService {
    /**
     * 签到
     *
     * @param userSignVO
     * @return
     */
    Boolean addSign(UserSignVO userSignVO) throws Exception;

    /**
     * 查询今天是否可以签到
     * true 表示可以签到 (即今天还未签到)
     *
     * @param userSignVO
     * @return
     * @throws Exception
     */
    Boolean isCanSign(UserSignVO userSignVO) throws Exception;

    /**
     * 获取某天签到记录
     *
     * @param userSign
     * @return
     */
    UserSignVO getLastDaySign(UserSignVO userSign);
}
