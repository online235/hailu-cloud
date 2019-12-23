package com.hailu.cloud.api.merchant.module.merchant.parameter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel()
public class McStoreAlbumUrlParameter {


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
     *图片类型（首页轮播图-0 、环境-1、菜品-2、其他-3）
     */
    @ApiModelProperty(value = "图片类型（首页轮播图-0 、环境-1、菜品-2、其他-3）")
    private Integer albumType;



}
