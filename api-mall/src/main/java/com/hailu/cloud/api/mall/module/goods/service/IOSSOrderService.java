package com.hailu.cloud.api.mall.module.goods.service;


import com.hailu.cloud.api.mall.module.goods.entity.order.OrderToVo;
import com.hailu.cloud.common.exception.BusinessException;

import java.util.Map;

/**
 * 第三方支付
 */
public interface IOSSOrderService {

    /**
     * @Author HuangL
     * @Email huangl96@163.com
     * @Description 当支付金额为0时调用
     * @Date 2018/11/28 14:49
     */
    void addZeroPay(OrderToVo ol);

    /**
     * 下单购买
     * @return
     */
    Map<String,Object> createOrder(String orderNo,Integer payType,String openId,String returnUrl) throws BusinessException;

    /**
     * 回调
     */
    void callback() throws BusinessException, Exception;

    /**
     * 银联支付回调
     * @throws BusinessException
     */
    void chinaumsCallback() throws BusinessException;

}
