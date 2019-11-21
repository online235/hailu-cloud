package com.hailu.cloud.api.mall.module.customerservice.vo;

import lombok.Data;

@Data
public class CsApplyExpressVo {
    /**
     * 售后申请id主键
     */
    private Integer csApplyId;
    /**
     * 快递单号
     */
    private String courierNumber;
    /**
     * 快递公司
     */
    private String courierCompany;
    /**
     * 快递编码
     */
    private String courierCode;
    /**
     * 0未填写，1已填写
     */
    private String isCourier;
}
