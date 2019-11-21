package com.hailu.cloud.api.merchant.module.app.entity;

import lombok.Data;

/**
 * 商品参数
 * @author leiqi
 *
 */
@Data
public class GoodsParameterVo {
	private int id;
	private int goodsId;
	/**
	 * 参数名称
	 */
	private String parameterName;
	/**
	 * 参数值
	 */
	private String parameterValue;
}
