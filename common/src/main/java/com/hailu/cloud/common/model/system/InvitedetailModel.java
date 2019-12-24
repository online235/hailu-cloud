package com.hailu.cloud.common.model.system;

import lombok.Data;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author junpei.deng
 */
@Validated
@Data
@ToString
public class InvitedetailModel {
    /**
     * 邀请人ID
     */
    @NotNull(message = "邀请人ID不能为空")
    private String invitationId;

    /**
     * 被邀请人ID
     */
    @NotBlank(message = "被邀请人ID不能为空")
    private String beInvitedId;

    /**
     * 用户类型 0_会员，1_区域代理，2_城市合伙人、3-销售人员）
     */
    @NotNull(message = "用户类型不能为空")
    private Integer type;
}
