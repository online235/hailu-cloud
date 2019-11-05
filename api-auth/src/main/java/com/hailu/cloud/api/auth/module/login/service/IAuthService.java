package com.hailu.cloud.api.auth.module.login.service;

import com.hailu.cloud.common.exception.BusinessException;

/**
 * @author zhijie
 */
public interface IAuthService {

    /**
     * 刷新accessToken
     *
     * @param refreshToken
     * @return
     * @throws BusinessException
     */
    String refreshAccessToken(String refreshToken) throws BusinessException;

    /**
     * 验证码登录
     *
     * @param phone
     * @param code
     * @return
     * @throws BusinessException
     */
    String login(String phone, String code) throws BusinessException;

    /**
     * 退出登录
     *
     * @param accessToken
     * @return
     */
    void logout(String accessToken);

}
