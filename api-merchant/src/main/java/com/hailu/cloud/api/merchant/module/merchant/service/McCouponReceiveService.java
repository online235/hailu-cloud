package com.hailu.cloud.api.merchant.module.merchant.service;

import com.hailu.cloud.api.merchant.module.merchant.model.CouponAndReceiveModel;
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
     * 核销到店卷
     * @param awardCode
     * @return
     */
    void updUsageStateByAwardCodeAndStoreId(String awardCode) throws BusinessException;
}
