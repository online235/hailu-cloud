package com.hailu.cloud.api.mall.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author leiqi
 * <p>
 * 系统静态变量
 */
@Component
public class Const {

    /**
     * 服務器圖片地址
     */
    public static String PRO_URL;

    @Value("static.server.prefix")
    public void setProUrl(String proUrl) {
        PRO_URL = proUrl;
    }

}
