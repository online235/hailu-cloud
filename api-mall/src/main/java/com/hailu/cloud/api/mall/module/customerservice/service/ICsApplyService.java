package com.hailu.cloud.api.mall.module.customerservice.service;


import com.hailu.cloud.api.mall.module.customerservice.entity.CsApplyEntity;
import com.hailu.cloud.api.mall.module.customerservice.vo.*;
import com.hailu.cloud.api.mall.module.goods.entity.order.OrderToVo;
import com.hailu.cloud.common.exception.BusinessException;

import java.util.List;

/**
 * @author Administrator
 */
public interface ICsApplyService {
    /**
     * 获取 售后服务的订单列表
     */
    List<CSOrderListVo> getCSOrdersList(String userId, int page, int row);

    /**
     * 获取 售后服务的订单列表
     */
    List<CSOrderGoods> findByOrderIdCS(int orderId);


    /**
     * 添加售后申请
     *
     * @param csApplyEntity
     * @return
     */
    int addCsApply(CsApplyEntity csApplyEntity) throws Exception;

    /**
     * 通订单ID 和订单明细ID 获取正在进行的明细的订单状态
     * 返回该订单明细是否正在申请售后 NULL 就是没有申请售后
     *
     * @return
     */
    CsApplyEntity getCsApply(Integer orderId, Integer orderGoodsId);

    /*
     * 更具订单明细 iD   返回 售后显示订单对象
     */
    CsApplyGoods getCyApplyGoods(Integer orderGoodsId);

    /*
     * 更具 申请ID 获取列表
     *
     */
    List<CsApplyListVo> getCsApplyList(String userId, Integer page, Integer row);

    /*
     * 更具 申请ID 获取列表
     *
     */
    CsApplyVo csApplyById(Integer csApplyId);

    /**
     * 取消审核申请
     *
     * @return
     */
    Boolean cancelCsApply(Integer csApplyId) throws BusinessException;

    /**
     * 添加退款订单
     */
    int orderRefund(CsApplyEntity csApplyEntity) throws Exception;

    /**
     * 更具用户Id 和订单Id 查询退款状态
     *
     * @param userId
     * @param orderId
     * @return
     */
    CsApplyVo csApplyByOrderId(String userId, Integer orderId);

    /*
     * 获取订单对象
     */
    OrderToVo findByOrderId(Integer orderId);

    /**
     * 修改快递单号
     *
     * @param csApplyId
     * @param courierNumber
     * @return
     */
    Boolean udpateCsApplyCourierNumber(Integer csApplyId, String courierNumber, String courierCompany, String courierCode);

    /**
     * 快递公司单号
     * getCouriers
     */
    List<ExpressVo> getCouriers();

    /**
     * 返回售后申请买家填写物流信息
     *
     * @return
     */
    CsApplyExpressVo getCsApplyCourierNumber(Integer csApplyId);
}
