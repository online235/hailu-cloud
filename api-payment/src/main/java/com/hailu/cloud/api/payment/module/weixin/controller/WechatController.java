package com.hailu.cloud.api.payment.module.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.api.payment.config.CredentFactory;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.utils.wechat.WechatUtil;
import io.swagger.annotations.Api;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/wechat")
@Api("微信")
public class WechatController {

    @Autowired
    private CredentFactory credentFactory;

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
    public Map<String, Object> getInfoByCode(@RequestParam(value = "code", required = true) String code) throws BusinessException {
        if (StringUtil.isEmpty(code)) {
            return null;
        }
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String,String> map = credentFactory.createPayParams("HLJSAPIWECAT");
        String requestUrl = com.hailu.cloud.common.utils.wechat.WechatUtil.web_oauth_accesstoken_url.replace("APPID", map.get(CredentFactory.APPID_FIELD))
                .replace("SECRET", map.get(CredentFactory.OPENID_SECRRECT_FIELD)).replace("CODE", code);
        JSONObject jsonObject = com.hailu.cloud.common.utils.wechat.WechatUtil.httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {

            try {
                String access_token = jsonObject.getString("access_token");
                if(StringUtils.isNotBlank(access_token)){
                    String openid = jsonObject.getString("openid");
                    result.put("openid", openid);
                    String unionid = jsonObject.getString("unionid");
                    result.put("unionid", unionid);
                    String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
                    JSONObject jsonObject1 = WechatUtil.httpRequest(infoUrl, "GET", null);
                    String userIcon = jsonObject1.getString("headimgurl");
                    result.put("userIcon", userIcon);
                    String nickName = jsonObject1.getString("nickname");
                    result.put("nickname", nickName);
                }else {
                    result.put("msg",jsonObject.get("errmsg"));
                    result.put("code",jsonObject.get("errcode"));
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }else {
            result.put("msg",jsonObject.toString());
            result.put("code","10000");
            throw new BusinessException(jsonObject.toString());
        }
        return result;
    }
}
