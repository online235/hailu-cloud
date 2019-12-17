package com.hailu.cloud.api.mall.module.goods.service;


import com.hailu.cloud.api.mall.module.goods.entity.SerialNumber;

import java.util.List;

/**
 * @Author HuangL
 * @Email huangl96@163.com
 * @Description 流水号Servier
 * @Date 2019/1/25 11:06
 */
public interface ISerialNumberService {
    /**
     * @Author HuangL
     * @Email huangl96@163.com
     * @Description 得到交易流水号
     * @Date 2019/1/25 11:30
     */
    SerialNumber verifySerialNumber(int code);

    /**
     * @Author HuangL
     * @Email huangl96@163.com
     * @Description 更改掉订单顺序
     * @Date 2019/1/28 15:51
     */
    void updateSerialNumber(SerialNumber serialNumber);
}
