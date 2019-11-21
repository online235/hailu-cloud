package com.hailu.cloud.api.mall.module.goods.entity.goods;

import lombok.Data;

import java.util.List;

/**
 * 会员特惠
 *
 * @author Administrator
 */
@Data
public class SpecialOffer {
    private Integer soId;
    /**
     * 开始时间
     */
    private Long startDate;
    /**
     * 结束时间
     */
    private Long endDate;
    /**
     * 会员特惠商品
     */
    private List<SpecialOfferSetting> offerSettingList;

}
