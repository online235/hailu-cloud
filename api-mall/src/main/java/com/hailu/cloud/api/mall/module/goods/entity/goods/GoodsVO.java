package com.hailu.cloud.api.mall.module.goods.entity.goods;

import com.hailu.cloud.api.mall.module.common.domain.DataPage;
import lombok.Data;

import java.util.Date;

/**
 * 商品表
 *
 * @author Administrator
 */
@Data
public class GoodsVO extends DataPage<GoodsVO> {

    private static final long serialVersionUID = -2955805150041080500L;

    /**
     * ID
     */
    private int id;

    /**
     * 外键ID
     */
    private String fid;

    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品描述
     */
    private String goodsDetails;

    /**
     * 商品icon
     */
    private String icon;

    /**
     * 商品类型(1=金钱
     */
    private int goodsType;

    /**
     * 商品价格
     */
    private double goodsPrice;

    /**
     * 有效天数
     */
    private int effeDay;

    /**
     * 商品状态（1=在售）
     */
    private int status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 商品顺序
     */
    private int sort;

}
