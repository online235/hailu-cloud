package com.hailu.cloud.api.merchant.module.merchant.model;

import com.hailu.cloud.api.merchant.module.merchant.entity.McCoupon;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: QiuFeng:WANG
 * @Description: 优惠卷
 * @Date: 2019/12/24 0024
 * @program: cloud
 * @create: 2019-12-24 15:
 */
@Data
@ApiModel
public class CouponAndReceiveModel extends McCoupon {

    /**
     * 店铺编号
     */
    @ApiModelProperty(value = "店铺编号")
    private Long storeId;

    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    /**
     * 店铺头像
     */
    @ApiModelProperty(value = "店铺头像")
    private String defaultHead;

    /**
     * 卷图片编号
     */
    @ApiModelProperty(value = "卷图片编号")
    private Long pictureId;

    /**
     * 卷图片路径
     */
    @ApiModelProperty(value = "卷图片路径")
    private String picturePath;

    /**
     * 优惠卷编号
     */
    @ApiModelProperty(value = "优惠卷编号")
    private Long receiveId;

    /**
     * 使用状态(待使用-1、已使用-2、已失效-3)
     */
    @ApiModelProperty(value = "使用状态(待使用-1、已使用-2、已失效-3)")
    private Long usageState;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date receiveDateTime;

    /**
     * 兑奖码
     */
    @ApiModelProperty(value = "兑奖码")
    private String awardCode;
}
