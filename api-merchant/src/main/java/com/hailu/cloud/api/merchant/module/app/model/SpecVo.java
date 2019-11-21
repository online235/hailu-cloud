package com.hailu.cloud.api.merchant.module.app.model;


import com.hailu.cloud.api.merchant.module.app.entity.Spec;
import com.hailu.cloud.api.merchant.module.app.entity.SpecValue;
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
