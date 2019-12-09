package com.hailu.cloud.common.model.auth;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 微信登录响应结果
 *
 * @author xuzhijie
 */
@Data
public class WeChatAuthResponse {

    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "refresh_token")
    private String refreshToken;

    /**
     * 过期时间， 单位秒
     */
    @JSONField(name = "expires_in")
    private Long expiresIn;

    /**
     * 微信主体下单个应用用户ID
     */
    @JSONField(name = "openid")
    private String openId;

    /**
     * 微信主体下多个应用唯一用户ID
     */
    @JSONField(name = "unionid")
    private String unionId;

    private String scope;

    @JSONField(name = "errcode")
    private String errCode;

    @JSONField(name = "errmsg")
    private String errMsg;

}
