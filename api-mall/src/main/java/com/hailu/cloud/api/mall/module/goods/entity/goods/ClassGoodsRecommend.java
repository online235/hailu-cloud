package com.hailu.cloud.api.mall.module.goods.entity.goods;

import com.hailu.cloud.api.mall.module.goods.tool.StringUtil;
import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class ClassGoodsRecommend {

    private int goodsId;
    private String goodsName;
    private String goodsImage;

    public void setGoodsImage(String goodsImage) {
        if (StringUtil.isNotEmpty(goodsImage) && !goodsImage.contains("http")) {
            goodsImage = Const.PRO_URL + goodsImage;
        }
        this.goodsImage = goodsImage;
    }
}
