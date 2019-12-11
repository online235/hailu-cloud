package com.hailu.cloud.api.admin.module.xinan.entity;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 心安期数统计
 *
 * @author zhijie
 */
@Data
public class XaStatistics {

    /**
     * id
     */
    private Long id;

    /**
     * 帮助会员（单位：人）
     */
    private Integer helpMenber;

    /**
     * 预筹集互助金（单位：万元）
     */
    private BigDecimal preMutualCapital;

    /**
     * 分摊人数
     */
    private BigDecimal apportionmentNum;

    /**
     * 全员每人预分摊
     */
    private BigDecimal averageMoney;

    /**
     * 本期时间（yyyy-MM）
     */
    private Date timeDate;

    /**
     * 期数
     */
    private Integer periodsNumber;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
