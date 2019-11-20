package com.hailu.cloud.api.xinan.module.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @BelongsProject: litemall
 * @BelongsPackage: org.linlinjava.litemall.db.domain.xinAn.vo
 * @Author: junpei.deng
 * @CreateTime: 2019-10-18 17:31
 * @Description: 订单视图
 */
@Data
public class OrderVo {
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
     * 邀请人ID
     */
    private String invitationMember;

    /**
     * 金额
     */
    private BigDecimal money;

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
     * 参保人关系
     */
    private Integer memberRelation;

    /**
     * 订单状态（1-未付款2-已付款3-已取消）
     */
    private Integer orderStatus;


    /**
     * xa_order
     */
    private static final long serialVersionUID = 1L;

    /**
     * 参保人ID
     */
    private String insuredId;

}
