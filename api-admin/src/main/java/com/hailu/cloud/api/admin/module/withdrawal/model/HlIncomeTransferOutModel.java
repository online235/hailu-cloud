package com.hailu.cloud.api.admin.module.withdrawal.model;
import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现申请表
 *
 * @author zhijie
 */
@Data
@ApiModel
@InjectDict
public class HlIncomeTransferOutModel {

    /**
     * 提现申请ID
     */
    @ApiModelProperty("提现申请ID")
    private Long id;

    /**
     * 会员表的user_id
     */
    @ApiModelProperty("会员表的user_id")
    private String memberId;

    /**
     * 提现金额
     */
    @ApiModelProperty("提现金额")
    private BigDecimal price;

    /**
     * 开户行名称
     */
    @ApiModelProperty("开户行名称")
    private String openAccountBank;

    /**
     * 银行名称
     */
    @ApiModelProperty("银行名称")
    private String bankName;

    /**
     * 银行卡卡号
     */
    @ApiModelProperty("银行卡卡号")
    private String bankCard;

    /**
     * 持卡人名称
     */
    @ApiModelProperty("持卡人名称")
    private String cardholder;

    /**
     * 审核状态：0申请中 1申请成功 2已拒绝
     */
    @ApiModelProperty("审核状态：0申请中 1申请成功 2已拒绝")
    private Integer state;

    /**
     * 审核状态：0申请中 1申请成功 2已拒绝
     */
    @ApiModelProperty("审核状态：0申请中 1申请成功 2已拒绝")
    @DictName(code = "STATE", joinField = "state")
    private String stateDisplay;


    /**
     * 拒绝原因
     */
    @ApiModelProperty("拒绝原因")
    private String remark;

    /**
     * 提现时间
     */
    @ApiModelProperty("提现时间")
    private Date createTime;

    /**
     * 审核人
     */
    @ApiModelProperty("审核人")
    private String updateBy;

    /**
     * 审核人姓名
     */
    @ApiModelProperty("审核人姓名")
    private String nickName;

    /**
     * 审核时间
     */
    @ApiModelProperty("审核时间")
    private Date examineTime;


    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("会员名称")
    private String memberName;


    @ApiModelProperty("会员头像")
    private String userIcon;


    @ApiModelProperty("用户手机号码")
    private String memberMobile;


}
