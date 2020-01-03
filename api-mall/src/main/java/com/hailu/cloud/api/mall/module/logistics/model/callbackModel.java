package com.hailu.cloud.api.mall.module.logistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: QiuFeng:WANG
 * @Description: 回调参数
 * @Date: 2020/1/2 0002
 * @program: cloud
 * @create: 2020-01-02 11:
 */
@ApiModel
@Data
public class callbackModel<M> {

    /**
     * data数据
     */
    @ApiModelProperty(value = "data数据")
    private M callbackData;

    /**
     * 客户标识
     */
    @ApiModelProperty(value = "客户标识")
    private String clientFlag;

    /**
     * 签名
     */
    @ApiModelProperty(value = "签名")
    private String verifyData;
}
