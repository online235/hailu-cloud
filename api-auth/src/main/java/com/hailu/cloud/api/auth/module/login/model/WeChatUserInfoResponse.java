package com.hailu.cloud.api.auth.module.login.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 微信用户信息响应结果
 *
 * @author xuzhijie
 */
@Data
public class WeChatUserInfoResponse {

    /**
     * 授权用户唯一标识
     */
    @JSONField(name = "nickname")
    private String nickname;

    /**
     * 授权用户唯一标识
     */
    @JSONField(name = "headimgurl")
    private String headImgUrl;

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

    @JSONField(name = "errcode")
    private String errCode;

    @JSONField(name = "errmsg")
    private String errMsg;

}
