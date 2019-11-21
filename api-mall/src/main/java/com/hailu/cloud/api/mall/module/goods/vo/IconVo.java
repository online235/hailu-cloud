package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

@Data
public class IconVo {
    private String id;
    private String iconPath;//图标地址
    private String name;//图标名称
    private String type;//图标类型     1:商品     2:首页功能图标

}
