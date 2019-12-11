package com.hailu.cloud.api.admin.module.xinan.entity;
import lombok.Data;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 受帮助成员表
 *
 * @author zhijie
 */
@Data
public class XaHelpMember {

    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 省份
     */
    private String province;

    /**
     * 省份code值
     */
    private String provinceCode;

    /**
     * 病名
     */
    private String disease;

    /**
     * 本期时间（yyyy-MM）
     */
    private Date timeDate;

    /**
     * 期数
     */
    private Integer periodsNumber;

    /**
     * 分摊次数
     */
    private Integer shareTimes;

    /**
     * 互助天数
     */
    private Integer helpDays;

    /**
     * 性别：1男；2女
     */
    private Integer sex;

    /**
     * 获取金额
     */
    private BigDecimal gainMoney;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
