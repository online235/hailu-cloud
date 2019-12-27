package com.hailu.cloud.api.admin.module.xinan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.xinan.dao.ShopMemberMapper;
import com.hailu.cloud.common.entity.member.ShopMember;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


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
        Page pageData = PageHelper.startPage(page,limit);
        List<ShopMember> query = memberMapper.selectFindShopMember(membername, membermobile);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), query);
    }

    /**
     * 根据userId拿到用户信息
     *
     * @param userId
     * @return
     */
    public ShopMember selectByPrimaryKey(String userId) {
            return memberMapper.selectByPrimaryKey(userId);
    }

    /**
     * 更改用户信息
     * @param shopMember
     */
    public void updateByPrimaryKeySelective(ShopMember shopMember){
        memberMapper.updateByPrimaryKeySelective(shopMember);
    }
}
