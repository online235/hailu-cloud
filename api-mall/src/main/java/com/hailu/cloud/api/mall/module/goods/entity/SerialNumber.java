package com.hailu.cloud.api.mall.module.goods.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class SerialNumber implements Serializable {
    private static final long serialVersionUID = 1610353311205134651L;
    private Integer id;
    private String type;
    private Long serialNumber;
    private Integer serial;
}
