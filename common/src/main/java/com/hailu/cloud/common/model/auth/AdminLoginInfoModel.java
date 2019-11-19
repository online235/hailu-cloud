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
public class AdminLoginInfoModel {

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
     * 头像，预留
     */
    private String avatar;

    /**
     * access token
     */
    private String accessToken;

    /**
     * refresh token
     */
    private String refreshToken;

    /**
     * 拥有的菜单
     */
    private List<SysMenuModel> menus;
}
