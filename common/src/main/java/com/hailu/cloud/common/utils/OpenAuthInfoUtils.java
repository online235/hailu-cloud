package com.hailu.cloud.common.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.hailu.cloud.common.model.security.OpenAuthInfoModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * request工具类
 *
 * @author xuzhijie
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OpenAuthInfoUtils {

    /**
     * 生成appId, appSecret
     *
     * @param time
     * @return
     */
    public static OpenAuthInfoModel create(long time) {
        OpenAuthInfoModel model = new OpenAuthInfoModel();
        model.setAppId("hl" + IdUtil.simpleUUID());
        model.setAppSecret(SecureUtil.sha256(model.getAppId() + time));
        return model;
    }

}
