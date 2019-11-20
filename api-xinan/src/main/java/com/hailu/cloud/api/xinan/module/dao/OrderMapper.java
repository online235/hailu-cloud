package com.hailu.cloud.api.xinan.module.dao;

import com.hailu.cloud.api.xinan.module.entity.Order;
import com.hailu.cloud.api.xinan.module.model.OrderVo;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     *
     * @mbggenerated 2019-10-17
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int insert(Order record);

    /**
     * @mbggenerated 2019-10-17
     */
    int insertSelective(Order record);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    Order selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int updateByPrimaryKey(Order record);

    /**
     * 根据会员ID和订单状态统计金额
     * @param memberId
     * @param orderStatus
     * @return
     */
    BigDecimal sumMoneyByMemberIdAndOrderStatus(@Param("memberId") String memberId, @Param("orderStatus") Integer orderStatus);

    /**
     * 根据会员ID和订单状态获取信息
     * @param memberId
     * @param orderStatus
     * @return
     */
    List<OrderVo> findByMemberIdAndOrderStatus(@Param("memberId") String memberId, @Param("orderStatus") Integer orderStatus, @Param("itemName") String itemName);

    /**
     * 根据订单号查询信息
     * @param orderNo
     * @return
     */
    Order findByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 根据名称和值查询信息
     * @param insuredName
     * @param insuredValue
     * @return
     */
    Order findByNameAndValue(@Param("insuredName") String insuredName, @Param("insuredValue") String insuredValue);
}