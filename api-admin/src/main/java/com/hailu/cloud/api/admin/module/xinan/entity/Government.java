package com.hailu.cloud.api.admin.module.xinan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
public class Government implements Serializable {
    /**
     *  编号
     */
    private Long id;

    /**
     * 管理员编号
     */
    @ApiModelProperty(value = "管理员编号")
    private Long adminId;

    /**
     * 捐赠次数
     */
    @ApiModelProperty(value = "捐赠次数")
    private Integer donationTimes;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date cratedat;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updatedat;

    /**
     * 公益文章
     */
    @ApiModelProperty(value = "公益文章")
    private String commonwealArticle;

    /**
     * xa_government_users
     */
    private static final long serialVersionUID = 1L;
}