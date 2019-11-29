package com.hailu.cloud.api.mall.module.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.api.mall.module.goods.tool.StringUtil;
import com.hailu.cloud.api.mall.module.user.dao.UserInfoMapper;
import com.hailu.cloud.api.mall.module.user.entity.UserInfo;
import com.hailu.cloud.api.mall.module.user.service.IUserInfoService;
import com.hailu.cloud.api.mall.module.user.vo.UserInfoVo;
import com.hailu.cloud.api.mall.util.Const;
import com.hailu.cloud.common.feigns.PaymentFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author leiqi
 * @Description 微信登录app或网页接口
 */
@Slf4j
@Controller
@RequestMapping(value = "api/user")
public class WxLoginController {

//    @Autowired
//    private IUserInfoService userInfoService;
//
//    @Resource
//    private UserInfoMapper userInfoDao;

    @Autowired
    private PaymentFeignClient paymentFeignClient;


//    /**
//     * 微信登录
//     *
//     * @param unionid
//     * @return
//     */
//    @RequestMapping(value = "wxlogin", method = RequestMethod.POST)
//    @ResponseBody
//    public UserInfoVo wxlogin(
//            @RequestParam(value = "unionid", required = true) String unionid,
//            @RequestParam(value = "cid", required = false) String cid,
//            @RequestParam(value = "systemType", required = false) Integer systemType
//    ) throws Exception {
//        if (systemType == null) {
//            systemType = 1;
//        }
//        UserInfoVo result = new UserInfoVo();
//        log.info("微信登录|unionid={}", unionid);
//        UserInfoVo userInfo = userInfoService.userInfoQueryByUnionid(unionid);
//        if (userInfo != null) {
//            //拼接图片地址
//            if (userInfo.getUserIcon() != null && userInfo.getUserIcon().length() > 4) {
//                if (userInfo.getUserIcon().indexOf("http") == -1) {
//                    userInfo.setUserIcon(Const.PRO_URL + userInfo.getUserIcon());
//                }
//            } else {
//                SysAttributeVO sysAttributeVO = new SysAttributeVO();
//                sysAttributeVO.setAttributeKey("userDefaultIcon");
//                userInfo.setUserIcon(Const.PRO_URL + sysAttributeService.getAttributeByKey(sysAttributeVO).getAttributeValue());
//            }
//            redisTemplate.delete(sysAttributeService.getToken() + userInfo.getUserId());
//            String token = UUID.randomUUID().toString();
//            redisTemplate.opsForValue().set(sysAttributeService.getToken() + userInfo.getUserId(), token, Long.parseLong(sysAttributeService.getTokenTime()), TimeUnit.HOURS);
//            userInfo.setToken(token);
//            if (userInfo.getPayPassword() == null) {//设置密码状态
//                userInfo.setUpdatePwState(0);//设置
//            } else {
//                userInfo.setUpdatePwState(1);//修改
//            }
//            result.setData(userInfo);
//            //更改用户信息 @Author huangl
//            if (StringUtils.isNotBlank(cid)) {
//                userInfo.setCid(cid);
//                if (systemType == 1) {
//                    systemType = 2;
//                }
//                userInfo.setSystemType(systemType);
//                userInfoDao.updateUserCid(userInfo);
//            }
//        } else {
//            result.setData(null);
//        }
//
//        return result;
//    }


//    /**
//     * unionid与手机注册的账号绑定
//     *
//     * @param unionid
//     * @param phone
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "unionidAndPhone", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseData<Map<String, Object>> unionidAndPhone(
//            @RequestParam(value = "unionid", required = true) String unionid,
//            @RequestParam(value = "openId", required = true) String openId,
//            @RequestParam(value = "state", required = true) String state,
//            @RequestParam(value = "phone", required = true) String phone,
//            @RequestParam(value = "smsCode", required = false) String smsCode,
//            String userIcon
//    ) throws Exception {
//        log.info("微信|unionid={}|phone={}|smsCode={}", unionid, phone);
//        ResponseData<Map<String, Object>> responseData = new ResponseData<>();
//        ResponseData<UserInfoVo> verifyCode = userInfoService.verifySmsCode(phone, smsCode);
//        // 验证码是否正确
//        if (0 != verifyCode.getCode()) {
//            responseData.setCode(301);
//            responseData.setMsg("验证码错误");
//            return responseData;
//        }
//        if (userInfoDao.findUnionid(unionid) != 0) {
//            responseData.setCode(301);
//            responseData.setMsg("该微信已经绑定过");
//            return responseData;
//        }
//        UserInfo userInfo = userInfoService.getUserInfoVo(phone);
//        if (userInfo != null) {
//            if (StringUtils.isNotEmpty(userInfo.getUnionid())) {
//                responseData.setCode(301);
//                responseData.setMsg("该手机号已经绑定微信");
//                return responseData;
//            }
//            UserInfoVo userInfoVo = new UserInfoVo();
//            //判断手机号微信号是否存在
//            userInfoVo.setUnionid(unionid);
//            userInfoVo.setUserMobile(phone);
//            if (userIcon != null && userIcon.length() > 5) {
//                String str = userIcon.substring(0, 5);
//                if (str.indexOf("https") == -1) {
//                    userIcon = "https" + userIcon.substring(4, userIcon.length());
//                }
//            }
//            userInfoVo.setWXState(state);
//            userInfoVo.setOpenId(openId);
//            userInfoVo.setUserIcon(userIcon);
//            userInfoService.upUserInfoByPhone(userInfoVo);
//            responseData.setCode(0);
//            responseData.setMsg("success");
//            responseData.setData(new HashMap<String, Object>() {{
//                put("message", "绑定成功");
//            }});
//        }
//        return responseData;
//    }
//
//    @RequestMapping(value = "/WXunbundle", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseData<Boolean> WXunbundle(@RequestParam String userId) {
//        Boolean b = userInfoService.WXunbundle(userId);
//        return new ResponseData<>(b);
//    }

//    /**
//     * 绑定微信
//     *
//     * @param unionid
//     * @param userId
//     * @return
//     * @Author huangl
//     */
//    @RequestMapping(value = "/updateWeCartBind", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseData<Boolean> updateWeCartBind(@RequestParam(value = "unionid", required = true) String unionid,
//                                                  @RequestParam(value = "openId", required = true) String openId,
//                                                  @RequestParam(value = "state", required = true) String state,
//                                                  @RequestParam(value = "userId", required = true) String userId) {
//        return userInfoService.updateWeCartBind(unionid, userId, openId, state);
//    }

    /**
     * 绑定微信
     *
     * @param
     * @param code
     * @return
     * @Author huangl
     */
    @RequestMapping(value = "/getInfoByCode")
    @ResponseBody
    public Map<String, Object> getInfoByCode(@RequestParam(value = "code", required = true) String code) {
        return paymentFeignClient.getInfoByCode(code).getData();
//        if (StringUtil.isEmpty(code)) {
//            return null;
//        }
//        Map<String, Object> result = new HashMap<String, Object>();
//        Map<String,String> map = CredentFactory.hLJsapiWecat();
//        String requestUrl = com.hailu.cloud.common.util.wechat.WechatUtil.web_oauth_accesstoken_url.replace("APPID", map.get(CredentFactory.APPID_FIELD))
//                .replace("SECRET", map.get(CredentFactory.OPENID_SECRRECT_FIELD)).replace("CODE", code);
//        JSONObject jsonObject = com.hailu.cloud.common.util.wechat.WechatUtil.httpRequest(requestUrl, "GET", null);
//        if (null != jsonObject) {
//
//            try {
//                String access_token = jsonObject.getString("access_token");
//                if(StringUtils.isNotBlank(access_token)){
//                    String openid = jsonObject.getString("openid");
//                    result.put("openid", openid);
//                    String unionid = jsonObject.getString("unionid");
//                    result.put("unionid", unionid);
//                    String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
//                    JSONObject jsonObject1 = com.hailu.cloud.common.util.wechat.WechatUtil.httpRequest(infoUrl, "GET", null);
//                    String userIcon = jsonObject1.getString("headimgurl");
//                    result.put("userIcon", userIcon);
//                    String nickName = jsonObject1.getString("nickname");
//                    result.put("nickname", nickName);
//
//                    //如果userId不为空，则绑定到用户里面
//                    if(StringUtils.isNotBlank(userId)){
//                        userInfoDao.updateWeCartBind(unionid,userId,openid,"4");
//                    }
//                }else {
//                    result.put("msg",jsonObject.get("errmsg"));
//                    result.put("code",jsonObject.get("errcode"));
//                }
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//            }
//        }else {
//            result.put("msg",jsonObject.toString());
//            result.put("code","10000");
//        }
//        return ResponseUtil.ok(result);
    }

}
