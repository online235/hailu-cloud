package com.hailu.cloud.api.mall.module.user.dao;

import com.hailu.cloud.api.mall.module.user.vo.UserFeedbackVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 提问反馈表
 * Created by somebody on 2016/6/15.
 */
@Mapper
public interface UserFeedbackMapper {

    int addFeedback(UserFeedbackVO userFeedbackVO);

}
