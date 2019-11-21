package com.hailu.cloud.api.mall.module.customerservice.vo;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class CsApplyListVo {
    /**
     * 售后订单号
     */
    private String csNumber;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品图片
     */
    private String goodsImage;
    /**
     * 商品数量
     */
    private int goodsNum;
    private Integer tpState;
    /**
     * 售后申请id主键
     */
    private Integer csApplyId;
    /**
     * 原因类型（0，维修，1换货，2退货）
     */
    private Integer csType;
    /**
     * 审核内容
     */
    private String auditContent;
    /**
     * 创建时间
     */
    private Long createDate;

}
