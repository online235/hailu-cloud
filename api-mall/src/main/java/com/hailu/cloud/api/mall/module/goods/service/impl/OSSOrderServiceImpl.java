package com.hailu.cloud.api.mall.module.goods.service.impl;


import com.hailu.cloud.api.mall.module.goods.entity.order.OrderToVo;
import com.hailu.cloud.api.mall.module.goods.service.IOSSOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 第三方支付
 *
 * @author Administrator
 */
@Slf4j
@Service
public class OSSOrderServiceImpl implements IOSSOrderService {
    @Override
    public Map<String, Object> unifiedorder(Map parameterJSON, Map<String, String> payParams) {
        return null;
    }

    @Override
    public Map<String, Object> gzunifiedorder(Map parameterJSON, Map<String, String> payParams) {
        return null;
    }

    @Override
    public String weixinSign(LinkedHashMap parameterMap) {
        return null;
    }

    @Override
    public String aliPayUnifiedorder(HttpServletRequest httpRequest, Map<String, String> parameterMap, Map<String, String> payParams) throws Exception {
        return null;
    }

    @Override
    public boolean verify(Map<String, String> params) {
        return false;
    }

    @Override
    public Boolean addBalancePay(String orderSn, String payPwd) throws Exception {
        return null;
    }

    @Override
    public Map<String, Object> sendUrlWeChat(String url) {
        return null;
    }

    @Override
    public void addZeroPay(OrderToVo ol) {

    }

    //    @Autowired
//    private IBalanceDetailService balanceDetailService;
//    @Autowired
//    private IOrderService orderService;
//    @Resource
//    private UserInfoMapper userInfoDao;
//
//    private WXPayConfig config;
//    private WXPayConstants.SignType signType;
//
//    public static final String TicketURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?";
//    @Value("${weixin.appid}")
//    private String weixinAppid;
//
//    @Value("${weixin.key}")
//    private String weixinAppKey;
//
//    @Value("${weixin.mchid}")
//    private String weixinMchid;
//
//    @Value("${weixin.deviceinfo}")
//    private String weixinDeviceinfo;
//    //=======alipay====================
//    @Value("${alipay.appid}")
//    private String alipayAppid;
//
//    @Value("${alipay.public.key}")
//    private String alipayPublicKey;
//
//    @Value("${alipay.pid}")
//    private String alipayPid;
//
//    @Value("${alipay.seller.id}")
//    private String alipaySeller;
//
//    @Value("${alipay.rsa.private.key}")
//    private String alipayRsaPrivateKey;
//
//    @Value("${alipay.aes.private.key}")
//    private String alipayAesPrivateKey;
//
//    /**
//     *
//     */
//    private String gzAppId = "wx1179f6994449d5c9";//公众号appid
//    private String gzMchId = "10019552";//公众商户号
//    private String gzAppKey = "06280205C69A37898F3D14A0C3DA8AF1";//支付密钥
//
//    /**
//     * 生成微信预付款订单
//     *
//     * @return
//     */
//    @Override
//    public Map<String, Object> unifiedorder(Map map,Map<String,String> payParams) {
//        SortedMap<String, String> sortedMap = new TreeMap<String, String>();
//
//        sortedMap.put("appid", payParams.get(CredentFactory.APPID_FIELD));
//        sortedMap.put("body", map.get("body").toString());
//        //sortedMap.put("device_info", payParams.get(CredentFactory.DEVICEINFO_FIELD));
//        sortedMap.put("mch_id", payParams.get(CredentFactory.MCH_ID_FIELD));
//        sortedMap.put("nonce_str", RandomUtil.captcha(32));
//        sortedMap.put("notify_url", Const.SYSTEM_URL + "/api/sys/weixinBack");
//        sortedMap.put("out_trade_no", map.get("out_trade_no").toString());
//        sortedMap.put("spbill_create_ip", map.get("spbill_create_ip").toString());//ip地址
//        sortedMap.put("total_fee", map.get("total_fee").toString());//本系统总金额单位为元，但是微信需要的单位为分
//        sortedMap.put("trade_type", "APP");
//        JSONObject attachJson = new JSONObject();
//        //附带信息
//        attachJson.put("isReserve",map.get("isReserve").toString());
//        attachJson.put("payFrom",map.get("payFrom"));
//        sortedMap.put("attach", attachJson.toJSONString());
//        String sign = MD5Util.createSign2("UTF-8", sortedMap, payParams.get(CredentFactory.APP_SECRRECT_FIELD));
//        sortedMap.put("sign", sign);
//        log.info("微信下单参数：{}",sortedMap.toString());
//        String xmlString = XMLUtil.getXmlInfo(sortedMap);
//        String resultXML = SendHttp.sendPost(APiConfig.WEIXIN_UNIFIEDORDER_URL, xmlString);
//        log.info("微信下单返回信息"+resultXML);
//        Map<String, String> xmlMap = sendResultParam(resultXML);
//        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
//        SortedMap<String, String> resultSort = new TreeMap<String, String>();
//        resultSort.put("appid", payParams.get(CredentFactory.APPID_FIELD));
//        resultSort.put("noncestr", xmlMap.get("nonce_str"));
//        resultSort.put("package", "Sign=WXPay");
//        resultSort.put("partnerid", payParams.get(CredentFactory.MCH_ID_FIELD));
//        resultSort.put("prepayid", xmlMap.get("prepay_id"));
//        long timestamp = System.currentTimeMillis() / 1000;
//        resultSort.put("timestamp", timestamp + "");
//        String sign2 = MD5Util.createSign("UTF-8", resultSort);
//        result.put("appid", payParams.get(CredentFactory.APPID_FIELD));
//        result.put("noncestr", xmlMap.get("nonce_str"));
//        result.put("packageValue", "Sign=WXPay");
//        result.put("partnerid", payParams.get(CredentFactory.MCH_ID_FIELD));
//        result.put("prepayid", xmlMap.get("prepay_id"));
//        result.put("timestamp", System.currentTimeMillis() / 1000 + "");
//        result.put("sign", sign2);
//        return result;
//    }
//
//    public Map<String, String> sendResultParam(String xmlResult) {
//        Map<String, String> respData = null;
//        try {
//            respData = XMLUtil.xmlToMap(xmlResult);
//            String RETURN_CODE = "return_code";
//            String return_code;
//            if (respData.containsKey(RETURN_CODE)) {
//                return_code = respData.get(RETURN_CODE);
//            } else {
//                throw new Exception(String.format("No  return_code  in XML: %s", xmlResult));
//            }
//            if (return_code.equals(WXPayConstants.FAIL)) {
//                return respData;
//            } else if (return_code.equals(WXPayConstants.SUCCESS)) {
////				if (this.isResponseSignatureValid(respData)) {
//                return respData;
////				}
////				else {
////					throw new Exception(String.format("Invalid sign value in XML: %s", xmlResult));
////				}
//            } else {
//                throw new Exception(String.format("return_code value %s is invalid in XML: %s", return_code, xmlResult));
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//        return respData;
//    }
//
//    /**
//     * 判断xml数据的sign是否有效，必须包含sign字段，否则返回false。
//     *
//     * @param reqData 向wxpay post的请求数据
//     * @return 签名是否有效
//     * @throws Exception
//     */
//    public boolean isResponseSignatureValid(Map<String, String> reqData) throws Exception {
//        // 返回数据的签名方式和请求中给定的签名方式是一致的
//        return XMLUtil.isSignatureValid(reqData, this.config.getKey(), this.signType);
//    }
//
//    /**
//     * 生成微信请求参数中的sign
//     *
//     * @return
//     */
//    @Override
//    public String weixinSign(LinkedHashMap parameterMap) {
//        StringBuilder sb = new StringBuilder();
//        Iterator<Map.Entry<String, String>> it = parameterMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, String> entry = it.next();
//            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//        }
//        String stringSignTemp = sb.append("key=").append(weixinAppKey).toString();
//        return MD5.MD5(stringSignTemp).toUpperCase();
//    }
//
//    /**
//     * @Author HuangL
//     * @Email huangl96@163.com
//     * @Description 支付宝支付
//     * @Date 2018/12/11 14:11
//     */
//    @Override
//    public String aliPayUnifiedorder(HttpServletRequest httpRequest, Map<String, String> parameterMap,Map<String,String> payParams) {
//        String outTradeNo = parameterMap.get("outTradeNo");
//        String sign_type = "RSA2";
//        String CHARSET = "utf-8";
//        String url = "https://openapi.alipay.com/gateway.do";
//        AlipayClient alipayClient = new DefaultAlipayClient(url, payParams.get(CredentFactory.APPID_FIELD), payParams.get(CredentFactory.RSA_PRIVATE_KEY_FIELD), "json", CHARSET, payParams.get(CredentFactory.PUBLIC_KEY_FIELD), sign_type);
//        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//        model.setBody("订单号:" + outTradeNo);
//        model.setSubject(parameterMap.get("goodsName"));
//        model.setOutTradeNo(outTradeNo);
//        model.setTimeoutExpress("30m");
//        model.setTotalAmount(getDecimalFormat(Double.parseDouble(parameterMap.get("totalFee"))));
//        model.setProductCode("QUICK_MSECURITY_PAY");
//        model.setPassbackParams(parameterMap.get("payFrom"));
//        request.setBizModel(model);
//        request.setNotifyUrl(Const.SYSTEM_URL + parameterMap.get("backUrl"));
//        String payInfo = "";
//        try {
//            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
//            payInfo = response.getBody();
//            System.out.println(response.getBody());
//        } catch (AlipayApiException e) {
//            log.error(e.getMessage(), e);
//        }
//        return payInfo;
//    }
//
//    @Override
//    public boolean verify(Map<String, String> params) {
//        return AlipayNotify.verify(params, alipayPid);
//    }
//
//    public static String getDecimalFormat(double d) {
//        String dfStr = "0.00";
//        DecimalFormat df = new DecimalFormat("0.00");
//        dfStr = df.format(d);
//        return dfStr;
//    }
//
//    @Override
//    public Map<String, Object> gzunifiedorder(Map map,Map<String,String> payParams) {
//        String nonce_str = RandomUtil.captcha(32);
//        SortedMap<String, String> sortedMap = new TreeMap<String, String>();
//        sortedMap.put("appid", payParams.get(CredentFactory.APPID_FIELD));
//        sortedMap.put("body", map.get("body").toString());
//        sortedMap.put("mch_id", payParams.get(CredentFactory.MCH_ID_FIELD));
//        sortedMap.put("nonce_str", nonce_str);
//        sortedMap.put("notify_url", Const.SYSTEM_URL + "/api/sys/weixinBack");
//        sortedMap.put("out_trade_no", map.get("out_trade_no").toString());
//        sortedMap.put("spbill_create_ip", map.get("spbill_create_ip").toString());//ip地址
//        sortedMap.put("total_fee", map.get("total_fee").toString());//本系统总金额单位为元，但是微信需要的单位为分
//        sortedMap.put("trade_type", "MWEB");
//        //sortedMap.put("scene_info", "{\"h5_info\":{\"type\":\"Wap\",\"wap_url\":\"www.dedakj.com\",\"wap_name\":\"订单支付\"}}");
//        //生成参数中的sign
//        //String sign3= weixinSign(parameterMap);
//        String sign = MD5Util.createSign2("UTF-8", sortedMap,payParams.get(CredentFactory.APP_SECRRECT_FIELD));
//        sortedMap.put("sign", sign);
//        //生成xml
//        String xmlString = XMLUtil.getXmlInfo(sortedMap);
//        String resultXML = SendHttp.sendPost(APiConfig.WEIXIN_UNIFIEDORDER_URL, xmlString);
//        log.info("微信H5返回信息："+resultXML);
//        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
//        if (resultXML.toLowerCase().contains("<return_code><![cdata[success]]></return_code>")) {
//            String backSign = resultXML.substring(resultXML.indexOf("<sign><![CDATA[") + 15, resultXML.indexOf("]]></sign>"));
//            String prepay_id = resultXML.substring(resultXML.indexOf("<prepay_id><![CDATA[") + 20, resultXML.indexOf("]]></prepay_id>"));
//            String mweburl = resultXML.substring(resultXML.indexOf("<mweb_url><![CDATA[") + 19, resultXML.indexOf("]]></mweb_url>"));
//            String noncestr = resultXML.substring(resultXML.indexOf("<nonce_str><![CDATA[") + 20, resultXML.indexOf("]]></nonce_str>"));
//            long timestamp = System.currentTimeMillis() / 1000;
//            result.put("appid", payParams.get(CredentFactory.APPID_FIELD));
//            result.put("noncestr", noncestr);
//            result.put("partnerid", payParams.get(CredentFactory.MCH_ID_FIELD));
//            result.put("prepayid", prepay_id);
//            result.put("package", mweburl.substring(108, 118));
//            result.put("timestamp", timestamp + "");
//            result.put("mweburl", mweburl);
//            result.put("sign", backSign);
//        }
//        //返回值为：微信签名 欲付款订单 本地的订单id
//        return result;
//    }
//
//    /**
//     * ,参与演员 orderSn,userId,
//     * 故事情节紧凑,环节扣动人心
//     *
//     * @Author huangl
//     * 德达康健的余额支付
//     */
//    public Map<String, Object> addBalancePay(Map<String, String> parameterMap) {
//        Map<String, Object> result = new HashMap<>();
//        String userId = parameterMap.get("userId");
//        String orderSn = parameterMap.get("outTradeNo");
//        OrderToVo ol = orderService.findByOrderSn(orderSn);
//        //直接下單
//        if (ol != null) {
//            if (ol.getOrderState() == 1) {
//                OrderPay op = new OrderPay();
//                op.setApiPayState(1);
//                op.setBuyerId(ol.getUserId());
//                op.setPaySn(RandomStringUtils.randomAlphanumeric(15));
//                op.setOrderSn(orderSn);
//                op.setPayTime(System.currentTimeMillis());
//                op.setPayType(1);
//                if (ol.getIsReserve() == 1) {
//                    //第一阶段支付了,取第二阶段的金额
//                    if (ol.getOneIsPay() == 1) {
//                        op.setPayAmount(ol.getReserveTwoAmount());
//                    } else {
//                        op.setPayAmount(ol.getReserveOneAmount());
//                    }
//                } else {
//                    op.setPayAmount(ol.getOrderAmount());
//                }
//                //验证余额是否充足
//                Double balance = userInfoDao.getAvailablePredeposit(userId);
//                if (balance == null) {
//                    balance = 0.00;
//                }
//                if (balance < op.getPayAmount()) {
//                    result.put("code", "402");
//                    result.put("msg", "余额不足");
//                    return result;
//                }
//                orderService.addOrderPay(op);
//                if (ol.getPaymentState() == 0) {
//                    OrderToPay otp = new OrderToPay();
//                    otp.setOrderId(ol.getOrderId());
//                    otp.setPaymentName("余额");
//                    otp.setPaymentState(0);
//                    otp.setPaymentTime(System.currentTimeMillis());
//                    //是否預定
//                    if (ol.getIsReserve() == 1) {
//                        //第一階段是否支付
//                        if (ol.getOneIsPay() == 0) {
//                            otp.setOneIsPay(1);
//                        }
//                        //第二階段支付
//                        if (ol.getTwoIsPay() == 0) {
//                            otp.setTwoIsPay(1);
//                            otp.setOrderState(2);
//                            otp.setPaymentState(1);
//                        }
//                    } else {
//                        otp.setOrderState(2);
//                        otp.setPaymentState(1);
//                    }
//                    orderService.updateOrderByPay(otp);
//                    System.out.println("支付成功");
//                }
//                //更改用户余额
//                // BigDecimal available= new BigDecimal(balance).subtract(new BigDecimal(op.getPayAmount()));
//                userInfoDao.updateAvailablePredeposit(-op.getPayAmount(), userId);
//                //增加余额记录
//                BalanceVo bdVo = new BalanceVo();
//                bdVo.setAmount(-op.getPayAmount());
//                bdVo.setCreateTime(System.currentTimeMillis());
//                bdVo.setType(2);
//                bdVo.setUserId(userId);
//                bdVo.setRemark("德达康健消费");
//                bdVo.setSerialNumber(balanceDetailService.getMaxSerialNumber());
//                balanceDetailService.addBalanceRecord(bdVo);
//
//                result.put("code", "0");
//                result.put("msg", "支付成功");
//            } else {
//                result.put("code", "402");
//                result.put("msg", "该订单已支付");
//            }
//        } else {
//            result.put("code", "402");
//            result.put("msg", "该订单已被删除");
//        }
//        return result;
//    }
//
//    @Override
//    public ResponseData<Boolean> addBalancePay(String orderSn, String payPwd) throws Exception {
//        ResponseData<Boolean> result = new ResponseData<Boolean>();
//        OrderToVo ol = orderService.findByOrderSn(orderSn);
//        try {
//            //直接下單
//            if (ol != null) {
//                //验证支付密码是否正确
//                String salt = userInfoDao.getRecordTo(ol.getUserId());
//                Map<String, String> map = UserInfoServiceImpl.generate(payPwd, salt);
//                payPwd = (String) map.get("pwd");
//                if (userInfoDao.getPayPwd(ol.getUserId()).equals(payPwd)) {
//                    if (ol.getOrderState() == 1) {
//                        OrderPay op = new OrderPay();
//                        op.setApiPayState(1);
//                        op.setBuyerId(ol.getUserId());
//                        op.setPaySn(RandomStringUtils.randomAlphanumeric(15));
//                        op.setOrderSn(orderSn);
//                        op.setPayTime(System.currentTimeMillis());
//                        op.setPayType(3);
//                        if (ol.getIsReserve() == 1) {
//                            //第一阶段支付了,取第二阶段的金额
//                            if (ol.getOneIsPay() == 1) {
//                                op.setPayAmount(ol.getReserveTwoAmount());
//                            } else {
//                                op.setPayAmount(ol.getReserveOneAmount());
//                            }
//                        } else {
//                            op.setPayAmount(ol.getOrderAmount());
//                        }
//                        //验证余额是否充足
//                        Double balance = userInfoDao.getAvailablePredeposit(ol.getUserId());
//                        if (balance == null) {
//                            balance = 0.00;
//                        }
//                        if (balance < op.getPayAmount()) {
//                            result.setCode(808);
//                            result.setMsg("余额不足");
//                            return result;
//                        }
//                        orderService.addOrderPay(op);
//                        if (ol.getPaymentState() == 0) {
//                            OrderToPay otp = new OrderToPay();
//                            otp.setOrderId(ol.getOrderId());
//                            otp.setPaymentName("余额");
//                            otp.setPaymentState(1);
//                            otp.setPaymentTime(System.currentTimeMillis());
//                            //是否預定
//                            if (ol.getIsReserve() == 1) {
//                                //第一階段是否支付
//                                if (ol.getOneIsPay() == 0) {
//                                    otp.setOneIsPay(1);
//                                    otp.setPaymentState(0);
//                                } else {
//                                    //第二階段支付
//                                    otp.setTwoIsPay(1);
//                                    otp.setOrderState(2);
//                                }
//                            } else {
//                                otp.setOrderState(2);
//                            }
//                            orderService.updateOrderByPay(otp);
//                        }
//                        List<OrderGoods> ogList = orderService.findByOrderId(ol.getOrderId());
//                        String remark = "";
//                        for (int i = 0; i < ogList.size(); i++) {
//                            remark = remark + ogList.get(i).getGoodsName();
//                        }
//                        //更改用户余额
//                        //BigDecimal available= new BigDecimal(balance).subtract(new BigDecimal(op.getPayAmount()));
//                        userInfoDao.updateAvailablePredeposit(-op.getPayAmount(), ol.getUserId());
//                        //增加余额记录
//                        BalanceVo bdVo = new BalanceVo();
//                        bdVo.setAmount(op.getPayAmount());
//                        bdVo.setCreateTime(System.currentTimeMillis());
//                        bdVo.setType(2);
//                        bdVo.setUserId(ol.getUserId());
//                        bdVo.setRemark(remark);
//                        bdVo.setSerialNumber(balanceDetailService.getMaxSerialNumber());
//                        balanceDetailService.addBalanceRecord(bdVo);
//
//                        result.setCode(0);
//                        result.setMsg("支付成功");
//                    } else {
//                        result.setCode(402);
//                        result.setMsg("该订单已支付");
//                    }
//                } else {
//                    result.setCode(402);
//                    result.setMsg("支付密码错误");
//                }
//
//            } else {
//                result.setCode(402);
//                result.setMsg("该订单已删除");
//            }
//        } catch (Exception e) {
//            result.setCode(402);
//            result.setMsg("SYSTEM ERROR");
//            log.error(e.getMessage(), e);
//        }
//        return result;
//    }
//
//    @Override
//    public Map<String, Object> sendUrlWeChat(String url) {
//        String access_token = WechatUtil.getAccessToken_token();
//        String ticketU = TicketURL + "access_token=" + access_token + "&type=jsapi";
//        String ticket = null;
//        JSONObject jsonObject1 = null;
//        try {
//            jsonObject1 = WechatUtil.httpRequest(ticketU, "GET", null);
//            ticket = jsonObject1.getString("ticket");
//        } catch (Exception e) {
//            boolean accessToken_2 = WechatUtil.getAccessToken_2();
//            if (accessToken_2) {
//                access_token = WechatUtil.getAccessToken_token();
//            }
//            ticketU = TicketURL + "access_token=" + access_token + "&type=jsapi";
//            try {
//                jsonObject1 = WechatUtil.httpRequest(ticketU, "GET", null);
//                ticket = jsonObject1.getString("ticket");
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//        String timestamp = System.currentTimeMillis() / 1000 + "";
//        String nonce_str = RandomUtil.captcha(32);
//        String string1 = "jsapi_ticket=" + ticket +
//                "&noncestr=" + nonce_str +
//                "&timestamp=" + timestamp +
//                "&url=" + url;
//        String signature = WechatUtil.byteToHex(string1);
//        //注意这里参数名必须全部小写，且必须有序
//        SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
//        sortedMap.put("appId", gzAppId);
//        sortedMap.put("debug", false);
//        sortedMap.put("timestamp", timestamp);
//        sortedMap.put("jsApiList", new String[]{
//                "updateAppMessageShareData",
//                "updateTimelineShareData",
//                "onMenuShareTimeline",
//                "onMenuShareAppMessage",
//                "onMenuShareQQ",
//                "onMenuShareWeibo",
//                "onMenuShareQZone",
//                "uploadImage",
//                "downloadImage"});
//        sortedMap.put("nonceStr", nonce_str);
//        sortedMap.put("url", url);
//        sortedMap.put("signature", signature);
//        return sortedMap;
//    }
//
//    @Override
//    public void addZeroPay(OrderToVo ol) {
//        try {
//            if (ol.getOrderState() == 1) {
//                OrderPay op = new OrderPay();
//                op.setApiPayState(1);
//                op.setBuyerId(ol.getUserId());
//                op.setPaySn(RandomStringUtils.randomAlphanumeric(15));
//                op.setOrderSn(ol.getOrderSn());
//                op.setPayTime(System.currentTimeMillis());
//                op.setPayType(3);
//                if (ol.getIsReserve() == 1) {
//                    //第一阶段支付了,取第二阶段的金额
//                    if (ol.getOneIsPay() == 1) {
//                        op.setPayAmount(ol.getReserveTwoAmount());
//                    } else {
//                        op.setPayAmount(ol.getReserveOneAmount());
//                    }
//                } else {
//                    op.setPayAmount(ol.getOrderAmount());
//                }
//                orderService.addOrderPay(op);
//                if (ol.getPaymentState() == 0) {
//                    OrderToPay otp = new OrderToPay();
//                    otp.setOrderId(ol.getOrderId());
//                    otp.setPaymentName("余额");
//                    otp.setPaymentState(1);
//                    otp.setPaymentTime(System.currentTimeMillis());
//                    if (ol.getIsReserve() == 1) {
//                        //第一階段是否支付
//                        if (ol.getOneIsPay() == 0) {
//                            otp.setOneIsPay(1);
//                            otp.setPaymentState(0);
//                        } else {
//                            //第二階段支付
//                            otp.setTwoIsPay(1);
//                            otp.setOrderState(2);
//                        }
//                    } else {
//                        otp.setOrderState(2);
//                    }
//                    orderService.updateOrderByPay(otp);
//                }
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//    }

}
