package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

@Data
public class SubRecordTo {

    private int id;
    private int goodsId;//商品id
    private long createTime;//申请时间
    private String smallImg; //商品图片
    private String goodsName;
    private String specName;//规格名
    private int auditState; //审核状态  0_审核中,1_审核通过,2_审核失败,3_已取消

}
