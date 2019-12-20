package com.hailu.cloud.api.merchant.module.merchant.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: QiuFeng:WANG
 * @Description: 到店卷其他字段(json格式)
 * @Date: 2019/12/20 0020
 * @program: cloud
 * @create: 2019-12-20 10:
 */
@Data
@ApiModel
public class McCouponOtherJsonModel {

    /**
     * 审核通过立即上架（1-是、2-否）
     */
    @ApiModelProperty(value = "审核通过立即上架（1-是、2-否）")
    private Integer ImmediatelyMounted;

    /**
     * 是否时间限制（1-是、2-否）
     */
    @ApiModelProperty(value = "是否时间限制（1-是、2-否）")
    private Integer timeLimit;

    /**
     * 每周不可用时间(0.1.2.3.4.5.6-星期)
     */
    @ApiModelProperty(value = "每周不可用时间(0.1.2.3.4.5.6-星期)")
    private Integer UnavailableTimeDay;

    /**
     * 节假日不可用时间(节假日)
     */
    @ApiModelProperty(value = "节假日不可用时间(节假日)")
    private String holidayUnavailableTime;

    /**
     * 是否需要预约(1-是、2-否)
     */
    @ApiModelProperty(value = "是否需要预约(1-是、2-否)")
    private Integer needAppointment;

    /**
     * 提前预约时间文本介绍
     */
    @ApiModelProperty(value = "提前预约时间文本介绍")
    private String appointmentIntroduction;

    /**
     * 进店是否可用积累使用
     */
    @ApiModelProperty(value = "进店是否可用积累使用")
    private String accumulationUse;

    /**
     * 到店卷是否全场通用(1-是、2-否)
     */
    @ApiModelProperty(value = "到店卷是否全场通用(1-是、2-否)")
    private Integer universalField;

    /**
     * 可否与其它优惠同享
     */
    @ApiModelProperty(value = "可否与其它优惠同享")
    private String preferentialConcession;

    /**
     * 适用年龄
     */
    @ApiModelProperty(value = "适用年龄")
    private String suitAge;
}
