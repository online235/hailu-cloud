package com.hailu.cloud.api.mall.module.goods.entity.order;

import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsCompl;
import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车
 *
 * @author leiqi
 */
@Data
public class ShoppingCartVo {
    /**
     * 购物车id
     */
    private int cartId;
    /**
     * 商品id
     */
    private int goodsId;
    /**
     * 商品id
     */
    private Long storeId;
    /**
     * 商品名称
     */
    private String shopName;
    /**
     * 会员id
     */
    private String userId;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 会员价格
     */
    private BigDecimal specGoodsVipPrice;
    /**
     * 进货价格
     */
    private BigDecimal specGoodsPurchasePrice;
    /**
     * 提成
     */
    private BigDecimal commission;
    /**
     * 是否参与推广 0_否  1_参与
     */
    private int isPopularize;
    /**
     * 购买商品数量
     */
    private int goodsNum;
    /**
     * 商品所在二级分类的id
     */
    private Integer firstGcId;
    /**
     * 商品所在一级分类的id
     */
    private Integer firstGcbigId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 规格id
     */
    private int specId;
    /**
     * 规格内容
     */
    private String specName;
    /**
     * 商品图片
     */
    private String goodsImages;
    /**
     * 商品类型 0_正常商品  2耗配,3_抽奖
     */
    private Integer type;
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 更新时间
     */
    private long updateTime;
    /**
     * 是否活动 1_是 0_否
     */
    private int isActivity;
    /**
     * 是否有赠品
     */
    private int isCompl;
    /**
     * 是否选中
     */
    private Integer isSelected;
    /**
     * 状态
     */
    private int state;
    /**
     * 满额包邮
     */
    private Double fullFreeMail;
    /**
     * 运费模板id
     */
    private Integer transportId;
    /**
     * 重量
     */
    private double weight;
    /**
     * 体积
     */
    private double volume;
    /**
     * 0 关闭 1_开启
     */
    private String isShare;


    public void setGoodsImages(String goodsImages) {
        if (StringUtils.isNotEmpty(goodsImages) && !goodsImages.contains("http")) {
            goodsImages = Const.PRO_URL + goodsImages;
        }
        this.goodsImages = goodsImages;
    }

}
