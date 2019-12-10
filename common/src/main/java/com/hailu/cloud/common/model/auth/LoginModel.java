package com.hailu.cloud.common.model.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xuzhijie
 */
@Data
@ApiModel
public class LoginModel {

    /**
     * access token
     */
    @ApiModelProperty("access token")
    private String accessToken;

    /**
     * refresh token
     */
    @ApiModelProperty("refresh token")
    private String refreshToken;

    /**
     * 是否有设置过密码
     */
    @ApiModelProperty("是否有设置过密码")
    private boolean hasPwd;


}
