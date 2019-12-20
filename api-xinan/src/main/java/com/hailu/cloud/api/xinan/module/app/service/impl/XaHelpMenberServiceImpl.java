package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.xinan.module.app.dao.XaHelpMemberMapper;
import com.hailu.cloud.api.xinan.module.app.entity.XaHelpMember;
import com.hailu.cloud.api.xinan.module.app.model.XaHelpMemberModel;
import com.hailu.cloud.api.xinan.module.app.service.XaHelpMenberService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class XaHelpMenberServiceImpl implements XaHelpMenberService {

    @Autowired
    private XaHelpMemberMapper xaHelpMemberMapper;
    @Autowired
    private BasicFeignClient uuidFeign;


    @Override
    public List<XaHelpMemberModel> findListByParameter(Object parameter) {
        return xaHelpMemberMapper.findListByParameter(parameter);
    }

    /**
     * 查询信息列表分页
     */
    @Override
    public PageInfoModel<List<XaHelpMemberModel>> findListByParameterNewPage(Integer pageNum, Integer size, Object parameter) {

        Page pageData = PageHelper.startPage(pageNum, size);
        List<XaHelpMemberModel> result = xaHelpMemberMapper.findListByParameter(parameter);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), result);
    }


    @Override
    public XaHelpMemberModel findXaHelpMemberModelById(Long id) {
        return xaHelpMemberMapper.findXaHelpMemberModelById(id);
    }


    @Override
    public Long insert(XaHelpMember xaHelpMember) {

        MerchantUserLoginInfoModel merchantUserLoginInfoModel = RequestUtils.getMerchantUserLoginInfo();
        xaHelpMember.setId(uuidFeign.uuid().getData());
        xaHelpMember.setCreateTime(new Date());
        xaHelpMember.setUpdateTime(new Date());
        xaHelpMember.setMenberId(Long.valueOf(merchantUserLoginInfoModel.getNumberid()));
        xaHelpMemberMapper.insert(xaHelpMember);
        return xaHelpMember.getId();
    }

    @Override
    public int update(XaHelpMember xaHelpMember) {

        xaHelpMember.setUpdateTime(new Date());
        return xaHelpMemberMapper.update(xaHelpMember);
    }



}
