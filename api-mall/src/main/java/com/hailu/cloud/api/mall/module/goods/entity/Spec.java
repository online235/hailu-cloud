package com.hailu.cloud.api.mall.module.goods.entity;

import lombok.Data;

import java.util.List;

/**
 * 规格
 *
 * @author Administrator
 */
@Data
public class Spec {

    private String spName;

    private List<SpecValue> specValueList;

}
