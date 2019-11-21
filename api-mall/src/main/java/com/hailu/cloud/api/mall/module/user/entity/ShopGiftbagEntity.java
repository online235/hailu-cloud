package com.hailu.cloud.api.mall.module.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class ShopGiftbagEntity implements Serializable {
    private Integer giftbagId;
    private Integer bagclassId;
    private Integer giftbagType;
    private Integer couponId;
    private Integer redpacketId;
    private Integer goodsId;
    private Integer Integeregral;
    private BigInteger createDate;
    private String createName;
    private BigInteger updateDate;
    private String updateName;
    private Integer deleteStatus;
    private Integer number;
}
