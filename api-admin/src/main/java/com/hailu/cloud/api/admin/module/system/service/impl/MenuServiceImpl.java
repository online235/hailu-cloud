package com.hailu.cloud.api.admin.module.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.system.dao.MenuMapper;
import com.hailu.cloud.api.admin.module.system.service.IMenuService;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysMenuModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuzhijie
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public void addMenu(SysMenuModel model) {
        menuMapper.addMenu(model);
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
        return new PageInfoModel<>(page.getPages(), datas);
    }

    @Override
    public void changeStatus(Long id, int enableStatus) {
        AdminLoginInfoModel infoModel = RequestUtils.getAdminLoginInfo();
        menuMapper.changeStatus(id, enableStatus, infoModel.getAccount());
    }

}
