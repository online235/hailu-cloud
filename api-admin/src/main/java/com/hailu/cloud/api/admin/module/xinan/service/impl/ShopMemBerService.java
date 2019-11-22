package com.hailu.cloud.api.admin.module.xinan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hailu.cloud.api.admin.module.xinan.dao.ShopMemberMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.ShopMember;
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

    /**
     * 用户列表
     *
     * @return
     */
    public Object selectFindShopMember(String membername, String membermobile, Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
        PageInfo pageInfo = new PageInfo(memberMapper.selectFindShopMember(membername, membermobile));
        return pageInfo;
    }

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
        memberMapper.updateByPrimaryKeySelective(shopMember);
    }
}