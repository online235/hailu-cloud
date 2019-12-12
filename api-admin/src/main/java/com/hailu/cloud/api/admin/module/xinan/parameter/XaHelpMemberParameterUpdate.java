package com.hailu.cloud.api.admin.module.xinan.parameter;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 受帮助成员表
 *
 * @author zhijie
 */
@Data
@ApiModel
public class XaHelpMemberParameterUpdate {

    /**
     * id
     */
    @ApiParam(name = "id", value = " id", required = true)
    private Long id;

    /**
     * 姓名
     */
    @ApiParam(name = "name", value = " 姓名")
    private String name;

    /**
     * 年龄
     */
    @ApiParam(name = "age", value = " 年龄")
    private Integer age;

    /**
     * 省份
     */
    @ApiParam(name = "province", value = " 省份")
    private String province;

    /**
     * 省份code值
     */
    @ApiParam(name = "provinceCode", value = " 省份code值")
    private String provinceCode;

    /**
     * 病名
     */
    @ApiParam(name = "disease", value = " 病名")
    private String disease;

    /**
     * 本期时间（yyyy-MM）
     */
    @ApiParam(name = "id", value = " 本期时间（yyyy-MM）")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date timeDate;

    /**
     * 期数
     */
    @ApiParam(name = "periodsNumber", value = "期数")
    private Integer periodsNumber;

    /**
     * 分摊次数
     */
    @ApiParam(name = "shareTimes", value = " 分摊次数")
    private Integer shareTimes;

    /**
     * 互助天数
     */
    @ApiParam(name = "helpDays", value = " 互助天数")
    private Integer helpDays;

    /**
     * 性别：1男；2女
     */
    @ApiParam(name = "sex", value = "性别：1男；2女")
    private Integer sex;

    /**
     * 获取金额
     */
    @ApiParam(name = "gainMoney", value = " 获取金额")
    private BigDecimal gainMoney;

}
