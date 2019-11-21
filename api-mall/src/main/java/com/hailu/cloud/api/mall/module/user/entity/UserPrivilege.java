package com.hailu.cloud.api.mall.module.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 用户特权
 * @Author 刘柱栋
 * @Date 2016/7/3 0:26
 * @Copyright Jelly.Liu. All rights reserved. Mail to liuzhudong57@gmail.com
 * @Version v1.0
 */
@Data
public class UserPrivilege implements Serializable {

    private static final long serialVersionUID = -7966544865296006636L;

    /**
     * id
     */
    private int id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 特权ID
     */
    private int privilegeId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 过期时间
     */
    private Date expireTime;

}
