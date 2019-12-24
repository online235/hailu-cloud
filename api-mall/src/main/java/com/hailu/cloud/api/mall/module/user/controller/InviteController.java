package com.hailu.cloud.api.mall.module.user.controller;

import com.hailu.cloud.api.mall.module.user.service.IInviteDetailService;
import com.hailu.cloud.common.model.system.InvitedetailModel;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 邀请相关信息
 * @author junpei.deng
 */
@Validated
@RestController
@RequestMapping("/invite")
@Slf4j
@Api("邀请相关信息")
public class InviteController {

    @Resource
    private IInviteDetailService inviteDetailService;


    @PostMapping("/add")
    public void add(@RequestBody @Validated InvitedetailModel invitedetailModel){
        log.info("保存邀请人信息：{}",invitedetailModel.toString());
        inviteDetailService.add(invitedetailModel);
    }




}
