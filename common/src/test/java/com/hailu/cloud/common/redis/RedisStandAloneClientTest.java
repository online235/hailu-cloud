package com.hailu.cloud.common.redis;

import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
@Disabled
@DisplayName("redis功能测试")
public class RedisStandAloneClientTest {

    @Test
    @DisplayName("字符串保存与获取")
    public void getAndSetStringTest() {
        RedisStandAloneClient client = new RedisStandAloneClient();
        client.initConfigure();

        String value = "你好,value";
        client.stringSet("aaa", value);

        String redisValue = client.stringGet("aaa");

        Assertions.assertEquals(value, redisValue, "redis保存失败");

    }

}
