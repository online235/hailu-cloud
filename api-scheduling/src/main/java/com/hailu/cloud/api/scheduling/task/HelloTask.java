package com.hailu.cloud.api.scheduling.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author xuzhijie
 */
@Slf4j
@Component
public class HelloTask {

    /**
     * 默认是fixedDelay 上一次执行完毕时间后执行下一轮
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void hello() {
        log.info("hello");
    }

}