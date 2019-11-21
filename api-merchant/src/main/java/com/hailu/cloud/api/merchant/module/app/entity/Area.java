package com.hailu.cloud.api.merchant.module.app.entity;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * shop_area 地区表
 * 
 * @项目名称：ddkj-entity
 * @类名称：Area
 * @类描述：
 * @修改备注：
 * @version
 * 
 */
@Data
@ToString
public class Area {
	/**
	 * 主键ID
	 */
	private Integer areaId;
	/**
	 * 地区名称
	 */
	private String areaName;
	/**
	 * 地区父ID
	 */
	private Integer areaParentId;
	/**
	 * 邮编
	 */
	private String zipCode;
	/**
	 * 当前地区的下级地区集合
	 */
	private List<Area> childern = Lists.newArrayList();

    /**
     * 是否全选
     */
    private Boolean isChecked;

    private int countChildren;
}
