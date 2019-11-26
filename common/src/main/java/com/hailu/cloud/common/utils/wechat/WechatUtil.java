package com.hailu.cloud.common.util.wechat;

import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WechatUtil {

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

}
