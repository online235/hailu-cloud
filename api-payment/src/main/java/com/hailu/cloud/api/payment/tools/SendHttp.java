package com.hailu.cloud.api.payment.tools;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;


/**
 * @author somebody
 * @date 2016/7/7
 */
public class SendHttp {
    public static String sendPost(String urlStr, String xmlString) {
        HttpClient client = new HttpClient();
        PostMethod myPost = new PostMethod(urlStr);
        client.getParams().setSoTimeout(300 * 1000);
        String responseString = null;
        try {
            myPost.setRequestEntity(new StringRequestEntity(
                    xmlString,
                    "application/x-www-form-urlencoded",
                    "utf-8"));
            int statusCode = client.executeMethod(myPost);
            if (statusCode == HttpStatus.SC_OK) {
                BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int count;
                while ((count = bis.read(bytes)) != -1) {
                    bos.write(bytes, 0, count);
                }
                byte[] strByte = bos.toByteArray();
                responseString = new String(strByte, 0, strByte.length, StandardCharsets.UTF_8);
                bos.close();
                bis.close();
            }
        } catch (Exception ignored) {

        }
        myPost.releaseConnection();
        return responseString;
    }
}