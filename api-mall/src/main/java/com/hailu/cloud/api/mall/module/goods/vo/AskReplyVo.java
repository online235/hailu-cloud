package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

/**
 * 回答vo
 *
 * @author 黄亮  E-mail 1428516543@qq.com
 */
@Data
public class AskReplyVo {
    private long createTime; //回答时间
    private String userImg; //用户头像
    private String context;//回复内容
    private String userName; //用户名
    private String userId; //用户ID

}
