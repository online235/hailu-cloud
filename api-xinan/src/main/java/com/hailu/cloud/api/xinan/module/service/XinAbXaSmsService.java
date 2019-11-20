package com.hailu.cloud.api.xinan.module.service;

import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.redis.enums.RedisEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: QiuFeng:WANG
 * @Description: 短信
 * @Date: 16:32 2019/11/2 0002
 */
@Service
public class XinAbXaSmsService {

    @Autowired
    private RedisStandAloneClient redisKit;

    /**
     * 查询验证码是否正确
     *
     * @param phone
     * @return
     */
    public boolean exists(String phone, String code) {
        String val = redisKit.stringGet(RedisEnum.DB_1.ordinal(), phone);
        return code.equals(val) ? true : false;
    }



}
