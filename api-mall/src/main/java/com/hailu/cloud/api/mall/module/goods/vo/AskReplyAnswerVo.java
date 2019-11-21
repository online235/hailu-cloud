package com.hailu.cloud.api.mall.module.goods.vo;


import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class AskReplyAnswerVo {
    private int askReplyId;
    private String userId;
    private int goodsId;
    private String goodsName;
    private String goodsImg;
    private String userName;
    private String userImg;
    private String context;
    private int pid;
    private long createTime;

}
