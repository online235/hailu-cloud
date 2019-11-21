package com.hailu.cloud.api.mall.module.user.service;


import com.hailu.cloud.api.mall.module.user.vo.UserFeedbackVO;

/**
 * 用户反馈
 * Created by somebody on 2016/6/15.
 */
public interface IUserFeedbackService {
    /**
     * @param userFeedbackVO
     * @return
     */
    Boolean addFeedback(UserFeedbackVO userFeedbackVO) throws Exception;
}
