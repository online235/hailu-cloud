package com.hailu.cloud.api.basic.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.hailu.cloud.api.basic.module.dict.service.ISysDictService;
import com.hailu.cloud.api.basic.module.nation.service.INationService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 项目启动监听
 *
 * @author xuzhijie
 */
@Slf4j
@Component
public class ApplicationListenReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Autowired
    private RedisStandAloneClient redisClient;

    @Autowired
    private ISysDictService dictService;

    @Autowired
    private INationService nationService;

    /**
     * 外部文件存储目录
     */
    @Value("${file.store.path:/opt/filestore}")
    private String fileStorePath;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Startup Successfully.");
        File file = new File(fileStorePath);
        file.mkdirs();
        log.info("资源目录：{}", file.getAbsolutePath());
        log.info("加载字典数据到Redis");
        dictService.findAll().forEach(item->{
            String dictKey = Constant.REDIS_KEY_DICT_CACHE + item.getCode();
            redisClient.hashSet(dictKey, item.getValue(), item.getName());
        });

        //从redis里面获取省市区的，看是否
        TimeInterval timer = DateUtil.timer();
        log.info("加载省市区地址到Redis");
        String dictKey = Constant.REDIS_KEY_DICT_CACHE + Constant.REDIS_KEY_DICT_CACHE_NATION;
        nationService.findAll().forEach(nation -> {
            String value = null;
            if(StringUtils.isNotBlank(nation.getProvince())){
                value = nation.getProvince();
            }else if(StringUtils.isNotBlank(nation.getCity())){
                value = nation.getCity();
            }else if(StringUtils.isNotBlank(nation.getDistrict())){
                value = nation.getDistrict();
            }
            redisClient.hashSet(dictKey, String.valueOf(nation.getId()), value);
        });
        log.debug("省市区字典注入耗时：" + timer.interval() + " ms");
        log.info("所有字典数据已缓存到Redis");
    }

}
