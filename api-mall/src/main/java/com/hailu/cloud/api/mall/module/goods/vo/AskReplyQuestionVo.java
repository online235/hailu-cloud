package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

import java.util.List;

/**
 * 问题vo
 *
 * @author 黄亮  E-mail 1428516543@qq.com
 */
@Data
public class AskReplyQuestionVo {
    private int askReplyId;
    /**
     * 内容
     */
    private String context;
    /**
     * 上面名称
     */
    private String goodsName;
    /**
     * 商品图片
     */
    private String goodsImg;

    private int goodsId;
    private String userImg;
    private long createTime; //评论时间

    private int COUNT; //回答数量
    private List<AskReplyVo> askReply; //回答vo
}
