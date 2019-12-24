package com.hailu.cloud.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.redis.enums.RedisEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.*;

/**
 * 处理post请求过来读取请求数据
 * @author junpei.deng
 */
@Slf4j
@Component
public class ChinaumsUtils {

    @Autowired
    private RedisStandAloneClient redisStandAloneClient;

    /**
     * 校验银联支付回调的参数
     * @return
     */
    public JSONObject checkChinaumsParams(HttpServletRequest request,String key) throws BusinessException {
        String postStr = postData(request);
        if(StringUtils.isBlank(postStr)){
            throw new BusinessException("获取回调数据出错!");
        }
        JSONObject params = Split(postStr);
        log.info("回调参数：{}",params.toJSONString());

        //防止网络问题出现，导致多次回调
       String timeAgain = redisStandAloneClient.stringGet(RedisEnum.DB_2.ordinal(),Constant.PAY_CALLBACK_AGAIN_TIME+params.get("merOrderId"));
       if(StringUtils.isNotBlank(timeAgain)){
           throw new BusinessException("该笔支付订单已处理，无需再次处理");
       }

        //校验签名
        if(!sign(params,key,"sign")){
            throw new BusinessException("校验签名错误!");
        }
        String tradeStatus =  params.getString("status");
        if(!StringUtils.equals("TRADE_SUCCESS",tradeStatus)){
            throw new BusinessException("订单状态有误!");
        }
        //将订单号去除前面四位
        String orderNo = params.getString("merOrderId");
        params.put("merOrderId",orderNo.substring(Constant.CHINAUMS_ORDER_HEADER.length()));
        redisStandAloneClient.stringSet(RedisEnum.DB_2.ordinal(),Constant.PAY_CALLBACK_AGAIN_TIME+params.get("merOrderId"),params.toJSONString(),Constant.PAY_CALLBACK_AGAIN_TIME_OUT);
        return params;
    }

    /**
     * @param urlparam 带分隔的url参数
     * @return
     */
    public  JSONObject Split(String urlparam){
        JSONObject jsonObject = new JSONObject();
        String[] param =  urlparam.split("&");
        for(String keyvalue:param){
            String[] pair = keyvalue.split("=");
            if(pair.length==2){
                jsonObject.put(pair[0], pair[1]);
            }
        }
        return jsonObject;
    }


    /**
     * 获取post过来的数据
     * @param request
     * @return
     */
    public  String postData(HttpServletRequest request) {
        String notifyData = "";
        try {
            InputStream is = request.getInputStream();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                notifyData=sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                is.close();
            }
        }catch (Exception e){
            log.error("获取回调数据异常:" + e.getMessage());
        }

        return notifyData;
    }

    /**
     * 校验签名
     * @param params
     * @param key
     * @param fileds 不参与签名校验
     * @return
     */
    public  boolean sign(JSONObject params,String key,String... fileds){
        String noSignStr = JsonToStr(params,fileds);
        noSignStr = noSignStr+key;
        log.info("ASCLL排序加秘钥的参数：{}",noSignStr);
        String signHas = DigestUtils.md5Hex(getContentBytes(noSignStr.replaceAll("\r|\n",""))).toUpperCase();
        return signHas.equalsIgnoreCase((String) params.get("sign"));
    }

    // 根据编码类型获得签名内容byte[]
    private   byte[] getContentBytes(String content) {
        try {
            return content.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("签名过程中出现错误");
        }
    }

    public  boolean useList(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 将Json转成String
     * @param params
     * @return
     */
    public  String JsonToStr(JSONObject params,String... fileds){
        if (params == null || params.size() == 0) {
            return "";
        }
        List<String> keys = new ArrayList<>(params.size());

        for (Map.Entry<String,Object> js: params.entrySet()){
            if(fileds != null && fileds.length > 0){
                if(useList(fileds,js.getKey())){
                    continue;
                }
            }
            if (StringUtils.isEmpty(js.getKey())){
                continue;
            }
            keys.add(js.getKey());
        }

        //排序 ASCll
        Collections.sort(keys);

        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = params.get(key);
            try {
                if(key.equals("billFunds") || key.equals("createTime") || key.equals("orderDesc") || key.equals("payTime")){
                    try {
                        value = URLDecoder.decode((String) value, "UTF-8");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            if (i == keys.size() - 1) {
                // 拼接时，不包括最后一个&字符
                buf.append(key + "=" + value);
            } else {
                buf.append(key + "=" + value + "&");
            }
        }

        return buf.toString();
    }

}
