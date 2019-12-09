package com.hailu.cloud.api.auth.module.login.service;

import com.hailu.cloud.common.exception.BusinessException;

/**
 * @author xuzhijie
 */
public interface IWeChatService {

    /**
     * 微信登录
     * @param loginType
     * @param code
     * @return
     * @throws BusinessException
     */
    Object login(Integer loginType, String code) throws BusinessException;

}
