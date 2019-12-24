package com.hailu.cloud.api.mall.module.multiindustry.service;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McCouponReceive;
import com.hailu.cloud.api.mall.module.multiindustry.model.CouponAndReceiveModel;
import com.hailu.cloud.api.mall.module.multiindustry.model.McCouponOtherJsonModel;
import com.hailu.cloud.api.mall.module.multiindustry.model.McCouponReceiveModel;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 优惠卷
 * @Date: 2019/12/23 0023
 * @program: cloud
 * @create: 2019-12-23 11:
 */
public interface McCouponReceiveService {

    /**
     *  添加优惠卷
     * @param record
     */
    McCouponReceive insertSelective(McCouponReceiveModel record) throws BusinessException;

    /**
     * 查询当天领了多少卷
     * @mbggenerated 2019-12-23
     */
    int findCountByMemberIdAndCouponId(Long memberId,Long couponId);

    /**
     * 优惠卷失效
     * @param id
     * @return
     */
    int updUsageStateById(Long id, Long usageState);

    /**
     * 用户查询优惠卷列表
     * @param usageState
     * @return
     */
    PageInfoModel<List<CouponAndReceiveModel>> findMcCouponByMemberIdList(Integer usageState, Integer page, Integer size);


    /**
     * 根据编号查询详细信息
     * @param receiveId
     * @return
     */
    CouponAndReceiveModel<McCouponOtherJsonModel> findMcCouponByReceiveId(Long receiveId);
}
