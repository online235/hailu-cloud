package com.hailu.cloud.api.mall.module.logistics.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.api.mall.module.logistics.entity.McLogisticsPush;
import com.hailu.cloud.api.mall.module.logistics.model.HomeDeliver;
import com.hailu.cloud.api.mall.module.logistics.model.callbackDataModel;
import com.hailu.cloud.api.mall.module.logistics.model.callbackModel;
import com.hailu.cloud.api.mall.module.logistics.service.McLogisticsPushService;
import com.hailu.cloud.common.exception.BusinessException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 宅急送
 * @Date: 2019/12/31 0031
 * @program: cloud
 * @create: 2019-12-31 10:
 */
@Service
public class HomeDeliveryImpl extends LinkService {

    @Autowired
    private McLogisticsPushService mcLogisticsPushService;


    @Override
    public Object findQueryStatus(HomeDeliver homeDeliver) throws IOException {
        JSONObject mapDate = new JSONObject();
        JSONObject mapDates = new JSONObject();
        JSONObject mapOrders = new JSONObject();

        List<Object> objects = new ArrayList<>();
        //用户标识
        mapDate.put("clientFlag", homeDeliver.getClientFlag());
        //data数据 用户标识
        mapDates.put("clientFlag", homeDeliver.getClientFlag());
        //orders数据 运单号
        mapOrders.put("mailNo", homeDeliver.getMailNo());

        objects.add(mapOrders);
        mapDates.put("orders", objects);
        //业务数据
        mapDate.put("data", mapDates);

        //数据加密
        String numOne = RandomUtil.randomNumbers(4);
        String numOTwo = RandomUtil.randomNumbers(4);
        String str = numOne + clientFlag + mapDates.toString() + key + other + numOTwo;

        //加密
        String strMd5 = DigestUtils.md5Hex(str.getBytes("UTF-8")).replace("-","").toLowerCase().substring(7, 28);
        mapDate.put("verifyData", numOne + strMd5 + numOTwo);

        //请求参数
        String homeDate = HttpUtil.post(QUERY_TRAJECTORY_URL, mapDate);

        return JSONArray.parse(homeDate);
    }

    @Override
    public Object callback(callbackModel<callbackDataModel> callbackModel){
        JSONObject date = new JSONObject();
        date.put("mailNo", callbackModel.getCallbackData().getMailNo());
        date.put("orderNo", callbackModel.getCallbackData().getOrderNo());
        date.put("success", true);

        McLogisticsPush push = new McLogisticsPush();
        BeanUtils.copyProperties(callbackModel.getCallbackData(), push);
        mcLogisticsPushService.insertSelective(push);

        return JSONArray.parse(date.toJSONString());
    }

    public static void main(String[] args) throws IOException {
        LinkService linkService = new HomeDeliveryImpl();

        System.out.println(linkService.find());
    }






}
