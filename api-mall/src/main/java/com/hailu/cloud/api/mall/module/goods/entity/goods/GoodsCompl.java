package com.hailu.cloud.api.mall.module.goods.entity.goods;


import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class GoodsCompl {
    /**
     * 赠品id
     */
    private Integer complId;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 赠送商品id
     */
    private Integer complGoodsId;
    /**
     * 开始时间
     */
    private long startTime;
    /**
     * 结束时间
     */
    private long endTime;
    /**
     * 赠送规格id
     */
    private Integer complSpecId;
    /**
     * 赠送规格名
     */
    private String complSpecName;
    /**
     * 贈送數量
     */
    private Integer complNumber;
    /**
     * 赠品名
     */
    private String complName;
    /**
     * 赠品图
     */
    private String complImage;

    public void setComplImage(String complImage) {
        if (StringUtils.isNotEmpty(complImage)) {
            complImage = Const.PRO_URL + complImage;
        }
        this.complImage = complImage;
    }

}
