package com.hailu.cloud.api.admin.module.system.dao;

import com.hailu.cloud.api.admin.module.system.model.LkAdminRoleModel;
import com.hailu.cloud.api.admin.module.system.model.LkRoleMenuModel;
import com.hailu.cloud.common.model.system.SysRoleModel;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author xuzhijie
 */
@Mapper
public interface RoleMapper {

    /**
     * 添加角色
     *
     * @param model
     */
    void addRole(SysRoleModel model);

    /**
     * 添加角色
     *
     * @param model
     */
    void updateRole(SysRoleModel model);

    /**
     * 查询角色列表
     *
     * @param roleName     角色名称
     * @param enableStatus 启用状态
     * @return
     */
    List<SysRoleModel> roleList(
            @Param("roleName") String roleName,
            @Param("enableStatus") Integer enableStatus);

    /**
     * 查询管理员角色列表
     *
     * @param userIds 管理员账号ID
     * @return
     */
    List<LkAdminRoleModel> adminRoleList(@Param("userIds") Set<Long> userIds);

    /**
     * 查询角色绑定的菜单
     *
     * @param roleIds 角色ID
     * @return
     */
    List<LkRoleMenuModel> findBindMenus(@Param("roleIds") Set<Long> roleIds);

    /**
     * 变更角色启用状态
     *
     * @param id           角色ID
     * @param enableStatus 启用状态
     */
    void changeStatus(
            @Param("id") Long id,
            @Param("enableStatus") int enableStatus,
            @Param("updateBy") String updateBy);

    /**
     * 变更角色
     *
     * @param id      角色ID
     * @param menuIds 角色ID
     */
    void linkMenus(@Param("id") Long id, @Param("menuIds") Set<Long> menuIds);

    /**
     * 变更角色
     *
     * @param id 角色ID
     */
    void unlinkMenus(@Param("id") Long id);
}
