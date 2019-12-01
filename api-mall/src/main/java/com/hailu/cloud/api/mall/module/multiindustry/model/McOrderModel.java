package com.hailu.cloud.api.mall.module.multiindustry.model;

import com.hailu.cloud.api.mall.module.multiindustry.entity.MultiIndustryOrder;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import lombok.Data;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/1 0001
 */
@Data
@InjectDict
public class McOrderModel extends MultiIndustryOrder {

    /**
     * 店铺默认头像
     */
    private String defaultHead;
}
