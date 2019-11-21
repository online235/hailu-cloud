package com.hailu.cloud.api.merchant.module.app.entity;


import lombok.Data;

import java.util.List;

/**
 * 商品规格表
 */

@Data
public class SpecVo extends Spec {

    /**
     * 规格值
     */
    private List<SpecValue> specValueList;

}
