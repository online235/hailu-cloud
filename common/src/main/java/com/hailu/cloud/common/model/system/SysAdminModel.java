package com.hailu.cloud.common.model.system;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @NotBlank(message = "账号昵称不能为空")
    @ApiModelProperty("账号昵称")
    private String nickName;

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
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
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\s\\S]{8,20}$", message = "密码强度不够，请输入大小写字母数字组合，长度8~20位，如：a123456780.A!@=+")
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
