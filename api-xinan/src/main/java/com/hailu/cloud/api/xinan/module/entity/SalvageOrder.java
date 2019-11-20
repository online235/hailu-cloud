package com.hailu.cloud.api.xinan.module.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SalvageOrder implements Serializable {
    /**
     * 编号
     */
    private String numberId;

    /**
     * 捐助会员编号
     */
    private String memberId;

    /**
     * 救助编号
     */
    private String rescueId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 状态(1-正常、2-删除)
     */
    private Integer status;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品类型
     */
    private String itemType;

    /**
     * 捐助金额
     */
    private BigDecimal money;

    /**
     * 订单来源
     */
    private String froms;

    /**
     * 支付方式（1-支付宝、2-微信、3-微信H5）
     */
    private Integer payType;

    /**
     * 订单状态（1-未付款、2-已付款、3-已取消）
     */
    private Integer orderStatus;

    /**
     * 邀请人
     */
    private String invitationMember;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * xa_salvage_order
     */
    private static final long serialVersionUID = 1L;
}