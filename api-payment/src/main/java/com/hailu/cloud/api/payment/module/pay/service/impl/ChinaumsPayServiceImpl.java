package com.hailu.cloud.api.payment.module.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.api.payment.config.CredentFactory;
import com.hailu.cloud.api.payment.feigns.ChinaumsFeignClient;
import com.hailu.cloud.api.payment.module.pay.service.AbstractPayService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.payment.PayRequest;
import com.hailu.cloud.common.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 银联支付
 * @author junpei.deng
 */
@Service
@Slf4j
public class ChinaumsPayServiceImpl extends AbstractPayService {



    @Resource
    private ChinaumsFeignClient chinaumsFeignClient;

    @Resource
    private CredentFactory credentFactory;

    @Override
    public Map<String, Object> payForWeCat(PayRequest payRequest) throws BusinessException, IOException {
        //请求参数
        JSONObject requestParams = new JSONObject();

        Map<String,String> paramsMap = new HashMap<>();
        //payWay 支付方式 1-APP、2-H5、3-JSAPI
        switch (payRequest.getPayWay()){
            case 1:
                //交易类型
                requestParams.put("tradeType","APP");
                //消息类型
                requestParams.put("msgType","wx.appPreOrder");

                paramsMap = credentFactory.createPayParams("chinaumsApp");
                break;
            case 2:
                throw new BusinessException("系统暂不支持H5支付！");
            case 3:
                requestParams.put("msgType","WXPay.jsPay");
                requestParams.put("subOpenId",payRequest.getOpenId());
                paramsMap = credentFactory.createPayParams("chinaumsJsapi");
                if(StringUtils.isNotBlank(payRequest.getReturnUrl())){
                    requestParams.put("returnUrl",payRequest.getReturnUrl());
                }
                break;
            default:
                throw new BusinessException("未知支付类型！");
        }

        //商户号
        requestParams.put("mid", paramsMap.get(CredentFactory.CHINAUMS_MID_FIELD));
        //终端号
        requestParams.put("tid", paramsMap.get(CredentFactory.CHINAUMS_TID_FIELD));
        //消息来源
        requestParams.put("msgSrc", paramsMap.get(CredentFactory.CHINAUMS_MSGSRC_FIELD));
        //业务类型
        requestParams.put("instMid", paramsMap.get(CredentFactory.CHINAUMS_INSTMID_FIELD));
        //是否要在商户系统下单，看商户需求  createBill()
        requestParams.put("requestTimestamp", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        requestParams.put("requestTimestamp", "2019-12-19 14:23:08");
        //金额 单位：分
        requestParams.put("totalAmount", payRequest.getMoney().multiply(BigDecimal.valueOf(100)).intValue());
        //微信子商户appId
        requestParams.put("subAppId",paramsMap.get(CredentFactory.CHINAUMS_SUBAPPID_FIELD));

        //订单号
        requestParams.put("merOrderId", Constant.CHINAUMS_ORDER_HEADER + payRequest.getOrderNo());
        //回调地址
        requestParams.put("notifyUrl",payRequest.getNotifyUrl());
        //加密
        requestParams.put("sign", SignUtil.sign(requestParams,paramsMap.get(CredentFactory.CHINAUMS_KEY_FIELD)));

        log.info("银联微信请求下单数据：{}",requestParams.toJSONString());
        //下单
        String result = null;
        if(payRequest.getPayWay() == 1){
            result = chinaumsFeignClient.chinaumsWechatPay(requestParams);
        }else if(payRequest.getPayWay() == 3){
            Map<String,Object> params = new HashMap<>();
            params.put("url","https://qr-test2.chinaums.com/netpay-portal/webpay/pay.do?"+SignUtil.JsonToStr(requestParams));
            return params;
        }else {
            throw new BusinessException("暂不支持该支付类型！");
        }

        log.info("银联微信请求下单数据返回数据："+result);
        JSONObject resultJson = JSON.parseObject(result);
        if(StringUtils.equals(resultJson.getString("errCode"),"SUCCESS")){
            //下单成功
            return resultJson.getJSONObject("appPayRequest");
        }else {
            log.error("银联微信请求下单失败：错误码{},错误信息：{}",resultJson.get("errCode"),resultJson.get("errMsg"));
            throw new BusinessException("银联微信请求下单失败");
        }
    }

    @Override
    public Map<String, Object> payForAli(PayRequest payRequest) {
        return null;
    }
}
