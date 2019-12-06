package com.hailu.cloud.api.admin.module.system.service;

import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysMenuModel;

import java.util.List;

/**
 * @author xuzhijie
 */
public interface IMenuService {

    /**
     * 添加菜单
     *
     * @param model
     * @return
     */
    SysMenuModel addMenu(SysMenuModel model);

    /**
     * 删除菜单
     *
     * @param menuIds
     * @return
     */
    void delMenu(String menuIds);

    /**
     * 编辑菜单
     *
     * @param model
     */
    void updateMenu(SysMenuModel model) throws BusinessException;

    /**
     * 查询菜单列表
     *
     * @param menuName     角色名称
     * @param enableStatus 启用状态
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return
     */
    PageInfoModel<List<SysMenuModel>> menuList(
            String menuName,
            Integer menuType,
            Integer enableStatus,
            int pageNum,
            int pageSize);

    /**
     * 查询所有菜单列表
     *
     * @return
     */
    List<SysMenuModel> menuTreeList(Boolean onlyShowEnable);

    /**
     * 变更菜单启用状态
     *
     * @param id           菜单ID
     * @param enableStatus 启用状态
     */
    void changeStatus(Long id, int enableStatus);

}
