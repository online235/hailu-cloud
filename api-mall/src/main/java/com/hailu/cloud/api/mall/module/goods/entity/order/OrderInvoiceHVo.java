package com.hailu.cloud.api.mall.module.goods.entity.order;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
public class OrderInvoiceHVo {
    /**
     * 订单ID
     */
    private int orderId;
    /**
     * 发票抬头[普通发票]
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
     * 会员ID
     */
    private String memberId;
    /**
     * 1普通发票2增值税发票
     */
    private int invState;
    private long createTime;

    public OrderInvoiceHVo(int orderId, String invTitle, String invCompany, String invCode, String memberId) {
        this.orderId = orderId;
        this.invTitle = invTitle;
        this.invCompany = invCompany;
        this.invCode = invCode;
        this.memberId = memberId;
        this.invState = 1;
        this.createTime = System.currentTimeMillis();
    }

}
