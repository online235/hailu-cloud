package com.hailu.cloud.api.xinan.module.dao;

import com.hailu.cloud.api.xinan.module.entity.XaSalvageOrder;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XaSalvageOrderMapper {
    /**
     *
     * @mbggenerated 2019-11-13
     */
    int insert(XaSalvageOrder record);

    /**
     *
     * @mbggenerated 2019-11-13
     */
    int insertSelective(XaSalvageOrder record);

    /**
     * 根据编号更改
     * @param xaSalvageOrder
     * @return
     */
    int UpdateSalvageOrder(XaSalvageOrder xaSalvageOrder);

    /**
     * 根据编号查询信息
     * @param memberId
     * @param orderNo
     * @return
     */
    XaSalvageOrder findSalvageOrder(@Param("memberId") String memberId, @Param("orderNo") String orderNo);

    /**
     * 根据订单号查询
     * @param orderNo
     * @return
     */
    XaSalvageOrder findSalvageOrderByOrderNO(String orderNo);

}