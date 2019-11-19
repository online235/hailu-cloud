package com.hailu.cloud.api.admin.module.system.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.system.dao.AdminMapper;
import com.hailu.cloud.api.admin.module.system.service.IAdminService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysAdminModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuzhijie
 */
@Service
public class AdminServiceImpl implements IAdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public void addAccount(SysAdminModel model) {
        model.setPwd(SecureUtil.md5(model.getPwd()));
        adminMapper.addAccount(model);
    }

    @Override
    public void changePwd(String oldPwd, String newPwd) throws BusinessException {
        AdminLoginInfoModel infoModel = RequestUtils.getAdminLoginInfo();
        String oldPwdMd5 = SecureUtil.md5(oldPwd);
        if (infoModel.getPwd().equals(oldPwdMd5)) {
            throw new BusinessException("原密码不正确");
        }
        String newPwdMd5 = SecureUtil.md5(newPwd);
        adminMapper.changePwd(infoModel.getId(), newPwdMd5, infoModel.getAccount());
    }

    @Override
    public void changePwdByAdmin(Long id, String newPwd) {
        AdminLoginInfoModel infoModel = RequestUtils.getAdminLoginInfo();
        String newPwdMd5 = SecureUtil.md5(newPwd);
        adminMapper.changePwd(id, newPwdMd5, infoModel.getAccount());
    }

    @Override
    public SysAdminModel searchAccount(String account) {
        return adminMapper.searchAccount(account);
    }

    @Override
    public PageInfoModel<List<SysAdminModel>> accountList(
            String nickName,
            String account,
            Integer status,
            int pageNum,
            int pageSize) {

        Page page = PageHelper.startPage(pageNum, pageSize);
        List<SysAdminModel> datas = adminMapper.accountList(nickName, account, status);
        return new PageInfoModel<>(page.getPages(), datas);
    }

    @Override
    public void changeStatus(Long id, int status) {
        AdminLoginInfoModel infoModel = RequestUtils.getAdminLoginInfo();
        adminMapper.changeStatus(id, status, infoModel.getAccount());
    }

    @Override
    public void changeRoles(Long id, Long[] roleIds) {
        adminMapper.unlinkRoles(id);
        adminMapper.linkRoles(id, roleIds);
    }
}
