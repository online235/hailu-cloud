package com.hailu.cloud.api.xinan.module.app.service.impl;


import com.hailu.cloud.api.xinan.module.app.model.HomeDataListModel;
import com.hailu.cloud.api.xinan.module.app.model.XaHelpMemberModel;
import com.hailu.cloud.api.xinan.module.app.model.XaStatisticsModel;
import com.hailu.cloud.api.xinan.module.app.service.XaHelpMenberService;
import com.hailu.cloud.api.xinan.module.app.service.XaStatisticsService;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class HomeDateService {


    @Autowired
    private RedisStandAloneClient redisStandAloneClient;
    @Resource
    private XaHelpMenberService xaHelpMenberService;
    @Resource
    private XaStatisticsService xaStatisticsService;

    private String helpMemberNumA;

    private String helpMemberNumB;

    private String helpMoneyCount;


    /**
     * 获取首页数据
     * @return
     */
    public HomeDataListModel getHomeDateListModel() {

        HomeDataListModel homeDataListModel = new HomeDataListModel();
        helpMemberNumA = redisStandAloneClient.stringGet("helpMemberNumA");
        helpMemberNumB = redisStandAloneClient.stringGet("helpMemberNumB");
        helpMoneyCount = redisStandAloneClient.stringGet("helpMoneyCount");
        if (StringUtil.isBlank(helpMemberNumA)) {
            helpMemberNumA = "998657";
        }
        if (StringUtil.isBlank(helpMemberNumB)) {
            helpMemberNumB = "998688";
        }
        if (StringUtil.isBlank(helpMoneyCount)) {
            helpMoneyCount = "123546765";
        }
        helpMemberNumA = String.valueOf(Long.parseLong(helpMemberNumA) + 10L);
        helpMemberNumB = String.valueOf(Long.parseLong(helpMemberNumB) + 10L);
        helpMoneyCount = String.valueOf(Long.parseLong(helpMoneyCount) + 10L);
        Map map = new HashMap();
        List<XaStatisticsModel> xaStatistics = xaStatisticsService.findListByParameter(map);
        List<XaStatisticsModel> xaStatisticLastList = xaStatisticsService.findListByParameter(map);
        XaStatisticsModel xaStatisticsModel = new XaStatisticsModel();
        List<XaHelpMemberModel> xaHelpMemberModels = new ArrayList<>();
        XaStatisticsModel xaStatisticsModelLast = new XaStatisticsModel();//上一期统计数据
        List<XaHelpMemberModel> xaHelpMemberModelsLast = new ArrayList<>();//上一期案例
        //最新期数统计
        if (!CollectionUtils.isEmpty(xaStatistics)) {
            xaStatisticsModel = xaStatistics.get(0);
            map.put("periodsNumber", xaStatisticsModel.getPeriodsNumber());
            map.put("timeDate", xaStatisticsModel.getTimeDate());
            //本期历史案例
            xaHelpMemberModels = xaHelpMenberService.findListByParameter(map);
            //上一期数据
            if (xaStatisticsModel.getPeriodsNumber() > 1) {
                map.clear();
                map.put("periodsNumber", xaStatisticsModel.getPeriodsNumber() - 1);
                map.put("timeDate", xaStatisticsModel.getTimeDate());
                xaStatisticLastList = xaStatisticsService.findListByParameter(map);
            } else {
                map.clear();
                //上个月期数数据
                map.put("periodsNumber", 2);
                map.put("timeDateLast", xaStatisticsModel.getTimeDate());
                xaStatisticLastList = xaStatisticsService.findListByParameter(map);
            }
            if (!CollectionUtils.isEmpty(xaStatisticLastList)) {
                xaStatisticsModelLast = xaStatisticLastList.get(0);
                map.clear();
                map.put("periodsNumber", xaStatisticsModelLast.getPeriodsNumber());
                map.put("timeDate", xaStatisticsModelLast.getTimeDate());
                xaHelpMemberModelsLast = xaHelpMenberService.findListByParameter(map);
            }
        }
        //最新案例
        xaStatisticsModel.setXaHelpMemberModelList(xaHelpMemberModels);
        xaStatisticsModelLast.setXaHelpMemberModelList(xaHelpMemberModelsLast);
        homeDataListModel.setXaStatisticsModelLast(xaStatisticsModelLast);
        homeDataListModel.setXaStatisticsModel(xaStatisticsModel);
        homeDataListModel.setHelpMemberNumA(helpMemberNumA);
        homeDataListModel.setHelpMemberNumB(helpMemberNumB);
        homeDataListModel.setHelpMoneyCount(helpMoneyCount);
        redisStandAloneClient.stringSet("helpMemberNumA", helpMemberNumA, 3600 * 24 * 30);
        redisStandAloneClient.stringSet("helpMemberNumB", helpMemberNumB, 3600 * 24 * 30);
        redisStandAloneClient.stringSet("helpMoneyCount", helpMoneyCount, 3600 * 24 * 30);
        return homeDataListModel;
    }



    /**
     * 获取该期的统计数据和案例
     * @param timeDate
     * @param periodsNumber
     * @return
     */
    public XaStatisticsModel getXaHelpMemberList(Date timeDate, Integer periodsNumber) {

        XaStatisticsModel xaStatisticsModel = new XaStatisticsModel();
        List<XaHelpMemberModel> xaHelpMemberModels = new ArrayList<>();
        Map map = new HashMap();
        map.put("periodsNumber", timeDate);
        map.put("timeDate", periodsNumber);
        List<XaStatisticsModel> xaStatistics = xaStatisticsService.findListByParameter(map);
        if (!CollectionUtils.isEmpty(xaStatistics)) {
            xaStatisticsModel = xaStatistics.get(0);
            map.put("periodsNumber", xaStatisticsModel.getPeriodsNumber());
            map.put("timeDate", xaStatisticsModel.getTimeDate());
            //本期历史案例
            xaHelpMemberModels = xaHelpMenberService.findListByParameter(map);
        }
        xaStatisticsModel.setXaHelpMemberModelList(xaHelpMemberModels);
        return xaStatisticsModel;

    }



}
