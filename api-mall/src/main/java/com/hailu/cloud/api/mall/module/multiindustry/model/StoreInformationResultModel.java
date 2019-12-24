package com.hailu.cloud.api.mall.module.multiindustry.model;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McStoreAlbum;
import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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


    @ApiModelProperty("省code")
    private String provinceCode;


    @ApiModelProperty("市code")
    private String cityCode;


    @ApiModelProperty("区code")
    private String areaCode;


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


    /**
     * 店铺位置经度
     */
    @ApiModelProperty("店铺位置经度")
    private String longitude;


    /**
     * 店铺纬度
     */
    @ApiModelProperty("店铺纬度")
    private String latitude;


    @ApiModelProperty(value = "店铺轮播图")
    private List<RotationStoreModel>  rotationStoreModelList;


    /**
     * 图片总数量
     */
    @ApiModelProperty(value = "图片总数量")
    private Integer albumNum;


}