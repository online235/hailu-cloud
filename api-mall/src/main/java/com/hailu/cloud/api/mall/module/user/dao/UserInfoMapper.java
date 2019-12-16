package com.hailu.cloud.api.mall.module.user.dao;

import com.hailu.cloud.api.mall.module.user.entity.InterestsIcon;
import com.hailu.cloud.api.mall.module.user.entity.UserInfo;
import com.hailu.cloud.api.mall.module.user.vo.RealNameVo;
import com.hailu.cloud.api.mall.module.user.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

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
     * 用户登录查询
     *
     * @param account
     * @param loginPwd
     * @return
     * @throws Exception
     */
    UserInfoVo userLoginQuery(@Param("account") String account, @Param("loginPwd") String loginPwd) throws Exception;

    /**
     * 验证帐号是否可注册
     *
     * @param account
     * @return
     * @throws Exception
     */
    Integer verifyAccountState(@Param("account") String account) throws Exception;

    /**
     * 注册
     *
     * @param userInfoVo
     * @return
     */
    void addMember(UserInfo userInfoVo) throws Exception;

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    UserInfoVo getUser(String userId) throws Exception;

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


    /**
     * 实名认证
     *
     * @param realNameVo
     * @throws Exception
     */
    void realName(RealNameVo realNameVo) throws Exception;

    /**
     * 获取实名认证信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    RealNameVo getRealName(String userId) throws Exception;

    /**
     * 修改密码
     *
     * @param userInfo
     * @return
     */
    int updatePwd(UserInfo userInfo);


    /**
     * 邀请码是否有效
     *
     * @return
     * @throws Exception
     */
    UserInfo isExistInviteCode(String inviteCode) throws Exception;


    String getPhone(String userId) throws Exception;

    int saveWeChatUser(UserInfo user);


    UserInfo byIdFindUser(String userId);

    /**
     * 铜鼓userid获取用户信息
     */
    UserInfoVo userInfoQueryByUserId(String userId) throws Exception;


    /**
     * 铜鼓unionid获取用户信息
     */
    UserInfoVo userInfoQueryByUnionid(String unionid) throws Exception;

    /**
     * unionid鱼手机号绑定
     *
     * @param userInfoVo
     * @throws Exception
     */
    void upUserInfoByPhone(UserInfoVo userInfoVo) throws Exception;

    /**
     * 获取用户支付密码
     *
     * @param userId
     * @return
     * @throws Exception
     * @Author huangl
     */
    String getPayPwd(String userId) throws Exception;


    /**
     * 设置 修改  支付密码
     *
     * @param userId
     * @param payPwd
     * @throws Exception
     * @Author huangl
     */
    void updatePayPwd(@Param("userId") String userId, @Param("payPwd") String payPwd) throws Exception;


    /**
     * 根据手机号获取实名认证信息
     *
     * @param userId
     * @param userId
     * @return
     * @throws Exception
     * @Author huangl
     */
    RealNameVo getRealNameByAccount(@Param("account") String account, @Param("userId") String userId) throws Exception;


    /**
     * 得到用户余额
     *
     * @param userId
     * @return
     * @Author huangl
     */
    Double getAvailablePredeposit(String userId);

    /**
     * 更新用户余额
     *
     * @param available
     */
    void updateAvailablePredeposit(@Param("available") Double available, @Param("userId") String userId);

    /**
     * 增加记录
     *
     * @param userId
     * @param string
     * @Author huangl
     */
    void addRecord(@Param("userId") String userId, @Param("string") String string);

    /**
     * 得到记录
     *
     * @param userId
     * @return
     * @Author huangl
     */
    String getRecordTo(String userId);

    int getRedPacketByUserId(String userId, int redPacketId, Long time);

    /**
     * 更改记录
     *
     * @param userId
     * @param string
     * @Author huangl
     */
    void updateRecord(@Param("userId") String userId, @Param("string") String string);




    /**
     * @Author WangTao
     * @Date 14:19 2018/3/6 0006
     * @param: TODO 说明:更新用户登录设备
     **/
    int updateUserCid(UserInfoVo user);



    int addUserRedPacket(String userId, int redPacketId, long time, Double balance, String userMobile);

    int WXunbundle(String phone);

    /**
     * @Author WangTao
     * @Date 18:10 2018/3/10 0010
     * @param: TODO 说明:更新用户手机号
     **/
    int updateUserPhone(String phone, String userId);


    /**
     * 绑定微信账号
     *
     * @param unionid
     * @param userId
     * @param openId
     * @param state
     */
    void updateWeCartBind(@Param("unionid") String unionid, @Param("userId") String userId, @Param("openId") String openId, @Param("state") String state);

    int findUnionid(String unionid);

    int findPhone(String phone);

    UserInfo getUserInfoVo(String account);

    /**
     * @Author HuangL
     * @Description 更改账户余额
     * @Date 2018-10-11_11:58
     */
    void updateAddBalance(@Param("amount") Double amount, @Param("userId") String userId);

    UserInfoVo findByAccount(String account);

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
     * 得到所有的会员权益
     *
     * @author HuangL
     * @email huangl96@163.com
     * @date 10:05 AM 9/6/2019
     */
    List<InterestsIcon> findInterestsIcons();


    /**
     * 更改商户的会员状态
     * @param userId
     */
    void updateMerchantMoney(String userId, int hlMember);

    /**
     * 更改会员状态和生成会员卡号
     * @param userId
     * @param status
     * @param memberCard
     */
    void editHlMember(@Param("userId") String userId, @Param("status") int status, @Param("memberCard") String memberCard, @Param("timeOut") Date timeOut);

    /**
     * 根据城市地址获取到该城市服务商的人数
     * @param cityId
     * @return
     */
    int countPoviderNum(@Param("cityId") Long cityId);

    /**
     * 更改商户类型和归属经销商
     * @param userId
     * @param merchantType
     * @param superiorMember
     */
    void editMerchantTypeAndSuperiorMember(@Param("userId") String userId, @Param("merchantType") int merchantType, @Param("superiorMember") String superiorMember, @Param("cityId") Long cityId);
}
