package com.hailu.cloud.api.xinan.module.app.entity;

import lombok.Data;

import java.util.Date;

/**
 * 广告表
 *
 * @author zhijie
 */
@Data
public class SysBanner {

    /**
     * ID
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 详情、富文本
     */
    private String content;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 图片地址
     */
    private String bannerImage;

    /**
     * 类型：1 链接  2图文详情 3 商品详情
     */
    private Integer type;

    /**
     * 广告位 1首页banner 2首页内容广告
     */
    private Integer space;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 标题图片
     */
    private String titleImage;

    /**
     * 广告所在的位置1.心安    2.商城   3、美食
     */
    private Integer bannerSpace;

    /**
     * 目标id
     */
    private Long targetId;

    /**
     * 外部链接
     */
    private String bannerUrl;

    /**
     * 内部链接，目标名字
     */
    private String targetName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
