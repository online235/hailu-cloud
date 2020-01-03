package com.hailu.cloud.api.mall.module.logistics.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: QiuFeng:WANG
 * @Description: data参数
 * @Date: 2020/1/2 0002
 * @program: cloud
 * @create: 2020-01-02 11:
 */
@ApiModel
@Data
public class callbackDataModel {

    /**
     * 客户标识
     */
    @ApiModelProperty(value = "客户标识")
    private String clientFlag;

    /**
     * 运单号
     */
    @ApiModelProperty(value = "运单号")
    private String mailNo;

    /**
     * 客户单号
     */
    @ApiModelProperty(value = "客户单号")
    private String orderNo;

    /**
     * 派送人
     */
    @ApiModelProperty(value = "派送人")
    private String contacter;

    /**
     * 派件员联系方式
     */
    @ApiModelProperty(value = "")
    private String contactPhone;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    private String time;

    /**
     * 跟踪信息描述
     */
    @ApiModelProperty(value = "跟踪信息描述")
    private String desc;

    /**
     * 事件/操作
     */
    @ApiModelProperty(value = "事件/操作")
    private String action;

    /**
     * 当前城市
     */
    @ApiModelProperty(value = "当前城市")
    private String city;

    /**
     * 站点类型
     */
    @ApiModelProperty(value = "站点类型")
    private String facilityType;

    /**
     * 操作站点编号
     */
    @ApiModelProperty(value = "操作站点编号")
    private String facilityNo;

    /**
     * 操作站点名称
     */
    @ApiModelProperty(value = "操作站点名称")
    private String facilityName;

    /**
     * 时区
     */
    @ApiModelProperty(value = "时区")
    private String tz;

    /**
     * 下站到达城市
     */
    @ApiModelProperty(value = "下站到达城市")
    private String nextCity;

    /**
     * 下站到达节点类型
     */
    @ApiModelProperty(value = "下站到达节点类型")
    private String nextNodeType;

    /**
     * 下站到达节点编码
     */
    @ApiModelProperty(value = "下站到达节点编码")
    private String nextNodeCode;

    /**
     * 下站达到节点名称
     */
    @ApiModelProperty(value = "下站达到节点名称")
    private String nextNodeName;

    /**
     * 国家
     */
    @ApiModelProperty(value = "")
    private String country;

    /**
     * 转件编号
     */
    @ApiModelProperty(value = "转件编号")
    private String nextMailNo;

    /**
     * 转件资源名称
     */
    @ApiModelProperty(value = "转件资源名称")
    private String nextSourceName;

    /**
     * 签收人
     */
    @ApiModelProperty(value = "签收人")
    private String signer;

    /**
     * 扩展字段
     */
    @ApiModelProperty(value = "扩展字段")
    private String extendedField;

    /**
     * 异常编码
     */
    @ApiModelProperty(value = "异常编码")
    private String exceptionCode;

    /**
     * 异常描述
     */
    @ApiModelProperty(value = "异常描述")
    private String exceptionDesc;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    private String remark;

}
