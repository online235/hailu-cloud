package com.hailu.cloud.api.xinan.module.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String memberId;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品类型
     */
    private String itemType;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 订单来源
     */
    private String froms;

    /**
     * 支付方式
     */
    private Integer payType;

    /**
     * 参保人名称
     */
    private String insuredName;

    /**
     * 参保人数据
     */
    private String insuredValue;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 邀请人ID
     */
    private String invitationMember;


    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 订单状态（1-未付款2-已付款3-已取消）
     */
    private Integer orderStatus;


    /**
     * xa_order
     */
    private static final long serialVersionUID = 1L;

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
     * 服务商选择的城市
     */
    private Long chooseCityId;
}