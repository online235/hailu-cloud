package com.hailu.cloud.api.admin.module.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.system.dao.RoleMapper;
import com.hailu.cloud.api.admin.module.system.service.IRoleService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysRoleModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuzhijie
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Override
    public void addRole(SysRoleModel model) {
        model.setId(basicFeignClient.uuid().getData());
        roleMapper.addRole(model);
    }

    @Override
    public PageInfoModel<List<SysRoleModel>> roleList(
            String roleName,
            Integer enableStatus,
            int pageNum,
            int pageSize) {

        Page page = PageHelper.startPage(pageNum, pageSize);
        List<SysRoleModel> datas = roleMapper.roleList(roleName, enableStatus);
        return new PageInfoModel<>(page.getPages(), page.getTotal(), datas);
    }

    @Override
    public void changeStatus(Long id, int enableStatus) {
        AdminLoginInfoModel infoModel = RequestUtils.getAdminLoginInfo();
        roleMapper.changeStatus(id, enableStatus, infoModel.getAccount());
    }

    @Override
    public void changeMenus(Long id, Long[] menuIds) {
        roleMapper.unlinkMenus(id);
        roleMapper.linkMenus(id, menuIds);
    }
}
