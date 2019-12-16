package com.hailu.cloud.api.merchant.module.merchant.model;

import com.hailu.cloud.common.model.page.PageInfoModel;
import lombok.Data;

import java.util.List;

/**
 * @Author xuzhijie
 * @Date 2019/10/23 9:05
 */
@Data
public class GoodsData extends PageInfoModel<List<Goods>> {

    /**
     * 品牌列表
     */
    private List<Brand> brandList;

    /**
     * 商品管理-分类列表
     */
    private List<GoodsClass> classList;

    /**
     * 规格图片上传路径
     */
    private String imgSrc;

}
