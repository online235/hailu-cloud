package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.entity.SalvageOrder;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalvageOrderMapper {
    /**
     *
     * @mbggenerated 2019-11-13
     */
    int insert(SalvageOrder record);

    /**
     *
     * @mbggenerated 2019-11-13
     */
    int insertSelective(SalvageOrder record);

    /**
     * 根据编号更改
     * @param salvageOrder
     * @return
     */
    int UpdateSalvageOrder(SalvageOrder salvageOrder);

    /**
     * 根据编号查询信息
     * @param memberId
     * @param orderNo
     * @return
     */
    SalvageOrder findSalvageOrder(@Param("memberId") String memberId, @Param("orderNo") String orderNo);

    /**
     * 根据订单号查询
     * @param orderNo
     * @return
     */
    SalvageOrder findSalvageOrderByOrderNO(String orderNo);

}