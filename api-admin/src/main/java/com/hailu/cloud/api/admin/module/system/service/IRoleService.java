package com.hailu.cloud.api.admin.module.system.service;

import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysRoleModel;

import java.util.List;

/**
 * @author xuzhijie
 */
public interface IRoleService {

    /**
     * 添加角色
     *
     * @param model
     */
    void addRole(SysRoleModel model);

    /**
     * 删除角色
     *
     * @param id
     */
    void delRole(Long id);

    /**
     * 添加角色
     *
     * @param model
     */
    void updateRole(SysRoleModel model) throws BusinessException;

    /**
     * 查询角色列表
     *
     * @param roleName     角色名称
     * @param enableStatus 启用状态
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return
     */
    PageInfoModel<List<SysRoleModel>> roleList(
            String roleName,
            Integer enableStatus,
            int pageNum,
            int pageSize);

    /**
     * 变更角色启用状态
     *
     * @param id           角色ID
     * @param enableStatus 启用状态
     */
    void changeStatus(Long id, int enableStatus);

    /**
     * 变更菜单
     *
     * @param id      角色ID
     * @param menuIds 菜单ID
     */
    void changeMenus(Long id, String menuIds) throws BusinessException;

}
