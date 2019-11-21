package com.hailu.cloud.api.mall.module.pay.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.pay.service
 * @Author: junpei.deng
 * @CreateTime: 2019-10-21 17:06
 * @Description: 支付订单
 */
@Slf4j
@Service
public abstract class AbstractPayService implements IPayService {


//    @Autowired
//    private IUserInfoService userInfoService;
//
//    @Autowired
//    private IOSSOrderService ossOrderService;
//
//
//    public Map<String,Object> pay(PayVo payVo, HttpServletRequest httpRequest){
//        Map<String,Object> data = new HashMap<>();
//        try {
//            data = this.createOrder(payVo);
//            if((int)data.get("code") != 0){
//                throw new BusinessException((String) data.get("msg"));
//            }
//            //支付来源处理  1-支付宝2-微信3-微信H5
//            switch (payVo.getPayType()){
//                case 1:
//                    data.put("orderInfo",payForZhiFuBao(payVo,httpRequest,(Map<String,String>)data.get("payParams")));
//                    break;
//                case 2:
//                    data = payForWeiXin(payVo,httpRequest,(String) data.get("isReserve"),payVo.getPayFrom(),(Map<String,String>)data.get("payParams"));
//                    break;
//                case 3:
//                    data = payForH5(payVo.getOrderNo(),httpRequest,payVo.getMoney(),(Map<String,String>)data.get("payParams"));
//                    break;
//                default:
//                    break;
//            }
//
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//        }
//        log.info("返回支付信息："+data.toString());
//        return data;
//    }
//
//    /**
//     * 支付宝支付
//     */
//    private String payForZhiFuBao(
//            PayVo payVo,
//            HttpServletRequest httpRequest,Map<String,String> map) throws Exception {
//
//        Map<String, String> parameterMap = new HashMap<>();
//        // 订单号
//        parameterMap.put("outTradeNo", payVo.getOrderNo());
//        // 总金额
//        parameterMap.put("totalFee", payVo.getMoney() + "");
//        parameterMap.put("goodsName", payVo.getItemName());
//        parameterMap.put("backUrl", "/api/sys/alipayBack");
//        parameterMap.put("userId", payVo.getMemberId());
//        parameterMap.put("payFrom",payVo.getPayFrom());
//        return ossOrderService.aliPayUnifiedorder(httpRequest, parameterMap,map);
//    }
//
//    /**
//     * 微信支付
//     */
//    private Map<String, Object> payForWeiXin(
//            PayVo payVo,
//            HttpServletRequest httpRequest,String isReserve,String payFrom,Map<String,String> map) throws Exception {
//
//        Map<String, Object> data;
//        String openid = payVo.getOpenId();
//        if (StringUtil.isNotEmpty(openid)) {
//            UserInfoVo userinfo = userInfoService.userInfoQueryByUserId(payVo.getMemberId());
//            userinfo.setUserIcon(payFrom);
//            data = WechatUtil.wxpay(userinfo, openid, payVo.getOrderNo(), "JSAPI", payVo.getMoney(), httpRequest,map);
//        } else {
//            Map<String, String> parameterMap = new HashMap<>();
//            parameterMap.put("body", java.net.URLDecoder.decode(payVo.getItemName(), "UTF-8"));
//            parameterMap.put("nonce_str", RandomUtil.captcha(10));
//            parameterMap.put("out_trade_no", payVo.getOrderNo());
//            // ip地址
//            parameterMap.put("spbill_create_ip", IPUtil.getRemoteHost(httpRequest));
//            // 本系统总金额单位为元，但是微信需要的单位为分
//            parameterMap.put("total_fee", (int) (payVo.getMoney() * 100) + "");
//            //parameterMap.put("total_fee", (int) (0.01 * 100) + "");
//            parameterMap.put("trade_type", "APP");
//            parameterMap.put("isReserve", isReserve);
//            parameterMap.put("payFrom",payFrom);
//
//            // 生成预付信息
//            data = ossOrderService.unifiedorder(parameterMap,map);
//        }
//        return data;
//    }
//
//    /**
//     * H5支付
//     */
//    private Map<String, Object> payForH5(
//            String orderSn,
//            HttpServletRequest httpRequest,
//            double orderAmount,Map<String,String> map) throws UnsupportedEncodingException {
//
//        Map<String, String> parameterMap = new HashMap<>();
//        parameterMap.put("body", java.net.URLDecoder.decode("德达康健商品", "UTF-8"));
//        parameterMap.put("nonce_str", RandomUtil.captcha(10));
//        parameterMap.put("out_trade_no", orderSn);
//        // ip地址
//        parameterMap.put("spbill_create_ip", IPUtil.getRemoteHost(httpRequest));
//        // 本系统总金额单位为元，但是微信需要的单位为分
//        parameterMap.put("total_fee", (int) (orderAmount * 100) + "");
//        parameterMap.put("trade_type", "MWEB");
//        // 生成预付信息
//        return ossOrderService.gzunifiedorder(parameterMap,map);
//    }
//
//
//    /**
//     * 阿里支付回调
//     * @param httpRequest
//     * @return
//     */
//    public String aLICallback(HttpServletRequest httpRequest){
//        String orderNoe = "";
//        String tradeNoe = "";
//        String tradeStatuse = "";
//        try {
//            // 获取支付宝POST过来反馈信息
//            Map<String, String> params = new HashMap<String, String>();
//            Map<String, String[]> requestParams = httpRequest.getParameterMap();
//            String value = "";
//            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
//                String name = iter.next();
//                value = "";
//                String[] values = (String[]) requestParams.get(name);
//                for (int i = 0; i < values.length; i++) {
//                    value = (i == values.length - 1) ? value + values[i] : value + values[i] + ",";
//                }
//                params.put(name, value);
//            }
//            // 获取支付宝的通知返回参数，
//            // 商户订单号
//            String outTradeNo = Alipayh5Contents.convertIOS2UTF8(httpRequest.getParameter("out_trade_no"));
//            // 支付宝交易号
//            String tradeNo = Alipayh5Contents.convertIOS2UTF8(httpRequest.getParameter("trade_no"));
//            // 交易状态
//            String tradeStatus = Alipayh5Contents.convertIOS2UTF8(httpRequest.getParameter("trade_status"));
//            if (outTradeNo.substring(outTradeNo.length() - 1, outTradeNo.length()).equals("s")) {
//                outTradeNo = outTradeNo.substring(0, outTradeNo.length() - 1);
//            }
//            orderNoe = outTradeNo;
//            tradeNoe = tradeNo;
//            tradeStatuse = tradeStatus;
//
//            //校验参数
//            if (ossOrderService.verify(params)) {
//                Map<String, Object> date = new HashMap<>();
//                //商家订单号
//                date.put("outTradeNo", orderNoe);
//                //支付订单号
//                date.put("tradeNo", tradeNo);
//                //支付状态
//                date.put("tradeStatus", tradeStatus);
//                //金额
//                date.put("money", Alipayh5Contents.convertIOS2UTF8(httpRequest.getParameter("total_amount")));
//                //支付类型 1-支付宝2-微信
//                date.put("payType",1);
//                Map<String,Object> result = this.callback(date);
//                if((int)result.get("code") != 1){
//                    System.out.println("out_trade_no={" + orderNoe + "}");
//                    System.out.println("trade_no={" + tradeNoe + "} tradeStatus={" + tradeStatuse + "}");
//                    log.error("支付宝支付通知服务器内部处理异常，action={}，原因：{}", result.get("msg"));
//                    return "fail";
//                }
//                log.info("out_trade_no={}, trade_no={}， 交易处于支付成功状态", outTradeNo, tradeNo);
//                return "SUCCESS";
//            }
//            return "fail";
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//            System.out.println("out_trade_no={" + orderNoe + "} e={" + e.toString() + "}");
//            System.out.println("trade_no={" + tradeNoe + "} tradeStatus={" + tradeStatuse + "}");
//            log.error("支付宝支付通知服务器内部处理异常，action={}，原因：{}", e.toString());
//            return "fail";
//        }
//    }
//
//
//    public String weCatCallback(HttpServletRequest httpRequest,Map<String,String> map){
//        try {
//            Map<String, Object> date = new HashMap<>();
//            String out_trade_no = "";
//            String transaction_id = "";
//            String resultCode = "";
//            Map<String, String> retMap = new HashMap<String, String>();
//            //支付返回信息
//            String returnCode = map.get("return_code");
//            if ("SUCCESS".equals(returnCode)) {
//                resultCode = map.get("result_code");
//                if("SUCCESS".equals(resultCode)){
//                    out_trade_no = map.get("out_trade_no");
//                    transaction_id = map.get("transaction_id");
//                    //商家订单号
//                    date.put("outTradeNo", out_trade_no);
//                    //支付订单号
//                    date.put("tradeNo", transaction_id);
//                    //支付状态
//                    date.put("tradeStatus", returnCode);
//                    //金额
//                    DecimalFormat df = new DecimalFormat("######0.00");
//                    String cash_fee = map.get("cash_fee");
//                    double fee = Double.parseDouble(cash_fee) / 100;
//                    fee = Double.parseDouble(df.format(fee));
//                    date.put("money", fee);
//                    //支付类型 1-支付宝2-微信
//                    date.put("payType",2);
//                    Map<String,Object> result = this.callback(date);
//                    if((int)result.get("code") != 1){
//                        log.error("支付失败！out_trade_no:" + ",result_code:" + resultCode + ", 原因:" +result.get("msg"));
//                        retMap.put("return_code", returnCode);
//                        retMap.put("return_msg", resultCode);
//                        return "<xml><return_code><![CDATA[FAIL]]></return_code></xml>";
//                    }
//                    log.info("支付成功！out_trade_no:" + out_trade_no + ", transaction_id:" + transaction_id);
//                }else {
//                    String errCode = map.get("err_code");
//                    log.error("支付失败！out_trade_no:" + ",result_code:" + resultCode + ", err_code:" + errCode);
//                    retMap.put("return_code", returnCode);
//                    retMap.put("return_msg", resultCode);
//                }
//            }else {
//                String returnMsg = map.get("return_msg");
//                retMap.put("return_code", returnCode);
//                retMap.put("return_msg", returnMsg);
//                log.error("支付通信失败！" + returnMsg);
//            }
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//        }
//        return "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>";
//
//    }
}
