package com.hailu.cloud.api.mall.module.goods.entity.goods;

import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * 会员特惠
 *
 * @author Administrator
 */
@Data
public class WeekFeaturedGoods {
    /**
     * d
     */
    private Integer wfgId;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 商品图片
     */
    private String goodsImage;
    /**
     * 商品金额
     */
    private BigDecimal goodsPrice;
    /**
     * 显示图片 广告用
     */
    private String showImg;

    public void setGoodsImage(String goodsImage) {
        if (StringUtils.isNotEmpty(goodsImage)) {
            goodsImage = Const.PRO_URL + goodsImage;
        }
        this.goodsImage = goodsImage;
    }

    public void setShowImg(String showImg) {
        if (StringUtils.isNotEmpty(showImg)) {
            showImg = Const.PRO_URL + showImg;
        }
        this.showImg = showImg;
    }
}
