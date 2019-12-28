package com.hailu.cloud.api.admin.module.partnermanagement.entity;
import lombok.Data;
import java.util.Date;

/**
 * 海露邀请详情
 *
 * @author zhijie
 */
@Data
public class HlInviteDetail {

    /**
     * id
     */
    private Long id;

    /**
     * 邀请人ID
     */
    private String invitationId;

    /**
     * 被邀请人ID
     */
    private String beInvitedId;

    /**
     * 用户类型 0_会员，1_区域代理，2_城市合伙人、3-销售人员、4-商家）
     */
    private Integer type;

    /**
     * 加入时间
     */
    private Date createTime;

}
