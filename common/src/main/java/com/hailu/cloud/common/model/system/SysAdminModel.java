package com.hailu.cloud.common.model.system;

import com.google.common.collect.Lists;
import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

/**
 * 管理员账号
 *
 * @author zhijie
 */
@Data
@InjectDict
@ApiModel
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
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\s\\S]{6,50}$", message = "密码强度不够，请输入大小写字母数字组合，长度6~50位，如：a123456780.A!@=+")
    private String pwd;

    /**
     * 启用状态
     */
    @ApiModelProperty("启用状态")
    private Integer enableStatus;

    @DictName(code = "ENABLE_STATUS", joinField = "enableStatus")
    private String enableStatusDisplay;

    /**
     * 市code
     */
    @ApiModelProperty("市code")
    private String cityCode;

    /**
     * 账号类型（管理员-1、政府-2）
     */
    @ApiModelProperty("账号类型（管理员-1、政府-2）")
    private Integer accountType;

    @DictName(code = "ADMIN_ACCOUNT_TYPE", joinField = "accountType")
    private String accountTypeDisplay;

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

    private List<SysRoleModel> roles = Lists.newArrayList();

}
