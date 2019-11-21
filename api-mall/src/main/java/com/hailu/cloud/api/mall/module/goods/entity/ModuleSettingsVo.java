package com.hailu.cloud.api.mall.module.goods.entity;

import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 首页模块设置
 *
 * @author Administrator
 */
@Data
public class ModuleSettingsVo {
    /**
     * 模块名
     */
    private String msName;
    /**
     * 模块图标
     */
    private String msIcon;
    /**
     * 模块名字颜色
     */
    private String msNameColor;
    /**
     * 模块类型 小程序{1_限时抢购,2_会员周,3_精品推荐}
     */
    private String msType;

    private List list;

    public void setMsIcon(String msIcon) {
        if (StringUtils.isNotEmpty(msIcon)) {
            msIcon = Const.PRO_URL + msIcon;
        }
        this.msIcon = msIcon;
    }

    public void setMsNameColor(String msNameColor) {
        if (StringUtils.isNotEmpty(msNameColor)) {
            msNameColor = color(msNameColor);
        }
        this.msNameColor = msNameColor;
    }

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
