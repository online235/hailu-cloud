package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.hailu.cloud.api.xinan.module.app.dao.ShopMemberMapper;
import com.hailu.cloud.api.xinan.module.app.entity.ShopMember;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: QiuFeng:WANG
 * @Description: 心安用户操作
 * @Date: 16:33 2019/11/2 0002
 */
@Service
public class XinAnShopMemBerService {

    @Resource
    private ShopMemberMapper memberMapper;

    @Autowired
    private BasicFeignClient uuidFeignClient;


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
    public void AddLitemallUser(String phone, long addtime) {
        ShopMember userInfo = new ShopMember();
        userInfo.setUserId(String.valueOf(uuidFeignClient.uuid()));
        userInfo.setLoginName(phone);
        userInfo.setMemberName(phone);
        userInfo.setRegistTime(addtime);
        userInfo.setMemberMobile(phone);
        memberMapper.AddLitemallUser(userInfo);
    }

    /**
     * 根据手机号码查询用户
     *
     * @param phone
     * @return
     */
    public ShopMember LoginPhone(String phone) {
        if (phone != null) {
            return memberMapper.LoginPhone(phone);
        }
        return null;
    }

    /**
     * 根据memberid拿到用户信息
     *
     * @param memberid
     * @return
     */
    public ShopMember selectByPrimaryKey(String memberid) {
        if (memberid != null) {
            return memberMapper.selectByPrimaryKey(memberid);
        } else {
            return null;
        }
    }

    /**
     * 根据userid拿到用户信息
     * @param userid
     * @return
     */
    public ShopMember selectByPrimaryByuserId(String userid){
        if (userid.isEmpty()){
            return null;
        }
        return memberMapper.selectByPrimaryByuserId(userid);
    }
}
