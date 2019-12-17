package com.hailu.cloud.api.admin.module.merchant.model;
import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商家店铺信息
 *
 * @author zhijie
 */
@Data
@ApiModel
@InjectDict
public class McStoreInformationModel {

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
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createdat;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updatedat;

    /**
     * 审核('审核中-1','审核通过-2','审核不通过-3')
     */
    @ApiModelProperty("审核('审核中-1','审核通过-2','审核不通过-3')")
    private Integer toExamine;


    @DictName(code = "BUSINESS_STATUS", joinField = "businessState")
    @ApiModelProperty("营业状态(1-营业中，2-休息中)")
    private String businessStateDisplay;


    /**
     * 店铺头像
     */
    @ApiModelProperty("店铺头像")
    private String defaultHead;

    /**
     * 每周营业（例1，2，3，4）
     */
    @ApiModelProperty("每周营业（例1，2，3，4）")
    private String weekDay;

    /**
     * 1、生活圈入驻店铺；2、百货入驻店铺
     */
    @ApiModelProperty("1、生活圈入驻店铺；2、百货入驻店铺")
    private Integer accountType;

    @DictName(code = "TO_EXAMINE", joinField = "toExamine")
    @ApiModelProperty("审核('审核中-1','审核通过-2','审核不通过-3')")
    private String toExamineDisplay;



    @DictName(code = "BUSINESS_DAY" , joinField = "weekDay")
    private String weekDayDisplay;

}
