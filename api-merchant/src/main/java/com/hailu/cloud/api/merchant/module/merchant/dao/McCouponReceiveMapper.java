package com.hailu.cloud.api.merchant.module.merchant.dao;

import com.hailu.cloud.api.merchant.module.merchant.entity.McCouponReceive;
import com.hailu.cloud.api.merchant.module.merchant.model.CouponAndReceiveModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 * @date         : 2019/12/24 0024 11:11
 */
@Mapper
public interface McCouponReceiveMapper {

    /**
     * 更改优惠卷状态
     * @param id
     * @return
     */
    int updUsageStateById(@Param("id") Long id, @Param("usageState") Long usageState);

    /**
     * 用户查询优惠卷列表
     * @param numberId
     * @return
     */
    List<CouponAndReceiveModel> findMcCouponByMemberIdList(@Param("numberId") String numberId, @Param("usageState") Integer usageState);

    /**
     * 核销到店卷
     * @param awardCode
     * @param numberId
     * @return
     */
    int updUsageStateByAwardCodeAndStoreId(@Param("awardCode") String awardCode, @Param("numberId") String numberId);
}