package com.hailu.cloud.api.mall.module.goods.vo;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.format.FastDateFormat;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * 这个vo是返回对应的活动数据
 *
 * @author 黄亮  E-mail 1428516543@qq.com
 */
@Data
public class ActPriceVo {
    private int actPriceId; //这个是活动商品id
    private Long startTime; //开始时间
    private Long endTime; //结束时间
    private Integer limitNumber; //每人限购
    private Integer repertory; //库存
    private int hasBeen; //收出数量
    private double activityPrice; //活动金额
    private double onePayPrice; //第一阶段支付金额
    private Double pledgePrice; //折扣金额
    private double twoPayPrice; //第二阶段支付金额
    private Integer reserveType; //预定类型 1_全款 , 2_分批
    private int actId; //活动id
    private int activityType; //活动类型 1 限时抢购 2, 常规活动  , 3 众筹预定 ,4_其他限购
    private String specInfo; //规格详情
    private String specName; //规格名
    private Integer goodsId;
    private Integer goodsSpecId;
    /**
     * 周抢购 开始时间 yyyy-MM-dd
     */
    private Long mwStartDate;
    /**
     * 结束时间 10-19
     */
    private String mwStartTime;

    /**
     * 是否能使用优惠券 0_否,1_是
     */
    private String isCoupon;
    /**
     * 是否能使用红包 0_否,1_是
     */
    private String isRedEnvelope;
    /**
     * 进货价格
     */
    private BigDecimal specGoodsPurchasePrice;

    /**
     * 会员价
     */
    private BigDecimal specGoodsVipPrice;


    public void setMwStartDate(Long mwStartDate) {
        if (mwStartDate != null && StringUtils.isNotEmpty(this.mwStartTime)) {
            String[] str = this.mwStartTime.split("-");
            String string = DateUtil.format(DateUtil.date(mwStartDate), DatePattern.NORM_DATE_FORMAT);
            this.startTime = DateUtil.parse(string + " " + str[0], FastDateFormat.getInstance("yyyy-MM-dd HH")).getTime();
            this.endTime = DateUtil.parse(string + " " + str[1], FastDateFormat.getInstance("yyyy-MM-dd HH")).getTime();
        }
        this.mwStartDate = mwStartDate;
    }

    public void setMwStartTime(String mwStartTime) {
        if (this.mwStartDate != null && StringUtils.isNotEmpty(mwStartTime)) {
            String[] str = mwStartTime.split("-");
            String string = DateUtil.format(DateUtil.date(mwStartDate), DatePattern.NORM_DATE_FORMAT);
            this.startTime = DateUtil.parse(string + " " + str[0], FastDateFormat.getInstance("yyyy-MM-dd HH")).getTime();
            this.endTime = DateUtil.parse(string + " " + str[1], FastDateFormat.getInstance("yyyy-MM-dd HH")).getTime();
        }
        this.mwStartTime = mwStartTime;
    }

}
