package com.hailu.cloud.api.merchant.module.lifecircle.entity;


import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Transport extends BaseEntity{
	private Integer id;
	private String title;
	private Integer sendTplId;
	private Integer storeId;

	/**
	 * 0:未删除;1.已删除
	 */
	private int isDel;
	
	/**
	 * 是否是默认的运费模板 0:否 1:是
	 */
	private Integer isDefault;
	
	private List<TransportExtend> transportExtendList;
}
