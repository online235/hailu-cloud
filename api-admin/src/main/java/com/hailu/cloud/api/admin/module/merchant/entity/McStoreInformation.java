package com.hailu.cloud.api.admin.module.merchant.entity;


import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangmugui
 */
@Data
@InjectDict
@ApiModel
public class McStoreInformation {


    /**
     * 编号
     */
    private Long id;


    /**
     * 商家编号
     */
    private String mcNumberId;

    /**
     * 店铺头像
     */
    private String defaultHead;

    /**
     *店铺名称
     */
    private String shopName;



    /**
     *店铺联系电话
     */
    private String phone;


    /**
     *省Id
     */
    private String provinceCode;


    /**
     *市Id
     */
    private String cityCode;


    /**
     *区id
     */
    private String areaCode;

    /**
     *店铺详细地址
     */
    private String detailAddress;


    /**
     *店铺详情
     */
    private String storeDetails;

    /**
     *人均价格
     */
    private java.math.BigDecimal perCapitaPrice;


    /**
     * 最低消费
     */
    private BigDecimal minPrice;



    /**
     *店铺子类型ID
     */
    private Long storeSonType;


    /**
     *店铺总类型ID
     */
    private Long storeTotalType;


    /**
     * 营业状态(1-营业中，2-休息中)
     */
    private Integer businessState;


    @DictName(code = "BUSINESS_STATUS", joinField = "businessState")
    private String businessStateDisplay;




    /**
     * 经营时间，多段“,”拼接;例如“08:00-12:00,14:00-16:00”
     */
    private String businessTime;



    /**
     * 创建时间
     */
    private java.util.Date dateTime;

    /**
     * 更新时间
     */
    private java.util.Date updateDateTime;


    /**
     * 审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')'
     */
    private Integer toExamine;


    @DictName(code = "TO_EXAMINE", joinField = "toExamine")
    private String toExamineDisplay;

    /**
     * 每周营业日用，“；”隔开（例1；2；3；4:）
     */
    private String weekDay;



    @DictName(code = "BUSINESS_DAY" , joinField = "weekDay")
    private String weekDayDisplay;

    /**
     * '1、生活圈入驻店铺；2、百货入驻店铺'
     */
    private Integer accountType;


    @DictName(code = "ACCOUNT_TYPE" , joinField = "accountType")
    private String accountTypeDisplay;


    /**
     *店铺位置经度
     */
    @ApiModelProperty("店铺位置经度")
    private java.math.BigDecimal longitude;


    /**
     *店铺纬度
     */
    @ApiModelProperty("店铺纬度")
    private java.math.BigDecimal latitude;


}
