package com.hailu.cloud.api.mall.module.goods.entity.goods;

import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 会员特惠商品
 *
 * @author Administrator
 */
@Data
public class SpecialOfferGoods {

    private Integer sogId;

    private Integer soId;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 显示图片
     */
    private String showImg;


    public void setShowImg(String showImg) {
        if (StringUtils.isNotEmpty(showImg)) {
            showImg = Const.PRO_URL + showImg;
        }
        this.showImg = showImg;
    }

}
