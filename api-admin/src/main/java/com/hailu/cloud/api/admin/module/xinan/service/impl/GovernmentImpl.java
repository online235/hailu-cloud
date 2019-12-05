package com.hailu.cloud.api.admin.module.xinan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.xinan.dao.GovernmentMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.CharitableCommonweal;
import com.hailu.cloud.api.admin.module.xinan.entity.Government;
import com.hailu.cloud.api.admin.module.xinan.service.GovernmentService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
@Service
public class GovernmentImpl implements GovernmentService {

    @Resource
    private GovernmentMapper governmentUsersMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Override
    public Government findGovernment() {
        AdminLoginInfoModel adminLoginInfoModel = RequestUtils.getAdminLoginInfo();
        return governmentUsersMapper.selectByPrimaryKey(adminLoginInfoModel.getId());
    }

    @Override
    public Government insertSelective(String commonwealArticle) throws BusinessException {
        AdminLoginInfoModel adminLoginInfoModel = RequestUtils.getAdminLoginInfo();
        if (findGovernment() != null){
            throw new BusinessException("请不要重复添加文章");
        }
        Government record = new Government();
        record.setId(basicFeignClient.uuid().getData());
        record.setAdminId(adminLoginInfoModel.getId());
        record.setCommonwealArticle(commonwealArticle);
        record.setCratedat(new Date());
        record.setUpdatedat(new Date());
        governmentUsersMapper.insertSelective(record);
        return record;
    }

    @Override
    public Government updateByPrimaryKeySelective(String commonwealArticle) {
        AdminLoginInfoModel adminLoginInfoModel = RequestUtils.getAdminLoginInfo();
        Government record = new Government();
        record.setAdminId(adminLoginInfoModel.getId());
        record.setCommonwealArticle(commonwealArticle);
        record.setUpdatedat(new Date());
        governmentUsersMapper.updateByPrimaryKeySelective(record);
        return record;
    }

    @Override
    public PageInfoModel<List<Government>> findGovernmentList(Integer page, Integer size) {
        Page pageData = PageHelper.startPage(page,size);
        List<Government> orders = governmentUsersMapper.findGovernmentList();
        return new PageInfoModel(pageData.getPages(), pageData.getTotal(), orders);
    }
}
