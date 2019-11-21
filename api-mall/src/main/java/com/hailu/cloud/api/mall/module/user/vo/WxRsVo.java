package com.hailu.cloud.api.mall.module.user.vo;

import lombok.Data;

@Data
public class WxRsVo {
    private String accessToken;//接口调用凭证
    private long expiresIn;//access_token接口调用凭证超时时间，单位（秒）
    private String refreshToken;//用户刷新access_token
    private String openid;//授权用户唯一标识
    private String scope;//用户授权的作用域，使用逗号（,）分隔
    private String unionid;//当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段

}
