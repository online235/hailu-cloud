package com.hailu.cloud.api.mall.module.goods.entity.goods;

import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

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

    public void setGcPic(String gcPic) {
        if (StringUtils.isNotEmpty(gcPic)) {
            gcPic = Const.PRO_URL + gcPic;
        }
        this.gcPic = gcPic;
    }
}
