package com.hailu.cloud.api.mall.module.user.entity;

import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author HuangL
 * @Email huangl96@163.com
 * @Description
 * @Date 9/6/2019 10:03 AM
 */
@Data
public class InterestsIcon {
    private String interKey;
    /**
     * name
     */
    private String interName;
    /**
     * 图标
     */
    private String interIcon;
    /**
     * 值 如果key为exclusive_discount 需要替换 &value&
     */
    private String interValue;

    public void setInterIcon(String interIcon) {
        if (StringUtils.isNotEmpty(interIcon)) {
            interIcon = Const.PRO_URL + interIcon;
        }
        this.interIcon = interIcon;
    }
}
