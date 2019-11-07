package com.hailu.cloud.api.auth.module.login.service;

import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.exception.RefreshTokenExpiredException;
import com.hailu.cloud.common.model.MemberModel;

/**
 * @author zhijie
 */
public interface IAuthService {

    /**
     * 刷新accessToken
     *
     * @param refreshToken
     * @return
     * @throws RefreshTokenExpiredException
     */
    String refreshAccessToken(String refreshToken) throws RefreshTokenExpiredException;

    /**
     * 验证码登录
     *
     * @param phone
     * @param code
     * @return
     * @throws BusinessException
     */
    MemberModel login(String phone, String code) throws BusinessException;

    /**
     * 退出登录
     *
     * @param refreshToken
     * @return
     */
    void logout(String refreshToken);

}
