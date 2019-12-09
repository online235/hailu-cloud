package com.hailu.cloud.api.basic.module.nation.entity;

import lombok.Data;

/**
 * 省市区字典
 */
@Data
public class Nation {

    private Long id;

    //省
    private String province;
    //市
    private String city;
    //区县
    private String district;
}
