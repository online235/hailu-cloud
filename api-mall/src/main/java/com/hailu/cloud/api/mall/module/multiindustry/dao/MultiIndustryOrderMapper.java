package com.hailu.cloud.api.mall.module.multiindustry.dao;

import com.hailu.cloud.api.mall.module.multiindustry.entity.MultiIndustryOrder;
import com.hailu.cloud.api.mall.module.multiindustry.model.McOrderModel;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MultiIndustryOrderMapper {
    /**
     *
     * @mbggenerated 2019-11-25
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-11-25
     */
    int insertSelective(MultiIndustryOrder record);

    /**
     * 查询订单
     * @mbggenerated 2019-11-25
     */
    MultiIndustryOrder selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-11-25
     */
    McOrderModel selectDefaultHead(Long id);

    /**
     * 根据用户编号查询多行业订单
     * @param memberId
     * @return
     */
    List<MultiIndustryOrder> findOrderListByMemberId(@Param("memberId") String memberId,@Param("state") Integer state,@Param("secondState") Integer secondState);


    /**
     * 根据店铺Id更改订单状态
     * @param id
     * @return
     */
    int updateOrderState(@Param("id") Long id, @Param("state") Integer state);


}