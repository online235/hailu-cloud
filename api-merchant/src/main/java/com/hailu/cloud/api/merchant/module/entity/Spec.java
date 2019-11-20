package com.hailu.cloud.api.merchant.module.entity;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 商品规格表
 */

@Data
@ToString

public class Spec implements Serializable {

    private static final long serialVersionUID = -6821151359949478536L;
    /**
     * 规格ID
     */
    private Integer spId;

    /**
     * 规格名称
     */
    private String spName;
    
    /**
     * 规格呈现方式
     * 0为仅文字
     * 1文字+图片
     */
    private Integer spFormat;
    
    /**
     * 规格值,以逗号分隔
     */
    private String spValue;

    /**
     * 排序
     */
    private Integer spSort;
    
}
