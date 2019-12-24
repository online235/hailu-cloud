package com.hailu.cloud.api.payment.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.pay.service
 * @Author: junpei.deng
 * @CreateTime: 2019-10-23 16:27
 * @Description: 支付信息
 */
@Component
public class CredentFactory {

    // region static

    public static final String XINANWECAT = "XINANWECAT";
    public static final String XINANALI = "XINANALI";
    public static final String HLJSAPIWECAT = "HLJSAPIWECAT";
    public static final String HLAPPWECAT = "HLAPPWECAT";
    public static final String HLALI = "HLALI";
    public static final String CHINAUMSAPP = "chinaumsApp";
    public static final String CHINAUMSJSAPI = "chinaumsJsapi";

    public static final String APPID_FIELD = "appId";
    public static final String APP_SECRRECT_FIELD = "appSecret";
    public static final String MCH_ID_FIELD = "mchId";
    public static final String DEVICEINFO_FIELD = "deviceinfo";
    public static final String PID_FIELD = "pid";
    public static final String SELLER_ID_FIELD = "sellerId";
    public static final String PUBLIC_KEY_FIELD = "publicKey";
    public static final String RSA_PRIVATE_KEY_FIELD = "rsaPrivateKey";
    public static final String AES_PRIVATE_KEY_FIELD = "aesPrivateKey";
    public static final String OPENID_SECRRECT_FIELD = "openIdKey";


    /**
     * 银联支付
     */
    public static final String CHINAUMS_MID_FIELD = "mid";
    public static final String CHINAUMS_TID_FIELD = "tid";
    public static final String CHINAUMS_MSGSRC_FIELD = "msgSrc";
    public static final String CHINAUMS_INSTMID_FIELD = "instMid";
    public static final String CHINAUMS_SUBAPPID_FIELD = "subAppId";
    public static final String CHINAUMS_KEY_FIELD = "key";

    // endregion

    // region 心安微信支付参数

    @Value("${xinan.weixin.appid}")
    private String xinAnWeChatAppId;

    @Value("${xinan.weixin.key}")
    private String xinAnWeChatKey;

    @Value("${xinan.weixin.mchid}")
    private String xinAnWeChatMchId;

    @Value("${xinan.weixin.deviceinfo}")
    private String xinAnWeChatDeviceInfo;

    /**
     * 心安微信支付
     *
     * @return
     */
    private Map<String, String> xinAnWeChatPayParams() {
        Map<String, String> map = new HashMap<>(10);
        map.put(APPID_FIELD, xinAnWeChatAppId);
        map.put(DEVICEINFO_FIELD, xinAnWeChatDeviceInfo);
        map.put(MCH_ID_FIELD, xinAnWeChatMchId);
        map.put(APP_SECRRECT_FIELD, xinAnWeChatKey);
        return map;
    }

    // endregion

    // region 心安支付宝支付参数

    @Value("${xinan.alipay.appid}")
    private String xinAnAliPayAppId;

    @Value("${xinan.alipay.pid}")
    private String xinAnAliPayPid;

    @Value("${xinan.alipay.seller.id}")
    private String xinAnAliPaySellerId;

    @Value("${xinan.alipay.public.key}")
    private String xinAnAliPayPublicKey;

    @Value("${xinan.alipay.rsa.private.key}")
    private String xinAnAliPayRsaPrivateKey;

    @Value("${xinan.alipay.aes.private.key}")
    private String xinAnAliPayAesPrivateKey;

    /**
     * 心安支付宝支付
     *
     * @return
     */
    private Map<String, String> xinAnAliPayParams() {
        Map<String, String> map = new HashMap<>(10);
        map.put(APPID_FIELD, xinAnAliPayAppId);
        map.put(PID_FIELD, xinAnAliPayPid);
        map.put(SELLER_ID_FIELD, xinAnAliPaySellerId);
        map.put(PUBLIC_KEY_FIELD, xinAnAliPayPublicKey);
        map.put(RSA_PRIVATE_KEY_FIELD, xinAnAliPayRsaPrivateKey);
        map.put(AES_PRIVATE_KEY_FIELD, xinAnAliPayAesPrivateKey);
        return map;
    }

    // endregion

    // region 海露微信js支付参数

    @Value("${hailu.weixin.jsapi.appid}")
    public String haiLuWeChatJsApiAppId;

    @Value("${hailu.weixin.jsapi.key}")
    public String haiLuWeChatJsApiKey;

    @Value("${hailu.weixin.jsapi.openIdKey}")
    public String haiLuWeChatJsApiOpenIdKey;

    @Value("${hailu.weixin.jsapi.mchid}")
    public String haiLuWeChatJsApiMchId;

    @Value("${hailu.weixin.jsapi.deviceinfo}")
    public String haiLuWeChatJsApiDeviceInfo;

    /**
     * 海露公众号微信支付
     *
     * @return
     */
    private Map<String, String> haiLuJsApiWecatPayParams() {
        Map<String, String> map = new HashMap<>(10);
        map.put(APPID_FIELD, haiLuWeChatJsApiAppId);
        map.put(DEVICEINFO_FIELD, haiLuWeChatJsApiDeviceInfo);
        map.put(MCH_ID_FIELD, haiLuWeChatJsApiMchId);
        map.put(APP_SECRRECT_FIELD, haiLuWeChatJsApiKey);
        map.put(OPENID_SECRRECT_FIELD, haiLuWeChatJsApiOpenIdKey);
        return map;
    }

    // endregion

    // region 海露微信支付参数

    @Value("${hailu.weixin.app.appid}")
    private String haiLuAppWeCatAppId;

    @Value("${hailu.weixin.jsapi.key}")
    private String haiLuAppWeCatKey;

    @Value("${hailu.weixin.jsapi.mchid}")
    private String haiLuAppWeCatMchId;

    @Value("${hailu.weixin.jsapi.deviceinfo}")
    private String haiLuAppWeCatDeviceInfo;

    /**
     * 海露APP微信支付
     *
     * @return
     */
    private Map<String, String> haiLuAppWecatPayParams() {
        Map<String, String> map = new HashMap<>(4);
        map.put(APPID_FIELD, haiLuAppWeCatAppId);
        map.put(DEVICEINFO_FIELD, haiLuAppWeCatDeviceInfo);
        map.put(MCH_ID_FIELD, haiLuAppWeCatMchId);
        map.put(APP_SECRRECT_FIELD, haiLuAppWeCatKey);
        return map;
    }

    // endregion

    // region 海露支付宝支付参数

    @Value("${hailu.alipay.appid}")
    public String haiLuAliPayAppId;

    @Value("${hailu.alipay.pid}")
    public String haiLuAliPayPid;

    @Value("${hailu.alipay.seller.id}")
    public String haiLuAliPaySellerId;

    @Value("${hailu.alipay.public.key}")
    public String haiLuAliPayPublicKey;

    @Value("${hailu.alipay.rsa.private.key}")
    public String haiLuAliPayRsaPrivateKey;

    @Value("${hailu.alipay.aes.private.key}")
    public String haiLuAliPayAesPrivateKey;

    /**
     * 海露支付宝支付
     *
     * @return
     */
    private Map<String, String> haiLuAliPayParams() {
        Map<String, String> map = new HashMap<>(6);
        map.put(APPID_FIELD, haiLuAliPayAppId);
        map.put(PID_FIELD, haiLuAliPayPid);
        map.put(SELLER_ID_FIELD, haiLuAliPaySellerId);
        map.put(PUBLIC_KEY_FIELD, haiLuAliPayPublicKey);
        map.put(RSA_PRIVATE_KEY_FIELD, haiLuAliPayRsaPrivateKey);
        map.put(AES_PRIVATE_KEY_FIELD, haiLuAliPayAesPrivateKey);
        return map;
    }

    /**
     * -------------------------------------------银联支付---------------------------------------------------------
     */


    /**
     * APP商户号
     */
    @Value("${chinaums.app.mid}")
    private String chinaumsAppMid;

    /**
     * APP终端号
     */
    @Value("${chinaums.app.tid}")
    private String chinaumsAppTid;

    /**
     * APP业务类型
     */
    @Value("${chinaums.app.instMid}")
    private String chinaumsAppInstMid;

    /**
     * APP消息来源
     */
    @Value("${chinaums.app.msgSrc}")
    private String chinaumsAppMsgSrc;

    /**
     * APP秘钥
     */
    @Value("${chinaums.app.key}")
    private String chinaumsAppKey;

    @Value("${chinaums.app.appid}")
    private String chinaumsAppSubAppId;

    /**
     * 银联App支付
     * @return
     */
    private Map<String,String> chinaumsAppParams(){
        Map<String, String> map = new HashMap<>(6);
        map.put(CHINAUMS_MID_FIELD,chinaumsAppMid);
        map.put(CHINAUMS_TID_FIELD,chinaumsAppTid);
        map.put(CHINAUMS_INSTMID_FIELD,chinaumsAppInstMid);
        map.put(CHINAUMS_MSGSRC_FIELD,chinaumsAppMsgSrc);
        map.put(CHINAUMS_KEY_FIELD,chinaumsAppKey);
        map.put(CHINAUMS_SUBAPPID_FIELD,chinaumsAppSubAppId);
        return map;
    }

    /**
     * 公众号商户号
     */
    @Value("${chinaums.JSAPI.mid}")
    private String chinaumsJSMid;

    /**
     * 公众号终端号
     */
    @Value("${chinaums.JSAPI.tid}")
    private String chinaumsJSTid;

    /**
     * 公众号业务类型
     */
    @Value("${chinaums.JSAPI.instMid}")
    private String chinaumsJSInstMid;

    /**
     * 公众号消息来源
     */
    @Value("${chinaums.JSAPI.msgSrc}")
    private String chinaumsJSMsgSrc;

    /**
     * 公众号秘钥
     */
    @Value("${chinaums.JSAPI.key}")
    private String chinaumsJSKey;

    @Value("${chinaums.JSAPI.appid}")
    private String chinaumsJSSubAppId;

    /**
     * 银联公众号支付
     * @return
     */
    private Map<String,String> chinaumsJSParams(){
        Map<String, String> map = new HashMap<>(6);
        map.put(CHINAUMS_MID_FIELD,chinaumsJSMid);
        map.put(CHINAUMS_TID_FIELD,chinaumsJSTid);
        map.put(CHINAUMS_INSTMID_FIELD,chinaumsJSInstMid);
        map.put(CHINAUMS_MSGSRC_FIELD,chinaumsJSMsgSrc);
        map.put(CHINAUMS_KEY_FIELD,chinaumsJSKey);
        map.put(CHINAUMS_SUBAPPID_FIELD,chinaumsJSSubAppId);
        return map;
    }



    // endregion


    public Map<String, String> createPayParams(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        switch (name) {
            case XINANWECAT:
                return xinAnWeChatPayParams();
            case XINANALI:
                return xinAnAliPayParams();
            case HLAPPWECAT:
                return haiLuAppWecatPayParams();
            case HLJSAPIWECAT:
                return haiLuJsApiWecatPayParams();
            case HLALI:
                return haiLuAliPayParams();
            case CHINAUMSAPP:
                return chinaumsAppParams();
            case CHINAUMSJSAPI:
                return chinaumsJSParams();
            default:
                return null;
        }
    }
}
