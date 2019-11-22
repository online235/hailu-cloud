package com.hailu.cloud.api.auth.module.login.service;

import com.hailu.cloud.common.exception.BusinessException;

/**
 * 登录验证回调处理
 */
public interface ILoginCallback {

    /**
     * 该账号是否存在
     *
     * @param account
     * @return
     */
    boolean exists(String account);

    /**
     * 获取用户ID
     *
     * @return
     */
    String getUserId();

    /**
     * 该账号是否启用
     *
     * @return
     */
    boolean isEnable();

    /**
     * 其他校验
     *
     * @return
     * @throws BusinessException
     */
    void extendValidate() throws BusinessException;

    /**
     * 将生成的token保存到用户信息返回给客户端
     *
     * @param accessToken
     * @param refreshToken
     * @return
     */
    Object handle(String accessToken, String refreshToken);

}