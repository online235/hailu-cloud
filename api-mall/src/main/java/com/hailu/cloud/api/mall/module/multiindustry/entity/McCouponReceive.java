package com.hailu.cloud.api.mall.module.multiindustry.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: QiuFeng:WANG
 * @Description: 优惠卷
 * @Date: 2019/12/24 0024
 * @program: cloud
 * @create: 2019-12-24 11:
 */
@Data
@ApiModel
public class McCouponReceive implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Long id;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private Long memberId;

    /**
     * 店铺编号
     */
    @ApiModelProperty(value = "店铺编号")
    private Long storeId;

    /**
     * 卷编号
     */
    @ApiModelProperty(value = "卷编号")
    private Long couponId;

    /**
     * 使用状态(待使用-1、已使用-2、已失效-3)
     */
    @ApiModelProperty(value = "使用状态(待使用-1、已使用-2、已失效-3)")
    private Integer usageState;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date dateTime;

    /**
     * 使用时间
     */
    @ApiModelProperty(value = "使用时间")
    private Date usageTime;

    /**
     * 兑奖码
     */
    @ApiModelProperty(value = "兑奖码")
    private String awardCode;

    /**
     * mc_coupon_receive
     */
    private static final long serialVersionUID = 1L;
}