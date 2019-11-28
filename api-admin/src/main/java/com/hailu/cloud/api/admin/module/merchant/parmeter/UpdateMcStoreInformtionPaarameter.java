package com.hailu.cloud.api.admin.module.merchant.parmeter;


import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hailu.cloud.common.fill.annotation.DictName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.text.DateFormat;

/**
 * @author zhangmugui
 */
@ApiModel
@Data
public class UpdateMcStoreInformtionPaarameter {


    /**
     * 店铺编号id
     */
    @ApiParam(name = "id", value = "店铺编号id",required = true)
    @NotNull
    private Long id;


    /**
     * 商家编号
     */
    @ApiParam(name = "mcNumberId", value = "商家编号")
    private String mcNumberId;


    /**
     * 店铺名称
     */
    @ApiParam(name = "shopName", value = "店铺名称")
    private String shopName;


    /**
     * 店铺联系电话
     */
    @ApiParam(name = "phone", value = "店铺联系电话")
    private String phone;


    /**
     * 省code
     */
    @ApiParam(name = "provinceCode", value = "省Id")
    private String provinceCode;


    /**
     * 市code
     */
    @ApiParam(name = "cityCode", value = "市code")
    private String cityCode;


    /**
     * 区code
     */
    @ApiParam(name = "areaCode", value = "区code")
    private String areaCode;

    /**
     * 店铺详细地址
     */
    @ApiParam(name = "detailAddress", value = "店铺详细地址")
    private String detailAddress;


    /**
     * 店铺详情
     */
    @ApiParam(name = "storeDetails", value = "店铺详情")
    private String storeDetails;

    /**
     * 人均价格
     */
    @ApiParam(name = "perCapitaPrice", value = "人均价格")
    private java.math.BigDecimal perCapitaPrice;


    /**
     * 店铺子类型ID
     */
    @ApiParam(name = "storeSonType", value = "店铺子类型ID")
    private Long storeSonType;


    /**
     * 店铺总类型ID
     */
    @ApiParam(name = "storeTotalType", value = "店铺总类型ID")
    private Long storeTotalType;


    /**
     * 营业状态(1-营业中，2-休息中)
     */
    @ApiParam(name = "businessState", value = "营业状态(1-营业中，2-休息中)")
    private Long businessState;


    /**
     * 关闭时间
     */
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    @ApiParam(name = "closingTime", value = "关闭时间")
    private java.util.Date closingTime;


    /**
     * 开店时间
     */
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    @ApiParam(name = "openingTime", value = "开店时间")
    private java.util.Date openingTime;


    /**
     * 审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')'
     */
    @ApiParam(name = "toExamine", value = "审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')'")
    private Integer toExamine;


    /**
     * 每周营业日用，“；”隔开（例1；2；3；4:）
     */
    @ApiParam(name = "weekDay", value = "每周营业日用，“；”隔开（例1；2；3；4:），1代表周一")
    private String weekDay;



}
