package com.hailu.cloud.api.mall.module.logistics.service.impl;

import com.hailu.cloud.api.mall.module.logistics.model.HomeDeliver;
import com.hailu.cloud.api.mall.module.logistics.service.HomeDeliveryService;

import java.io.IOException;
import java.text.ParseException;

/**
 * @Author: QiuFeng:WANG
 * @Description: 链接类
 * @Date: 2019/12/31 0031
 * @program: cloud
 * @create: 2019-12-31 11:
 */
public abstract class LinkService implements HomeDeliveryService {

    //秘钥
    public static final String key = "aafc04a1bacb487fa8d03f2a7bfdb555";

    //客户标识
    public static final String clientFlag = "test";

    //常量值
    public static final String other = "z宅J急S送g";

    //常量值
    public static final String mailNo = "ZJS111747043240";

    //查询物流轨迹url
    public static final String QUERY_TRAJECTORY_URL = "http://businesstest.zjs.com.cn:9200/interface/iwc/querystatustest";


    public Object find() throws IOException {
        HomeDeliver homeDeliver = new HomeDeliver();
        homeDeliver.setClientFlag(clientFlag);
        homeDeliver.setMailNo(mailNo);

        return findQueryStatus(homeDeliver);
    }

}
