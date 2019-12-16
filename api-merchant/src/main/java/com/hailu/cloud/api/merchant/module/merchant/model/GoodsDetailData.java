package com.hailu.cloud.api.merchant.module.merchant.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author xuzhijie
 * @Date 2019/10/23 9:05
 */
@Data
public class GoodsDetailData {

    /**
     * 商品
     */
    private Goods goods;

    /**
     * 商品规格
     */
    private List<GoodsSpec> goodsSpecs;

    /**
     * 商品库存数量
     */
    private Integer goodsStoreNum;

    /**
     * 商品图片
     */
    private List<String> imageList;

    /**
     * app详情
     */
    private List<String> goodsBodyList;

    /**
     * 商品属性
     */
    private List<GoodsAttrVo> attrVoList;

    /**
     * 商品属性MAP
     */
    private Map<String, List<GoodsSpecVo>> specMap;

    /**
     * 规格颜色的图片
     */
    private Map<String, String> colImgMap;

    /**
     * 商品类型-品牌
     */
    private List<Brand> brands;

    /**
     * 商品类型-规格
     */
    private List<SpecVo> specs;

    /**
     * 商品类型-属性
     */
    private List<GoodsAttribute> goodsAttributes;

    /**
     * 商品参数列表
     */
    private List<GoodsParameterVo> parameterVoList;

    /**
     * 商品包装列表
     */
    private List<PackagingVo> packagingVoList;

    /**
     * 一级地区
     */
    private List<Area> areas;

    /**
     * 运费模板
     */
    private Transport transport;

    /**
     * 图片server路径
     */
    private String imgServer;

    /**
     * 图片目录
     */
    private String imgSrc;

    /**
     * 商品分类ID
     */
    private String gcId;

    /**
     * 商品分类名称
     */
    private String gcName;

    /**
     * 商品类型ID
     */
    private Integer typeId;

    /**
     * 获取售后pc默认模板
     */
    private String pcParentText;

}
