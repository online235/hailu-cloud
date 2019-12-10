package com.hailu.cloud.api.auth.module.login.dao;

import com.hailu.cloud.common.model.system.OpenApiAccountModel;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

/**
 * @author xuzhijie
 */
@Mapper
public interface OpenApiAccountMapper {

    /**
     * 查找账号
     *
     * @param appId
     * @param appSecret
     * @return
     */
    OpenApiAccountModel searchAccount(@Param("appId") String appId, @Param("appSecret") String appSecret);
}
