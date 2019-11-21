package com.hailu.cloud.api.mall.module.goods.entity.order;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class OrderParam {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 购物车ids
     */
    private String cartIdStr;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 商品规格id
     */
    private Integer goodsSpecId;
    /**
     * 规格名
     */
    private String goodsSpecName;
    /**
     * 商品图片封面
     */
    private String goodsImage;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 商品数量
     */
    private Integer goodsNum;
    /**
     * 商品类型id
     */
    private Integer gcId;
    /**
     * 收货地址id
     */
    private Integer addressId;
    /**
     * 省级名
     */
    private String cityName;
    /**
     * 使用健康豆
     */
    private Integer poinPrice;
    /**
     * 优惠卷id
     */
    private Integer couponId;
    /**
     * 使用红包 0_否,1_是
     */
    private Integer isRedEnvelope;
    /**
     * 是否开发票 0_否 , 1_是
     */
    private Integer isInvoice;
    /**
     * 发票抬头
     */
    private String invTitle;
    /**
     * 单位名称
     */
    private String invCompany;
    /**
     * 纳税人识别号
     */
    private String invCode;
    /**
     * 是否有赠 品 1_是,0_否 09/17/2019 废弃直接判断是否有赠品
     */
    private Integer isCompl = 0;
    /**
     * 商品类型 0_正常，2_会员周,3_健康豆
     */
    private Integer type = 0;
    /**
     * 备注
     */
    private String remark;

    /**
     *
     */
    private String inviteUserId;

}
