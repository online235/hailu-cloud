package com.hailu.cloud.api.mall.module.goods.entity.goods;

import lombok.Data;

import java.util.List;

@Data
public class GoodsClassVo {
    /**
     * 分类id
     */
    private Integer gcId;
    /**
     * 分类名
     */
    private String gcName;
    /**
     * 分类图片
     */
    private String gcPic;
    /**
     * 父Id
     */
    private Integer parentId;

    private List<GoodsClassVo> goodsList;

}
