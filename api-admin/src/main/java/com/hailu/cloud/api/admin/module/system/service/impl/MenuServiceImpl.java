package com.hailu.cloud.api.admin.module.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.system.dao.MenuMapper;
import com.hailu.cloud.api.admin.module.system.service.IMenuService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysMenuModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xuzhijie
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Override
    public SysMenuModel addMenu(SysMenuModel model) {
        model.setId(basicFeignClient.uuid().getData());
        if (model.getParentId() == null) {
            model.setParentId(0L);
        }
        AdminLoginInfoModel loginInfoModel = RequestUtils.getAdminLoginInfo();
        model.setCreateBy(loginInfoModel.getAccount());
        menuMapper.addMenu(model);
        return model;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMenu(String menuIds) {
        List<Long> menuIdList = Arrays.stream(menuIds.split(","))
                .map(StringUtils::trim)
                .filter(StringUtils::isNotBlank)
                .map(Long::valueOf)
                .collect(Collectors.toList());

        menuMapper.unbindRoleMenu(menuIdList);
        menuMapper.delMenu(menuIdList);
    }

    @Override
    public void updateMenu(SysMenuModel model) throws BusinessException {
        if (model.getId() == null) {
            throw new BusinessException("菜单ID不能为空");
        }
        AdminLoginInfoModel loginInfoModel = RequestUtils.getAdminLoginInfo();
        model.setUpdateBy(loginInfoModel.getAccount());
        menuMapper.updateMenu(model);
    }

    @Override
    public PageInfoModel<List<SysMenuModel>> menuList(
            String menuName,
            Integer menuType,
            Integer enableStatus,
            int pageNum,
            int pageSize) {

        Page page = PageHelper.startPage(pageNum, pageSize);
        List<SysMenuModel> datas = menuMapper.menuList(menuName, menuType, enableStatus);
        return new PageInfoModel<>(page.getPages(), page.getTotal(), datas);
    }

    @Override
    public List<SysMenuModel> menuTreeList(Boolean onlyShowEnable) {
        Integer enableStatus = Optional.ofNullable(onlyShowEnable).map(state-> state.booleanValue() ? 1 : null).orElse(null);
        List<SysMenuModel> queryData = menuMapper.menuList(null, null, enableStatus);
        Map<Long, SysMenuModel> mapping = new HashMap<>(queryData.size());
        queryData.forEach(menu -> mapping.put(menu.getId(), menu));
        queryData.stream()
                .filter(menu -> menu.getParentId() != null && menu.getParentId() != 0)
                .forEach(menu -> {
                    if (!mapping.containsKey(menu.getParentId())) {
                        return;
                    }
                    SysMenuModel parent = mapping.get(menu.getParentId());
                    parent.getChildren().add(menu);
                });
        return queryData.stream().filter(menu -> menu.getParentId() == 0).collect(Collectors.toList());
    }

    @Override
    public void changeStatus(Long id, int enableStatus) {
        AdminLoginInfoModel infoModel = RequestUtils.getAdminLoginInfo();
        menuMapper.changeStatus(id, enableStatus, infoModel.getAccount());
    }

}
