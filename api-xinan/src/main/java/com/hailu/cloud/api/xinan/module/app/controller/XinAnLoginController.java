package com.hailu.cloud.api.xinan.module.app.controller;

import com.hailu.cloud.api.xinan.feigns.AuthFeignClient;
import com.hailu.cloud.api.xinan.module.app.service.impl.ShopMemBerService;
import com.hailu.cloud.common.entity.member.ShopMember;
import com.hailu.cloud.common.exception.AccessTokenExpiredException;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: QiuFeng:WANG
 * @Description: 心安登陆接口
 * @Date: 15:29 2019/11/2 0002
 */
@RestController
@RequestMapping("/app/xinan")
@Validated
@Api(tags = "心安-用户操作-注册")
@Slf4j
public class XinAnLoginController {

    @Autowired
    private ShopMemBerService memberService;


    @Autowired
    private AuthFeignClient authFeignClient;

    /**
     * 根据手机号和验证码验证并且注册
     *
     * @param phone
     * @param code
     * @return
     */
    @ApiOperation(value = "根据手机号和验证码验证并且注册", notes = "<pre>" +
            "{\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"code\": 0,\n" +
            "  \"data\": {\n" +
            "    \"memberName\": \"13927555292\",           // 会员名称\n" +
            "    \"memberMobile\": \"13927555292\"          //手机号码\n" +
            "    \"merchantType\": \"0\"                    //商户类型 0_无，1_区域代理，2_服务商\n" +
            "    \"hlMember\": \"0\"                        //是否为海露会员（0-否、1-是）\n" +
            "    \"accessToken\": \"41d5c78e3df548519a2f918d4523d6a9\"      //token\n" +
            "    \"memberId\": \"null\"      //token\n" +
            "    \"userId\": \"9de1897a-3204-4f29-8e00-9d42650c073d\"          //会员Id\n" +
            "    \"userIcon\": \"https://ddkj-storage.oss-cn-shenzhen.aliyuncs.com/formalServer/upload/img/adv/1571472978960.jpg\"          //用户头像 \n" +
            "    \"userName\": \"134****1225\"         //用户名\n" +
            "    \"nickName\": \"134****1225\"         //登陆名称\n" +
            "    \"userMobile\": \"13411441225\"       //手机号码\n" +
            "    \"createTime\": \"null\"              //创建时间\n" +
            "    \"inviteCode\": \"null\"              //邀请码\n" +
            "    \"beInviteUser\": \"null\"            //被邀请码\n" +
            "    \"sex\": \"1\"                        //性别\n" +
            "    \"unionid\": \"null\"                 //unionid\n" +
            "    \"openId\": \"null\"                  //openId \n" +
            "    \"balance\": \"0.0\"                  //总余额 \n" +
            "    \"isBindWeChat\": \"0\"               //是否绑定微信 \n" +
            "    \"memberSex\": \"null\"               //成员性别 \n" +
            "    \"wxstate\": \"null\"                 //微信状态1_app,2_H5,3_pc \n" +
            "  },\n" +
            "}")
    @PostMapping("register")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "insuredIds", value = "邀请人ID", required = false, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = false),
            @ApiImplicitParam(name = "registerType", value = "注册类型：1、验证码;2、密码", paramType = "query", required = false),
    })
    public MemberLoginInfoModel register(
            @Pattern(regexp = "^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$", message = "手机号不正确") String phone,
            @NotBlank(message = "验证码不能为空") String code, String insuredIds, String password,
            @RequestParam(name = "registerType", required = false, defaultValue = "1") Integer registerType) throws BusinessException {

        if (registerType == 2) {
            if (password.length() < 6) {
                throw new BusinessException("密码长度不能小于6个长度");
            }
            if (StringUtils.isBlank(password)) {
                throw new BusinessException("密码不能为空");
            }
        }
        memberService.registerAndPassword(phone, code, insuredIds, password);
        return authFeignClient.vericodeLogin("0", phone, code).getData();
    }


    /**
     * 微信登录输入手机号码，验证码
     *
     * @param phone
     * @param code
     * @return
     */
    @ApiOperation(value = "微信登录输入手机号码，验证码")
    @PostMapping("wetChatSubmitPhone")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "accessToken", value = "accessToken", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query")
    })
    public MemberLoginInfoModel wetChatSubmitPhone(
            @Pattern(regexp = "^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$", message = "手机号不正确") String phone,
            @NotBlank(message = "验证码不能为空") String code, @NotEmpty String accessToken) throws BusinessException, AccessTokenExpiredException {


        MemberLoginInfoModel memberLoginInfoModel = memberService.getMemberLoginInfoModel(accessToken);
        return memberService.verification(phone, code, memberLoginInfoModel);
    }


    /**
     * 微信登录设置密码
     *
     * @return
     */
    @ApiOperation(value = "微信登录设置密码")
    @PostMapping("wetChatUpdatePassword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query")
    })
    public MemberLoginInfoModel wetChatUpdatePassword(@NotNull String password) throws BusinessException {

        if (StringUtils.isBlank(password)) {
            // 验证码不正确
            throw new BusinessException("密码不能为空");
        }
        MemberLoginInfoModel memberLoginInfoModel = RequestUtils.getMemberLoginInfo();
        return memberService.wetChatUpdatePassword(password, memberLoginInfoModel);

    }


    /**
     * 解除微信绑定
     *
     * @return
     */
    @ApiOperation(value = "解除微信绑定")
    @PostMapping("relieveWetChat")
    public void relieveWetChat() {

        MemberLoginInfoModel memberLoginInfoModel = RequestUtils.getMemberLoginInfo();
        String userId = memberLoginInfoModel.getUserId();
        ShopMember shopMember = memberService.selectByPrimaryByuserId(userId);
        memberService.relieveWetChatShopMember(shopMember, memberLoginInfoModel);
    }


    /**
     * 忘记密码，重设密码
     *
     * @param phone
     * @param code
     * @return
     */
    @ApiOperation(value = "忘记密码，重设密码（用手机号码、验证码修改密码）")
    @PostMapping("updatePassword")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query")
    })
    public void updatePassword(
            @Pattern(regexp = "^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$", message = "手机号不正确") String phone,
            @NotBlank(message = "验证码不能为空") String code, String password) throws BusinessException {

        memberService.updatePassword(phone, code, password);
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    @ApiOperation(value = "获取用户信息")
    @PostMapping("selectShopMember")
    @ResponseBody
    public MemberLoginInfoModel selectShopMember() {
        MemberLoginInfoModel memberLoginInfoModel = RequestUtils.getMemberLoginInfo();
        String userId = memberLoginInfoModel.getUserId();
        ShopMember shopMember = memberService.selectByPrimaryByuserId(userId);
        BeanUtils.copyProperties(shopMember, memberLoginInfoModel);
        return memberLoginInfoModel;
    }


}
