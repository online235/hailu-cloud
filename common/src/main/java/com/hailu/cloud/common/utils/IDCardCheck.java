package com.hailu.cloud.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.show.api.ShowApiRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 身份证校验
 */
@Slf4j
@Component
public class IDCardCheck {

    @Value("${id-card.url:}")
    private String url;

    @Value("${id-card.showapi-sign:}")
    private String showapiSign;

    @Value("${id-card.showapi-appid:}")
    private String showapiAppid;


    /**
     * 校验身份证是否正确
     *
     * @param idCard
     * @param name
     * @return
     */
    public boolean checkIdCard(String idCard, String name) {
        //校验身份号码和名称不能为空
        if (StringUtils.isBlank(idCard) || StringUtils.isBlank(name)) {
            return false;
        }
        log.info("查询身份证的身份证号码和名称:" + idCard + "名称：" + name);
        //调用SDK查询身份信息
        String result = new ShowApiRequest(url, showapiAppid, showapiSign).addTextPara("idcard", idCard).addTextPara("name", name).post();
        log.info("查询身份证接口返回信息：" + result);
        JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("showapi_res_body");
        //验证标示  0：匹配 1：身份证与姓名不匹配 2：无此身份证号码
        if (jsonObject.getInteger("code") == 0) {
            return true;
        }
        return false;
    }

}
