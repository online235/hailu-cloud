package com.hailu.cloud.common.model.system;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 管理员账号
 *
 * @author zhijie
 */
@Data
@InjectDict
public class SysAdminModel {

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Integer id;

    /**
     * 账号昵称
     */
    @ApiModelProperty("账号昵称")
    private String nickName;

    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String account;

    /**
     * 头像，预留
     */
    @ApiModelProperty("头像，预留")
    private String avatar;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String pwd;

    /**
     * 启用状态
     */
    @ApiModelProperty("启用状态")
    private Integer status;

    @DictName(code = "ENABLE_STATUS", joinField = "status")
    private String statusDisplay;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private String updateBy;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updateTime;

}
