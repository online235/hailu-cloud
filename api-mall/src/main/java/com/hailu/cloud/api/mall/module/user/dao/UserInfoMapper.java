package com.hailu.cloud.api.mall.module.user.dao;

import com.hailu.cloud.api.mall.module.user.vo.UserInfoVo;
import com.hailu.cloud.common.entity.member.ShopMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 刘柱栋
 * @Description 用户信息数据操作接口
 * @date 2016/6/9 21:49
 * @copyright Jelly.Liu. All rights reserved. Mail to liuzhudong57@gmail.com
 * @since v1.0
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 修改个人信息
     *
     * @return
     */
    int updateUserInfo(
            @Param("userIcon") String userIcon,
            @Param("nickName") String nickName,
            @Param("sex") String sex,
            @Param("userId") String userId);

    int saveWeChatUser(ShopMember user);

    ShopMember byIdFindUser(String userId);

    /**
     * 铜鼓userid获取用户信息
     */
    UserInfoVo userInfoQueryByUserId(String userId) throws Exception;

    /**
     * 根据城市地址获取到该城市服务商的人数
     *
     * @param cityId
     * @return
     */
    int countPoviderNum(@Param("cityId") Long cityId);

    /**
     * 更改商户类型和归属经销商
     *
     * @param userId
     * @param merchantType
     * @param superiorMember
     */
    void editMerchantTypeAndSuperiorMember(@Param("userId") String userId, @Param("merchantType") int merchantType, @Param("superiorMember") String superiorMember, @Param("cityId") Long cityId);
}
