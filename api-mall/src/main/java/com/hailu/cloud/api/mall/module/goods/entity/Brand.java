package com.hailu.cloud.api.mall.module.goods.entity;

import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 品牌
 *
 * @author Administrator
 */
@Data
public class Brand {

    private Integer brandId;

    private String brandName;

    private String brandPic;

    public void setBrandPic(String brandPic) {
        if (StringUtils.isNotEmpty(brandPic)) {
            brandPic = Const.PRO_URL + brandPic;
        }
        this.brandPic = brandPic;
    }
}
