package com.hailu.cloud.api.mall.module.goods.entity;

import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 首页商品推荐
 *
 * @author Administrator
 */
@Data
public class HomeRecommendGoods {
    private Integer hrgId;
    /**
     * 商品id
     */
    private String hrgGoodsId;
    /**
     * 显示图片
     */
    private String hrgShowImg;

    public void setHrgShowImg(String hrgShowImg) {
        if (StringUtils.isNotEmpty(hrgShowImg)) {
            hrgShowImg = Const.PRO_URL + hrgShowImg;
        }
        this.hrgShowImg = hrgShowImg;
    }

}
