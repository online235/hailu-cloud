package com.hailu.cloud.api.mall.module.user.service.impl;

import com.hailu.cloud.api.mall.module.user.dao.UserInfoMapper;
import com.hailu.cloud.api.mall.module.user.service.IUserInfoService;
import com.hailu.cloud.common.entity.member.ShopMember;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.utils.RedisCacheUtils;
import com.hailu.cloud.common.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 刘柱栋
 * @Description 用户信息服务
 * @date 2016/6/9 22:02
 * @copyright Jelly.Liu. All rights reserved. Mail to liuzhudong57@gmail.com
 * @since v1.0
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Resource
    private UserInfoMapper userInfoDao;

    @Resource
    private RedisStandAloneClient redisClient;

    /**
     * 修改用户信息
     *
     * @param
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserInfo(String userIcon, String nickName, String sex) {
        MemberLoginInfoModel loginInfoModel = RequestUtils.getMemberLoginInfo();

        userInfoDao.updateUserInfo(userIcon, nickName, sex, loginInfoModel.getUserId());
        loginInfoModel.setUserIcon(userIcon);
        loginInfoModel.setMemberName(nickName);
        loginInfoModel.setMemberSex(sex);
        RedisCacheUtils.updateUserInfo(redisClient, loginInfoModel);
    }

    @Override
    public ShopMember saveWeChatUset(String userId) {
        ShopMember byIdFindUser = userInfoDao.byIdFindUser(userId);
        if (byIdFindUser != null) {
            return byIdFindUser;

        } else {
            ShopMember userinfo = new ShopMember();
            userinfo.setUserId(userId);
            userinfo.setCreateTime(System.currentTimeMillis());
            userInfoDao.saveWeChatUser(userinfo);
            return userinfo;
        }
    }

    @Override
    public ShopMember userInfoQueryByUserId(String userId) throws Exception {
        return userInfoDao.userInfoQueryByUserId(userId);
    }

    @Override
    public int findPoviderPrice(Long merchantCityId) {
        //根据城市ID获取服务商的数量
        int sumPovider = userInfoDao.countPoviderNum(merchantCityId);
        if (sumPovider >= 200) {
            return 30000;
        } else {
            return 8888;
        }
    }


    @Override
    public ShopMember findById(String userId) {
        ShopMember userInfo = userInfoDao.byIdFindUser(userId);
        if (userInfo != null) {
            return userInfo;
        }
        return null;
    }

    @Override
    public void editMerchantTypeAndSuperiorMember(String userId, int merchantType, String superiorMember, Long cityId) {
        userInfoDao.editMerchantTypeAndSuperiorMember(userId, merchantType, superiorMember, cityId);
    }
}
