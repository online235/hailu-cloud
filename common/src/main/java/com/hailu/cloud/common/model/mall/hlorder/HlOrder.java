package com.hailu.cloud.common.model.mall.hlorder;

import com.hailu.cloud.common.model.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 海露订单
 * @author junpei.deng
 */
@Data
public class HlOrder extends BaseEntity {
    /**
     * id
     */
    private Long id;

    /**
     * 会员ID
     */
    private String userId;

    /**
     * 操作人ID
     */
    private Long editId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 买家留言
     */
    private String remark;

    /**
     * 订单状态（1-待支付、2-待发货、3-已发货、4-已完成）
     */
    private Integer orderStatus;

    /**
     * 收件人
     */
    private String recipient;

    /**
     * 快递公司
     */
    private String courierCompany;

    /**
     * 快递单号
     */
    private String courierNumber;

    /**
     * 物流状态（1-已发货、2-待接收、3-已完成）
     */
    private Integer logisticsStatus;

    /**
     * 省ID
     */
    private String provinceId;

    /**
     * 市ID
     */
    private String cityId;

    /**
     * 区县ID
     */
    private String areaId;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 订单类型（1-购买服务商）
     */
    private Integer orderType;

    /**
     * 状态（1-正常、2-删除）
     */
    private Integer status;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 邀请人
     */
    private String invitationMember;

    /**
     * 支付类型（1-支付宝、2-微信、3-微信H5）
     */
    private Integer payType;

    /**
     * 收件人手机号码
     */
    private String recipientPhone;

    private Date payTime;

    /**
     * hl_order
     */
    private static final long serialVersionUID = 1L;
}