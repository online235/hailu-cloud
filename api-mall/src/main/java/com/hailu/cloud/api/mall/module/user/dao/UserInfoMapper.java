package com.hailu.cloud.api.mall.module.user.dao;

import com.hailu.cloud.api.mall.module.user.entity.UserInfo;
import com.hailu.cloud.api.mall.module.user.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

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

    int saveWeChatUser(UserInfo user);

    UserInfo byIdFindUser(String userId);

    /**
     * 铜鼓userid获取用户信息
     */
    UserInfoVo userInfoQueryByUserId(String userId) throws Exception;

    /**
     * @Author HuangL
     * @Description 添加用户使用记录
     * @Date 2018-10-25_17:10
     */
    void saveUseRecord(@Param("sessionID") String sessionID);

    /**
     * @Author HuangL
     * @Description 得到是否已有
     * @Date 2018-10-25_18:01
     */
    int findUseRecord(String sessionID);

    /**
     * 更改商户的会员状态
     *
     * @param userId
     */
    void updateMerchantMoney(String userId, int hlMember);

    /**
     * 更改会员状态和生成会员卡号
     *
     * @param userId
     * @param status
     * @param memberCard
     */
    void editHlMember(@Param("userId") String userId, @Param("status") int status, @Param("memberCard") String memberCard, @Param("timeOut") Date timeOut);

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
