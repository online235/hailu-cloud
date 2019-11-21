package com.hailu.cloud.api.mall.module.goods.entity.goods;

import com.hailu.cloud.api.mall.module.goods.vo.ActGoodsPriceVo;
import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class GoodsActVo {
    private Integer actId;
    /**
     * 活动开始时间
     */
    private long activityStartTime;
    /**
     * 活动结束时间
     */
    private long activityEndTime;
    /**
     * 每人限购数量
     */
    private Integer limitNumber;
    /**
     * 活动类型 1_限时抢购,2_满减优惠
     */
    private Integer activityType;
    /**
     * 活动描述
     */
    private String articleDescribe;
    /**
     * 活动名
     */
    private String activityName;
    private String reserveFlow;
    private long reserveTwoStartTime;
    private long reserveTwoEndTime;
    private int reservePayType;
    /**
     * 活动详情
     */
    private List<ActGoodsPriceVo> actGoods;
}
