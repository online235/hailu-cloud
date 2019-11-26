package com.hailu.cloud.api.mall.module.sys.controller;

import com.hailu.cloud.api.mall.module.goods.service.IOSSOrderService;
import com.hailu.cloud.common.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付
 */
@Slf4j
@Controller
@RequestMapping(value = "api/sys")
public class SysPayController  {
    @Autowired
    private IOSSOrderService ossOrderService;




    /**
     * 支付成功 支付宝支付回调地址
     *
     * @return
     */
    @RequestMapping(value = "/alipayBack")
    @ResponseBody
    public String alipayBack(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        return null;
//            String passbackParams = httpRequest.getParameter("passback_params");
//            if(passbackParams.equals("XA")){
//               return xinanPayService.aLICallback(httpRequest);
//            }else if(passbackParams.equals("HL")){
//                return buyMemberService.aLICallback(httpRequest);
//            }else{
//                return mallPayService.aLICallback(httpRequest);
//            }

//            String orderNoe = "";
//            String tradeNoe = "";
//            String tradeStatuse = "";
//            try {
//                // 获取支付宝POST过来反馈信息
//                Map<String, String> params = new HashMap<String, String>();
//                Map<String, String[]> requestParams = httpRequest.getParameterMap();
//                String value = "";
//                for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
//                    String name = iter.next();
//                    value = "";
//                    String[] values = (String[]) requestParams.get(name);
//                    for (int i = 0; i < values.length; i++) {
//                        value = (i == values.length - 1) ? value + values[i] : value + values[i] + ",";
//                    }
//                    params.put(name, value);
//                }
//                // 获取支付宝的通知返回参数，
//                // 商户订单号
//                String outTradeNo = Alipayh5Contents.convertIOS2UTF8(httpRequest.getParameter("out_trade_no"));
//                // 支付宝交易号
//                String tradeNo = Alipayh5Contents.convertIOS2UTF8(httpRequest.getParameter("trade_no"));
//                // 交易状态
//                String tradeStatus = Alipayh5Contents.convertIOS2UTF8(httpRequest.getParameter("trade_status"));
//                if (outTradeNo.substring(outTradeNo.length() - 1, outTradeNo.length()).equals("s")) {
//                    outTradeNo = outTradeNo.substring(0, outTradeNo.length() - 1);
//                }
//                orderNoe = outTradeNo;
//                tradeNoe = tradeNo;
//                tradeStatuse = tradeStatus;
//
//
//                Map<String,Object> date = new HashMap<>();
//                //商家订单号
//                date.put("outTradeNo",orderNoe);
//                //支付订单号
//                date.put("tradeNo",tradeNo);
//                //支付状态
//                date.put("tradeStatus",tradeStatus);
//                //金额
//                date.put("money",Alipayh5Contents.convertIOS2UTF8(httpRequest.getParameter("total_amount")));
//                //类型  passback_params 字段，当该字段不为空且为XinAn的时候走心安的订单
//                String passbackParams = httpRequest.getParameter("passback_params");
//                Map<String,Object> resultParams = null;
//
//                abstractPayService.aLICallback(httpRequest);

//                if (ossOrderService.verify(params)) {// 验证成功
//                    log.info("out_trade_no={}, trade_no={}，验证成功", outTradeNo, tradeNo);
//                    if (tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) {
//                        // 餘額充值
//                        if (outTradeNo.substring(0, 2).equals("CZ")) {
//                            // 得到充值訂單
//                            BalanceRechargeVo irVo = balanceDetailService.getBalanceRecharge(outTradeNo);
//                            if (irVo != null) {
//                                rechargeBalance(irVo, 1);
//                            }
//                        } else {
//                            OrderToVo ol = orderService.findByOrderSn(outTradeNo);
//                            if (ol.getOrderState() < 3) {
//                                // 直接下單
//                                if (ol != null) {
//                                    if (ol.getOrderState() == 1) {
//                                        System.out.println("订单： " + outTradeNo + "。进入了支付回调，支付状态为:" + ol.getPaymentState() + "----订单状态为:"
//                                                + ol.getOrderState());
//                                        OrderPay op = new OrderPay();
//                                        op.setApiPayState(1);
//                                        op.setBuyerId(ol.getUserId());
//                                        op.setPaySn(tradeNo);
//                                        op.setOrderSn(outTradeNo);
//                                        op.setPayTime(System.currentTimeMillis());
//                                        op.setPayType(1);
//                                        op.setWechatSource("APP");
//                                        if (ol.getPaymentState() == 0) {
//                                            OrderToPay otp = new OrderToPay();
//                                            otp.setOrderId(ol.getOrderId());
//                                            // otp.setPaymentId(op.getPayId());
//                                            otp.setPaymentName("支付宝");
//                                            otp.setPaymentState(1);
//                                            otp.setPaymentTime(System.currentTimeMillis());
//                                            // 是否預定
//                                            if (ol.getIsReserve() == 1) {
//                                                // 第一階段是否支付
//                                                if (ol.getOneIsPay() == 0) {
//                                                    otp.setOneIsPay(1);
//                                                    otp.setPaymentState(0);// 是否支付
//                                                    op.setPayAmount(ol.getReserveOneAmount());
//                                                } else {
//                                                    otp.setTwoIsPay(1);
//                                                    otp.setOrderState(2);
//                                                    op.setPayAmount(ol.getReserveTwoAmount());
//                                                }
//                                            } else {
//                                                otp.setOrderState(2);
//                                                op.setPayAmount(ol.getOrderAmount());
//                                            }
//                                            //System.out.println("out_trade_no={"+orderNoe+"} orderState={"+otp.getOrderState()+"}");
//                                            orderService.addOrderPay(op);
//                                            orderService.updateOrderByPay(otp);
//                                            orderService.verifyCoupons(otp, ol);
//                                            if (otp.getOrderState() == 2) {
//                                                ossOrderService.updateCommiss(ol);
//                                            }
//                                            System.out.println("支付成功");
//                                        }
//                                    }
//                                }
//                            } else if ("WAIT_BUYER_PAY".equals(tradeStatus)) {
//                                log.info("out_trade_no={}, trade_no={}， 交易处于等待支付状态", outTradeNo, tradeNo);
//                            }
//                        }
//                        log.info("out_trade_no={}, trade_no={}， 交易处于支付成功状态", outTradeNo, tradeNo);
//                        return "SUCCESS";
//                    }
//                }
//                return "fail";
//            } catch (Exception e) {
//                System.out.println("out_trade_no={" + orderNoe + "} e={" + e.toString() + "}");
//                System.out.println("trade_no={" + tradeNoe + "} tradeStatus={" + tradeStatuse + "}");
//                log.error("支付宝支付通知服务器内部处理异常，action={}，原因：{}", e.toString());
//                return "fail";
//            }
//        }
    }

    /**
     * 支付成功 微信支付回调地址
     *
     * @returnN
     */
    @ResponseBody
    @RequestMapping(value = "/callbackweixin")
    public String weixinBack(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        log.info("微信回调开始");
        ossOrderService.callback(com.hailu.cloud.common.util.wechat.WechatUtil.weCatCallback(com.hailu.cloud.common.util.wechat.WechatUtil.xmlToMap(RequestUtils.getRequest().getInputStream())));
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>";

    }


}
