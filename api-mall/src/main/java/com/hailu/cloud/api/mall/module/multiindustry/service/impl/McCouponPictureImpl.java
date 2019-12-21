package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import com.hailu.cloud.api.mall.module.multiindustry.dao.McCouponPictureMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.McCouponPicture;
import com.hailu.cloud.api.mall.module.multiindustry.service.McCouponPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/20 0020
 * @program: cloud
 * @create: 2019-12-20 10:
 */
@Service
public class McCouponPictureImpl implements McCouponPictureService {

    @Resource
    private McCouponPictureMapper mcCouponPictureMapper;

    @Override
    public List<McCouponPicture> findMcCouponPictureListByCouponId(Long couponId) {
        return mcCouponPictureMapper.findMcCouponPictureListByCouponId(couponId);
    }

}
