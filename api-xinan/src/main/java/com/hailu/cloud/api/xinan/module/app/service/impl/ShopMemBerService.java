package com.hailu.cloud.api.xinan.module.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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
     * @param shopMember
     */
    public void updateByPrimaryKeySelective(ShopMember shopMember){
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
     * 根据手机号注册用户
     *
     * @param phone
     * @param addtime
     * @return
     */
    public void addLitemallUser(String phone, long addtime) {
        ShopMember userInfo = new ShopMember();
        userInfo.setUserId(String.valueOf(uuidFeignClient.uuid().getData()));
        userInfo.setLoginName(phone);
        userInfo.setMemberName(phone);
        userInfo.setRegistTime(addtime);
        userInfo.setMemberMobile(phone);
        userInfo.setCreateTime(addtime);
        memberMapper.AddLitemallUser(userInfo);
    }


    /**
     * 查询是否加入海露
     * @param userId
     * @return
     */
    public int findShopMemberByUserIdAndMerchantType(String userId){
        return memberMapper.findShopMemberByUserIdAndMerchantType(userId);
    }

    /**
     * 心安注册
     * @param phone
     * @param code
     * @param insuredIds
     * @throws BusinessException
     */
    public void register(String phone,String code,String insuredIds) throws BusinessException {
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
        int result = findShopMemberByUserIdAndMerchantType(insuredIds);
        if (result > 0){
            MemberLoginInfoModel model = RequestUtils.getMemberLoginInfo();
            redisStandAloneClient.stringSet(RedisEnum.DB_2.ordinal(),Constant.REDIS_INVITATION_MEMBER_POVIDER_CACHE+model.getUserId(),insuredIds,0);
        }

        // 注册账号
        addLitemallUser(phone,System.currentTimeMillis());
    }

}
