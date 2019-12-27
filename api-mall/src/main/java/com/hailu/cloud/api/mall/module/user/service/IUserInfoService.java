package com.hailu.cloud.api.mall.module.user.service;

import com.hailu.cloud.common.entity.member.ShopMember;

/**
 * @author 刘柱栋
 * @Description 用户信息服务
 * @date 2016/6/9 22:00
 * @copyright Jelly.Liu. All rights reserved. Mail to liuzhudong57@gmail.com
 * @since v1.0
 */
public interface IUserInfoService {

    /**
     * 修改个人信息
     *
     * @param userIcon
     * @param nickName
     * @param sex
     * @return
     */
    void updateUserInfo(String userIcon, String nickName, String sex) throws Exception;

    ShopMember saveWeChatUset(String userId);

    /**
     * 通过userid获取用户信息
     * @return
     */
    ShopMember userInfoQueryByUserId(String userId) throws Exception;

    /**
     * 获取该城市加入服务商需要金额
     * @param merchantCityId
     * @return
     */
    int findPoviderPrice(Long merchantCityId);

    /**
     * 根据UserId获取信息
     * @param userId
     * @return
     */
    ShopMember findById(String userId);

    void editMerchantTypeAndSuperiorMember(String userId,int merchantType,  String superiorMember,Long cityId);
}
