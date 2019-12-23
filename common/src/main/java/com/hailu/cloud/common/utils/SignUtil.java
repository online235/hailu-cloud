package com.hailu.cloud.common.utils;

import cn.hutool.crypto.SecureUtil;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.*;

/**
 * @author 190726
 */
public class SignUtil {
    public static String createSign(String characterEncoding, SortedMap<String, String> parameters, String key) {

        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        //最后加密时添加商户密钥，由于key值放在最后，所以不用添加到SortMap里面去，单独处理，编码方式采用UTF-8
        if (StringUtils.isBlank(key)) {
            String val = sb.toString();
            if (val.endsWith("&")) {
                val.substring(0, val.length() - 1);
            }
            return SecureUtil.md5(val).toUpperCase();
        }
        sb.append("key=" + key);
        return SecureUtil.md5(sb.toString()).toUpperCase();
    }

    public static String createSign(String characterEncoding, SortedMap<String, String> parameters) {
        return createSign(characterEncoding, parameters, null);
    }


    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  ASCLL排序，SHA1签名
     * @param parameters
     * @return
     */
    public static String createASCLLSign(SortedMap<String, String> parameters) {
        List<String> keyList = new ArrayList<>();

        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            keyList.add(key);
        }

        //ASCLL排序
        Collections.sort(keyList);
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i< keyList.size();i++){
            String key = keyList.get(i);
            sb.append(key + "=" + parameters.get(key));
            if(i != keyList.size() - 1){
                sb.append("&");
            }
        }
        return encode(sb.toString());
    }


}
