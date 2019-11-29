package com.hailu.cloud.api.merchant.module.merchant.dao;

import com.hailu.cloud.api.merchant.module.merchant.entity.MultiIndustryOrder;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MultiIndustryOrderMapper {
    /**
     * 更具商户查询多行业订单
     * @param storeId
     * @return
     */
    List<MultiIndustryOrder> findOrderListByStoreId(@Param("storeId") Long storeId);

    /**
     * 根据店铺Id更改订单状态
     * @param id
     * @return
     */
    int updateOrderState(@Param("id") Long id);
}