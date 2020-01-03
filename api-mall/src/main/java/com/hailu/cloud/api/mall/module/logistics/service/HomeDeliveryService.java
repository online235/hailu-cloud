package com.hailu.cloud.api.mall.module.logistics.service;

import com.hailu.cloud.api.mall.module.logistics.model.HomeDeliver;
import com.hailu.cloud.api.mall.module.logistics.model.callbackDataModel;
import com.hailu.cloud.api.mall.module.logistics.model.callbackModel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

/**
 * @Author: QiuFeng:WANG
 * @Description: 宅急送
 * @Date: 2019/12/31 0031
 * @program: cloud
 * @create: 2019-12-31 10:
 */
public interface HomeDeliveryService {

    /**
     * 查询轨道
     * @param homeDeliver
     * @return
     */
    Object findQueryStatus(HomeDeliver homeDeliver) throws IOException;

    /**
     * 状态回调推送
     * @param callbackModel
     * @return
     */
    Object callback(callbackModel<callbackDataModel> callbackModel);


}
