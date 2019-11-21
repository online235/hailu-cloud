package com.hailu.cloud.api.admin.module.system.dao;

import com.hailu.cloud.common.model.system.SysRoleModel;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

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
    void linkMenus(@Param("id") Long id, @Param("menuIds") Long[] menuIds);

    /**
     * 变更角色
     *
     * @param id 角色ID
     */
    void unlinkMenus(@Param("id") Long id);
}
