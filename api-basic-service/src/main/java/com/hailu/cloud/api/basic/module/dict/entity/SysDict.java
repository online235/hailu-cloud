package com.hailu.cloud.api.basic.module.dict.entity;

import lombok.Data;

/**
 * null
 *
 * @author zhijie
 */
@Data
public class SysDict {

    /**
     * 主键
     */
    private Long id;

    /**
     * 分类code
     */
    private String code;

    /**
     * 分类描述
     */
    private String desc;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典值
     */
    private String value;

}
