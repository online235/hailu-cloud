package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;

@Data
@ToString
/**
 * 对应的表为shop_goods_spec
 * @author cgl
 *
 */
public class GoodsSpec {
	/**
	 * 商品规格索引id
	 */
	private Integer goodsSpecId;

	/**
	 * 商品id
	 */
	private Integer goodsId;

	/**
	 * 规格名称
	 */
	private String specName;

	/**
	 * 规格商品价格
	 */
	private BigDecimal specGoodsPrice;
	/**
	 * 规格商品VIP价格
	 */
	private BigDecimal specGoodsVipPrice;

	/**
	 * 是否参与推广 0_否  1_参与
	 */
	private int isPopularize;

	/**
	 * 规格商品库存
	 */
	private Integer specGoodsStorage;

	/**
	 * 售出数量
	 */
	private Integer specSalenum;

	/**
	 * 规格商品颜色
	 */
	private String specGoodsColor;

	/**
	 * 规格商品编号
	 */
	private String specGoodsSerial;

	/**
	 * 商品规格序列化
	 */
	private String specGoodsSpec;

	/**
	 * 这个商品颜色对应的图片
	 */
	private String colImg;

	/**
	 * 当前商品的 规格名称 对应的规格值
	 */
	private Map<String, String> sepcMap;

	/**
	 * 序列化Str经过实体化,变为map
	 */
	private Map<String, String> specGoodsSpecMap;

	/**
	 * 所有规格值id以逗号分隔
	 */
	private String specValueIdStr;

	/**
	 * 商品规格是否开启
	 */
	private Integer specIsOpen;

	private String str;

	//商品重量(kg)
	private BigDecimal weight;
	//商品体积
	private BigDecimal volume;
	//提成
	private BigDecimal commission;
	//进货价
	private BigDecimal specGoodsPurchasePrice;
}
