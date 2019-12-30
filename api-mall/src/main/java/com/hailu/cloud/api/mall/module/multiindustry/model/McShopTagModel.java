package com.hailu.cloud.api.mall.module.multiindustry.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class McShopTagModel {
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
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称")
    private String tagName;


}