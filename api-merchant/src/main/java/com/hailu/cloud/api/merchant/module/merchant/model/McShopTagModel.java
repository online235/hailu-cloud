package com.hailu.cloud.api.merchant.module.merchant.model;

import com.hailu.cloud.common.fill.annotation.DictName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
public class McShopTagModel  {
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