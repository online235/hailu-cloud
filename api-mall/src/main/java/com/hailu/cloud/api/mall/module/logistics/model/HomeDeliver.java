package com.hailu.cloud.api.mall.module.logistics.model;

import lombok.Data;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/31 0031
 * @program: cloud
 * @create: 2019-12-31 11:
 */
@Data
public class HomeDeliver {

    /**
     * 用户标识
     */
    private String clientFlag;

    /**
     * 运单号
     */
    private String mailNo;

}
