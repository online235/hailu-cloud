package com.hailu.cloud.api.mall.module.pay.service;

import com.hailu.cloud.api.mall.module.pay.vo.PayVo;

import java.util.Map;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.pay.service
 * @Author: junpei.deng
 * @CreateTime: 2019-10-21 17:02
 * @Description: 支付服务
 */
public interface IPayService {

    public Map<String, Object> createOrder(PayVo payVo);

    public Map<String,Object> callback(Map<String, Object> params) throws Exception;

}
