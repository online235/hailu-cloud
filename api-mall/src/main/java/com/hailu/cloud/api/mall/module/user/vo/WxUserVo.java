package com.hailu.cloud.api.mall.module.user.vo;

import lombok.Data;

@Data
public class WxUserVo {
    private String openId;//普通用户的标识，对当前开发者帐号唯一
    private String nickname;//普通用户昵称
    private int sex;//普通用户性别，1为男性，2为女性
    private String headimgurl;//用户头像
    private String unionid;//用户统一标识
}
