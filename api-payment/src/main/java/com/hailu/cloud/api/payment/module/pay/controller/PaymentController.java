package com.hailu.cloud.api.payment.module.pay.controller;

import com.hailu.cloud.api.payment.module.pay.service.impl.ChinaumsPayServiceImpl;
import com.hailu.cloud.api.payment.module.pay.service.impl.PayServiceImpl;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.payment.PayRequest;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @BelongsProject: payment
 * @Author: junpei.deng
 * @CreateTime: 2019-11-18 17:47
 * @Description: 支付接口
 */
@Slf4j
@RequestMapping("/payment")
@RestController
@Api("支付")
public class PaymentController {

    @Resource
    private PayServiceImpl payService;

    @Resource
    private ChinaumsPayServiceImpl chinaumsPayService;

    @PostMapping("/gateway")
    public Map<String,Object> gateway(@RequestBody PayRequest payRequest) throws BusinessException, IOException {
        return chinaumsPayService.pay(payRequest);
    }

}
