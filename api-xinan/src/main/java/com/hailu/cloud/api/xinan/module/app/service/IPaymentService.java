package com.hailu.cloud.api.xinan.module.app.service;

import com.hailu.cloud.common.exception.BusinessException;

import java.util.Map;

public interface IPaymentService {

    Map<String, Object> createOrder(Integer payType, Double moneyPrice, String insuredIds ) throws BusinessException;

    void callback(Map<String, Object> params) throws BusinessException;

    /**
     *  海露支付
     * @param payType
     * @param money
     * @param address
     * @param provinceId
     * @param cityId
     * @param areaId
     * @param itemName
     * @param name
     * @param phone
     * @param chooseCityId
     * @param openid
     * @return
     * @throws BusinessException
     */
    Map<String,Object> createHlOrder(Integer payType,double money,String address,Long provinceId,Long cityId,Long areaId,String itemName,String name,String phone,Long chooseCityId,String openid) throws BusinessException;

    /**
     * 海露支付回调
     * @param params
     * @throws BusinessException
     */
    void callbackHl(Map<String,Object> params) throws BusinessException;

    /**
     * 心安捐赠支付
     * @param payType
     * @param moneyPrice
     * @param orderId
     * @return
     */
    Map<String, Object> donationOrder(Integer payType, Double moneyPrice, String orderId) throws BusinessException;

    /**
     * 心安捐赠支付回调
     * @param params
     * @throws BusinessException
     */
    void callbackDonation(Map<String,Object> params) throws BusinessException;
}
