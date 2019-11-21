package com.hailu.cloud.api.mall.module.goods.vo;

import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsTo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ActivityVo {
    private int id;
    private String activityName;
    private String iconUrl;
    private String imgUrl;
    private String context;
    private String remark;
    private int type;
    private Date activityStartTime;//活动开始时间
    private Date activityEndTime;//活动结束时间
    private Date currentTime;//当前时间
    private String goodsId;//商品id
    private List<GoodsTo> goodsTos;//商品
}
