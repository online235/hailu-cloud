package com.hailu.cloud.api.mall.module.logistics.service;

import com.hailu.cloud.api.mall.module.logistics.entity.McLogisticsPush;

/**
 * @Author: QiuFeng:WANG
 * @Description: 推送回调
 * @Date: 2020/1/3 0003
 * @program: cloud
 * @create: 2020-01-03 14:
 */
public interface McLogisticsPushService {


    /**
     * 添加回调信息
     * @param record
     * @return
     */
    McLogisticsPush insertSelective(McLogisticsPush record);

}
