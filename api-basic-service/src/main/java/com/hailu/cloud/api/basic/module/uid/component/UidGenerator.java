package com.hailu.cloud.api.basic.module.uid.component;


import com.hailu.cloud.api.basic.module.uid.component.exception.UidGenerateException;

import java.util.Map;

/**
 * @author yutianbao
 */
public interface UidGenerator {

    /**
     * Get a unique ID
     *
     * @return UID
     * @throws UidGenerateException
     */
    long uuid() throws UidGenerateException;

    /**
     * 解析uuid组成信息
     *
     * @param uid
     * @return Parsed info
     */
    Map<String, Object> parseUuid(long uid);

    /**
     * uid转成字符串
     *
     * @return
     * @throws UidGenerateException
     */
    String getUuidAsString() throws UidGenerateException;
}
