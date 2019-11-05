package com.hailu.cloud.api.notify.module.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.hailu.cloud.api.notify.module.sms.client.SmsFeignClient;
import com.hailu.cloud.api.notify.module.sms.model.SmsModel;
import com.hailu.cloud.api.notify.module.sms.service.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zhijie
 */
@Service(value = "freeSmsServiceImpl")
public class FreeSmsServiceImpl implements ISmsService {

    @Autowired
    private SmsFeignClient smsFeignClient;

    @Value("${sms.free.server.sname}")
    private String sname;

    @Value("${sms.free.server.spwd}")
    private String spwd;

    @Value("${sms.free.server.scorpid}")
    private String scorpid;

    @Value("${sms.free.server.sprdid}")
    private String sprdid;

    @Value("${sms.free.server.sms-prefix}")
    private String smsPrefix;

    @Override
    public SmsModel send(String phone, String message) {
        String smsResponse = smsFeignClient.send(this.sname, this.spwd, this.scorpid, this.sprdid, phone, smsPrefix + message);
        return JSON.parseObject(smsResponse, SmsModel.class);
    }

}
