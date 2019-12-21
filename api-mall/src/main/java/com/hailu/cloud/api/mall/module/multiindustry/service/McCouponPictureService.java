package com.hailu.cloud.api.mall.module.multiindustry.service;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McCouponPicture;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 到店卷图片
 * @Date: 2019/12/20 0020
 * @program: cloud
 * @create: 2019-12-20 10:
 */
public interface McCouponPictureService {

    /**
     * 查询图片
     * @param couponId
     * @return
     */
    List<McCouponPicture> findMcCouponPictureListByCouponId(Long couponId);
}
