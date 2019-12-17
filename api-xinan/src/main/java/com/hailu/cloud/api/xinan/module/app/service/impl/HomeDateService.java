package com.hailu.cloud.api.xinan.module.app.service.impl;


import cn.hutool.core.date.DateUtil;
import com.hailu.cloud.api.xinan.module.app.model.BannerResult;
import com.hailu.cloud.api.xinan.module.app.model.HomeDataListModel;
import com.hailu.cloud.api.xinan.module.app.model.XaHelpMemberModel;
import com.hailu.cloud.api.xinan.module.app.model.XaStatisticsModel;
import com.hailu.cloud.api.xinan.module.app.service.SysBannerService;
import com.hailu.cloud.api.xinan.module.app.service.XaHelpMenberService;
import com.hailu.cloud.api.xinan.module.app.service.XaStatisticsService;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.text.resources.cldr.FormatData;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.*;

@Service
public class HomeDateService {


    @Autowired
    private RedisStandAloneClient redisStandAloneClient;
    @Resource
    private XaHelpMenberService xaHelpMenberService;
    @Resource
    private XaStatisticsService xaStatisticsService;
    @Resource
    private SysBannerService sysBannerService;

    private String helpMemberNum;


    private String helpMoneyCount;


    /**
     * 获取首页数据
     *
     * @return
     */
    public HomeDataListModel getHomeDateListModel() {

        HomeDataListModel homeDataListModel = new HomeDataListModel();
        helpMemberNum = redisStandAloneClient.stringGet("helpMemberNum");
        helpMoneyCount = redisStandAloneClient.stringGet("helpMoneyCount");
        if (StringUtil.isBlank(helpMemberNum)) {
            helpMemberNum = "998657";
        }
        if (StringUtil.isBlank(helpMoneyCount)) {
            helpMoneyCount = "123546765";
        }
        helpMemberNum = String.valueOf(Long.parseLong(helpMemberNum) + 10L);
        helpMoneyCount = String.valueOf(Long.parseLong(helpMoneyCount) + 10L);
        Map map = new HashMap();
        List<XaStatisticsModel> xaStatistics = xaStatisticsService.findListByParameter(map);
        XaStatisticsModel xaStatisticsModel = new XaStatisticsModel();
        List<XaHelpMemberModel> xaHelpMemberModelList = new ArrayList<>();
        //最新期数统计
        if (!CollectionUtils.isEmpty(xaStatistics)) {
            xaStatisticsModel = xaStatistics.get(0);
            map.put("periodsNumber", xaStatisticsModel.getPeriodsNumber());
            map.put("timeDate", DateUtil.format(xaStatisticsModel.getTimeDate(), "YYYY-MM"));
            //本期历史案例
            xaHelpMemberModelList = xaHelpMenberService.findListByParameter(map);
        }
        xaStatisticsModel.setXaHelpMemberModelList(xaHelpMemberModelList);
        //最新三条历史案例
        map.clear();
        map.put("num",0);
        map.put("size",3);
        List<XaHelpMemberModel> xaHelpMemberModels = xaHelpMenberService.findListByParameter(map);
        //轮播图
        map.clear();
        map.put("bannerSpace",1);
        map.put("nowTime",DateUtil.format(new Date(),"YYYY-MM-dd"));
        List<BannerResult> bannerResultList = sysBannerService.findListByParameter(map);
        homeDataListModel.setBannerResultList(bannerResultList);
        homeDataListModel.setXaHelpMemberModels(xaHelpMemberModels);
        homeDataListModel.setXaStatisticsModel(xaStatisticsModel);
        homeDataListModel.setHelpMemberNum(helpMemberNum);
        homeDataListModel.setHelpMoneyCount(helpMoneyCount);
        redisStandAloneClient.stringSet("helpMemberNum", helpMemberNum, 3600 * 24 * 30);
        redisStandAloneClient.stringSet("helpMoneyCount", helpMoneyCount, 3600 * 24 * 30);
        return homeDataListModel;
    }


    /**
     * 获取该期的统计数据和案例
     *
     * @param timeDate
     * @param periodsNumber
     * @return
     */
    public XaStatisticsModel getXaHelpMemberList(String timeDate, Integer periodsNumber) {

        Map map = new HashMap();
        map.put("periodsNumber", periodsNumber);
        map.put("timeDate", timeDate);
        List<XaStatisticsModel> xaStatistics = xaStatisticsService.findListByParameter(map);
        XaStatisticsModel xaStatisticsModel = xaStatistics.get(0);
        map.put("periodsNumber", periodsNumber);
        map.put("timeDate", timeDate);
        //本期历史案例
        List<XaHelpMemberModel> xaHelpMemberModels = xaHelpMenberService.findListByParameter(map);
        xaStatisticsModel.setXaHelpMemberModelList(xaHelpMemberModels);
        return xaStatisticsModel;

    }


}
