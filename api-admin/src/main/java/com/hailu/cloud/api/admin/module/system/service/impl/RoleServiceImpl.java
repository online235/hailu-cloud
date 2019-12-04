package com.hailu.cloud.api.admin.module.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.system.dao.RoleMapper;
import com.hailu.cloud.api.admin.module.system.model.LkRoleMenuModel;
import com.hailu.cloud.api.admin.module.system.service.IRoleService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysRoleModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
        AdminLoginInfoModel loginInfoModel = RequestUtils.getAdminLoginInfo();
        model.setCreateBy(loginInfoModel.getAccount());
        roleMapper.addRole(model);
    }

    @Override
    public void updateRole(SysRoleModel model) throws BusinessException {
        if( model.getId() == null ){
            throw new BusinessException("角色ID不能为空");
        }
        AdminLoginInfoModel loginInfoModel = RequestUtils.getAdminLoginInfo();
        model.setUpdateBy(loginInfoModel.getAccount());
        roleMapper.updateRole(model);
    }

    @Override
    public PageInfoModel<List<SysRoleModel>> roleList(
            String roleName,
            Integer enableStatus,
            int pageNum,
            int pageSize) {

        Page page = PageHelper.startPage(pageNum, pageSize);
        List<SysRoleModel> datas = roleMapper.roleList(roleName, enableStatus);

        Map<Long, SysRoleModel> mapping = new HashMap<>(datas.size());
        datas.forEach(item-> mapping.put(item.getId(), item));
        List<LkRoleMenuModel> lk = roleMapper.findBindMenus(mapping.keySet());

        lk.forEach(item->{
            List<Long> menuIds = Arrays.stream(item.getMenuId().split(","))
                    .map(StringUtils::trim)
                    .filter(StringUtils::isNotBlank)
                    .map(Long::valueOf)
                    .collect(Collectors.toList());
            mapping.get(item.getRoleId()).setMenuIds(menuIds);
        });

        return new PageInfoModel<>(page.getPages(), page.getTotal(), datas);
    }

    @Override
    public void changeStatus(Long id, int enableStatus) {
        AdminLoginInfoModel infoModel = RequestUtils.getAdminLoginInfo();
        roleMapper.changeStatus(id, enableStatus, infoModel.getAccount());
    }

    @Override
    public void changeMenus(Long id, String menuIds) throws BusinessException {
         Set<Long> menuSet = Arrays.stream(menuIds.split(","))
                .map(StringUtils::trim)
                .filter(StringUtils::isNotBlank)
                .map(Long::valueOf)
                .collect(Collectors.toSet());
        if( menuSet.isEmpty() ){
            throw new BusinessException("请选择菜单");
        }
        roleMapper.unlinkMenus(id);
        roleMapper.linkMenus(id, menuSet);
    }
}
