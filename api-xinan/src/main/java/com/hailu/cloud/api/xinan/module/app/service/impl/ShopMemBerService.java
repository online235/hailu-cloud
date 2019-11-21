package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hailu.cloud.api.xinan.module.app.dao.ShopMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: QiuFeng:WANG
 * @Description: 心安用户操作
 * @Date: 16:33 2019/11/2 0002
 */
@Service
public class ShopMemBerService {

    @Autowired
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
}
