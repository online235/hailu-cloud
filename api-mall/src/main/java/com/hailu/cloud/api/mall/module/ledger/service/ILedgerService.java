package com.hailu.cloud.api.mall.module.ledger.service;

import com.hailu.cloud.api.mall.module.user.entity.UserInfo;

import java.math.BigDecimal;

/**
 * 分销处理
 * @Author junpei.deng
 * @Date 2019/11/03 14:52
 */
public interface ILedgerService {

    /**
     * 处理购买海露会员邀请人购买成功得到分红
     * @param parentId
     */
    void editInvitation(String parentId);

    void editInvitationProvider(UserInfo parentUserInfo, BigDecimal money);

    /**
     * 分销商品
     * @param orderId
     * @param userId
     */
    void distributionGoods(Integer orderId, String userId);
}
