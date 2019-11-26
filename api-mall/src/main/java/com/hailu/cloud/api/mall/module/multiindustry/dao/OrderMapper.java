package com.hailu.cloud.api.mall.module.multiindustry.dao;

import com.hailu.cloud.api.mall.module.multiindustry.entity.Order;

public interface OrderMapper {
    /**
     *
     * @mbggenerated 2019-11-25
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-11-25
     */
    int insert(Order record);

    /**
     *
     * @mbggenerated 2019-11-25
     */
    int insertSelective(Order record);

    /**
     *
     * @mbggenerated 2019-11-25
     */
    Order selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-11-25
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     *
     * @mbggenerated 2019-11-25
     */
    int updateByPrimaryKey(Order record);
}