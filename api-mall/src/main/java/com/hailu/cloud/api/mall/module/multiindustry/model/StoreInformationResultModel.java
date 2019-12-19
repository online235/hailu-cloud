package com.hailu.cloud.api.mall.module.multiindustry.model;

import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreAlbum;
import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhangmugui
 */
@Data
@InjectDict
@ApiModel
public class StoreInformationResultModel{
    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private Long id;

    /**
     * 商家编号
     */
    @ApiModelProperty("商家编号")
    private String mcNumberId;

    /**
     * 店铺名称
     */
    @ApiModelProperty("店铺名称")
    private String shopName;

    /**
     * 店铺联系电话
     */
    @ApiModelProperty("店铺联系电话")
    private String phone;

    /**
     * 省Id
     */
    @ApiModelProperty("省Id")
    private String provinceCode;

    /**
     * 市Id
     */
    @ApiModelProperty("市Id")
    private String cityCode;

    /**
     * 区id
     */
    @ApiModelProperty("区id")
    private String areaCode;

    /**
     * 店铺详细地址
     */
    @ApiModelProperty("店铺详细地址")
    private String detailAddress;

    /**
     * 店铺详情
     */
    @ApiModelProperty("店铺详情")
    private String storeDetails;

    /**
     * 人均价格
     */
    @ApiModelProperty("人均价格")
    private BigDecimal perCapitaPrice;

    /**
     * 店铺子类型ID
     */
    @ApiModelProperty("店铺子类型ID")
    private Long storeSonType;

    /**
     * 店铺总类型ID
     */
    @ApiModelProperty("店铺总类型ID")
    private Long storeTotalType;

    /**
     * 营业状态(1-营业中，2-休息中)
     */
    @ApiModelProperty("营业状态(1-营业中，2-休息中)")
    private Integer businessState;

    /**
     * 营业状态(1-营业中，2-休息中)
     */
    @DictName(code = "BUSINESS_STATUS", joinField = "businessState")
    @ApiModelProperty("营业状态(营业中，休息中)")
    private String businessStateDisplay;

    /**
     * 关闭时间
     */
    @ApiModelProperty("关闭时间")
    private String closingTime;

    /**
     * 开发时间
     */
    @ApiModelProperty("开发时间")
    private String openingTime;


    /**
     * 审核('审核中-1','审核通过-2','审核不通过-3')
     */
    @ApiModelProperty("审核('审核中-1','审核通过-2','审核不通过-3')")
    private Integer toExamine;

    @DictName(code = "TO_EXAMINE", joinField = "toExamine")
    @ApiModelProperty("审核('审核中','审核通过','审核不通过')")
    private String toExamineDisplay;

    /**
     * 店铺头像
     */
    @ApiModelProperty("店铺头像")
    private String defaultHead;

    /**
     * 每周营业日用（1星期日，2星期一）
     */
    @ApiModelProperty("每周营业日用（1星期日，2星期一）")
    private String weekDay;

    @DictName(code = "BUSINESS_DAY" , joinField = "weekDay")
    @ApiModelProperty("每周营业日用（1星期日，2星期一）")
    private String weekDayDisplay;


    @ApiModelProperty(value = "相册列表")
    private List<StoreAlbum>  storeAlbumList;


    /**
     * 经营子类类型名称
     */
    @ApiModelProperty("经营子类类型名称")
    private String storeTypeName;


    /**
     * 经营父类类型名称
     */
    @ApiModelProperty("经营父类类型名称")
    private String storeTotalTypeName;



}