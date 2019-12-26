package com.hailu.cloud.api.mall.module.multiindustry.service;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McCoupon;
import com.hailu.cloud.api.mall.module.multiindustry.model.CouponAndPictureModel;
import com.hailu.cloud.api.mall.module.multiindustry.model.McCouponOtherJsonModel;
import com.hailu.cloud.common.model.page.PageInfoModel;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 到店卷
 * @Date: 2019/12/20 0020
 * @program: cloud
 * @create: 2019-12-20 10:
 */
public interface McCouponService {

    /**
     * 查询到店卷详细信息
     * @param id
     * @return
     */
    McCoupon<McCouponOtherJsonModel> selectByPrimaryKey(Long id, Long numberId);

    /**
     * 到店卷列表
     */
    PageInfoModel<List<CouponAndPictureModel>> findMcCouponList(Long mcNumberId, Integer page, Integer size);


    /**
     * 查询到店卷是否过去
     * @mbggenerated 2019-12-20
     */
    int findMcCouponById(Long id);
}
