package com.hailu.cloud.common.model.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * 认证授权信息
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
     * 登录类型
     * 0 心安/商城用户登录
     * 1 商户登录
     * 2 管理员登录
     */
    private int loginType;

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
     * 登录用户详细信息
     */
    private E userInfo;

}
