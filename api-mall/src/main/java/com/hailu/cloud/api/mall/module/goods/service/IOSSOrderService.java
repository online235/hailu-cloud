package com.hailu.cloud.api.mall.module.goods.service;


import com.hailu.cloud.api.mall.module.goods.entity.order.OrderToVo;
import com.hailu.cloud.common.exception.BusinessException;

import java.util.Map;

/**
 * 第三方支付
 */
public interface IOSSOrderService {

//    /**
//     * @Author HuangL
//     * @Description 得到微信签名发送验证
//     * @Date 2018-10-30_09:16
//     */
//    Map<String, Object> sendUrlWeChat(String url);

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
    Map<String,Object> createOrder(String orderNo,Integer payType,String openId) throws BusinessException;

    /**
     * 回调
     * @param params
     */
    void callback(Map<String, Object> params) throws BusinessException;

}
