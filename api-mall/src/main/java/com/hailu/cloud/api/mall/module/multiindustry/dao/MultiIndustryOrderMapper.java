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
    int insert(MultiIndustryOrder record);

    /**
     *
     * @mbggenerated 2019-11-25
     */
    int insertSelective(MultiIndustryOrder record);

    /**
     *
     * @mbggenerated 2019-11-25
     */
    MultiIndustryOrder selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-11-25
     */
    McOrderModel selectDefaultHead(Long id);

    /**
     *
     * @mbggenerated 2019-11-25
     */
    int updateByPrimaryKeySelective(MultiIndustryOrder record);

    /**
     * 根据用户编号查询多行业订单
     * @param memberId
     * @return
     */
    List<MultiIndustryOrder> findOrderListByMemberId(@Param("memberId") String memberId,@Param("state")   Integer state);
}