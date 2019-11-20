package com.hailu.cloud.api.merchant.module.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class GoodsClass implements Serializable {

    private static final long serialVersionUID = 6210315253221632717L;
    /**
     * 主键
     */
    private Integer gcId;

    /**
     * 分类名称
     */
    private String gcName;

    /**
     * 分类图标
     */
    private String gcPic;

    /**
     * 类型ID
     */
    private Integer typeId;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 父ID
     */
    private Integer gcParentId;

    /**
     * 排序
     */
    private Integer gcSort;

    /**
     * 0为否，1为是，默认为1
     */
    private Integer gcShow;

    /**
     * 名称
     */
    private String gcTitle;

    /**
     * 关键词
     */
    private String gcKeywords;

    /**
     * 描述
     */
    private String gcDescription;

    /**
     * 层级path
     */
    private String gcIdpath;

    private int deep;

    private int hasChild;

    /**
     * 佣金比例
     */
    private Float expenScale;

    /**
     * 是否关联子分类 0否, 1是
     */
    private Integer isRelate;

    private List<GoodsClass> classList;

    private String searchType = "gcIdSearch";

    /**
     * 最后一级所有的第一个分类名称(手机客户端使用)
     */
    private String gcLastChild;

    /**
     * 富文本
     */
    private String content;

    /**
     * pc图标
     */
    private String classIconPc;

    /**
     * 是否推荐 1是 0否
     */
    private Integer isRecommend;

    /**
     * PC一级分类图标地址
     */
    private String classification;

    /**
     * PC一级分类图标地址 选中状态
     */
    private String classificationNo;

    /**
     * PC售后图片
     */
    private String afterSalesImg;

    /**
     * 删除状态
     */
    private String isDel;

}
