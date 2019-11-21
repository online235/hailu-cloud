package com.hailu.cloud.api.mall.module.sys.controller;

import com.hailu.cloud.api.mall.module.goods.service.IOSSOrderService;
import com.hailu.cloud.api.mall.module.pay.service.Impl.BuyMemberServiceImpl;
import com.hailu.cloud.api.mall.module.pay.service.Impl.MallPayServiceImpl;
import com.hailu.cloud.api.mall.module.pay.service.Impl.XinanPayServiceImpl;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付
 */
@Slf4j
@Controller
@RequestMapping(value = "api/sys")
public class SysPayController  {
//    @Autowired
//    private IBalanceDetailService balanceDetailService;
    @Autowired
    private IOSSOrderService ossOrderService;

    @Autowired
    private MallPayServiceImpl mallPayService;

    @Autowired
    private XinanPayServiceImpl xinanPayService;

    @Autowired
    private BuyMemberServiceImpl buyMemberService;


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
    @RequestMapping(value = "/weixinBack")
    public String weixinBack(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        return null;
//        log.info("微信回调开始");
//        Map<String, String> map = WechatUtil.xmlToMap(httpRequest.getInputStream());
//        log.info("微信返回数据：{}",map.toString());
//       String attach =  map.get("attach");
//       if(attach == null){
//           return "<xml><return_code><![CDATA[FAIL]]></return_code></xml>";
//       }
//        JSONObject result = JSONObject.parseObject(attach);
//       if(result.getString("payFrom").equals("XA")){
//           return xinanPayService.weCatCallback(httpRequest,map);
//       }else if(result.getString("payFrom").equals("HL")){
//            return buyMemberService.weCatCallback(httpRequest,map);
//       }
//       else {
//           return mallPayService.weCatCallback(httpRequest,map);
//       }

//        String out_trade_no = "";
//        String transaction_id = "";
//        Map<String, String> map = WechatUtil.xmlToMap(httpRequest.getInputStream());
//        Map<String, String> retMap = new HashMap<String, String>();
//        String returnCode = map.get("return_code");
//        if ("SUCCESS".equals(returnCode)) {
//            String resultCode = map.get("result_code");
//            if ("SUCCESS".equals(resultCode)) {
//                out_trade_no = map.get("out_trade_no");
//                transaction_id = map.get("transaction_id");
//                // 下面为业务逻辑处理：如果支付成功，则修改该用户的付费状态，更新付费时间。
//                if (out_trade_no.substring(0, 2).equals("CZ")) {
//                    // 得到充值訂單
//                    BalanceRechargeVo irVo = balanceDetailService.getBalanceRecharge(out_trade_no);
//                    if (irVo != null) {
//                        rechargeBalance(irVo, 2);
//                    }
//                } else {
//                    if (out_trade_no.substring(out_trade_no.length() - 1, out_trade_no.length()).equals("s")) {
//                        out_trade_no = out_trade_no.substring(0, out_trade_no.length() - 1);
//                    }
//                    if (out_trade_no.length() > 16) {
//                        out_trade_no = out_trade_no.substring(0, 15);
//                    }
//                    OrderToVo ol = orderService.findByOrderSn(out_trade_no);
//                    // 直接下單
//                    if (ol != null) {
//                        if (ol.getOrderState() == 1) {
//                            DecimalFormat df = new DecimalFormat("######0.00");
//                            String cash_fee = map.get("cash_fee");
//                            double fee = Double.parseDouble(cash_fee) / 100;
//                            fee = Double.parseDouble(df.format(fee));
//                            if (ol.getPaymentState() == 0) {
//                                OrderPay op = new OrderPay();
//                                op.setApiPayState(1);
//                                op.setBuyerId(ol.getUserId());
//                                op.setPaySn(transaction_id);
//                                op.setOrderSn(out_trade_no);
//                                op.setPayTime(System.currentTimeMillis());
//                                op.setPayType(2);
//                                op.setWechatSource("APP");
//
//                                OrderToPay otp = new OrderToPay();
//                                otp.setOrderId(ol.getOrderId());
//                                otp.setPaymentName("微信");
//                                otp.setPaymentState(1);
//                                otp.setPaymentTime(System.currentTimeMillis());
//                                // 是否預定
//                                if (ol.getIsReserve() == 1) {
//                                    // 第一階段是否支付
//                                    if (ol.getOneIsPay() == 0 && fee == ol.getReserveOneAmount()) {
//                                        // 根據訂單編號查詢訂單支付詳情如果有就不改變狀態
//                                        int count = orderService.getOrderPayCount(out_trade_no);
//                                        if (count == 0) {
//                                            otp.setOneIsPay(1);
//                                            otp.setPaymentState(0);// 是否支付
//                                            op.setPayAmount(ol.getReserveOneAmount());
//                                        } else {
//                                            return null;
//                                        }
//                                    } else if (fee == ol.getReserveTwoAmount() && map.get("attach").equals("2")) {
//                                        otp.setTwoIsPay(1);
//                                        otp.setOrderState(2);
//                                        op.setPayAmount(ol.getReserveTwoAmount());
//                                    } else {
//                                        return null;
//                                    }
//                                } else {
//                                    otp.setOrderState(2);
//                                    op.setPayAmount(ol.getOrderAmount());
//                                }
//                                orderService.updateOrderByPay(otp); // 更改訂單
//                                orderService.addOrderPay(op); // 支付詳情
//                                orderService.verifyCoupons(otp, ol);
//                                if (otp.getOrderState() == 2) {
//                                    ossOrderService.updateCommiss(ol);
//                                }
//                                System.out.println(
//                                        "进入了支付回调，支付状态为:" + otp.getPaymentState() + "----订单状态为:" + otp.getOrderState());
//                                log.info("支付成功！out_trade_no:" + out_trade_no + ", transaction_id:" + transaction_id);
//                            }
//                        }
//                    }
//                }
//            } else {
//                String errCode = map.get("err_code");
//                log.error("支付失败！out_trade_no:" + ",result_code:" + resultCode + ", err_code:" + errCode);
//                retMap.put("return_code", returnCode);
//                retMap.put("return_msg", resultCode);
//            }
//        } else {
//            String returnMsg = map.get("return_msg");
//            retMap.put("return_code", returnCode);
//            retMap.put("return_msg", returnMsg);
//            log.error("支付通信失败！" + returnMsg);
//        }
//        return "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>";
    }

//
//    /**
//     * @Author HuangL
//     * @Description 充值余额
//     * @Date 2018-10-25_15:10
//     */
//    private void rechargeBalance(BalanceRechargeVo irVo, int type) {
//        if (irVo.getState() == 2) {
//            irVo.setState(1);
//            irVo.setPayTime(System.currentTimeMillis());
//            irVo.setSource(type == 1 ? "支付宝" : "微信");
//            balanceDetailService.updateBalanceRecharge(irVo);
//            // 增加余额记录
//            BalanceVo bdVo = new BalanceVo();
//            bdVo.setAmount(irVo.getRechargeBalance());
//            bdVo.setCreateTime(System.currentTimeMillis());
//            bdVo.setType(1);
//            bdVo.setUserId(irVo.getUserId());
//            bdVo.setRemark(type == 1 ? "支付宝充值购买" : "微信充值购买");
//            bdVo.setSerialNumber(balanceDetailService.getMaxSerialNumber());
//            balanceDetailService.addBalanceRecord(bdVo);
//
//            log.info("支付成功");
//        }
//    }

    /**
     * @Author HuangL
     * @Description 通过微信地址分享
     * @Date 2018-10-30_09:14
     */
    @RequestMapping(value = "sendUrlWeChat", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> sendUrlWeChat(@RequestParam("url") String url) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (StringUtils.isNotEmpty(url)) {
                Map<String, Object> map = ossOrderService.sendUrlWeChat(url);
                return map;
            } else {
                throw new BusinessException("url is null");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
}
