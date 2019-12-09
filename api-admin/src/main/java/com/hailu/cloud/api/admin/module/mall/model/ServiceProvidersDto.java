package com.hailu.cloud.api.admin.module.mall.model;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import lombok.Data;


/**
 * @author 190726
 */
@Data
@InjectDict
public class ServiceProvidersDto {

    private Long id;

    /**
     * 服务商名称
     */

    private String name;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 省ID
     */

    private Long provinceId;

    @DictName(code = "NATION", joinField = "provinceId")
    private String provinceIdStr;

    /**
     * 市ID
     */
    private Long cityId;

    @DictName(code = "NATION", joinField = "cityId")
    private String cityIdStr;

    /**
     * 区县ID
     */

    private Long areaId;

    @DictName(code = "NATION", joinField = "areaId")
    private String areaIdStr;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 礼包名称
     */
    private String goodsName;

    /**
     * 会员Id
     */
    private String userId;

    /**
     * 是否服务商
     */
    private Integer isService;
}
