package com.hailu.cloud.api.mall.module.multiindustry.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: QiuFeng:WANG
 * @Description: 优惠卷
 * @Date: 2019/12/24 0024
 * @program: cloud
 * @create: 2019-12-24 11:
 */
@Data
@ApiModel
public class McCouponReceiveModel {

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
}
