package com.hailu.cloud.api.basic.module.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.hailu.cloud.api.basic.module.sms.client.SmsFeignClient;
import com.hailu.cloud.api.basic.module.sms.model.SmsModel;
import com.hailu.cloud.api.basic.module.sms.service.ISmsService;
import com.hailu.cloud.common.exception.BusinessException;
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
    public void send(String phone, String message) throws BusinessException {
        String smsResponse = smsFeignClient.send(this.sname, this.spwd, this.scorpid, this.sprdid, phone, smsPrefix + message);
        SmsModel smsModel = JSON.parseObject(smsResponse, SmsModel.class);
        if (smsModel.getState() != 0) {
            throw new BusinessException(smsModel.getMsgState());
        }
    }

}
