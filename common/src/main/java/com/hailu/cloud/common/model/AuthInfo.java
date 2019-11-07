package com.hailu.cloud.common.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 授权信息
 *
 * @Author xuzhijie
 * @Date 2019/11/6 10:24
 */
@Getter
@Setter
public class AuthInfo<E> {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * access token
     */
    private String accessToken;

    /**
     * access token 有效期
     */
    private long accessTokenExpire;

    /**
     * refresh token
     */
    private String refreshToken;

    /**
     * refresh token有效期
     */
    private long refreshTokenExpire;

    /**
     * 登录用户信息
     */
    private E userInfo;

}