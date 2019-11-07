package com.hailu.cloud.common.filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.AuthInfo;
import com.hailu.cloud.common.model.MemberModel;
import jodd.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 针对请头头里面的UserInfo进行解析
 *
 * @Author xuzhijie
 * @Date 2019/11/7 14:17
 */
@Slf4j
public class RequestHeaderUserInfoParseFilter implements Filter {

    private Type memberModelType = new TypeToken<AuthInfo<MemberModel>>() {
    }.getType();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String userInfoJson = req.getHeader(Constant.HEADER_GATEWAY_USER_INFO);
            if (StringUtils.isNotBlank(userInfoJson)) {
                userInfoJson = Base64.decodeToString(userInfoJson);
                AuthInfo<MemberModel> authInfo = new Gson().fromJson(userInfoJson, memberModelType);
                request.setAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER, authInfo);
            }
        } catch (Exception e) {
            log.error("解析请求头[" + Constant.REQUEST_ATTRIBUTE_CURRENT_USER + "]", e);
        }
        chain.doFilter(request, response);
    }
}
