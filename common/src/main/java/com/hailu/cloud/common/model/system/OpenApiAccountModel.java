package com.hailu.cloud.common.model.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * open api 账号
 *
 * @author zhijie
 */
@Data
public class OpenApiAccountModel {

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 接入公司名称
     */
    @ApiModelProperty(name = "接入公司名称", required = true)
    private String companyName;

    /**
     * app id
     */
    @ApiModelProperty("app id")
    private String appId;

    /**
     * app secret
     */
    @ApiModelProperty("app secret")
    private String appSecret;

    /**
     * 生成时间
     */
    @ApiModelProperty("生成时间")
    private Long time;

}
