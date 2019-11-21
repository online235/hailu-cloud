package com.hailu.cloud.api.mall.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 刘柱栋
 * @Description 接口请求参数基类
 * @date 2016/6/7 21:40
 * @copyright Jelly.Liu. All rights reserved. Mail to liuzhudong57@gmail.com
 * @since v1.0
 */
@Data
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = -2599849962601508061L;

    /**
     * 版本号
     */
    private String appVersion;

    /**
     * 来源
     */
    private String source;
}
