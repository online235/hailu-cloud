package com.hailu.cloud.api.admin.module.system.dao;

import com.hailu.cloud.common.model.system.SysMenuModel;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author xuzhijie
 */
@Mapper
public interface MenuMapper {

    /**
     * 添加菜单
     *
     * @param model
     */
    void addMenu(SysMenuModel model);

    /**
     * 编辑菜单
     *
     * @param model
     */
    void updateMenu(SysMenuModel model);

    /**
     * 查询菜单列表
     *
     * @param menuName     角色名称
     * @param enableStatus 启用状态
     * @return
     */
    List<SysMenuModel> menuList(
            @Param("menuName") String menuName,
            @Param("menuType") Integer menuType,
            @Param("enableStatus") Integer enableStatus);

    /**
     * 变更菜单启用状态
     *
     * @param id           菜单ID
     * @param enableStatus 启用状态
     */
    void changeStatus(
            @Param("id") Long id,
            @Param("enableStatus") int enableStatus,
            @Param("updateBy") String updateBy);

}
