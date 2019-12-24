package com.hailu.cloud.api.mall.module.ledger.controller;

import com.hailu.cloud.api.mall.module.ledger.po.BankCardDto;
import com.hailu.cloud.api.mall.module.ledger.service.IBankCardService;
import com.hailu.cloud.api.mall.module.ledger.vo.BankCard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.PublicKey;
import java.util.List;

/**
 * 银行卡相关信息
 * @author junpei.deng
 */
@Validated
@RestController
@Api(tags = "银行卡相关信息")
@Slf4j
@RequestMapping(value = "/bankCard")
public class BankCardController {

    @Resource
    private IBankCardService bankCardService;


    @ApiOperation(value = "保存或修改银行卡",notes = "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": {\n" +
            "    \"id\": \"16112548336307200\",     \n" +
            "    \"cardNo\": \"154635546513\",      卡号\n" +
            "    \"name\": \"小邓\",                      持卡人名称\n" +
            "    \"bankName\": \"中国银行\",                发卡行\n" +
            "    \"openAccountBank\": \"奥园分行\"          开户行\n" +
            "  }\n" +
            "}")
    @PostMapping("/save")
    public BankCardDto save(@RequestBody @Validated BankCardDto bankCardDto){
        log.info("保存或修改银行卡：{}",bankCardDto.toString());
        return bankCardService.save(bankCardDto);
    }

    @ApiOperation(value = "查询列表",notes = "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"id\": \"16112548336307200\",\n" +
            "      \"cardNo\": \"154635546513\",\n" +
            "      \"name\": \"小邓\",\n" +
            "      \"bankName\": \"中国银行\",\n" +
            "      \"openAccountBank\": \"奥园分行\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"16112548336307201\",\n" +
            "      \"cardNo\": \"1465535646488\",\n" +
            "      \"name\": \"小邓\",\n" +
            "      \"bankName\": \"农业银行\",\n" +
            "      \"openAccountBank\": \"梅溪分行\"\n" +
            "    }\n" +
            "  ]\n" +
            "}")
    @GetMapping("/findList")
    public List<BankCardDto> findList(){
        log.info("查询银行卡列表");
        return bankCardService.findList();
    }

}
