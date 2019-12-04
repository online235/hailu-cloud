package com.hailu.cloud.api.mall.module.multiindustry.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@ApiModel
public class StoreAlbum implements Serializable {
    /**
     * 相册id
     */
    @ApiModelProperty("相册id")
    private Long id;

    /**
     * 店铺id
     */
    @ApiModelProperty("店铺id")
    private Long storeId;

    /**
     * 相册路径
     */
    @ApiModelProperty("相册路径")
    private String albumUrl;

    /**
     * 创新时间
     */
    @ApiModelProperty("创新时间")
    private Date createTime;

    /**
     * 更改时间
     */
    @ApiModelProperty("更改时间")
    private Date updateTime;

    /**
     * mc_store_album
     */
    private static final long serialVersionUID = 1L;


}