package com.hailu.cloud.api.admin.module.mall.dao;


import com.hailu.cloud.api.admin.module.mall.model.HlOrderModel;
import com.hailu.cloud.common.model.mall.hlorder.HlOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HlOrderMapper {

    /**
     *
     * @mbggenerated 2019-12-10
     */
    HlOrder selectByPrimaryKey(Long id);

    /**
     * 根据订单号查询
     * @param orderNo
     * @return
     */
    HlOrder findByOrder(@Param("orderNo") String orderNo);

    /**
     * 查询订单
     * @param orderType
     * @return
     */
    List<HlOrderModel> findList(@Param("orderType") Integer orderType,@Param("userName") String userName,@Param("goodsName")String goodsName,@Param("orderStatus") Integer orderStatus);

    int updateByPrimaryKeySelective(HlOrder hlOrder);

}
