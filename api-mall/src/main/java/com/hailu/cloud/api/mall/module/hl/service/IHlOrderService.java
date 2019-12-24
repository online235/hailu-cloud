package com.hailu.cloud.api.mall.module.hl.service;


import com.hailu.cloud.common.model.mall.hlorder.HlOrder;

import java.math.BigDecimal;

public interface IHlOrderService {

    /**
     *
     * @param userId    用户ID
     * @param editId    操作ID
     * @param goodsName 商品名称
     * @param remark    留言
     * @param recipient 收件人
     * @param courierCompany    快递公司
     * @param courierNumber     快递单号
     * @param provinceId        省ID
     * @param cityId            市ID
     * @param areaId            区县ID
     * @param address           详细地址
     * @param orderType         订单类型
     * @return
     */
    HlOrder buildOrder(String userId, Long editId, String goodsName, String remark, String recipient,
                       String courierCompany, String courierNumber, String provinceId, String cityId,
                       String areaId, String address, Integer orderType, BigDecimal money,String invitationMember,
                       Integer payType);


    /**
     * 根据订单号查询信息
     * @param orderNo
     * @return
     */
    HlOrder findByOrderNo(String orderNo);

    HlOrder saveEntity(HlOrder order);

}
