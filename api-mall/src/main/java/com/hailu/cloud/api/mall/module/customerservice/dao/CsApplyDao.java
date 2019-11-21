package com.hailu.cloud.api.mall.module.customerservice.dao;

import com.hailu.cloud.api.mall.module.customerservice.entity.CsApplyEntity;
import com.hailu.cloud.api.mall.module.customerservice.vo.CSOrderGoods;
import com.hailu.cloud.api.mall.module.customerservice.vo.CsApplyExpressVo;
import com.hailu.cloud.api.mall.module.customerservice.vo.ExpressVo;
import com.hailu.cloud.api.mall.module.goods.entity.order.OrderToVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface CsApplyDao {
    /**
     * 通订单ID 和订单明细ID 获取正在进行的明细的订单状态
     *
     * @return
     */
    CsApplyEntity getCsApply(
            @Param("orderId") Integer orderId,
            @Param("orderGoodsId") Integer orderGoodsId);

    /**
     * 获取服务单号
     *
     * @return
     */
    String getCsApplyNumber();

    /**
     * 添加售后申请
     *
     * @param csApplyEntity
     * @return
     */
    int addCsApply(CsApplyEntity csApplyEntity);

    /**
     * 获取订单明细对象
     *
     * @return
     */
    CSOrderGoods getCSOrderGoods(@Param("recId") Integer recId);

    /**
     * 更具订单Id 获取订单对象
     *
     * @param orderId
     * @return
     */
    OrderToVo findByOrderId(@Param("orderId") Integer orderId);

    /***
     * 通过key 获取val
     */
    String getAttributeValue(@Param("key") String key);

    /**
     * 获取售后申请列表
     */
    List<CsApplyEntity> getCsApplyList(
            @Param("userId") String userId,
            @Param("page") Integer page,
            @Param("row") Integer row);

    /**
     * 获取 售后服务明细
     *
     * @param csApplyId
     * @return
     */
    CsApplyEntity csApplyById(@Param("csApplyId") Integer csApplyId);

    /**
     * 取消审核申请
     */
    int cancelCsApply(@Param("csApplyId") Integer csApplyId);

    /**
     * 修改订单状态
     */
    int updateOrderRefund(
            @Param("refundState") Integer refundState,
            @Param("returnState") Integer returnState,
            @Param("orderId") Integer orderId);


    /**
     * 获取售后申请列表
     */
    List<CsApplyEntity> csApplyByOrderId(
            @Param("userId") String userId,
            @Param("orderId") Integer orderId);


    Integer shopOrderPayType(@Param("orderSn") String orderSn);

    /**
     * 修改快递单号
     *
     * @param csApplyId
     * @param courierNumber
     * @return
     */
    int udpateCsApplyCourierNumber(
            @Param("csApplyId") Integer csApplyId,
            @Param("courierNumber") String courierNumber,
            @Param("courierCompany") String courierCompany,
            @Param("courierCode") String courierCode);

    /**
     * 获取所有快递公司
     *
     * @return
     */
    List<ExpressVo> getCouriers();

    /**
     * 返回售后申请买家填写物流信息
     *
     * @return
     */
    CsApplyExpressVo getCsApplyCourierNumber(@Param("csApplyId") Integer csApplyId);
}
