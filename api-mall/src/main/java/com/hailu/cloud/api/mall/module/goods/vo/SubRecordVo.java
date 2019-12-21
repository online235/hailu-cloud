package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

@Data
public class SubRecordVo {
    private int id;
    private int goodsId;//商品id
    private String buySource;//购买来源
    private long buyTime;//购买日期
    private String modelNumber;//产品型号
    private String productionNumber;//生产编号
    private String userId;//用户id
    private int specId;//规格id
    private String specName;//规格名
    private String applyForOdd; //申请编号
    private long createTime;//申请时间
    private long updateTime; //更新时间

}
