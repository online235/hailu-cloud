package com.hailu.cloud.api.auth.module.login.dao;

import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.system.SysMenuModel;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author xuzhijie
 */
@Mapper
public interface AdminMapper {

    /**
     * 根据账号查询
     *
     * @param account 账号
     * @return
     */
    AdminLoginInfoModel searchAccount(@Param("account") String account);

    /**
     * 查询账号可以访问哪些菜单
     *
     * @param adminId
     * @return
     */
    List<SysMenuModel> findAccountMenu(@Param("adminId") Long adminId);
}
