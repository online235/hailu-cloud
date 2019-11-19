package com.hailu.cloud.api.payment.config;

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
     * 心安微信支付资料
     */
    public static String XA_WECAT_APPID;
    public static String XA_WECAT_KEY;
    public static String XA_WECAT_MCHID;
    public static String XA_WECAT_DEVICEINFO;

    /**
     * 心安支付宝资料
     */
    public static String XA_ALIPAY_APPID;
    public static String XA_ALIPAY_PID;
    public static String XA_ALIPAY_SELLER_ID;
    public static String XA_ALIPAY_PUBLIC_KEY;
    public static String XA_ALIPAY_RSA_PRIVATE_KEY;
    public static String XA_ALIPAY_AES_PRIVATE_KEY;


    /**
     * 海露微信支付资料
     */
    /**
     * 公众号
     */
    public static String HL_JSAPI_WECAT_APPID;
    public static String HL_JSAPI_WECAT_KEY;
    public static String HL_JSAPI_WECAT_MCHID;
    public static String HL_JSAPI_WECAT_DEVICEINFO;
    public static String HL_JSAPI_WECAT_OPENID_KEY;
    /**
     * App
     */
    public static String HL_APP_WECAT_APPID;
    public static String HL_APP_WECAT_KEY;
    public static String HL_APP_WECAT_MCHID;
    public static String HL_APP_WECAT_DEVICEINFO;


    /**
     * 海露支付宝资料
     */
    public static String HL_ALIPAY_APPID;
    public static String HL_ALIPAY_PID;
    public static String HL_ALIPAY_SELLER_ID;
    public static String HL_ALIPAY_PUBLIC_KEY;
    public static String HL_ALIPAY_RSA_PRIVATE_KEY;
    public static String HL_ALIPAY_AES_PRIVATE_KEY;



    @Value("${xinan.weixin.appid}")
    public void setXA_WECAT_APPID(String xa_wecat_appid) {
        XA_WECAT_APPID = xa_wecat_appid;
    }

    @Value("${xinan.weixin.key}")
    public void setXA_WECAT_KEY(String xa_wecat_key) {
        XA_WECAT_KEY = xa_wecat_key;
    }

    @Value("${xinan.weixin.mchid}")
    public void setXA_WECAT_MCHID(String xa_wecat_mchid) {
        XA_WECAT_MCHID = xa_wecat_mchid;
    }

    @Value("${xinan.weixin.deviceinfo}")
    public void setXA_WECAT_DEVICEINFO(String xa_wecat_deviceinfo) {
        XA_WECAT_DEVICEINFO = xa_wecat_deviceinfo;
    }

    /**
     * 海露公众号微信支付
     * @param
     */
    @Value("${hailu.weixin.jsapi.appid}")
    public void setHL_JSAPI_WECAT_APPID(String hl_jsapi_wecat_appid) {
        HL_JSAPI_WECAT_APPID = hl_jsapi_wecat_appid;
    }

    @Value("${hailu.weixin.jsapi.key}")
    public void setHL_JSAPI_WECAT_KEY(String hl_jsapi_wecat_key) {
        HL_JSAPI_WECAT_KEY = hl_jsapi_wecat_key;
    }

    @Value("${hailu.weixin.jsapi.openIdKey}")
    public void setHL_JSAPI_WECAT_OPENID_KEY(String hl_jsapi_wecat_openid_key) {
        HL_JSAPI_WECAT_OPENID_KEY = hl_jsapi_wecat_openid_key;
    }

    @Value("${hailu.weixin.jsapi.mchid}")
    public void setHL_JSAPI_WECAT_MCHID(String hl_jsapi_wecat_mchid) {
        HL_JSAPI_WECAT_MCHID = hl_jsapi_wecat_mchid;
    }

    @Value("${hailu.weixin.jsapi.deviceinfo}")
    public void setHL_JSAPI_WECAT_DEVICEINFO(String hl_jsapi_wecat_deviceinfo) {
        HL_JSAPI_WECAT_DEVICEINFO = hl_jsapi_wecat_deviceinfo;
    }

    /**
     * 海露APP微信支付
     * @param
     */
    @Value("${hailu.weixin.app.appid}")
    public void setHL_APP_WECAT_APPID(String hl_app_wecat_appid) {
        HL_APP_WECAT_APPID = hl_app_wecat_appid;
    }

    @Value("${hailu.weixin.jsapi.key}")
    public void setHL_APP_WECAT_KEY(String hl_app_wecat_key) {
        HL_APP_WECAT_KEY = hl_app_wecat_key;
    }

    @Value("${hailu.weixin.jsapi.mchid}")
    public void setHL_APP_WECAT_MCHID(String hl_app_wecat_mchid) {
        HL_APP_WECAT_MCHID = hl_app_wecat_mchid;
    }

    @Value("${hailu.weixin.jsapi.deviceinfo}")
    public void setHL_APP_WECAT_DEVICEINFO(String hl_app_wecat_deviceinfo) {
        HL_APP_WECAT_DEVICEINFO = hl_app_wecat_deviceinfo;
    }


    @Value("${xinan.alipay.appid}")
    public void setXA_ALIPAY_APPID(String xa_alipay_appid) {
        XA_ALIPAY_APPID = xa_alipay_appid;
    }

    @Value("${xinan.alipay.pid}")
    public void setXA_ALIPAY_PID(String xa_alipay_pid) {
        XA_ALIPAY_PID = xa_alipay_pid;
    }

    @Value("${xinan.alipay.seller.id}")
    public void setXA_ALIPAY_SELLER_ID(String xa_alipay_seller_id) {
        XA_ALIPAY_SELLER_ID = xa_alipay_seller_id;
    }

    @Value("${xinan.alipay.public.key}")
    public void setXA_ALIPAY_PUBLIC_KEY(String xa_alipay_public_key) {
        XA_ALIPAY_PUBLIC_KEY = xa_alipay_public_key;
    }

    @Value("${xinan.alipay.rsa.private.key}")
    public void setXA_ALIPAY_RSA_PRIVATE_KEY(String xa_alipay_rsa_private_key) {
        XA_ALIPAY_RSA_PRIVATE_KEY = xa_alipay_rsa_private_key;
    }

    @Value("${xinan.alipay.aes.private.key}")
    public void setXA_ALIPAY_AES_PRIVATE_KEY(String xa_alipay_aes_private_key) {
        XA_ALIPAY_AES_PRIVATE_KEY = xa_alipay_aes_private_key;
    }


    @Value("${hailu.alipay.appid}")
    public void setHL_ALIPAY_APPID(String hl_alipay_appid) {
        HL_ALIPAY_APPID = hl_alipay_appid;
    }

    @Value("${hailu.alipay.pid}")
    public void setHL_ALIPAY_PID(String hl_alipay_pid) {
        HL_ALIPAY_PID = hl_alipay_pid;
    }

    @Value("${hailu.alipay.seller.id}")
    public void setHL_ALIPAY_SELLER_ID(String hl_alipay_seller_id) {
        HL_ALIPAY_SELLER_ID = hl_alipay_seller_id;
    }

    @Value("${hailu.alipay.public.key}")
    public void setHL_ALIPAY_PUBLIC_KEY(String hl_alipay_public_key) {
        HL_ALIPAY_PUBLIC_KEY = hl_alipay_public_key;
    }

    @Value("${hailu.alipay.rsa.private.key}")
    public void setHL_ALIPAY_RSA_PRIVATE_KEY(String hl_alipay_rsa_private_key) {
        HL_ALIPAY_RSA_PRIVATE_KEY = hl_alipay_rsa_private_key;
    }

    @Value("${hailu.alipay.aes.private.key}")
    public void setHL_ALIPAY_AES_PRIVATE_KEY(String hl_alipay_aes_private_key) {
        HL_ALIPAY_AES_PRIVATE_KEY = hl_alipay_aes_private_key;
    }
}
