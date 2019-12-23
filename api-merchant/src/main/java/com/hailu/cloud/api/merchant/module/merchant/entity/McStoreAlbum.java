package com.hailu.cloud.api.merchant.module.merchant.entity;


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
     * 图片标题
     */
    @ApiModelProperty(value = "图片标题")
    private String title;


    /**
     * 图片副标题
     */
    @ApiModelProperty(value = "图片副标题")
    private String subheading;


    /**
     *图片类型（环境-1、菜品-2、其他-3）
     */
    @ApiModelProperty(value = "图片类型（首页轮播图-0 、环境-1、菜品-2、其他-3）")
    private Integer albumType;



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



}
