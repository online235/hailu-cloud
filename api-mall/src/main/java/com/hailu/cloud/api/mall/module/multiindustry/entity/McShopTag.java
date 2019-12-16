package com.hailu.cloud.api.mall.module.multiindustry.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
public class McShopTag implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Long id;

    /**
     * 标签编号
     */
    @ApiModelProperty(value = "标签编号")
    private Long tagId;

    /**
     * 店铺编号
     */
    @ApiModelProperty(value = "店铺编号")
    private Long storeId;

    /**
     * 状态(正常-1、停用-2)
     */
    @ApiModelProperty(value = "状态(正常-1、停用-2)")
    private Integer state;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * mc_shop_tag
     */
    private static final long serialVersionUID = 1L;
}