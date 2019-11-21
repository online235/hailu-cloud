package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

@Data
public class AddressVo {
    private String person;//收货人
    private String phone;//联系方式
    private String area;//地址
    private String address;//详细地址
}
