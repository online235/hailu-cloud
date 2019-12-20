package com.hailu.cloud.api.admin.module.merchant.entity;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 * @date         : 2019/12/20 0020 10:28
 */
@Data
@ApiModel
@InjectDict
public class McCouponPicture implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Long id;

    /**
     * 卷编号
     */
    @ApiModelProperty(value = "卷编号")
    private Long couponId;

    /**
     * 图片类型(主图-1、特色-2)
     */
    @ApiModelProperty(value = "图片类型(主图-1、特色-2)")
    private Integer pictureType;

    @DictName(code = "VOLUME_PICTURE_TYPE", joinField = "pictureType")
    private Integer pictureTypeDisPlay;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径")
    private String picturePath;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date dateTime;

    /**
     * 状态(启用-1、禁用-0)
     */
    @ApiModelProperty(value = "状态(启用-1、禁用-0)")
    private Integer state;

    @DictName(code = "ENABLE_STATUS", joinField = "state")
    private Integer stateDisPlay;

    /**
     * mc_coupon_picture
     */
    private static final long serialVersionUID = 1L;
}