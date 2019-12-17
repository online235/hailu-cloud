package com.hailu.cloud.api.admin.module.banner.parameter;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 广告表
 *
 * @author zhijie
 */
@Data
@ApiModel
public class SysBannerParameter {

    /**
     * ID
     */
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 详情、富文本
     */
    @ApiModelProperty("详情、富文本")
    private String content;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间,yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间,yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

    /**
     * 序号
     */
    @ApiModelProperty("序号")
    private Integer sort;

    /**
     * 图片地址
     */
    @ApiModelProperty("图片地址")
    private String bannerImage;

    /**
     * 类型：1 链接  2图文详情 3 商品详情
     */
    @ApiModelProperty("类型：1 链接  2图文详情 3 商品详情")
    private Integer type;

    /**
     * 广告位 1首页banner 2首页内容广告
     */
    @ApiModelProperty("广告位 1首页banner 2首页内容广告")
    private Integer spaceContent;

    /**
     * 副标题
     */
    @ApiModelProperty("副标题")
    private String subtitle;

    /**
     * 标题图片
     */
    @ApiModelProperty("标题图片")
    private String titleImage;

    /**
     * 广告所在的位置1.心安    2.商城   3、美食
     */
    @ApiModelProperty("广告所在的位置1.心安    2.商城   3、美食")
    private Integer bannerSpace;

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
