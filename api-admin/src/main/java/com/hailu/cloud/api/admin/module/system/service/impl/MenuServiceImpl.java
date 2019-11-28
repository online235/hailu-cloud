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
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Override
    public void addMenu(SysMenuModel model) {
        model.setId(basicFeignClient.uuid().getData());
        if( model.getParentId() == null ){
            model.setParentId(0L);
        }
        AdminLoginInfoModel loginInfoModel = RequestUtils.getAdminLoginInfo();
        model.setCreateBy(loginInfoModel.getAccount());
        menuMapper.addMenu(model);
    }

    @Override
    public void updateMenu(SysMenuModel model) throws BusinessException {
        if( model.getId() == null ){
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
    public void changeStatus(Long id, int enableStatus) {
        AdminLoginInfoModel infoModel = RequestUtils.getAdminLoginInfo();
        menuMapper.changeStatus(id, enableStatus, infoModel.getAccount());
    }

}
