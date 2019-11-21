package com.hailu.cloud.api.mall.module.customerservice.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Administrator
 */
@Data
public class CsApplyEntity {
    /**
     * 售后申请id主键
     */
    private Integer csApplyId;
    /**
     * 商品ID
     */
    private Integer goodsId;
    /**
     * 订单iD
     */
    private Integer orderId;
    /**
     * 订单明细ID
     */
    private Integer orderGoodsId;
    /**
     * 商品数量
     */
    private Integer goodsNum;
    /**
     * 售后原因ID
     */
    private Integer csReasonId;
    /**
     * 描述
     */
    private String remarks;
    /**
     * 照片
     */
    private String photo;
    /**
     * 退回方式
     */
    private Integer returnMode;
    /**
     * 售后类型(0维修，1退货，2换货,3.整单退款)
     */
    private Integer csType;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 收件人手机号码
     */
    private String addresseePhn;
    /**
     * 收货地址
     */
    private String receivingAddress;
    /**
     * 审核留言
     */
    private String reviewMessage;
    /**
     * 维修流程:提交申请(0)，商城审核(1)，收货(2)，处理中(3)，完成(4)，（5）取消申请，（6）商城拒绝 (7)商城拒绝完成 ;
     * 换货流程:提交申请(0)，商城审核(1)，收货(2)，处理中(3)，完成(4),（5）取消申请，（6）商城拒绝;(7)商城拒绝完成 ;
     * 七天无理由退货: 提交申请(0)，商城审核(1)，收货(2)，退款(3)，完成(4) ，（5）取消申请，（6）商城拒绝）(7)商城拒绝完成 ;
     */
    private Integer tpState;
    /**
     * 0未删除1删除
     */
    private Integer deleteStatus;
    /**
     * 创建时间
     */
    private Long createDate;
    /**
     * 创建人
     */
    private String createName;
    /**
     * 更新时间
     */
    private Long updateDate;
    /**
     * 更新人
     */
    private String updateName;
    /**
     * 售后订单号
     */
    private String csNumber;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 差价原因
     */
    private String causeDifference;
    /**
     * 售后原因ID
     */
    private String csReason;
    /**
     * 用户金额
     */
    private Double userAmount;
    /**
     * 快递单号
     */
    private String courierNumber;
    /**
     * 快递公司号码
     */
    private String courierCompany;
    /**
     * 快递公司编号
     */
    private String courier_code;
    /**
     * 是否填写快递单号
     */
    private Integer isCourier;
}
