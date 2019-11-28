package com.hailu.cloud.api.admin.module.system.dao;

import com.hailu.cloud.common.model.system.SysAdminModel;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author xuzhijie
 */
@Mapper
public interface AdminMapper {

    /**
     * 添加账号
     *
     * @param model
     */
    void addAccount(SysAdminModel model);

    /**
     * 修改自己的密码
     *
     * @param id       账号ID
     * @param pwd      密码
     * @param updateBy 修改人
     */
    void changePwd(@Param("id") Long id, @Param("pwd") String pwd, @Param("updateBy") String updateBy);

    /**
     * 根据账号查询
     *
     * @param account      账号
     * @param enableStatus
     * @return
     */
    SysAdminModel searchAccount(@Param("account") String account, @Param("enableStatus") int enableStatus);

    /**
     * 查询账号列表
     *
     * @param nickName     昵称
     * @param account      账号
     * @param enableStatus 启用状态
     * @return
     */
    List<SysAdminModel> accountList(
            @Param("nickName") String nickName,
            @Param("account") String account,
            @Param("enableStatus") Integer enableStatus);

    /**
     * 变更账号启用状态
     *
     * @param id           账号ID
     * @param enableStatus 启用状态
     * @param updateBy     修改人
     */
    void changeStatus(@Param("id") Long id, @Param("enableStatus") int enableStatus, @Param("updateBy") String updateBy);

    /**
     * 变更角色
     *
     * @param id      账号ID
     * @param roleIds 角色ID
     */
    void linkRoles(@Param("id") Long id, @Param("roleIds") Long[] roleIds);

    /**
     * 变更角色
     *
     * @param id 账号ID
     */
    void unlinkRoles(@Param("id") Long id);

    /**
     * 检查帐户是否重复
     *
     * @param id 账号ID
     * @return
     */
    SysAdminModel checkAccountIsRepeat(@Param("id") Long id);

}
