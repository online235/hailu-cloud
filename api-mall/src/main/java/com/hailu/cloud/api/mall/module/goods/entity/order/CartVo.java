package com.hailu.cloud.api.mall.module.goods.entity.order;

import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsCompl;
import lombok.Data;

import java.util.List;

/**
 * 购物车
 *
 * @author leiqi
 */
@Data
public class CartVo {
    /**
     * 购物车id
     */
    private int cartId;
    /**
     * 商品id
     */
    private int goodsId;
    /**
     * 会员id
     */
    private String userId;
    /**
     * 商品价格
     */
    private double price;
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
    private List<GoodsCompl> complList;
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


}
