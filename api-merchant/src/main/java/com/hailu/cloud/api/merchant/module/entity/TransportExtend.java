package com.hailu.cloud.api.merchant.module.entity;



import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransportExtend {
	private Integer id;
	
	/**
	 * 平邮py 快递kd EMS es
	 */
	private String type;

	private String areaId;
	private String topAreaId;
	private String areaName;
	/**
	 * 寄件类型,1_重量,2_体积
	 */
	private String shippingType;

	/**
	 * 首件(重量/体积)
	 */
	private Double snum;
	private Double sprice;
	/**
	 * 续件(重量/体积)
	 */
	private Double xnum;
	private Double xprice;
	private String volumeUnit;
	private Integer transportId;
	private String transportTitle;
	/*
	 * 快递id
	 */
	private Long expressId;
	private String expressName;

	/**
	 * 0:未删除;1.已删除
	 */
	private int isDel;

	/**
	 * 创建时间
	 */
	private Long createdTime;
	/**
	 * 更新时间
	 */
	private Long updatedTime;
}
