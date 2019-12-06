package com.hailu.cloud.api.auth.module.login.dao;

import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

/**
 * @author xuzhijie
 */
@Mapper
public interface AdminMapper {

    /**
     * 根据账号查询
     *
     * @param account      账号
     * @return
     */
    AdminLoginInfoModel searchAccount(@Param("account") String account);

}
