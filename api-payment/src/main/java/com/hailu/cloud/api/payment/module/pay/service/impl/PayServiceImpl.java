package com.hailu.cloud.api.payment.module.pay.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.hailu.cloud.api.payment.config.CredentFactory;
import com.hailu.cloud.api.payment.module.pay.service.AbstractPayService;
import com.hailu.cloud.api.payment.tools.SendHttp;
import com.hailu.cloud.api.payment.tools.SignUtil;
import com.hailu.cloud.api.payment.tools.wecat.WeiXinPayConstant;
import com.hailu.cloud.api.payment.tools.wecat.XmlUtil;
import com.hailu.cloud.common.model.payment.PayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 普通支付类
 */
@Service
@Slf4j
public class PayServiceImpl extends AbstractPayService {

    @Autowired
    private CredentFactory credentFactory;

    @Override
    public Map<String, Object> payForWeCat(PayRequest payRequest) {
        SortedMap<String, String> sortedMap = new TreeMap<String, String>();
        //获取支付参数
        Map<String, String> payParams = credentFactory.createPayParams(payRequest.getPayParams());
        sortedMap.put("appid", payParams.get(CredentFactory.APPID_FIELD));
        //内容
        sortedMap.put("body", payRequest.getGoodsName());
//        sortedMap.put("detail", payRequest.getBody());
        sortedMap.put("mch_id", payParams.get(CredentFactory.MCH_ID_FIELD));
        //随机字符串
        sortedMap.put("nonce_str", RandomUtil.randomNumbers(32));
        //毁掉地址
        sortedMap.put("notify_url", payRequest.getNotifyUrl());
        //订单号
        sortedMap.put("out_trade_no", payRequest.getOrderNo());
        //ip地址
        sortedMap.put("spbill_create_ip", payRequest.getIp());
        sortedMap.put("total_fee", String.valueOf(payRequest.getMoney().multiply(BigDecimal.valueOf(100)).intValue()));
        // 支付方式 1-APP、2-H5、3-JSAPI
        if (payRequest.getPayWay() == 1) {
            sortedMap.put("trade_type", "APP");
        } else if (payRequest.getPayWay() == 2) {
            sortedMap.put("trade_type", "MWEB");
        } else if (payRequest.getPayWay() == 3) {
            sortedMap.put("trade_type", "JSAPI");
            sortedMap.put("openid", payRequest.getOpenId());
        }
        //金额 //本系统总金额单位为元，但是微信需要的单位为分
        String sign = SignUtil.createSign("UTF-8", sortedMap, payParams.get(CredentFactory.APP_SECRRECT_FIELD));
        sortedMap.put("sign", sign);
        log.info("微信下单参数：{}", sortedMap.toString());
        String xmlString = XmlUtil.getXmlInfo(sortedMap);
        String resultXML = SendHttp.sendPost(WEIXIN_UNIFIEDORDER_URL, xmlString);
        log.info("微信下单返回信息" + resultXML);

        Map<String, String> xmlMap = sendResultParam(resultXML);
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        SortedMap<String, String> resultSort = new TreeMap<String, String>();


        if(payRequest.getPayWay() == 1 || payRequest.getPayWay() == 2){
            //生成签名
            resultSort.put("appid", payParams.get(CredentFactory.APPID_FIELD));
            resultSort.put("noncestr", xmlMap.get("nonce_str"));
            resultSort.put("package", "Sign=WXPay");
            resultSort.put("partnerid", payParams.get(CredentFactory.MCH_ID_FIELD));
            resultSort.put("prepayid", xmlMap.get("prepay_id"));
            long timestamp = System.currentTimeMillis() / 1000;
            resultSort.put("timestamp", timestamp + "");
            String sign2 = SignUtil.createSign("UTF-8", resultSort, payParams.get(CredentFactory.APP_SECRRECT_FIELD));


            result.put("appid", payParams.get(CredentFactory.APPID_FIELD));
            result.put("noncestr", xmlMap.get("nonce_str"));
            result.put("packageValue", "Sign=WXPay");
            result.put("partnerid", payParams.get(CredentFactory.MCH_ID_FIELD));
            result.put("prepayid", xmlMap.get("prepay_id"));
            result.put("timestamp", System.currentTimeMillis() / 1000 + "");
            result.put("sign", sign2);
        }else if(payRequest.getPayWay() == 3){

            resultSort.put("appId", payParams.get(CredentFactory.APPID_FIELD));
            resultSort.put("timeStamp", System.currentTimeMillis() / 1000 + "");
            resultSort.put("nonceStr", xmlMap.get("nonce_str"));
            resultSort.put("signType", "MD5");
            resultSort.put("package", "prepay_id=" + xmlMap.get("prepay_id"));
            String sign2 = SignUtil.createSign("UTF-8", resultSort, payParams.get(CredentFactory.APP_SECRRECT_FIELD));

            result.put("appId", payParams.get(CredentFactory.APPID_FIELD));
            result.put("timeStamp", resultSort.get("timeStamp"));
            result.put("nonceStr", xmlMap.get("nonce_str"));
            result.put("signType", "MD5");
            result.put("package", "prepay_id=" +xmlMap.get("prepay_id"));
            result.put("paySign", sign2);
        }

        return result;
    }

    @Override
    public Map<String, Object> payForAli(PayRequest payRequest) {
        String outTradeNo = payRequest.getOrderNo();

        //获取支付参数
        Map<String, String> payParams = credentFactory.createPayParams(payRequest.getPayParams());
        AlipayClient alipayClient = new DefaultAlipayClient(ALI_GATEWAY_URL, payParams.get(CredentFactory.APPID_FIELD), payParams.get(CredentFactory.RSA_PRIVATE_KEY_FIELD), "json", "utf-8", payParams.get(CredentFactory.PUBLIC_KEY_FIELD), "RSA2");
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("订单号:" + outTradeNo);
        model.setSubject(payRequest.getGoodsName());
        model.setOutTradeNo(outTradeNo);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(getDecimalFormat(payRequest.getMoney().doubleValue()));
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(payRequest.getNotifyUrl());
        String payInfo = "";
        try {
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            payInfo = response.getBody();
            log.info("支付宝返回参数：" + response.getBody());
        } catch (AlipayApiException e) {
            log.error(e.getMessage(), e);
        }
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("orderInfo", payInfo);
        return result;
    }


    public Map<String, String> sendResultParam(String xmlResult) {
        Map<String, String> respData = null;
        try {
            respData = XmlUtil.xmlToMap(xmlResult);
            String RETURN_CODE = "return_code";
            String return_code;
            if (respData.containsKey(RETURN_CODE)) {
                return_code = respData.get(RETURN_CODE);
            } else {
                throw new Exception(String.format("No  return_code  in XML: %s", xmlResult));
            }
            if (return_code.equals(WeiXinPayConstant.FAIL)) {
                return respData;
            } else if (return_code.equals(WeiXinPayConstant.SUCCESS)) {
                return respData;
            } else {
                throw new Exception(String.format("return_code value %s is invalid in XML: %s", return_code, xmlResult));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return respData;
    }


    public static String getDecimalFormat(double d) {
        String dfStr = "0.00";
        DecimalFormat df = new DecimalFormat("0.00");
        dfStr = df.format(d);
        return dfStr;
    }

}
