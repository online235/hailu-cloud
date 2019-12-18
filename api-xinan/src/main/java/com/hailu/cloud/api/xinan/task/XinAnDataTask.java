package com.hailu.cloud.api.xinan.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author xuzhijie
 */
@Slf4j
@Component
public class XinAnDataTask {


    private static String helpMemberNum;


    private static String helpMoneyCount;

    @Autowired
    private RedisStandAloneClient redisStandAloneClient;


    /**
     * 默认是fixedDelay 上一次执行完毕时间后执行下一轮
     */
    @Scheduled(cron = "0/60 * * * * *")
    public void dataTask() {
        helpMemberNum = redisStandAloneClient.stringGet("helpMemberNum");
        helpMoneyCount = redisStandAloneClient.stringGet("helpMoneyCount");
        if (StringUtil.isBlank(helpMemberNum)) {
            helpMemberNum = "998657";
        }
        if (StringUtil.isBlank(helpMoneyCount)) {
            helpMoneyCount = "123546765";
        }
        helpMemberNum = String.valueOf(Long.parseLong(helpMemberNum) + RandomUtil.randomInt(1, 3));
        helpMoneyCount = String.valueOf(Long.parseLong(helpMoneyCount) + 10L);
        redisStandAloneClient.stringSet("helpMemberNum", helpMemberNum, 3600 * 24 * 30);
        redisStandAloneClient.stringSet("helpMoneyCount", helpMoneyCount, 3600 * 24 * 30);

    }


}