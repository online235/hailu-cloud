package com.hailu.cloud.api.payment.tools.wecat;


/**
 * @Author HuangL
 * @Description
 * @Date create in 10:25 2018-10-24
 * @Modefy By:
 */
public class WeiXinPayConstant {

    public enum SignType {
        MD5, HMACSHA256
    }


    public static final String FAIL = "FAIL";
    public static final String SUCCESS = "SUCCESS";
    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";

    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";


}
