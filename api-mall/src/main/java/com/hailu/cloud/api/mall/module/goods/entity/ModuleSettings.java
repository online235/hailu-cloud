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
public class ModuleSettings {
    /**
     * 主键id
     */
    private Integer msId;
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
     * 模块显示位置 1_app,2_小程序
     */
    private String msShowSite;
    /**
     * 单行显示条数
     */
    private Integer msShowNum;
    /**
     * 模块类型 app { 1_预定,2_限时抢购,3_新品首发,4_分类}  ,小程序{1_限时抢购,2_会员周,3_精品推荐}
     */
    private String msType;
    /**
     * 模块分类id
     */
    private Integer msGcId;
    /**
     * 封面图片
     */
    private String msCoverImg;
    /**
     * 是否起用 0_否,1_是
     */
    private String msState;
    /**
     * 序号
     */
    private Integer msSort;

    private List list;

    public void setMsIcon(String msIcon) {
        if (StringUtils.isNotEmpty(msIcon)) {
            msIcon = Const.PRO_URL + msIcon;
        }
        this.msIcon = msIcon;
    }

    public void setMsCoverImg(String msCoverImg) {
        if (StringUtils.isNotEmpty(msCoverImg)) {
            msCoverImg = Const.PRO_URL + msCoverImg;
        }
        this.msCoverImg = msCoverImg;
    }

}
