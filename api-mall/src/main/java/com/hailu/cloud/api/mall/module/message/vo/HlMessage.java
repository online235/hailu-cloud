package com.hailu.cloud.api.mall.module.message.vo;

import com.hailu.cloud.common.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * @author junpei.deng
 */
@Data
public class HlMessage extends BaseEntity {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 信息状态（0-未读、1-已读）
     */
    private Integer messageStatus;

    /**
     * 类型（1-文本显示、2-跳转、3-物流信息）
     */
    private Integer type;

    /**
     * 跳转地址
     */
    private String jumpUrl;

    /**
     * 图片地址
     */
    private String iconUrl;

    /**
     * 状态（1-正常、2-删除）
     */
    private Integer status;

    /**
     * 内容
     */
    private String content;

    /**
     * 其他消息需要用到的value值
     */
    private String value;

    /**
     * hl_message
     */
    private static final long serialVersionUID = 1L;
}