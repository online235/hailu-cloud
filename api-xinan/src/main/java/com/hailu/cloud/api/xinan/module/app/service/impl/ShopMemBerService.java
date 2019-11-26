package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.xinan.module.app.dao.ShopMemberMapper;
import com.hailu.cloud.api.xinan.module.app.entity.ShopMember;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


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
        Page pageSize = PageHelper.startPage(page,limit);
        List<ShopMember> datas = memberMapper.selectFindShopMember(membername, membermobile);
        return new PageInfoModel<>(pageSize.getPages(), pageSize.getTotal(), datas);
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
    public void updateByPrimaryKeySelective(ShopMember shopMember , HttpServletRequest request){
        MemberLoginInfoModel loginInfo = (MemberLoginInfoModel) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        shopMember.setUserId(loginInfo.getUserId());
        memberMapper.updateByPrimaryKeySelective(shopMember);
    }
}
