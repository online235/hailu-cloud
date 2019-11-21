package com.hailu.cloud.api.mall.module.customerservice.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Administrator
 */
@Data
public class CsApplyVo {
    /**
     * 售后申请id主键
     */
    private Integer csApplyId;
    /**
     * 描述
     */
    private String remarks;
    /**
     * 售后类型(0维修，1退货，2换货,3.整单退款)
     */
    private Integer csType;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 审核留言
     */
    private String reviewMessage;
    /**
     * 审核 售后进度
     * 维修流程:提交申请(0)，商城审核(1)，收货(2)，处理中(3)，完成(4)，（5）取消申请，（6）商城拒绝
     * 换货流程:提交申请(0)，商城审核(1)，收货(2)，处理中(3)，完成(4),（5）取消申请，（6）商城拒绝;
     * 七天无理由退货: 提交申请(0)，商城审核(1)，收货(2)，退款(3)，完成(4) ，（5）取消申请，（6）商城拒绝）
     */
    private Integer tpState;
    /**
     * 0未删除1删除
     */
    private Integer deleteStatus;
    /**
     * 创建时间
     */
    private Long createDate;
    /**
     * 售后订单号
     */
    private String csNumber;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 差价原因
     */
    private String causeDifference;
    /**
     * 支付方式 1支付宝 ,2微信 ,3微信
     */
    private String payType;
    private String auditContent;
    /***获取审核记录明细**/
    private List<CsApplyProgressVo> csApplyProgressList;

}
