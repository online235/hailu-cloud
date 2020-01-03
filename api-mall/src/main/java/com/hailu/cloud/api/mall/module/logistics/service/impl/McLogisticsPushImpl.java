package com.hailu.cloud.api.mall.module.logistics.service.impl;

import com.hailu.cloud.api.mall.module.logistics.dao.McLogisticsPushMapper;
import com.hailu.cloud.api.mall.module.logistics.entity.McLogisticsPush;
import com.hailu.cloud.api.mall.module.logistics.service.McLogisticsPushService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: QiuFeng:WANG
 * @Description: 推送回调
 * @Date: 2020/1/3 0003
 * @program: cloud
 * @create: 2020-01-03 14:
 */
@Service
public class McLogisticsPushImpl implements McLogisticsPushService {

    @Resource
    private McLogisticsPushMapper mcLogisticsPushMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Override
    public McLogisticsPush insertSelective(McLogisticsPush record) {
        record.setId(basicFeignClient.uuid().getData());
        record.setDateTime(new Date());
        mcLogisticsPushMapper.insertSelective(record);
        return record;
    }
}
