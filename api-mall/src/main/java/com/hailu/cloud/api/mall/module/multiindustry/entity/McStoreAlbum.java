package com.hailu.cloud.api.mall.module.multiindustry.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zhangmugui
 */
@Data
@ApiModel
public class McStoreAlbum {


    /**
     * 相册id
     */
    @ApiModelProperty(value = "相册id")
    private Long id;


    /**
     *店铺id
     */
    @ApiModelProperty(value = "店铺id")
    private Long storeId;

    /**
     * 相册路径
     */
    @ApiModelProperty(value = "相册路径")
    private String albumUrl;

    /**
     * 图片类型（环境-1、其他-2）
     */
    @ApiModelProperty(value = "图片类型（环境-1、其他-2）")
    private String albumType;

    /**
     * 创新时间
     */
    @ApiModelProperty(value = "创新时间")
    private Date createTime;


    /**
     * 更改时间
     */
    @ApiModelProperty(value = "更改时间")
    private Date updateTime;


    /**
     * 图片总数量
     */
    @ApiModelProperty(value = "图片总数量")
    private Integer albumNum;




}
