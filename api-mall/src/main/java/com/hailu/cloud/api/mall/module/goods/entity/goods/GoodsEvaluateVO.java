package com.hailu.cloud.api.mall.module.goods.entity.goods;

import com.hailu.cloud.api.mall.module.common.domain.DataPage;
import lombok.Data;

/**
 * 商品评价
 *
 * @author Administrator
 */
@Data
public class GoodsEvaluateVO extends DataPage<GoodsEvaluateVO> {
    /**
     * 评价ID
     */
    private int gevalId;
    /**
     * 订单表自增ID
     */
    private int gevalOrderid;
    /**
     * 订单编号
     */
    private int gevalOrderno;
    /**
     * 订单商品表编号
     */
    private int gevalOrdergoodsid;
    /**
     * 商品表编号
     */
    private int gevalGoodsid;
    /**
     * 商品名称
     */
    private String gevalGoodsname;
    /**
     * 商品价格
     */
    private double gevalGoodsprice;
    /**
     * 1-5分
     */
    private int gevalScores;
    /**
     * 信誉评价内容
     */
    private String gevalContent;
    /**
     * 0表示不是 1表示是匿名评价
     */
    private int gevalIsanonymous;
    /**
     * 店铺编号
     */
    private int gevalStoreid;
    /**
     * 店铺名称
     */
    private String gevalStorename;
    /**
     * 评价人编号
     */
    private int gevalFrommemberid;
    /**
     * 评价人名称
     */
    private String gevalFrommembername;
    /**
     * 评价信息的状态 0为正常 1为禁止显示
     */
    private int gevalState;
    /**
     * 管理员对评价的处理备注
     */
    private String gevalRemark;
    /**
     * 解释内容
     */
    private String gevalExplain;
    /**
     * 晒单图片
     */
    private String gevalImage;
    /**
     * 0:未删除;1.已删除
     */
    private int isDel;
    /**
     * 创建时间
     */
    private int createTime;
    /**
     * 修改时间
     */
    private int updateTime;
    /**
     * 规格名
     */
    private String specInfo;
    /**
     * 服务评分
     */
    private int serviceScore;
    /**
     * 物流评分
     */
    private int logisticsScore;
    /**
     * 好评1，中评 2，差评3
     */
    private int comment;
    /**
     * 点赞数
     */
    private int thumbUpNumber;
    /**
     * 评价人头像
     */
    private String gevalFrommemberimg;

}
