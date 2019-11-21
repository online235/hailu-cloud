package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

import java.util.List;

/**
 * 实时话题
 *
 * @author leiqi
 */
@Data
public class SshtVo {
    private int id;
    private String userId; //用户id
    private String userIcon; //用户头像
    private String nickName;  //用户昵称
    private int goodsId; //商品id
    private String content; //内容
    private long time;  //创建时间
    private int pid;  //pid
    private int actId; //活动id
    private List<SshtVo> htReply;//回复

}
