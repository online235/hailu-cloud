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
    @ApiModelProperty("省code")
    private String provinceCode;

    /**
     * 市Id
     */
    @ApiModelProperty("市code")
    private String cityCode;

    /**
     * 区id
     */
    @ApiModelProperty("区code")
    private String areaCode;


    /**
     *街道code
     */
    @ApiModelProperty("街道code")
    private String streetCode;

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
     * 最低消费
     */
    @ApiModelProperty("最低消费")
    private BigDecimal minPrice;

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
     * 经营时间，多段“,”拼接;例如“08:00-12:00,14:00-16:00”
     */
    @ApiModelProperty("经营时间，多段“,”拼接;例如“08:00-12:00,14:00-16:00”")
    private String businessTime;



    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date dateTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateDateTime;

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

    /**
     *店铺位置经度
     */
    @ApiModelProperty("店铺位置经度")
    private String longitude;


    /**
     *店铺纬度
     */
    @ApiModelProperty("店铺纬度")
    private String latitude;



    @DictName(code = "ACCOUNT_TYPE" , joinField = "accountType")
    private String accountTypeDisplay;


}
