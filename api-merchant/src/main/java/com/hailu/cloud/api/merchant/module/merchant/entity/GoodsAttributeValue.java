package com.hailu.cloud.api.merchant.module.merchant.entity;

import lombok.Data;

/**
 * Created by ss on 2014/11/5.
 */
@Data
public class GoodsAttributeValue extends BaseEntity{

    /**
     * 属性值id
     */
    private Integer attrValueId;

    /**
     * 属性值名称
     */
    private String attrValueName;

    /**
     * 所属属性id
     */
    private Integer attrId;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 属性值排序
     */
    private Integer attrValueSort;

    private Integer delSign;
    
    /**
     * 所属性值名称
     */
    private String attrName;
}
