package com.hailu.cloud.api.auth.module.login.service.impl;

import com.hailu.cloud.api.auth.module.login.service.ILoginCallback;
import com.hailu.cloud.api.auth.module.login.service.IWeChatLoginCallback;
import com.hailu.cloud.common.exception.BusinessException;

/**
 * @author xuzhijie
 */
public abstract class AbstractWeChatLoginCallback implements IWeChatLoginCallback {

    @Override
    public boolean isEnable() {
        // 默认账号是启用的
        return true;
    }

    @Override
    public void extendValidate() throws BusinessException {
        // 扩展自自定义校验
    }
}
