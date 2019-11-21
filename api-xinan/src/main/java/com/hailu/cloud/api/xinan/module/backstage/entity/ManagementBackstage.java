package com.hailu.cloud.api.xinan.module.backstage.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ManagementBackstage implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * xa_management_backstage
     */
    private static final long serialVersionUID = 1L;
}