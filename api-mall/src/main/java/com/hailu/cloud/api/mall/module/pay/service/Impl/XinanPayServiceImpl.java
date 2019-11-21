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
 * @CreateTime: 2019-10-21 17:04
 * @Description: 心安支付逻辑
 */
@Slf4j
@Service
public class XinanPayServiceImpl extends AbstractPayService {
    @Override
    public Map<String, Object> createOrder(PayVo payVo) {
        return null;
    }

    @Override
    public Map<String, Object> callback(Map<String, Object> params) throws Exception {
        return null;
    }

    //    @Autowired
//    private XinAnOrderService xinAnOrderService;
//
//    @Autowired
//    private XaOrderMapper xaOrderMapper;
//
//    @Autowired
//    private XinAnPayService xinAnPayService;
//
//    @Autowired
//    private XinAnPayExpandService xinAnPayExpandService;
//
//    @Autowired
//    private XinAnInsuredService xinAnInsuredService;
//
//
//    @Override
//    @Transactional
//    public Map<String, Object> createOrder(PayVo payVo) {
//
//        Map<String,Object> data = new HashMap<>();
//
////        //获取需要支付的参保人
////        if(StringUtils.isBlank(payVo.getInsuredIds())){
////            data.put("msg","参保人不能为空！");
////            data.put("code",10000);
////            return data;
////        }
////        String[] insuredArr = payVo.getInsuredIds().split(",");
////
////
////        //根据会员ID获取信息
////        List<Order> orderVoList = new ArrayList<>();
////        //金额 计算出总金额
////        BigDecimal money = new BigDecimal("0");
////        //根据参保人ID获取订单参数
////        for(String o : insuredArr){
////            Insured insured = xinAnInsuredService.findById(o);
////            if(insured == null){
////                log.info("参保ID为{}，查询不到信息！",o);
////                continue;
////            }
////            Order order = xaOrderMapper.findByNameAndValue(insured.getName(),insured.getValue());
////            money = money.add(order.getMoney());
////            orderVoList.add(order);
////        }
////        //校验金额是否一致
////        if(money.compareTo(BigDecimal.valueOf(payVo.getMoney())) != 0){
////            data.put("msg","金额与后台计算不一致！");
////            data.put("code",10000);
////            return data;
////        }
////
////        boolean moneyFlag = false;
////        //校验金额是否为0 ,直接支付成功
////        if(money.compareTo(BigDecimal.valueOf(0.00)) == 0){
////            moneyFlag = true;
////        }
////
////
////        Pay pay = new Pay();
////
////        //会员ID
////        pay.setMemberId(payVo.getMemberId());
////        //金额
////        pay.setMoney(money);
////        //支付方式
////        pay.setPayType(payVo.getPayType() == 1? 1 : 2);
////        //支付订单号
////        String orderNo = NumberUtil.generateOrderSn();
////        pay.setPayOrderNo(orderNo);
////        //支付状态 1-未付款 2-已付款3-已退款4-已过期
////        pay.setPayStatus( moneyFlag ? 2:1);
////        //会员ID
////        pay.setMemberId(payVo.getMemberId());
////
////        xinAnPayService.saveEntity(pay);
////
////
////
////        //订单拓展表
////        for (Order o:orderVoList){
////            PayExpand payExpand = new PayExpand();
////            //金额
////            payExpand.setMoney(o.getMoney());
////            //支付ID
////            payExpand.setPayId(pay.getId());
////            //支付订单号
////            payExpand.setPayOrderNo(orderNo);
////            //订单号
////            payExpand.setOrderNo(o.getOrderNo());
////
////            xinAnPayExpandService.saveEntity(payExpand);
////            if(moneyFlag){
////                //获取订单信息
////                Order order = xaOrderMapper.findByOrderNo(o.getOrderNo());
////                //支付方式
////                order.setPayType(4);
////                //将订单状态改成已支付 订单状态（1-未付款2-已付款3-已取消）
////                order.setOrderStatus(2);
////                xinAnOrderService.saveEntity(order);
////                //修改参保人状态
////                xinAnInsuredService.editInsurdPayStatus(order.getInsuredName(),order.getInsuredValue());
////            }
////
////        }
////        payVo.setOrderNo(orderNo);
////        data.put("userId",payVo.getMemberId());
////        data.put("code",0);
////        data.put("isReserve","1");
////        payVo.setItemName("购买心安会员");
////        if(moneyFlag){
////            //1代表0元，无需支付，直接成功购买
////            data.put("msg","购买成功，请等待审核");
////            data.put("code",1);
////        }
////        //支付参数
////        if(payVo.getPayType() == 1){
////            data.put("payParams", CredentFactory.xinAnAli());
////        }else {
////            data.put("payParams",CredentFactory.xinAnWecat());
////        }
//        return data;
//    }
//
//    @Override
//    public Map<String, Object> callback(Map<String, Object> params){
//        //返回参数
//        Map<String,Object> result = new HashMap<>();
//        result.put("code",1);
//        try {
//            //类型  1-支付宝2-微信
//            int payType = (int) params.get("payType");
//
//            //商家订单号
//            String outTradeNo = (String) params.get("outTradeNo");
//            //第三方交易号
//            String tradeNo = (String) params.get("tradeNo");
//            //交易状态
//            String tradeStatus = (String) params.get("tradeStatus");
//            //金额
//            BigDecimal money = BigDecimal.valueOf((double)params.get("money"));
//
//            //根据订单号获取订单
//            Pay pay = xinAnPayService.findByPayOrderNo(outTradeNo);
//
//            if(pay == null){
//                result.put("code",10000);
//                result.put("msg","订单不存在");
//                log.warn("订单号：{}，订单不存在",outTradeNo);
//                return result;
//            }
//
//            //判断金额是否正确
//            if(money.compareTo(pay.getMoney()) != 0){
//                result.put("code",10000);
//                result.put("msg","付款金额和订单金额不一致");
//                log.warn("订单号：{}，付款金额和订单金额不一致",outTradeNo);
//                return result;
//            }
//            //支付时间
//            pay.setPayTime(System.currentTimeMillis());
//            //第三方交易号
//            pay.setTradeNo(tradeNo);
//            //付款状态 1-待付款2-已付款3-已取消
//            pay.setPayStatus(2);
//            //支付方式
//            pay.setPayType(payType);
//            xinAnPayService.saveEntity(pay);
//
//            //根据订单拓展表获取相关联的数据
//            List<PayExpand> payExpandList = xinAnPayExpandService.findListByPayId(pay.getId());
//            for(PayExpand p:payExpandList){
//                //获取订单信息
//                Order order = xaOrderMapper.findByOrderNo(p.getOrderNo());
//                //支付方式
//                order.setPayType(payType);
//                //将订单状态改成已支付 订单状态（1-未付款2-已付款3-已取消）
//                order.setOrderStatus(2);
//                xinAnOrderService.saveEntity(order);
//                //修改参保人状态
//                xinAnInsuredService.editInsurdPayStatus(order.getInsuredName(),order.getInsuredValue());
//            }
//        }catch (Exception e){
//            log.warn("处理支付订单出现错误：",e.getMessage());
//            result.put("code",10000);
//            result.put("msg",e.getMessage());
//        }
//        return result;
//    }
}
