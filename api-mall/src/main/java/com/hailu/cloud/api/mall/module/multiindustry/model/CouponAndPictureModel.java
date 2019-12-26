package com.hailu.cloud.api.mall.module.multiindustry.model;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McCoupon;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: QiuFeng:WANG
 * @Description: 到店卷
 * @Date: 2019/12/26 0026
 * @program: cloud
 * @create: 2019-12-26 18:
 */
@Data
@ApiModel
public class CouponAndPictureModel extends McCoupon {

    /**
     * 图片编号
     */
    @ApiModelProperty(value = "图片编号")
    private Long pictureId;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径")
    private String picturePath;

}
