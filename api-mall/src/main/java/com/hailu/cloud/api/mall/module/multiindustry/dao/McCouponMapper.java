package com.hailu.cloud.api.mall.module.multiindustry.dao;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McCoupon;
import com.hailu.cloud.api.mall.module.multiindustry.model.CouponAndPictureModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 * @date         : 2019/12/20 0020 10:29
 */
@Mapper
public interface McCouponMapper {

    /**
     * 查询到店卷详细信息
     * @mbggenerated 2019-12-20
     */
    McCoupon selectByPrimaryKey(@Param("id") Long id, @Param("numberId") Long numberId);

    /**
     * 到店卷列表
     */
    List<CouponAndPictureModel> findMcCouponList(
            @Param("mcNumberId") Long mcNumberId);

    /**
     * 查询到店卷是否过去
     * @mbggenerated 2019-12-20
     */
    int findMcCouponById(@Param("id") Long id);
}