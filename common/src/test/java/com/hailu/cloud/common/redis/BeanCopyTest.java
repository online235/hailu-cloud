package com.hailu.cloud.common.redis;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.hailu.cloud.common.model.auth.LoginModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @Author xuzhijie
 * @Date 2019/11/8 11:11
 */
@Slf4j
@Disabled
public class BeanCopyTest {

    @Test
    public void t() {

        LoginModel loginModel_1 = new LoginModel();
        loginModel_1.setAccessToken("aaa");

        LoginModel loginModel_2 = new LoginModel();
        loginModel_2.setAccessToken("bbb");

        BeanUtil.copyProperties(loginModel_1, loginModel_2);
        log.info(JSON.toJSONString(loginModel_1));
        log.info(JSON.toJSONString(loginModel_2));

    }

}
