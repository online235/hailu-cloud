package com.hailu.cloud.api.payment.config;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.pay.service
 * @Author: junpei.deng
 * @CreateTime: 2019-10-23 16:27
 * @Description: 支付信息
 */
public class CredentFactory {

    public static final String XINANWECAT = "XINANWECAT";
    public static final String XINANALI = "XINANALI";
    public static final String HLJSAPIWECAT = "HLJSAPIWECAT";
    public static final String HLAPPWECAT = "HLAPPWECAT";
    public static final String HLALI = "HLALI";



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
     * 心安微信支付
     * @return
     */
    private static Map<String,String> xinAnWecat(){
        Map<String,String> map = new HashMap<>();
        map.put(APPID_FIELD, Const.XA_WECAT_APPID);
        map.put(DEVICEINFO_FIELD,Const.XA_WECAT_DEVICEINFO);
        map.put(MCH_ID_FIELD,Const.XA_WECAT_MCHID);
        map.put(APP_SECRRECT_FIELD,Const.XA_WECAT_KEY);
        return map;
    }

    /**
     * 心安支付宝支付
     * @return
     */
    private static Map<String,String> xinAnAli(){
        Map<String,String> map = new HashMap<>();
        map.put(APPID_FIELD,Const.XA_ALIPAY_APPID);
        map.put(PID_FIELD,Const.XA_ALIPAY_PID);
        map.put(SELLER_ID_FIELD,Const.XA_ALIPAY_SELLER_ID);
        map.put(PUBLIC_KEY_FIELD,Const.XA_ALIPAY_PUBLIC_KEY);
        map.put(RSA_PRIVATE_KEY_FIELD,Const.XA_ALIPAY_RSA_PRIVATE_KEY);
        map.put(AES_PRIVATE_KEY_FIELD,Const.XA_ALIPAY_AES_PRIVATE_KEY);
        return map;
    }

    /**
     * 海露公众号微信支付
     * @return
     */
    private static Map<String,String> hLJsapiWecat(){
        Map<String,String> map = new HashMap<>();
        map.put(APPID_FIELD, Const.HL_JSAPI_WECAT_APPID);
        map.put(DEVICEINFO_FIELD,Const.HL_JSAPI_WECAT_DEVICEINFO);
        map.put(MCH_ID_FIELD,Const.HL_JSAPI_WECAT_MCHID);
        map.put(APP_SECRRECT_FIELD,Const.HL_JSAPI_WECAT_KEY);
        map.put(OPENID_SECRRECT_FIELD,Const.HL_JSAPI_WECAT_OPENID_KEY);
        return map;
    }

    /**
     * 海露APP微信支付
     * @return
     */
    private static Map<String,String> hLAPPWecat(){
        Map<String,String> map = new HashMap<>();
        map.put(APPID_FIELD, Const.HL_APP_WECAT_APPID);
        map.put(DEVICEINFO_FIELD,Const.HL_APP_WECAT_DEVICEINFO);
        map.put(MCH_ID_FIELD,Const.HL_APP_WECAT_MCHID);
        map.put(APP_SECRRECT_FIELD,Const.HL_APP_WECAT_KEY);
        return map;
    }

    /**
     * 海露支付宝支付
     * @return
     */
    private static Map<String,String> hLAli(){
        Map<String,String> map = new HashMap<>();
        map.put(APPID_FIELD,Const.HL_ALIPAY_APPID);
        map.put(PID_FIELD,Const.HL_ALIPAY_PID);
        map.put(SELLER_ID_FIELD,Const.HL_ALIPAY_SELLER_ID);
        map.put(PUBLIC_KEY_FIELD,Const.HL_ALIPAY_PUBLIC_KEY);
        map.put(RSA_PRIVATE_KEY_FIELD,Const.HL_ALIPAY_RSA_PRIVATE_KEY);
        map.put(AES_PRIVATE_KEY_FIELD,Const.HL_ALIPAY_AES_PRIVATE_KEY);
        return map;
    }

    public static Map<String,String> createPayParams(String name){
        if(StringUtils.isBlank(name)){
            return null;
        }
        switch (name){
            case XINANWECAT:
                return xinAnWecat();
            case XINANALI:
                return xinAnAli();
            case HLAPPWECAT:
                return hLAPPWecat();
            case HLJSAPIWECAT:
                return hLJsapiWecat();
            case HLALI:
                return hLAli();
            default:
                return null;
        }
    }
}
