package com.hailu.cloud.api.xinan.module.app.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhangmugui
 */
@Data
@ApiModel
public class HomeDataListModel {


    @ApiModelProperty("加入心安人数")
    private String helpMemberNum;


    @ApiModelProperty("最新三条救助案例")
    private List<XaHelpMemberModel> xaHelpMemberModels;


    @ApiModelProperty("最新一期期数统计和案例")
    private XaStatisticsModel xaStatisticsModel;



    @ApiModelProperty("已划拨互助金额")
    private String helpMoneyCount;


    @ApiModelProperty("轮播图列表")
    private List<BannerResult> bannerResultList;



}
