package com.hailu.cloud.api.auth.module.login.service;

import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.exception.RefreshTokenExpiredException;

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
     * @throws BusinessException
     */
    String refreshAccessToken(String refreshToken) throws RefreshTokenExpiredException, BusinessException;

    /**
     * 验证码登录
     *
     * @param loginType 登录类型(0:心安&商城; 1:商户)
     * @param phone
     * @param code
     * @return
     * @throws BusinessException
     */
    Object vericodeLogin(Integer loginType, String phone, String code) throws BusinessException;

    /**
     * 验证码登录
     *
     * @param loginType 登录类型(0:心安&商城; 1:商户)
     * @param account
     * @param pwd
     * @return
     * @throws BusinessException
     */
    Object login(Integer loginType, String account, String pwd) throws BusinessException;

    /**
     * 退出登录
     *
     * @param refreshToken
     * @return
     * @throws BusinessException
     */
    void logout(String refreshToken) throws BusinessException;

}
