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
    private Long id;

    /**
     * 账号昵称
     */
    @NotBlank(message = "账号昵称不能为空")
    @ApiModelProperty(value = "账号昵称", required = true)
    private String nickName;

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    @ApiModelProperty(value = "账号", required = true)
    private String account;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    /**
     * 头像，预留
     */
    @ApiModelProperty("头像，预留")
    private String avatar;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\s\\S]{8,20}$", message = "密码强度不够，请输入大小写字母数字组合，长度8~20位，如：a123456780.A!@=+")
    private String pwd;

    /**
     * 启用状态
     */
    @ApiModelProperty("启用状态")
    private Integer enableStatus;

    @DictName(code = "ENABLE_STATUS", joinField = "enableStatus")
    private String enableStatusDisplay;

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
