package com.hailu.cloud.api.mall.module.goods.entity.order;


import lombok.Data;

@Data
public class ShoppingAddressVo {
    private Integer addressId;
    private String userId;
    private String userName;
    /**
     * 收货人
     */
    private String person;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 地址
     */
    private String area;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 是否为默认地址  0 默认 1 其他
     */
    private int status;
    private Integer postcode;
    /**
     * 省级id
     */
    private Integer provinceId;
    /**
     * 市级id
     */
    private Integer cityId;
    private Integer areaId;
    /**
     * 省级名
     */
    private String provinceName;
    private Object object;

}
