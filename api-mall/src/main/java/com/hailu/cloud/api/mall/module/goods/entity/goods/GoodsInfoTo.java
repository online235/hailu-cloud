package com.hailu.cloud.api.mall.module.goods.entity.goods;

import com.hailu.cloud.api.mall.module.goods.vo.ActDiscounts;
import com.hailu.cloud.api.mall.module.goods.vo.PackagingVo;
import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 得到商品详情
 *
 * @author Administrator
 */
@Data
public class GoodsInfoTo {
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品描述
     */
    private String goodsDescription;
    /**
     * 店铺id
     */
    private Integer storeId;
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
     * 商品二级名
     */
    private String gcName;
    /**
     * 商品一级类型id
     */
    private Integer gcBigId;
    /**
     * 商品一级名
     */
    private String gcBigName;
    /**
     * 商品封面图片
     */
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
     * 是否能使用优惠券 0_否,1_是
     */
    private String isCoupon;
    /**
     * 服务s
     */
    private String services;
    /**
     * 商品包装
     */
    private List<PackagingVo> goodsPack;
    /**
     * 商品参数
     */
    private List<GoodsParameterVo> goodsParameter;
    /**
     * 商品评价list
     */
    private List<GoodsEvaluateTO> evaluateTOS;
    /**
     * 满减优惠
     */
    private List<ActDiscounts> discounts;
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

    public void setGoodsImageMore(String goodsImageMore) {
        if (StringUtils.isNotEmpty(goodsImageMore)) {
            StringBuilder sb = new StringBuilder();
            for (String str : goodsImageMore.split(",")) {
                if (StringUtils.isNotEmpty(str)) {
                    sb.append(Const.PRO_URL).append(str).append(",");
                }
            }
            goodsImageMore = sb.substring(0, sb.length() - 1);
        }
        this.goodsImageMore = goodsImageMore;
    }

    public void setGoodsBody(String goodsBody) {
        if (StringUtils.isNotEmpty(goodsBody)) {
            StringBuilder sb = new StringBuilder();
            for (String str : goodsBody.split(",")) {
                if (StringUtils.isNotEmpty(str)) {
                    sb.append(Const.PRO_URL).append(str).append(",");
                }
            }
            goodsBody = sb.substring(0, sb.length() - 1);
        }
        this.goodsBody = goodsBody;
    }

}
