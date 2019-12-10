package com.hailu.cloud.api.auth.module.login.service;

import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.OpenApiLoginInfoModel;

/**
 * @author xuzhijie
 */
public interface IOpenApiAuthService {

    /**
     * 开放API登录
     *
     * @param appId
     * @param appSecret
     * @return
     * @throws BusinessException
     */
    OpenApiLoginInfoModel login(String appId, String appSecret) throws BusinessException;

}
