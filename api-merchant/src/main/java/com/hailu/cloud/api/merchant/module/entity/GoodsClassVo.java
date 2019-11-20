package com.hailu.cloud.api.merchant.module.entity;

import lombok.Data;

@Data
public class GoodsClassVo {
    /**
     *主键
     */
    private Integer gcId;
    /**
     *父ID
     */
    private Integer gcParentId;

    /**
     *分类名称
     */
    private String gcName;

}
