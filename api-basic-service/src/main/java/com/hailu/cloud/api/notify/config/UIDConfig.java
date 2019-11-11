package com.hailu.cloud.api.notify.config;

import com.hailu.cloud.api.notify.module.uid.component.UidGenerator;
import com.hailu.cloud.api.notify.module.uid.component.impl.CachedUidGenerator;
import com.hailu.cloud.api.notify.module.uid.component.service.IWorkerIdAssignerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * uuid生成器配置
 *
 * @author xuzhijie
 */
@Configuration
public class UIDConfig {

    /**
     * {"workerBits":23,"timeBits":31,"seqBits":9}
     * 可支持28个节点以整体并发量14400 UID/s的速度持续运行68年
     * <p>
     * {"workerBits":27,"timeBits":30,"seqBits":6}
     * 可支持37个节点以整体并发量2400 UID/s的速度持续运行34年
     * <p>
     * {"workerBits":22,"timeBits":28,"seqBits":13}
     * 最多可支持约8.7年
     *
     * @param epochStr
     * @param workerIdAssigner
     * @return
     */
    @Bean("cachedUidGenerator")
    public UidGenerator cachedUidGenerator(
            @Value("${uid.epochStr}") String epochStr,
            IWorkerIdAssignerService workerIdAssigner) {

        CachedUidGenerator generator = new CachedUidGenerator();
        // 机器id所占位数
        generator.setWorkerBits(23);
        // 时间所占位数
        generator.setTimeBits(31);
        // 序列号所占位数
        generator.setSeqBits(9);
        // 起始日期，一旦定了后不再修改，上线时设置为当天日期，只有首次上线时需要设置，后面迭代版本不允许再修改
        generator.setEpochStr(epochStr);
        generator.setWorkerIdAssigner(workerIdAssigner);
        return generator;
    }

}
