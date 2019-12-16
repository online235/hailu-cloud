package com.hailu.cloud.api.mall.module.goods.entity.goods;

import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 商品评价
 *
 * @author Administrator
 */
@Data
public class GoodsEvaluateTO {
    /**
     * 信誉评价内容
     */
    private String gevalContent;
    /**
     * 0表示不是 1表示是匿名评价
     */
    private int gevalIsanonymous;
    /**
     * 评价时间
     */
    private long createTime;
    /**
     * 评价人编号
     */
    private String gevalFrommemberid;
    /**
     * 评价人名称
     */
    private String gevalFrommembername;
    /**
     * 评价人头像
     */
    private String gevalFrommemberimg;
    /**
     * 晒单图片
     */
    private String gevalImage;
    /**
     * 好评1，中评 2，差评3
     */
    private int comment;
    /**
     * 点赞数
     */
    private int thumbUpNumber;
    /**
     * 1-5分
     */
    private int gevalScores;

    public void setGevalFrommemberimg(String gevalFrommemberimg) {
        if (StringUtils.isNotEmpty(gevalFrommemberimg) && !("http").equals(gevalFrommemberimg.substring(0, 4))) {
            gevalFrommemberimg = Const.PRO_URL + gevalFrommemberimg;
        }
        this.gevalFrommemberimg = gevalFrommemberimg;
    }

    public void setGevalImage(String gevalImage) {
        if (StringUtils.isNotEmpty(gevalImage) && !"null".equals(gevalImage)) {
            StringBuilder sb = new StringBuilder();
            for (String str : gevalImage.split(",")) {
                sb.append(str).append(",");
            }
            gevalImage = sb.substring(0, sb.length() - 1);
        }
        this.gevalImage = gevalImage;
    }
}
