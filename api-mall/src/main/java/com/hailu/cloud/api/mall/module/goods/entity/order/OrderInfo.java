package com.hailu.cloud.api.mall.module.goods.entity.order;

import com.hailu.cloud.api.mall.module.goods.vo.AddressVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Administrator
 */
@Data
public class OrderInfo {
    private String userId;
    private Integer orderId;
    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 订单生成时间
     */
    private Long createTime;
    /**
     * 收货地址id
     */
    private Integer addressId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 运费
     */
    private Double freight;
    /**
     * 商品总金额
     */
    private Double goodsAmount;
    /**
     * 订单应付金额
     */
    private Double orderAmount;
    /**
     * 优惠金额
     */
    private Double discount;
    /**
     * 优惠卷id
     */
    private Integer couponId;
    /**
     * 优惠卷金额
     */
    private Double couponPrice;
    /**
     * 订单状态：0:已取消,1:待付款,2:待发货,3:待收货,4:交易完成,5:已提交,6:已确认
     */
    private Integer orderState;

    /**
     * 是否预定 1_是,0_否
     */
    private Double isReserve;
    /**
     * 预定id
     */
    private Integer reserveId;
    /**
     * 第一阶段支付金额
     */
    private Double reserveOneAmount;
    /**
     * 第一階段是否支付
     */
    private Integer oneIsPay;
    /**
     * 第二階段是否支付
     */
    private Integer twoIsPay;
    /**
     * 第二阶段支付金额
     */
    private Double reserveTwoAmount;
    /**
     * 是否评价 0_否 , 1_是
     */
    private Integer evaluateState;
    /**
     * 发票
     */
    private OrderInvoiceHVo orderInvoice;
    /**
     * 取消订单时间
     */
    private long cancelOrderTime;
    /**
     * 现在的时间
     */
    private long newTime;
    /**
     * 第二阶段开始支付时间
     */
    private long twoPayStartTime;
    /**
     * 第二阶段结束支付时间
     */
    private long twoPayEndTime;
    private Integer isRecour;
    /**
     * 退款状态:1是无退款,2是部分退款,3是全部退款
     */
    private Integer refundState;
    /**
     * 退货状态:1是无退货,2是部分退货,3是全部退货
     */
    private Integer returnState;
    /**
     * 预计发货时间
     */
    private Long predictTime;
    /**
     * 健康豆对应金额
     */
    private double deductionAmount;
    /**
     * 预定优惠金额
     */
    private Double reserveDiscounts;
    /**
     * 支付方式名
     */
    private String paymentName;
    /**
     * 订单商品list
     */
    private List<OrderGoods> orderGoods;
    private AddressVo addres;
    /**
     * 0_否,1_是
     */
    private String isLimit;

}
