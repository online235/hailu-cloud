package com.hailu.cloud.api.admin.module.system.entity;
import lombok.Data;
import java.util.Date;

/**
 * 管理员账号
 *
 * @author zhijie
 */
@Data
public class SysAdmin {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 账号昵称
     */
    private String nickName;

    /**
     * 账号
     */
    private String account;

    /**
     * 头像，预留
     */
    private String avatar;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private Date updateTime;

}
