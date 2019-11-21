package com.hailu.cloud.api.mall.module.goods.vo;

import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsActVo;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsListVo;
import lombok.Data;

import java.util.List;

@Data
public class RecommendVo {
    private Integer recommendId; // id
    private Integer showNum; // app显示条数
    private Integer isReserve; // 是否预定 0_否,1_是
    private Integer isActivity; // 是否活动 0_否,1_是
    private Integer isPublish; // 是否首发 0_否,1_是
    private Integer gcBigId; // 类型id
    private String gcBigName; // 类型名
    private String recommImg;//图片
    private String recommendInfo; //类型名
    private String coverImg;//封面图片
    private String titleColor; //文字颜色
    private List<GoodsActVo> goodsAct; //活动
    private List<GoodsListVo> goodsList; //商品
    private List<NewGoodsVo> newGoods;

    public static String color(String c) {
        String co = "";
        switch (c) {
            case "0":
                co = "#d32836";
                break;
            case "1":
                co = "#3aa9e2";
                break;
            case "2":
                co = "#01994b";
                break;
            case "3":
                co = "#f09319";
                break;
            case "4":
                co = "#0bcbf2";
                break;
            case "5":
                co = "#b169ee";
                break;
            case "6":
                co = "#17a9eb";
                break;
            case "7":
                co = "#75bf25";
                break;
            case "8":
                co = "#fd7f03";
                break;
            case "9":
                co = "#f75bbb";
                break;
            default:
                break;
        }

        return co;
    }

}
