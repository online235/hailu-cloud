package com.hailu.cloud.api.mall.module.multiindustry.model;

import com.hailu.cloud.api.mall.module.multiindustry.entity.MultiIndustryOrder;
import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/1 0001
 */
@Data
@InjectDict
public class McOrderModel<C> extends MultiIndustryOrder<C> {

    /**
     * 店铺默认头像
     */
    @ApiModelProperty(value = "店铺默认头像", dataType = "String")
    private String defaultHead;

    /**
     * 营业状态(1-营业中，2-休息中)
     */
    @ApiModelProperty(value = "营业状态(1-营业中，2-休息中)", dataType = "String")
    private String businessState;

    @ApiModelProperty(value = "营业状态", dataType = "String")
    @DictName(code = "BUSINESS_STATUS", joinField = "businessState")
    private String businessStateDisPlay;

    /**
     * 开发时间
     */
    @ApiModelProperty(value = "开放时间", dataType = "String")
    private String openingTime;

    /**
     * 关闭时间
     */
    @ApiModelProperty(value = "关闭时间", dataType = "String")
    private String closingTime;
}
