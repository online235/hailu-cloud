package com.hailu.cloud.api.merchant.module.app.entity;

import lombok.Data;

/**
 * 包装售后
 * @author leiqi
 *
 */
@Data
public class PackagingVo {
	private int id;
	private int goodsId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 内容
	 */
	private String context;

}
