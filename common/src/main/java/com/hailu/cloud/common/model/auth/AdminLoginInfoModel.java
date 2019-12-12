package com.hailu.cloud.common.model.auth;

import com.hailu.cloud.common.model.system.SysMenuModel;
import lombok.Data;

import java.util.List;

/**
 * 管理员账号
 *
 * @author zhijie
 */
@Data
public class AdminLoginInfoModel extends LoginModel {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 账号昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 账号
     */
    private String account;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 启用状态
     */
    private Integer enableStatus;

    /**
     * 头像，预留
     */
    private String avatar;

    /**
     * 市code
     */
    private String cityCode;

    /**
     * 账号类型（管理员-1、政府-2）
     */
    private String accountType;

    /**
     * 允许访问的前端路由
     */
    private List<SysMenuModel> menus;

    /**
     * 允许访问的后端接口
     */
    private List<String> apis;
}
