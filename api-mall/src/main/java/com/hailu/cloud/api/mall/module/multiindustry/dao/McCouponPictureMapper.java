package com.hailu.cloud.api.mall.module.multiindustry.dao;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McCouponPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 * @date         : 2019/12/20 0020 10:29
 */
@Mapper
public interface McCouponPictureMapper {

    /**
     * 查询单张图片
     * @mbggenerated 2019-12-20
     */
    McCouponPicture selectByPrimaryKey(Long id);

    /**
     * 查询图片
     * @param couponId
     * @return
     */
    List<McCouponPicture> findMcCouponPictureListByCouponId(@Param("couponId") Long couponId);
}