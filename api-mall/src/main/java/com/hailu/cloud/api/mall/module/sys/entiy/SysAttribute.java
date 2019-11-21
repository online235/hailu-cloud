package com.hailu.cloud.api.mall.module.sys.entiy;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by somebody on 2016/6/29.
 * 系统属性表
 */
@Data
public class SysAttribute implements Serializable {

    private static final long serialVersionUID = 5521568353427532963L;

    /**
     * id
     */
    private int id;

    /**
     * 属性key
     */
    private String attributeKey;

    /**
     * 属性value
     */
    private String attributeValue;
    /**
     * 属性备注
     */
    private String attributeNote;

    /**
     * type 0表示图片1表示文字2表示网页
     */
    private String type;

}
