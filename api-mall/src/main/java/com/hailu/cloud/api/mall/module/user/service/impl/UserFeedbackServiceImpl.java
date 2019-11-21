package com.hailu.cloud.api.mall.module.user.service.impl;

import com.hailu.cloud.api.mall.module.user.dao.UserFeedbackMapper;
import com.hailu.cloud.api.mall.module.user.service.IUserFeedbackService;
import com.hailu.cloud.api.mall.module.user.vo.UserFeedbackVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by somebody on 2016/6/15.
 * 用户提问反馈
 */
@Service
public class UserFeedbackServiceImpl implements IUserFeedbackService {

    @Resource
    private UserFeedbackMapper userFeedbackDao;

    /**
     * 添加反馈
     *
     * @param userFeedbackVO
     * @return
     */
    @Override
    public Boolean addFeedback(UserFeedbackVO userFeedbackVO) throws Exception {
        userFeedbackVO.setCreateTime(System.currentTimeMillis());
        return 1 == userFeedbackDao.addFeedback(userFeedbackVO);
    }
}
