package com.hailu.cloud.api.xinan.module.app.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
public class GovernmentUsers implements Serializable {
    /**
     *  编号
     */
    private Long id;

    /**
     * 登陆账号
     */
    @ApiModelProperty("登陆账号")
    private String loginAccount;

    /**
     * 登陆密码
     */
    @ApiModelProperty("登陆密码")
    private String loginPassword;

    /**
     * 市编号
     */
    @ApiModelProperty("市编号")
    private String cityCode;

    /**
     * 捐赠次数
     */
    @ApiModelProperty("捐赠次数")
    private Integer donationTimes;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date cratedat;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updatedat;

    /**
     * 公益文章
     */
    @ApiModelProperty("公益文章")
    private String commonwealArticle;

    /**
     * xa_government_users
     */
    private static final long serialVersionUID = 1L;
}