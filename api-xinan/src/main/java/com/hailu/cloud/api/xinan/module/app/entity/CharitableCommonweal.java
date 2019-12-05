package com.hailu.cloud.api.xinan.module.app.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
public class CharitableCommonweal implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private Long id;

    /**
     * 政府用户编号
     */
    @ApiModelProperty("政府用户编号")
    private Long adminId;

    /**
     * 公益标题
     */
    @ApiModelProperty("公益标题")
    private String commonwealTitle;

    /**
     * 默认图片
     */
    @ApiModelProperty("默认图片")
    private String defaultPicture;

    /**
     * 状态(正常-1、删除-2)
     */
    @ApiModelProperty("状态(正常-1、删除-2)")
    private Integer state;

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
     * 文章
     */
    @ApiModelProperty("文章")
    private String article;

    /**
     * xa_charitable_commonweal
     */
    private static final long serialVersionUID = 1L;
}