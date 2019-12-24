package com.hailu.cloud.api.mall.module.multiindustry.dao;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McCouponReceive;
import com.hailu.cloud.api.mall.module.multiindustry.model.CouponAndReceiveModel;
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
     *  添加优惠卷
     * @param record
     */
    int insertSelective(McCouponReceive record);

    /**
     * 优惠卷失效
     * @param id
     * @return
     */
    int updUsageStateById(@Param("id") Long id, @Param("usageState") Long usageState);

    /**
     * 查询当天领了多少卷
     * @mbggenerated 2019-12-23
     */
    int findCountByMemberIdAndCouponId(@Param("memberId") Long memberId, @Param("couponId") Long couponId);

    /**
     * 用户查询优惠卷列表
     * @param memberId
     * @return
     */
    List<CouponAndReceiveModel> findMcCouponByMemberIdList(@Param("memberId") String memberId, @Param("usageState") Integer usageState);

    /**
     * 根据编号查询详细信息
     * @param receiveId
     * @return
     */
    CouponAndReceiveModel findMcCouponByReceiveId(@Param("memberId") String memberId, @Param("receiveId") Long receiveId);
}