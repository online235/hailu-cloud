package com.hailu.cloud.api.xinan.module.app.service;

import com.hailu.cloud.common.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public interface IPaymentService {

    Map<String, Object> createOrder(Integer payType, Double moneyPrice, String insuredIds) throws BusinessException;

    void callback() throws Exception;

    /**
     * 银联支付回调
     * @throws Exception
     */
    void chinaumsCallback() throws Exception;

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
    Map<String,Object> createHlOrder(Integer payType,double money,String address,String provinceId,String cityId,String areaId,String itemName,String name,String phone,Long chooseCityId,String openid,String remark,Integer buyType,String inviteNum) throws BusinessException;

    /**
     * 海露支付回调
     * @throws BusinessException
     */
    void callbackHl() throws Exception;

    /**
     * 银联海露支付回调
     */
    void chinaumsCallbackHl() throws Exception;
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
     * @throws BusinessException
     */
    void callbackDonation() throws Exception;

    /**
     * 银联支付 心安捐赠
     * @throws Exception
     */
    void chinaumsCallbackDonation() throws Exception;
}
