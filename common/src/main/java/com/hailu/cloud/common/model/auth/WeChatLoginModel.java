package com.hailu.cloud.common.model.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 微信
 *
 * @author xuzhijie
 */
@Data
@ApiModel
public class WeChatLoginModel extends LoginModel {

    /**
     * 微信access token 2小时有效
     */
    private String weChatAccessToken;

    /**
     * 微信refresh token 30天有效
     */
    private String weChatRefreshToken;

    /**
     * 过期时间， 单位秒
     */
    private Long weChatExpiresIn;

    /**
     * 微信主体下单个应用用户ID
     */
    private String weChatOpenId;

    /**
     * 微信主体下多个应用唯一用户ID
     */
    private String weChatUnionId;

    /**
     * 授权用户唯一标识
     */
    @ApiModelProperty("微信昵称")
    private String weChatNickname;

    /**
     * 授权用户唯一标识
     */
    @ApiModelProperty("微信头像")
    private String weChatHeadImgUrl;

}
