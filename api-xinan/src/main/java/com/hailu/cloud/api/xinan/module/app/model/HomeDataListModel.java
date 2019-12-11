package com.hailu.cloud.api.xinan.module.app.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangmugui
 */
@Data
@ApiModel
public class HomeDataListModel {


    @ApiModelProperty("互助会员数A")
    private String helpMemberNumA;


    @ApiModelProperty("互助会员数B")
    private String helpMemberNumB;


    @ApiModelProperty("最新一期期数统计和案例")
    private XaStatisticsModel xaStatisticsModel;



    @ApiModelProperty("上一期期数统计和案例")
    private XaStatisticsModel xaStatisticsModelLast;


    @ApiModelProperty("已划拨互助金额")
    private String helpMoneyCount;


}
