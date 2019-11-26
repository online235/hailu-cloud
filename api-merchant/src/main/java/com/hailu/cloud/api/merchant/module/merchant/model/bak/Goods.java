package com.hailu.cloud.api.merchant.module.merchant.model.bak;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author cgl
 * 时间: 2015年06月10日16:20:41
 * 商品实体类
 */
@Data
@ApiModel("商品实体类")
public class Goods extends BaseModel {
    /**
     * 主键ID
     */
    @ApiModelProperty("商品ID")
    private Integer goodsId;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String goodsName;

    /**
     * 商品副标题
     */
    @ApiModelProperty("商品副标题")
    private String goodsSubtitle;

    /**
     * 商品分类id
     */
    @ApiModelProperty("商品分类id")
    private Integer gcId;

    /**
     * 商品分类名称
     */
    @ApiModelProperty("商品分类名称")
    private String gcName;

    /**
     * 商品品牌id
     */
    @ApiModelProperty("商品品牌id")
    private Integer brandId;

    /**
     * 商品品牌名称
     */
    @ApiModelProperty("商品品牌名称")
    private String brandName;

    /**
     * 类型id
     */
    @ApiModelProperty("类型id")
    private Integer typeId;

    /**
     * 店铺id
     */
    @ApiModelProperty("店铺id")
    private Long storeId;

    /**
     * 店铺名称
     */
    @ApiModelProperty("店铺名称")
    private String storeName;

    /**
     * 商品规格开启状态，1开启，0关闭
     */
    @ApiModelProperty("商品规格开启状态，1开启，0关闭")
    private Integer specOpen;

    /**
     * 商品默认对应的规格id
     */
    @ApiModelProperty("商品默认对应的规格id")
    private Integer specId;

    /**
     * 规格名称
     */
    @ApiModelProperty("规格名称")
    private String specName;

    /**
     * 商品默认封面图片
     */
    @ApiModelProperty("商品默认封面图片")
    private String goodsImage;

    /**
     * 商品多图
     */
    @ApiModelProperty("商品多图")
    private String goodsImageMore;

    /**
     * 商品店铺价格
     */
    @ApiModelProperty("商品店铺价格")
    private BigDecimal goodsStorePrice;

    /**
     * 商品店铺会员价格
     */
    private BigDecimal goodsStoreVipPrice;

    /**
     * 价格区间
     */
    @ApiModelProperty("价格区间")
    private String goodsStorePriceInterval;

    /**
     * 商品货号
     */
    @ApiModelProperty("商品货号")
    private String goodsSerial;

    /**
     * 商品上架1下架0仓库中2
     */
    @ApiModelProperty("商品上架1下架0仓库中2")
    private Integer goodsShow;

    /**
     * 商品浏览数
     */
    @ApiModelProperty("商品浏览数")
    private Integer goodsClick;

    /**
     * 商品状态,30:审核通过,40:违规下架,50:审核未通过,60:待审核
     */
    @ApiModelProperty("商品状态,30:审核通过,40:违规下架,50:审核未通过,60:待审核")
    private Integer goodsState;


    /**
     * 商品推荐
     * 是:1 否:0
     */
    @ApiModelProperty("商品推荐 是:1 否:0")
    private Integer goodsCommend;

    /**
     * 商品关键字
     */
    @ApiModelProperty("商品关键字")
    private String goodsKeywords;

    /**
     * 商品描述
     */
    @ApiModelProperty("商品描述")
    private String goodsDescription;

    /**
     * 商品详细内容
     */
    @ApiModelProperty("商品详细内容")
    private String goodsBody;

    /**
     * 商品属性
     */
    @ApiModelProperty("商品属性")
    private String goodsAttr;

    /**
     * 商品规格
     */
    @ApiModelProperty("商品规格")
    private String goodsSpec;

    /**
     * 颜色自定义图片
     */
    @ApiModelProperty("颜色自定义图片")
    private String goodsColImg;

    /**
     * 商品类型,1为全新、2为二手
     */
    @ApiModelProperty("商品类型,1为全新、2为二手")
    private Integer goodsForm;

    /**
     * 运费模板ID，不使用运费模板值为0
     */
    @ApiModelProperty("运费模板ID，不使用运费模板值为0")
    private Integer transportId;

    /**
     * 平邮
     */
    @ApiModelProperty("平邮")
    private BigDecimal pyPrice;

    /**
     * 快递
     */
    @ApiModelProperty("快递")
    private BigDecimal kdPrice;

    /**
     * EMS
     */
    @ApiModelProperty("EMS")
    private BigDecimal esPrice;

    /**
     * 商品所在地(市)
     */
    @ApiModelProperty("商品所在地(市)")
    private Integer cityId;
    /**
     * 商品所在地(市)的名字
     */
    @ApiModelProperty("商品所在地(市)的名字")
    private String cityName;

    /**
     * 商品所在地(省)
     */
    @ApiModelProperty("商品所在地(省)")
    private Integer provinceId;

    /**
     * 商品所在地(省)的名字
     */
    @ApiModelProperty("商品所在地(省)的名字")
    private String provinceName;

    /**
     * 商品违规下架原因
     */
    @ApiModelProperty("商品违规下架原因")
    private String goodsCloseReason;

    /**
     * 商品所在店铺状态 0开启 1关闭
     */
    @ApiModelProperty("商品所在店铺状态 0开启 1关闭")
    private Integer goodsStoreState;

    /**
     * 评论次数
     */
    @ApiModelProperty("评论次数")
    private Integer commentnum;

    /**
     * 售出数量
     */
    @ApiModelProperty("售出数量")
    private Integer salenum;

    /**
     * 商品收藏数量
     */
    @ApiModelProperty("商品收藏数量")
    private Integer goodsCollect;

    /**
     * 商品运费承担方式 默认 0为买家承担 1为卖家承担
     */
    @ApiModelProperty("商品运费承担方式 默认 0为买家承担 1为卖家承担")
    private Integer goodsTransfeeCharge;

    /**
     * 店铺自定义分类id
     */
    @ApiModelProperty("店铺自定义分类id")
    private Integer storeClassId;

    /**
     * 商品的库存,在存数据库的时候不用这个字段,只在取的时候用到
     */
    @ApiModelProperty("商品的库存,在存数据库的时候不用这个字段,只在取的时候用到")
    private Integer goodsTotalStorage;

    /**
     * 分类路径classPath
     */
    @ApiModelProperty("分类路径classPath")
    private String classPath;

    /**
     * 需要排序的字段
     */
    @ApiModelProperty("需要排序的字段")
    private String sortField;

    /**
     * 时间排序,降序desc,升序asc
     */
    @ApiModelProperty("时间排序,降序desc,升序asc")
    private String orderBy;
    /**
     * 栏目里包括的ids
     */
    @ApiModelProperty("栏目里包括的ids")
    private String[] goodids;

    /**
     * 商品市场价格
     */
    @ApiModelProperty("商品市场价格")
    private BigDecimal goodsMarketPrice;

    /**
     * 商品成本价格
     */
    @ApiModelProperty("商品成本价格")
    private BigDecimal goodCostPrice;
    //一级分类
    @ApiModelProperty("一级分类")
    private Integer gcBigId;
    //商品pc详情
    @ApiModelProperty("商品pc详情")
    private String goodsPcBody;
    //商品对应赠送积分
    @ApiModelProperty("商品对应赠送积分")
    private Integer goodsIntegral;
    //一级分类名字
    @ApiModelProperty("一级分类名字")
    private String gcBigName;

    //商品pc售后保障详情
    @ApiModelProperty("商品pc售后保障详情")
    private String goodsPcServerBody;

    //满额包邮金额
    @ApiModelProperty("满额包邮金额")
    private BigDecimal fullFreeMail;

    //一级佣金比例
    @ApiModelProperty("一级佣金比例")
    private BigDecimal oneCommission;

    //二级佣金比例
    @ApiModelProperty("二级佣金比例")
    private BigDecimal twoCommission;

    //是否参与分销
    @ApiModelProperty("是否参与分销")
    private String isShare;

    //是否推荐到购物车,0_否,1_是
    @ApiModelProperty("是否推荐到购物车,0_否,1_是")
    private String isCartRecommend;

    //区域代理提成(整数)
    @ApiModelProperty("区域代理提成(整数元)")
    private Integer regionalAgentCommission;

    //服务商提成(整数)
    @ApiModelProperty("服务商提成(整数元)")
    private Integer serviceProviderCommission;

    //是否参与推广 0_否  1_参与
    private int isPopularize;
}
