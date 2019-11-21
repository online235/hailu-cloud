package com.hailu.cloud.api.mall.module.goods.entity;

import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Administrator
 */
@Data
public class Navigation {

    private Integer navId;
    /**
     *
     */
    private Long createTime;
    /**
     * 类型1_首页导航,2_底部导航
     */
    private String navType;
    /**
     * 导航标题
     */
    private String navName;
    /**
     * 导航icon
     */
    private String navIcon;
    /**
     * 点击后切换的icon (底部导航使用)
     */
    private String navChickIcon;

    /**
     * 导航链接
     */
    private String navUrl;
    /**
     * 排序
     */
    private Integer sort;

    public void setNavIcon(String navIcon) {
        if (StringUtils.isNotEmpty(navIcon)) {
            navIcon = Const.PRO_URL + navIcon;
        }
        this.navIcon = navIcon;
    }

    public void setNavChickIcon(String navChickIcon) {
        if (StringUtils.isNotEmpty(navChickIcon)) {
            navChickIcon = Const.PRO_URL + navChickIcon;
        }
        this.navChickIcon = navChickIcon;
    }

}
