package com.hailu.cloud.api.mall.module.pay.service.Impl;


import com.hailu.cloud.api.mall.module.pay.service.AbstractPayService;
import com.hailu.cloud.api.mall.module.pay.vo.PayVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.pay.service.Impl
 * @Author: junpei.deng
 * @CreateTime: 2019-10-21 17:05
 * @Description: 商城支付
 */
@Slf4j
@Service
public class MallPayServiceImpl extends AbstractPayService {
    @Override
    public Map<String, Object> createOrder(PayVo payVo) {
        return null;
    }

    @Override
    public Map<String, Object> callback(Map<String, Object> params) throws Exception {
        return null;
    }

    //    @Autowired
//    private IOrderService orderService;
//
//    @Autowired
//    private IOSSOrderService ossOrderService;
//
//    @Autowired
//    private IBalanceDetailService balanceDetailService;
//
//    @Autowired
//    private LedgerFixedRatioServiceImpl ledgerFixedRatioService;
//
//    @Resource
//    private OrderMapper orderMapper;
//
//
//
//    @Override
//    public Map<String, Object> createOrder(PayVo payVo) {
//        log.info("订单支付|payType={}|orderNumber={}",  payVo);
//        Map<String, Object> data = new HashMap<>();
//        String orderSn = payVo.getOrderNo();
//        if(StringUtils.isBlank(orderSn)){
//            data.put("msg", "订单号不能为空！");
//            data.put("code",10000);
//            return data;
//        }
//        //获取根据订单编号得到订单的信息
//        OrderToVo ol = orderService.findByOrderSn(orderSn);
//        if (ol != null) {
//            List<OrderGoodsVm> orderGoodsList = orderMapper.findOrderVmByOrderId(ol.getOrderId());
//            StringBuffer itemNameSb = new StringBuffer();
//            for(OrderGoodsVm o : orderGoodsList){
//                itemNameSb.append(o.getGoodsName()+",");
//            }
//            String itemName = itemNameSb.toString();
//            payVo.setItemName(itemName.substring(itemName.length()-1,itemName.length()));
//            data.put("code",0);
//            payVo.setMemberId(ol.getUserId());
//            //订单取金额逻辑 , 如果是预定2
//            double orderAmount;
//            String isReserve = "0";
//            if (ol.getIsReserve() == 1) {
//                //第一阶段支付了,取第二阶段的金额
//                if (ol.getOneIsPay() == 1) {
//                    orderSn = orderSn + "s";
//                    orderAmount = ol.getReserveTwoAmount();
//                    isReserve = "2";
//                } else {
//                    orderAmount = ol.getReserveOneAmount();
//                    isReserve = "1";
//                }
//            } else {
//                orderAmount = ol.getOrderAmount();
//            }
//            data.put("isReserve",isReserve);
//            if (orderAmount <= 0) {
//                ossOrderService.addZeroPay(ol);
//                //金额为0的订单
//                data.put("code",10);
//                return data;
//            }
//
//        } else {
//            data.put("msg", "不能重复支付");
//            data.put("code",10000);
//        }
//        //支付参数
//        if(payVo.getPayType() == 1){
//            data.put("payParams", CredentFactory.hLAli());
//        }else {
//            /**
//             * 海露的微信公众号和App走不同支付账号
//             */
//            if(StringUtils.isNotBlank(payVo.getOpenId())){
//                data.put("payParams",CredentFactory.hLJsapiWecat());
//            }else {
//                data.put("payParams",CredentFactory.hLAPPWecat());
//            }
//
//        }
//        return data;
//    }
//
//    @Override
//    public Map<String, Object> callback(Map<String,Object> params) {
//
//        //返回参数
//        Map<String,Object> result = new HashMap<>();
//        result.put("code",1);
//        try {
//            //类型  1-支付宝2-微信
//            int payType = (int)params.get("payType");
//
//            //商家订单号
//            String outTradeNo = (String) params.get("outTradeNo");
//            outTradeNo = outTradeNo.split("_")[0];
//            //第三方交易号
//            String tradeNo = (String) params.get("tradeNo");
//            //交易状态
//            String tradeStatus = (String) params.get("tradeStatus");
//            //金额
//            BigDecimal money = (BigDecimal) params.get("momey");
//
//            // 餘額充值
//            if (outTradeNo.substring(0, 2).equals("CZ")) {
//                // 得到充值訂單
//                BalanceRechargeVo irVo = balanceDetailService.getBalanceRecharge(outTradeNo);
//                if (irVo != null) {
//                    //充值金额
//                    rechargeBalance(irVo, payType);
//                }
//            } else {
//                OrderToVo ol = orderService.findByOrderSn(outTradeNo);
//                if (ol.getOrderState() < 3) {
//                    // 直接下單
//                    if (ol != null) {
//                        if (ol.getOrderState() == 1) {
//                            log.info("订单： " + outTradeNo + "。进入了支付回调，支付状态为:" + ol.getPaymentState() + "----订单状态为:"
//                                    + ol.getOrderState());
//                            OrderPay op = new OrderPay();
//                            op.setApiPayState(1);
//                            op.setBuyerId(ol.getUserId());
//                            op.setPaySn(tradeNo);
//                            op.setOrderSn(outTradeNo);
//                            op.setPayTime(System.currentTimeMillis());
//                            op.setPayType(payType);
//                            op.setWechatSource("APP");
//                            if (ol.getPaymentState() == 0) {
//                                OrderToPay otp = new OrderToPay();
//                                otp.setOrderId(ol.getOrderId());
//                                // otp.setPaymentId(op.getPayId());
//                                otp.setPaymentName(payType == 1? "支付宝":"微信");
//                                otp.setPaymentState(1);
//                                otp.setPaymentTime(System.currentTimeMillis());
//                                // 是否預定
//                                if (ol.getIsReserve() == 1) {
//                                    // 第一階段是否支付
//                                    if (ol.getOneIsPay() == 0 && money == new BigDecimal(ol.getReserveOneAmount())) {
//                                        otp.setOneIsPay(1);
//                                        otp.setPaymentState(0);// 是否支付
//                                        op.setPayAmount(ol.getReserveOneAmount());
//                                    } else if(money == new BigDecimal(ol.getReserveTwoAmount())){
//                                        otp.setTwoIsPay(1);
//                                        otp.setOrderState(2);
//                                        op.setPayAmount(ol.getReserveTwoAmount());
//                                    }else {
//                                        return null;
//                                    }
//                                } else {
//                                    otp.setOrderState(2);
//                                    op.setPayAmount(ol.getOrderAmount());
//                                }
//                                //System.out.println("out_trade_no={"+orderNoe+"} orderState={"+otp.getOrderState()+"}");
//                                orderService.updateOrderByPay(otp); // 更改訂單
//                                orderService.addOrderPay(op); // 支付詳情
//
//                                log.info("支付成功");
//                            }
//                        }
//                    }
//                    //判断该商品是否是可分享商品
//                    //判断刚订单是否被分享
//                    //处理分销
//                    ThreadPoolManager.getManager().executeTask(new Runnable() {
//                        @Override
//                        public void run() {
//                            ledgerFixedRatioService.distributionGoods(ol.getOrderId(),ol.getUserId());
//                        }
//                    });
//                } else if ("WAIT_BUYER_PAY".equals(tradeStatus)) {
//                    result.put("code",10000);
//                    result.put("msg","交易处于等待支付状态");
//                    log.info("out_trade_no={}, trade_no={}， 交易处于等待支付状态", outTradeNo, tradeNo);
//                }
//            }
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//            result.put("code",10000);
//            result.put("msg",e.getMessage());
//        }
//        return result;
//    }
//
//
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
//            log.info("充值成功！");
//        }
//    }



}
