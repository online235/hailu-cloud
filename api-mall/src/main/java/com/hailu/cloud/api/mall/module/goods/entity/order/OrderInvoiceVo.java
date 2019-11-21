package com.hailu.cloud.api.mall.module.goods.entity.order;

import lombok.Data;


/**
 * 发票
 *
 * @author liutao
 */
@Data
public class OrderInvoiceVo {
    private Integer id;
    //订单号
    private String orderNumber;
    //订单
    private Integer orderId;
    //发票类型
    private Integer invoiceType;
    //发票抬头类型
    private Integer titleType;
    //发票金额
    private Double invoiceAmount;
    //收票人手机
    private String phone;
    //收票人地址
    private String address;
    //纳税人识别号
    private String identificationNumber;
    //单位全称
    private String theUnitName;
    //收票人姓名
    private String name;
    //注册地址
    private String registrationAddress;
    //注册电话
    private String registrationPhone;
    //开户银行
    private String bank;
    //银行账号
    private String bankAccount;
    //邮编
    private String postcode;
    //下单时间
    private java.util.Date orderTime;
    //企业税号
    private String enterpriseIdNumber;
    //开票状态
    private Integer invoicingStatus;
}
