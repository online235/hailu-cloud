package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class ActGoodsPriceVo {
    private Integer goodsSpecId;//商品规格id
    private Integer goodsId;//商品id
    private Double discountPrice; //折扣多少
    private Double specPrice; //默认价格
    private String remark; //备注
    private String goodsImage; //商品默认封面图片
    private String goodsName;//商品名称
    private String goodsDescription;//商品描述
    private Double onePayPrice; //预定第一阶段支付价格
    private Double pledgePrice; //定金可抵押金额
    private Double twoPayPrice; //预定第二阶段支付价格
    private int hasBeen;//已购数量
    private int reservePayType; //预定类型 1_全款 , 2_分批
    private long nowTime;
    private long predictTime; //预计发货时间
    private long towStartTime; //第二阶段开始时间
    private long towEndTime;//第二阶段结束时间
    private String reserveFlow; //预约流程
    private int limitNumber; //限购数量
    private int virtual;//虚拟 数量

}
