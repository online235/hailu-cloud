package com.hailu.cloud.api.mall.module.user.service;

import com.hailu.cloud.api.mall.module.user.entity.UserInfo;
import com.hailu.cloud.api.mall.module.user.vo.RealNameVo;
import com.hailu.cloud.api.mall.module.user.vo.UserInfoVo;
import com.hailu.cloud.common.exception.BusinessException;

import java.util.Date;

/**
 * @author 刘柱栋
 * @Description 用户信息服务
 * @date 2016/6/9 22:00
 * @copyright Jelly.Liu. All rights reserved. Mail to liuzhudong57@gmail.com
 * @since v1.0
 */
public interface IUserInfoService {

    /**
     * 验证帐号是否可注册
     *
     * @param account 账号
     * @return
     * @throws Exception
     */
    Boolean verifyAccountState(String account) throws Exception;

    /**
     * 注册
     *
     * @param account
     * @param loginPwd
     * @param cid
     * @param systemType
     * @param openId
     * @param state
     * @return
     */
    UserInfoVo register(String account, String loginPwd, String inviteCode, String unionid,
                        String userIcon, String nickName, String memberSex, String cid, Integer systemType, String sourceRegistration, String openId, String state) throws Exception;

    /**
     * 验证smsCode
     *
     * @param account
     * @param smsCode
     * @return
     */
    UserInfoVo verifySmsCode(String account, String smsCode) throws Exception;

    /**
     * 修改个人信息
     *
     * @param userInfoVo
     * @return
     */
    Boolean updateUserInfo(UserInfoVo userInfoVo) throws Exception;

    /**
     * 修改个人密码
     *
     * @param loginName
     * @param oldPwd
     * @param newPwd
     * @return
     */
    Boolean updatePwd(String userId, String loginName, String oldPwd, String newPwd) throws Exception;

    /**
     * 忘记密码
     *
     * @param loginName
     * @param newPwd
     * @return
     */
    Boolean forgetPwd(String loginName, String newPwd) throws Exception;


    UserInfo saveWeChatUset(String userId);

    /**
     * 实名认证
     *
     * @param realNameVo
     * @throws Exception
     */
    public void realName(RealNameVo realNameVo) throws Exception;

    /**
     * 获取实名认证信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    RealNameVo getRealName(String userId) throws Exception;

    /**
     * 通过userid获取用户信息
     */
    UserInfoVo userInfoQueryByUserId(String userId) throws Exception;


    /**
     * 通过unionid获取用户信息
     */
    UserInfoVo userInfoQueryByUnionid(String unionid) throws Exception;

    /**
     * unionid鱼手机号绑定
     *
     * @param userInfoVo
     * @throws Exception
     */
    public void upUserInfoByPhone(UserInfoVo userInfoVo) throws Exception;

    /**
     * 设置支付密码
     *
     * @param userId
     * @param payPwd
     * @param type
     * @return
     * @throws Exception
     */
    void setPayPwd(String userId, String payPwd, int type) throws Exception;

    /**
     * 修改支付密码
     *
     * @param userId
     * @param payPwd
     * @return
     * @throws Exception
     */
    void updatePayPwd(String userId, String payPwd, String newPayPwd) throws Exception;

    /**
     * 第一次设置支付密码,验证是否可以设置支付密码
     *
     * @param account
     * @param smsCode
     * @param name
     * @param cardId
     * @return
     */
    Boolean verifyIsSetPwd(String account, String smsCode, String name, String cardId) throws BusinessException;


    /**
     * 验证是否可以修改
     *
     * @param userId
     * @param payPwd
     * @param name
     * @param cardId
     * @return
     */
    Boolean verifyIsAlterPwd(String userId, String payPwd, String name, String cardId) throws BusinessException;

    Boolean WXunbundle(String phone);

    Integer verifyAccountIfUpdate(String phone, String msg);

    /**
     * @Author WangTao
     * @Date 18:04 2018/3/10 0010
     * @param: TODO 说明:用户修改手机号码
     **/
    Integer updateUserPhone(String phone, String msg, String userId);


    /**
     * 绑定微信
     *
     * @param unionid
     * @param userId
     * @param openId
     * @param state
     * @return
     * @Author huangl
     */
    Boolean updateWeCartBind(String unionid, String userId, String openId, String state) throws BusinessException;


    int findUnionid(String unionid);

    /**
     * 通过手机号码查找对象
     **/
    UserInfo getUserInfoVo(String account);

    /**
     * @Author HuangL
     * @Description 更改充值订单
     * @Date 2018-10-11_14:36
     */
    void updateAddBalance(double rechargeBalance, String userId);
    /**
     * 注册
     *
     * @param account
     * @param loginPwd
     * @param systemType
     * @param cid
     * @return
     */
    UserInfoVo shareRegister(String account, String loginPwd, String QRcode, String unionid, String inviteCode,
                             String userIcon, String nickName, String memberSex, String cid, Integer systemType, String sourceRegistration) throws Exception;

    /**
     * 更改海露会员状态
     * @param userId
     * @param status
     */
    void editHlMember(String userId, int status, String memberCard, Date timeOut);

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
    UserInfo findById(String userId);

    void editMerchantTypeAndSuperiorMember(String userId,int merchantType,  String superiorMember,Long cityId);
}
