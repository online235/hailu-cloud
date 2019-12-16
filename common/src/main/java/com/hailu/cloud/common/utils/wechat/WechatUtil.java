package com.hailu.cloud.common.utils.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WechatUtil {

    /**
     * 微信网页授权获取网页accesstoken和OPENID
     **/
    public static String web_oauth_accesstoken_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";


    public static Map<String, String> xmlToMap(String xml) throws Exception {
        Map<String, String> rMap = new HashMap<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        String FEATURE = null;
        try {
            FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
            dbf.setFeature(FEATURE, true);
            FEATURE = "http://xml.org/sax/features/external-general-entities";
            dbf.setFeature(FEATURE, false);
            FEATURE = "http://xml.org/sax/features/external-parameter-entities";
            dbf.setFeature(FEATURE, false);
            FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
            dbf.setFeature(FEATURE, false);
            dbf.setXIncludeAware(false);
            dbf.setExpandEntityReferences(false);
        } catch (ParserConfigurationException e) {
            
            log.error(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DocumentBuilder safebuilder = dbf.newDocumentBuilder();
        ByteArrayInputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        Document document = safebuilder.parse(stream);
        Element root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        if (null != nodes) {
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String value = node.getTextContent();
                    if (null != value) {
                        value = value.replace("<![CDATA[", "").replace("]]", "");
                    }
                    rMap.put(node.getNodeName(), value);
                }
            }
        }
        return rMap;
    }

    public static Map<String, String> xmlToMap(InputStream in) throws Exception {
        Map<String, String> rMap = new HashMap<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        String FEATURE = null;
        try {
            FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
            dbf.setFeature(FEATURE, true);
            FEATURE = "http://xml.org/sax/features/external-general-entities";
            dbf.setFeature(FEATURE, false);
            FEATURE = "http://xml.org/sax/features/external-parameter-entities";
            dbf.setFeature(FEATURE, false);
            FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
            dbf.setFeature(FEATURE, false);
            dbf.setXIncludeAware(false);
            dbf.setExpandEntityReferences(false);
            DocumentBuilder safebuilder = dbf.newDocumentBuilder();
            Document document = safebuilder.parse(in);
            Element root = document.getDocumentElement();
            NodeList nodes = root.getChildNodes();
            if (null != nodes) {
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node node = nodes.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        String value = node.getTextContent();
                        if (null != value) {
                            value = value.replace("<![CDATA[", "").replace("]]", "");
                        }
                        rMap.put(node.getNodeName(), value);
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            
            log.error(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            in.close();
        }
        log.info("微信返回数据：{}",rMap.toString());
        return rMap;
    }


    public static Map<String, Object> weCatCallback(Map<String,String> map){
        Map<String, Object> date = new HashMap<>();
        try {

            String out_trade_no = "";
            String transaction_id = "";
            String resultCode = "";
            Map<String, String> retMap = new HashMap<String, String>();
            //支付返回信息
            String returnCode = map.get("return_code");
            if ("SUCCESS".equals(returnCode)) {
                resultCode = map.get("result_code");
                if("SUCCESS".equals(resultCode)){
                    out_trade_no = map.get("out_trade_no");
                    transaction_id = map.get("transaction_id");
                    //商家订单号
                    date.put("outTradeNo", out_trade_no);
                    //支付订单号
                    date.put("tradeNo", transaction_id);
                    //支付状态
                    date.put("tradeStatus", returnCode);
                    //金额
                    DecimalFormat df = new DecimalFormat("######0.00");
                    String cash_fee = map.get("cash_fee");
                    double fee = Double.parseDouble(cash_fee) / 100;
                    fee = Double.parseDouble(df.format(fee));
                    date.put("money", fee);
                    //支付类型 1-支付宝2-微信
                    date.put("payType",2);
                }else {
                    String errCode = map.get("err_code");
                    throw new BusinessException("支付失败！out_trade_no:" + ",result_code:" + resultCode + ", err_code:" + errCode);
                }
            }else {
                String returnMsg = map.get("return_msg");
                retMap.put("return_code", returnCode);
                retMap.put("return_msg", returnMsg);
                log.error("支付通信失败！" + returnMsg);
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GETPOST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);
            if("GET".equalsIgnoreCase(requestMethod)){
                httpUrlConn.connect();
            }
            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            System.out.println("Weixin server connection timed out.");
        } catch (Exception e) {
            System.out.println("https request error:{}" + e.getMessage());
        }
        return jsonObject;
    }

}
