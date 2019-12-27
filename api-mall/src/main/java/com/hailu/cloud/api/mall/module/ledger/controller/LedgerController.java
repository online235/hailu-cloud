package com.hailu.cloud.api.mall.module.ledger.controller;

import com.hailu.cloud.api.mall.module.ledger.service.impl.LedgerFixedRatioServiceImpl;
import com.hailu.cloud.common.entity.member.ShopMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/ledger")
public class LedgerController {

    @Autowired
    private LedgerFixedRatioServiceImpl ledgerFixedRatioService;

    @PostMapping("/editInvitationProvider")
    public void editInvitationProvider(@RequestBody ShopMember userInfo, @RequestParam(value = "money") BigDecimal money){
        ledgerFixedRatioService.editInvitationProvider(userInfo,money);
    }


}
