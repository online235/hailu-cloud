package com.hailu.cloud.api.mall.module.pay.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.pay.vo
 * @Author: junpei.deng
 * @CreateTime: 2019-10-21 17:55
 * @Description: 支付参数
 */
@ToString
@Data
public class PayVo {

    /**
     * 会员ID
     */
    private String memberId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 支付来源   XA-心安 mall-商城 HL-海露
     */
    private String payFrom;

    /**
     * 支付类型 1-支付宝 2-微信 3-微信H5
     */
    private int payType;

    /**
     * 金额
     */
    private Double money;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 邀请人ID
     */
    private String invitationMember;

    /**
     * 参保人ID，以逗号分隔
     */
    private String insuredIds;

    /**
     * 省ID
     */
    private Long provinceId;

    /**
     * 市ID
     */
    private Long cityId;

    /**
     * 区县ID
     */
    private Long areaId;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 名称
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 商品类型  1-会员、 2-服务商
     */
    private Integer itemType;

    /**
     * 服务商选择城市的ID
     */
    private Long chooseCityId;

    /**
     * 公众号支付OpenId
     */
    private String openId;
}
