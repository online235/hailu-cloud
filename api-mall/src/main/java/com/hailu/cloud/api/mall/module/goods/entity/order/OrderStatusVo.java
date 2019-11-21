package com.hailu.cloud.api.mall.module.goods.entity.order;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class OrderStatusVo {
    private int id;
    private int orderId;
    private String orderNumber;
    private int goodsId;
    private String userId;
    private int state;
    private String remark;

}
