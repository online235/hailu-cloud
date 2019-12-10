package com.hailu.cloud.api.xinan.module.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.crypto.SecureUtil;
import com.hailu.cloud.api.xinan.module.app.dao.ShopMemberMapper;
import com.hailu.cloud.api.xinan.module.app.entity.ShopMember;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.redis.enums.RedisEnum;
import com.hailu.cloud.common.utils.RedisCacheUtils;
import com.hailu.cloud.common.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @Author: QiuFeng:WANG
 * @Description: 心安用户操作
 * @Date: 16:33 2019/11/2 0002
 */
@Service
public class ShopMemBerService {

    @Resource
    private ShopMemberMapper memberMapper;

    @Autowired
    private BasicFeignClient uuidFeignClient;

    @Autowired
    private RedisStandAloneClient redisStandAloneClient;


    /**
     * 密码加密的key
     */
    @Value("${user.passwd.sign.key}")
    private String signKey;


    /**
     * 根据memberid拿到用户信息
     *
     * @param memberid
     * @return
     */
    public Object selectByPrimaryKey(String memberid) {
        if (memberid != null) {
            return memberMapper.selectByPrimaryKey(memberid);
        } else {
            return null;
        }
    }

    /**
     * 更改用户信息
     *
     * @param shopMember
     */
    public void updateByPrimaryKeySelective(ShopMember shopMember) {
        MemberLoginInfoModel loginInfo = RequestUtils.getMemberLoginInfo();
        shopMember.setUserId(loginInfo.getUserId());
        memberMapper.updateByPrimaryKeySelective(shopMember);
        BeanUtil.copyProperties(shopMember, loginInfo, CopyOptions.create().ignoreNullValue());
        RedisCacheUtils.updateUserInfo(redisStandAloneClient, loginInfo);
    }

    public boolean exists(String phone) {
        int result = memberMapper.selectLitemallByPhone(phone);
        return result > 0 ? true : false;
    }

    /**
     * 根据手机号或者密码注册用户
     *
     * @param phone
     * @param addtime
     * @return
     */
    public ShopMember addLitemallUser(String phone, long addtime, String password) {

        ShopMember userInfo = new ShopMember();
        userInfo.setUserId(String.valueOf(uuidFeignClient.uuid().getData()));
        userInfo.setMemberId(uuidFeignClient.uuid().getData());
        userInfo.setLoginName(phone);
        userInfo.setMemberName(phone);
        userInfo.setRegistTime(addtime);
        userInfo.setMemberMobile(phone);
        userInfo.setCreateTime(addtime);
        if (StringUtils.isBlank(password)) {
            userInfo.setMemberPasswd(SecureUtil.sha256(password + "&key=" + signKey));
        }
        memberMapper.AddShopMember(userInfo);
        return userInfo;
    }


    /**
     * 微信绑定账号,保存操作
     * @param phone
     * @param addtime
     * @return
     */
    public ShopMember addLitemallWetchatUser(String phone, long addtime, MemberLoginInfoModel memberLoginInfoModel) {

        ShopMember userInfo = new ShopMember();
        userInfo.setUserId(String.valueOf(uuidFeignClient.uuid().getData()));
        userInfo.setMemberId(uuidFeignClient.uuid().getData());
        userInfo.setLoginName(phone);
        userInfo.setMemberName(phone);
        userInfo.setRegistTime(addtime);
        userInfo.setMemberMobile(phone);
        userInfo.setCreateTime(addtime);
        userInfo.setCreateTime(addtime);
        userInfo.setOpenId(memberLoginInfoModel.getWeChatOpenId());
        userInfo.setUnionid(memberLoginInfoModel.getWeChatUnionId());
        userInfo.setMemberSex(memberLoginInfoModel.getMemberSex());
        userInfo.setWechat(memberLoginInfoModel.getWeChatNickname());
        userInfo.setWxState("1_app");
        memberMapper.AddShopMember(userInfo);
        return userInfo;
    }


    /**
     * 微信绑定账号，更新操作
     * @param addtime
     * @return
     */
    public ShopMember updateLitemallWetchatUser(ShopMember shopMember,long addtime, MemberLoginInfoModel memberLoginInfoModel) {

        shopMember.setUserId(String.valueOf(uuidFeignClient.uuid().getData()));
        shopMember.setMemberId(uuidFeignClient.uuid().getData());
        shopMember.setOpenId(memberLoginInfoModel.getWeChatOpenId());
        shopMember.setUnionid(memberLoginInfoModel.getWeChatUnionId());
        shopMember.setWechat(memberLoginInfoModel.getWeChatNickname());
        shopMember.setWxState("1_app");
        shopMember.setMemberLoginTime(addtime);
        memberMapper.updateByPrimaryKeySelective(shopMember);
        return shopMember;
    }


    /**
     * 查询是否加入海露
     *
     * @param userId
     * @return
     */
    public int findShopMemberByUserIdAndMerchantType(String userId) {
        return memberMapper.findShopMemberByUserIdAndMerchantType(userId);
    }


    /**
     * 心安注册(有密码)
     *
     * @param phone
     * @param code
     * @param insuredIds
     * @throws BusinessException
     */
    public void registerAndPassword(String phone, String code, String insuredIds, String password) throws BusinessException {
        boolean exists = exists(phone);
        if (exists) {
            // 账号已存在
            throw new BusinessException("账号已存在");
        }
        // 万能验证码，前期测试时使用
        if (!code.equals("111111")) {
            String veriCode = redisStandAloneClient.stringGet(Constant.REDIS_KEY_VERIFICATION_CODE + phone + 0);

            if (!code.equals(veriCode)) {
                // 验证码不正确
                throw new BusinessException("无效验证码");
            }
        }
        // 注册账号
        ShopMember ShopMember = addLitemallUser(phone, System.currentTimeMillis(), password);
        if (insuredIds != null) {
            int result = findShopMemberByUserIdAndMerchantType(insuredIds);
            if (result > 0) {
                redisStandAloneClient.stringSet(RedisEnum.DB_2.ordinal(), Constant.REDIS_INVITATION_MEMBER_POVIDER_CACHE + ShopMember.getUserId(), insuredIds, 0);
            }
        }

    }


    /**
     * 微信验证手机号码
     */
    public MemberLoginInfoModel verification(String phone, String code, MemberLoginInfoModel memberLoginInfoModel) throws BusinessException {

        // 万能验证码，前期测试时使用
        if (!code.equals("111111")) {
            String veriCode = redisStandAloneClient.stringGet(Constant.REDIS_KEY_VERIFICATION_CODE + phone + 0);
            if (!code.equals(veriCode)) {
                // 验证码不正确
                throw new BusinessException("无效验证码");
            }
        }
        ShopMember shopMember = this.findObjectByPhone(phone);
        memberLoginInfoModel.setHasPwd(false);
        if (shopMember != null) {
            // 账号已存在
            if (!StringUtils.isBlank(shopMember.getMemberPasswd())) {
                memberLoginInfoModel.setHasPwd(true);
            }
            //更新操作绑定微信
            shopMember = updateLitemallWetchatUser(shopMember,System.currentTimeMillis(),memberLoginInfoModel);
        } else {
            //账号不存在，注册账号绑定微信
            shopMember = addLitemallWetchatUser(phone, System.currentTimeMillis(), memberLoginInfoModel);
        }
        memberLoginInfoModel.setMemberMobile(phone);
        memberLoginInfoModel.setUserId(shopMember.getUserId());
        memberLoginInfoModel.setWeChatRefreshToken(null);
        memberLoginInfoModel.setWeChatAccessToken(null);
        memberLoginInfoModel.setWeChatExpiresIn(null);
        memberLoginInfoModel.setWeChatOpenId(null);
        memberLoginInfoModel.setWeChatUnionId(null);
        memberLoginInfoModel.setPwd(null);
        BeanUtil.copyProperties(shopMember, memberLoginInfoModel, CopyOptions.create().ignoreNullValue());
        RedisCacheUtils.updateUserInfo(redisStandAloneClient, memberLoginInfoModel);
        return memberLoginInfoModel;

    }


    /**
     * 用户字符id获取用户
     */
    public ShopMember selectByPrimaryByuserId(String userId){
        return memberMapper.selectByPrimaryByuserId(userId);
    }


    /**
     * 心安微信登录设置密码
     *
     */
    public MemberLoginInfoModel wetChatUpdatePassword(String password,MemberLoginInfoModel memberLoginInfoModel){

        ShopMember shopMember = this.selectByPrimaryByuserId(memberLoginInfoModel.getUserId());
        // 账号加入密码
        shopMember.setMemberPasswd(SecureUtil.sha256(password + "&key=" + signKey));
        //更新密码
        memberMapper.updateByPrimaryKeySelective(shopMember);
        RedisCacheUtils.updateUserInfo(redisStandAloneClient, memberLoginInfoModel);
        return memberLoginInfoModel;

    }


    /**
     * 心安忘记密码
     *
     * @param phone
     * @param code
     * @throws BusinessException
     */
    public void updatePassword(String phone, String code, String password) throws BusinessException {

        ShopMember shopMember = this.findObjectByPhone(phone);
        if (shopMember == null) {
            // 账号已存在
            throw new BusinessException("手机号码不存在，重新登录");
        }
        // 万能验证码，前期测试时使用
        if (!code.equals("111111")) {
            String veriCode = redisStandAloneClient.stringGet(Constant.REDIS_KEY_VERIFICATION_CODE + phone + 0);

            if (!code.equals(veriCode)) {
                // 验证码不正确
                throw new BusinessException("无效验证码");
            }
        }
        // 账号加入密码
        shopMember.setMemberPasswd(SecureUtil.sha256(password + "&key=" + signKey));
        //更新密码
        memberMapper.updateByPrimaryKeySelective(shopMember);
    }


    /**
     * 手机号码查询数据
     *
     * @param phone
     * @return
     */
    public ShopMember findObjectByPhone(String phone) {
        return memberMapper.findObjectByPhone(phone);
    }

}
