package com.hailu.cloud.api.admin.module.system.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.system.dao.AdminMapper;
import com.hailu.cloud.api.admin.module.system.dao.RoleMapper;
import com.hailu.cloud.api.admin.module.system.service.IAdminService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysAdminModel;
import com.hailu.cloud.common.model.system.SysRoleModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xuzhijie
 */
@Service
public class AdminServiceImpl implements IAdminService {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private RoleMapper roleMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    /**
     * 密码加密的key
     */
    @Value("${user.passwd.sign.key}")
    private String signKey;

    @Override
    public void addAccount(SysAdminModel model) throws BusinessException {
        SysAdminModel existAccount = searchAccount(model.getAccount(), 1);
        if( existAccount != null ){
            throw new BusinessException("账号已存在");
        }
        model.setId(basicFeignClient.uuid().getData());
        model.setPwd(SecureUtil.sha256(model.getPwd() + "&key=" + signKey));
        model.setEnableStatus(1);
        adminMapper.addAccount(model);
    }

    @Override
    public void changePwd(String oldPwd, String newPwd) throws BusinessException {
        AdminLoginInfoModel infoModel = RequestUtils.getAdminLoginInfo();
        String oldPwdMd5 = SecureUtil.sha256(oldPwd + "&key=" + signKey);
        if (!infoModel.getPwd().equals(oldPwdMd5)) {
            throw new BusinessException("原密码不正确");
        }
        String newPwdMd5 = SecureUtil.sha256(newPwd + "&key=" + signKey);
        adminMapper.changePwd(infoModel.getId(), newPwdMd5, infoModel.getAccount());
    }

    @Override
    public void changePwdByAdmin(Long id, String newPwd) {
        AdminLoginInfoModel infoModel = RequestUtils.getAdminLoginInfo();
        String newPwdMd5 = SecureUtil.sha256(newPwd + "&key=" + signKey);
        adminMapper.changePwd(id, newPwdMd5, infoModel.getAccount());
    }

    @Override
    public SysAdminModel searchAccount(String account, int enableStatus) {
        return adminMapper.searchAccount(account, enableStatus);
    }

    @Override
    public PageInfoModel<List<SysAdminModel>> accountList(
            String nickName,
            String account,
            Integer enableStatus,
            Integer accountType,
            int pageNum,
            int pageSize) {

        Page page = PageHelper.startPage(pageNum, pageSize);
        List<SysAdminModel> datas = adminMapper.accountList(nickName, account, enableStatus, accountType);

        Map<Long, SysAdminModel> mapping = new HashMap<>(datas.size());
        datas.forEach(admin -> mapping.put(admin.getId(), admin));
        roleMapper.adminRoleList(mapping.keySet()).forEach(role->{
            SysRoleModel sysAdminModel = new SysRoleModel();
            sysAdminModel.setId(role.getRoleId());
            sysAdminModel.setRoleName(role.getRoleName());
            mapping.get(role.getAdminId()).getRoles().add(sysAdminModel);
        });
        return new PageInfoModel<>(page.getPages(), page.getTotal(), datas);
    }

    @Override
    public void changeStatus(Long id, int enableStatus) throws BusinessException {
        if( enableStatus == 1 ){
            SysAdminModel existsAccount = adminMapper.checkAccountIsRepeat(id);
            if( existsAccount != null ){
                if( existsAccount.getId().compareTo(id) == 0 ){
                    return;
                }
                throw new BusinessException("系统中已存在一个相同的账号，并且该账号目前为启用状态");
            }
        }
        AdminLoginInfoModel infoModel = RequestUtils.getAdminLoginInfo();
        adminMapper.changeStatus(id, enableStatus, infoModel.getAccount());
    }

    @Override
    public void changeRoles(Long id, String roleIds) throws BusinessException {
        Set<Long> roleSet = Arrays.stream(roleIds.split(","))
                .map(StringUtils::trim)
                .filter(StringUtils::isNotBlank)
                .map(Long::valueOf)
                .collect(Collectors.toSet());
        if( roleSet.isEmpty() ){
            throw new BusinessException("请选择角色");
        }
        adminMapper.unlinkRoles(id);
        adminMapper.linkRoles(id, roleSet);
    }
}
