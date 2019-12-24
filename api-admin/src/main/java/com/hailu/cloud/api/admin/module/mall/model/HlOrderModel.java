package com.hailu.cloud.api.admin.module.mall.model;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import com.hailu.cloud.common.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author junpei.deng
 */
@Data
@InjectDict
@ApiModel(value = "海露订单")
public class HlOrderModel extends BaseEntity {

    /**
     * id
     */
    @ApiModelProperty(value = "id（编辑时传入，新增是无需传）", dataType = "String")
    private Long id;

    /**
     * 会员ID
     */
    private String userId;

    /**
     * 会员名称
     */
    private String userName;

    /**
     * 操作人ID
     */
    private Long editId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    @ApiModelProperty(value = "商品名称", dataType = "String",required = true)
    private String goodsName;

    /**
     * 买家留言
     */
    private String remark;

    /**
     * 订单状态（1-待支付、2-待发货、3-已发货、4-已完成）
     */
    @NotBlank(message = "订单状态不能为空")
    @ApiModelProperty(value = "订单状态", dataType = "Integer",required = true)
    private Integer orderStatus;

    /**
     * 收件人
     */
    private String recipient;

    /**
     * 快递公司
     */
    private String courierCompany;

    /**
     * 快递单号
     */
    private String courierNumber;

    /**
     * 物流状态（1-待发货、2-待接收、3-已完成）
     */
    private Integer logisticsStatus;

    /**
     * 省ID
     */
    private String provinceId;

    @DictName(code = "NATION", joinField = "provinceId")
    private String provinceStr;

    /**
     * 市ID
     */
    private String cityId;

    @DictName(code = "NATION", joinField = "cityId")
    private String cityStr;

    /**
     * 区县ID
     */
    private String areaId;

    @DictName(code = "NATION", joinField = "areaId")
    private String areaStr;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 订单类型（1-购买服务商）
     */
    private Integer orderType;

    /**
     * 状态（1-正常、2-删除）
     */
    private Integer status;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 邀请人
     */
    private String invitationMember;

    /**
     * 支付类型（1-支付宝、2-微信、3-微信H5）
     */
    private Integer payType;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 收件人手机号码
     */
    private String recipientPhone;

}
