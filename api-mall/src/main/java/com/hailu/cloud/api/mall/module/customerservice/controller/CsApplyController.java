package com.hailu.cloud.api.mall.module.customerservice.controller;

import com.hailu.cloud.api.mall.module.customerservice.entity.CsApplyEntity;
import com.hailu.cloud.api.mall.module.customerservice.service.ICsApplyService;
import com.hailu.cloud.api.mall.module.customerservice.service.ICsReasonService;
import com.hailu.cloud.api.mall.module.customerservice.vo.*;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsCompl;
import com.hailu.cloud.api.mall.module.goods.entity.order.OrderToVo;
import com.hailu.cloud.api.mall.module.goods.service.IOrderService;
import com.hailu.cloud.api.mall.module.goods.tool.StringUtil;
import com.hailu.cloud.api.mall.util.Const;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("api/mall")
@Slf4j
public class CsApplyController {

    @Autowired
    private ICsApplyService csApplyService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICsReasonService csReasonService;

    /**
     * 查看 可以售后的订单
     */
    @GetMapping("/getCSOrders")
    public Map<String, Object> getOrders(
            @RequestParam(value = "userId") String userId,
            @RequestParam("page") int page,
            @RequestParam("row") int row) {

        page = (page - 1) * row;
        List<CSOrderListVo> orders = csApplyService.getCSOrdersList(userId, page, row);
        //跟据订单id得到订单中的商品
        if (orders.size() > 0) {
            for (CSOrderListVo ol : orders) {
                List<CSOrderGoods> og = csApplyService.findByOrderIdCS(ol.getOrderId());
                if (og.size() > 0) {
                    // 订单状态：0:已取消;1:待付款;2:待发货;3:待收货;4:交易完成;5:已提交;6:已确认;
                    for (CSOrderGoods orderGoods : og) {
                        if (StringUtil.isNotEmpty(orderGoods.getGoodsImage()) && !("http").equals(orderGoods.getGoodsImage().substring(0, 4))) {
                            orderGoods.setGoodsImage(Const.PRO_URL + orderGoods.getGoodsImage());
                        }
                        List<GoodsCompl> goodsCompl = orderService.getGoodsCompl(orderGoods.getRecId());
                        orderGoods.setGoodsClmpl(goodsCompl);
                        CsApplyEntity csApplyEntity = csApplyService.getCsApply(ol.getOrderId(), orderGoods.getRecId());
                        // <!--  提交申请(0)，商城审核(1)，收货(2)，退款(3)，完成(4) ，（5）取消申请，（6）商城拒绝）   查出 0,1,2,3,4,6 订单明细-->
                        if (csApplyEntity == null) {
                            //0就是 没正在进行中的售后流程  1显示申请中 2显示为空
                            orderGoods.setStatus(0);
                        } else {
                            Integer tpState = csApplyEntity.getTpState();
                            orderGoods.setCsApplyId(csApplyEntity.getCsApplyId());
                            if (tpState == 0) {
                                // 申请 1显示申请中
                                orderGoods.setStatus(1);
                            } else if (tpState == 1) {
                                // 收货 1显示申请中
                                orderGoods.setStatus(1);
                            } else if (tpState == 2) {
                                // 退款 1显示申请中
                                orderGoods.setStatus(1);
                            } else if (tpState == 3) {
                                // 处理中 1显示申请中
                                orderGoods.setStatus(1);
                            } else if (tpState == 4) {
                                // 完成 2显示为空
                                orderGoods.setStatus(2);
                            } else if (tpState == 5) {
                                // 取消申请 2显示为空
                                orderGoods.setStatus(0);
                            } else if (tpState == 6) {
                                // 商城拒绝 2显示为空
                                orderGoods.setStatus(0);
                            }
                        }
                    }
                }
                //提交申请(0)，商城审核(1)，收货(2)，处理中(3)，完成(4),（5）取消申请，（6）商城拒绝;
                ol.setOrderGoods(og);
            }
        }
        Map<String, Object> data = new HashMap<>();
        data.put("orders", orders);
        return data;
    }


    /**
     * 查看 可以售后的订单
     * 维修流程:提交申请(0)，商城审核(1)，收货(2)，处理中(3)，完成(4)，（5）取消申请，（6）商城拒绝 (7)商城拒绝完成 ;
     * 换货流程:提交申请(0)，商城审核(1)，收货(2)，处理中(3)，完成(4),（5）取消申请，（6）商城拒绝;(7)商城拒绝完成 ;
     * 七天无理由退货: 提交申请(0)，商城审核(1)，收货(2)，退款(3)，完成(4) ，（5）取消申请，（6）商城拒绝）(7)商城拒绝完成 ;
     */
    @PostMapping("/addApply")
    public Boolean addApply(
            @RequestParam("orderId") int orderId,
            @RequestParam("orderGoodsId") int orderGoodsId,
            @RequestParam("csReasonId") int csReasonId,
            @RequestParam("remarks") String remarks,
            @RequestParam("photo") String photo,//照片 //照片
            @RequestParam("returnMode") Integer returnMode,//退回方式 (0 原路返回)
            @RequestParam("csType") Integer csType,// 售后类型(0维修，1退货，2换货,3.整单退款)
            @RequestParam("consignee") String consignee,// 收货人
            @RequestParam("addresseePhn") String addresseePhn,// 收件人手机号码
            @RequestParam("receivingAddress") String receivingAddress,// 收货地址
            @RequestParam("userAmount") double userAmount,// 用户获取金额
            @RequestParam("userId") String userId) throws Exception {

        CsApplyEntity csApplyEntity = new CsApplyEntity();
        csApplyEntity.setOrderId(orderId);
        csApplyEntity.setOrderGoodsId(orderGoodsId);
        csApplyEntity.setCsReasonId(csReasonId);
        csApplyEntity.setRemarks(remarks);
        csApplyEntity.setPhoto(photo);
        csApplyEntity.setReturnMode(returnMode);
        csApplyEntity.setCsType(csType);
        csApplyEntity.setConsignee(consignee);
        csApplyEntity.setAddresseePhn(addresseePhn);
        csApplyEntity.setReceivingAddress(receivingAddress);
        csApplyEntity.setUserAmount(userAmount);
        csApplyEntity.setUserId(userId);
        OrderToVo orderToVo = csApplyService.findByOrderId(orderId);
        if (userAmount > orderToVo.getOrderAmount()) {
            throw new BusinessException("用户输入金额不能大于订单金额");
        }
        if (csType == 1) {
            if (userAmount <= 0) {
                throw new BusinessException("用户输入金额不能小于0");
            }
        }
        CsApplyEntity csApplyEntity2 = csApplyService.getCsApply(orderId, csReasonId);

        if (csApplyEntity2 != null && csApplyEntity2.getTpState() == 4) {
            throw new BusinessException("该商品已有完成售后申请");
        }
        if (csApplyEntity2 != null && csApplyEntity2.getTpState() < 4) {
            throw new BusinessException("该商品有正在进行中的售后申请");
        }
        /**
         * 2018-4-6 18:35:14
         * 临时从原因表去原因
         */
        CsReasonVo csReasons = csReasonService.findCsReason(csReasonId);
        String reason = "";
        if (csReasons != null && csReasons.getReason() != null) {
            reason = csReasons.getReason();
        }
        csApplyEntity.setCsReason(reason);
        int i = csApplyService.addCsApply(csApplyEntity);
        if (i == 0) {
            throw new BusinessException("添加失败");
        }
        return true;
    }

    /**
     * 查看 可以售后的订单    明细商品
     * 返回申请售后订单明细商品显示
     */
    @GetMapping("/getCyApplyGoods")
    public CsApplyGoods getCyApplyGoods(
            @RequestParam("orderGoodsId") int orderGoodsId) throws BusinessException {

        CsApplyGoods responseData = new CsApplyGoods();
        CsApplyGoods csApplyGoods = csApplyService.getCyApplyGoods(orderGoodsId);
        if (csApplyGoods == null) {
            throw new BusinessException("没有该订单明细");
        }
        return csApplyGoods;
    }

    /**
     * 查看 售后申请列表
     */
    @GetMapping("/getCsApplyList")
    public Map<String, Object> getCsApplyList(
            @RequestParam(value = "userId") String userId,
            @RequestParam("page") int page,
            @RequestParam("row") int row) {

        page = (page - 1) * row;
        List<CsApplyListVo> orders = csApplyService.getCsApplyList(userId, page, row);
        //跟据订单id得到订单中的商品  getCsApplyList
        Map<String, Object> data = new HashMap<>();
        data.put("orders", orders);
        return data;
    }

    /***
     * 获取售后申请详情接口
     */
    @GetMapping("/csApplyById")
    public CsApplyVo csApplyById(
            @RequestParam(value = "csApplyId") Integer csApplyId) {
        return csApplyService.csApplyById(csApplyId);
    }

    /***
     * 获取售后申请详情接口
     */
    @PostMapping("/cancelCsApply")
    public Boolean cancelCsApply(
            @RequestParam(value = "csApplyId") Integer csApplyId) throws BusinessException {
        return csApplyService.cancelCsApply(csApplyId);
    }

    /**
     * 整单退款
     */
    @PostMapping("/orderRefund")
    public Boolean orderRefund(
            @RequestParam("orderId") int orderId,
            @RequestParam("csReasonId") int csReasonId,
            @RequestParam("consignee") String consignee,// 收货人
            @RequestParam("addresseePhn") String addresseePhn,// 收件人手机号码
            @RequestParam("receivingAddress") String receivingAddress,// 收货地址
            @RequestParam("userId") String userId) throws Exception {

        CsReasonVo csReasons = csReasonService.findCsReason(csReasonId);
        String reason = "";
        if (csReasons != null && csReasons.getReason() != null) {
            reason = csReasons.getReason();
        }
        CsApplyEntity csApplyEntity = new CsApplyEntity();
        csApplyEntity.setReturnMode(0);
        csApplyEntity.setCsType(3);
        csApplyEntity.setOrderId(orderId);
        csApplyEntity.setCsReasonId(csReasonId);
        csApplyEntity.setConsignee(consignee);
        csApplyEntity.setAddresseePhn(addresseePhn);
        csApplyEntity.setReceivingAddress(receivingAddress);
        csApplyEntity.setUserId(userId);
        csApplyEntity.setCsReason(reason);
        int i = csApplyService.orderRefund(csApplyEntity);
        if (i == 0) {
            throw new BusinessException("添加失败");
        }
        return true;
    }

    /**
     * 查看 售后申请列表
     */
    @GetMapping("/csApplyByOrderId")
    public CsApplyVo getCsApplyList(
            @RequestParam(value = "userId") String userId,
            @RequestParam("orderId") int orderId) throws BusinessException {

        CsApplyVo csApplyVo = csApplyService.csApplyByOrderId(userId, orderId);
        if (csApplyVo == null) {
            throw new BusinessException("数据错误");
        }
        return csApplyVo;
    }

    /**
     * 返回快递公司
     */
    @GetMapping("/getCouriers")
    public List<ExpressVo> getCouriers() {
        return csApplyService.getCouriers();
    }

    /**
     * 修改快递单号
     */
    @PostMapping("/udpateCsApplyCourierNumber")
    public Boolean udpateCsApplyCourierNumber(
            Integer csApplyId,
            String courierNumber,
            String courierCompany,
            String courierCode) {
        return csApplyService.udpateCsApplyCourierNumber(csApplyId, courierNumber, courierCompany, courierCode);
    }

    /**
     * 返回售后申请
     */
    @GetMapping("/getCsApplyCourierNumber")
    public CsApplyExpressVo getCsApplyCourierNumber(Integer csApplyId) {
        return csApplyService.getCsApplyCourierNumber(csApplyId);
    }
}
