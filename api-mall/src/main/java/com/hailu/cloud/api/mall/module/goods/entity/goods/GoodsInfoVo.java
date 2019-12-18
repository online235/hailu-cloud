package com.hailu.cloud.api.mall.module.goods.entity.goods;

import com.hailu.cloud.api.mall.module.goods.vo.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 商品详情
 *
 * @author Administrator
 */
@Data
public class GoodsInfoVo {
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品副标题
     */
    private String goodsSubtitle;
    /**
     * 商品默认封面图片
     */
    private String goodsImage;
    /**
     * 商品描述
     */
    private String goodsDescription;
    /**
     * 店铺id
     */
    private String storeId;
    /**
     * 店铺名
     */
    private String storeName;
    /**
     * 规格id
     */
    private Integer specId;
    /**
     * 商品二级类型id
     */
    private Integer gcId;
    /**
     * 商品一级类型id
     */
    private Integer gcBigId;
    /**
     * 商品一级名
     */
    private String gcBigName;
    /**
     * 商品二级名
     */
    private String gcName;
    private String goodsImageMore;
    /**
     * 满额包邮
     */
    private Double fullFreeMail;
    /**
     * 运费模板id
     */
    private Integer transportId;
    /**
     * 商品详细
     */
    private String goodsBody;
    /**
     * 是否收藏 1_是,0_否
     */
    private int isCollect;
    /**
     * 商品包装
     */
    private List<PackagingVo> goodsPack;
    /**
     * 商品参数
     */
    private List<GoodsParameterVo> goodsParameter;
    /**
     * 是否有赠品 1_是,0_否
     */
    private Integer isCompl;
    /**
     * 限时抢购
     */
    private List<ActGoodsPriceVo> timeLimit;
    /**
     * 活动类型
     */
    private Integer activityType;
    /**
     * 满减优惠
     */
    private List<ActDiscounts> dis;
    /**
     * 所有规格
     */
    private List<Map<String, Object>> spec;
    /**
     * 所有规格对应id
     */
    private List<Map<String, Object>> specGoods;
    /**
     * 商品赠品
     */
    private List<GoodsCompl> compl;
    /**
     * 项目进展
     */
    private List<ProgressVo> progress;
    /**
     * 实时话题
     */
    private List<SshtVo> ssht;
    /**
     * 重量
     */
    private BigDecimal weight;
    /**
     * 体积
     */
    private BigDecimal volume;


    /**
     * 是否参与推广 0_否  1_参与
     */
    private int isPopularize;

}
