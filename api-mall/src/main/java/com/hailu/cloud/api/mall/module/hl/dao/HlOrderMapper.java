package com.hailu.cloud.api.mall.module.hl.dao;


import com.hailu.cloud.common.model.mall.hlorder.HlOrder;
import org.apache.ibatis.annotations.Param;

public interface HlOrderMapper {
    /**
     *
     * @mbggenerated 2019-12-10
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-10
     */
    int insert(HlOrder record);

    /**
     *
     * @mbggenerated 2019-12-10
     */
    int insertSelective(HlOrder record);

    /**
     *
     * @mbggenerated 2019-12-10
     */
    HlOrder selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-10
     */
    int updateByPrimaryKeySelective(HlOrder record);

    /**
     *
     * @mbggenerated 2019-12-10
     */
    int updateByPrimaryKey(HlOrder record);

    /**
     * 根据订单号查询
     * @param orderNo
     * @return
     */
    HlOrder findByOrder(@Param("orderNo") String orderNo);
}