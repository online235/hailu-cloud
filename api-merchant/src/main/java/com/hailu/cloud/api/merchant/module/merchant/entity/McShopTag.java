package com.hailu.cloud.api.merchant.module.merchant.entity;

import com.hailu.cloud.common.fill.annotation.DictName;
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
     * 状态(启用-1、禁用-0)
     */
    @ApiModelProperty(value = "状态(启用-1、禁用-0)")
    private Integer state;

    @DictName(code = "ENABLE_STATUS", joinField = "state")
    private String stateDisplay;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * mc_shop_tag
     */
    private static final long serialVersionUID = 1L;
}