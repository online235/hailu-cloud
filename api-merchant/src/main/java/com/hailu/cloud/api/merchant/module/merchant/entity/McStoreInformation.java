package com.hailu.cloud.api.merchant.module.merchant.entity;


import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import lombok.Data;

@Data
@InjectDict
public class McStoreInformation {


    /**
     * 编号
     */
    private Long id;

    /**
     * 店铺头像
     */
    private String shopAvatar;


    /**
     * 商家编号
     */
    private String mcNumberId;


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
     * 关闭时间
     */
    private String closingTime;


    /**
     * 开店时间
     */
    private String openingTime;



    /**
     * 创建时间
     */
    private java.util.Date createdat;

    /**
     * 更新时间
     */
    private java.util.Date updatedat;


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





}
