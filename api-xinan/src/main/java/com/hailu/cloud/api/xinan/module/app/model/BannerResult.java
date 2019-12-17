package com.hailu.cloud.api.xinan.module.app.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("轮播图")
public class BannerResult {



    @ApiModelProperty("轮播图id")
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;


    /**
     * 图片地址
     */
    @ApiModelProperty("图片地址")
    private String bannerImage;


    /**
     * 详情、富文本
     */
    @ApiModelProperty("详情、富文本")
    private String content;

    /**
     * 类型：1 链接  2图文详情 3 商品详情
     */
    @ApiModelProperty("类型：1 链接  2图文详情 3 商品详情")
    private Integer type;


    /**
     * 目标id
     */
    @ApiModelProperty("目标id")
    private Long targetId;

    /**
     * 外部链接
     */
    @ApiModelProperty("外部链接")
    private String bannerUrl;

    /**
     * 内部链接，目标名字
     */
    @ApiModelProperty("内部链接，目标名字")
    private String targetName;



}
