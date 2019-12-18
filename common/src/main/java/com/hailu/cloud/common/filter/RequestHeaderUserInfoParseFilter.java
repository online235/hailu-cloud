package com.hailu.cloud.common.filter;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.auth.AuthInfo;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.security.AuthInfoParseTool;
import com.hailu.cloud.common.utils.RequestUtils;
import jodd.util.Base64;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 针对请头头里面的UserInfo进行解析
 *
 * @author xuzhijie
 */
@Slf4j
@AllArgsConstructor
public class RequestHeaderUserInfoParseFilter implements Filter {

    private RedisStandAloneClient redisClient;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String headerLoginType = req.getHeader(Constant.JWT_LOGIN_TYPE);
            if (StringUtils.isNotBlank(headerLoginType)) {
                int loginType = Integer.parseInt(headerLoginType);
                String userInfoJson = Base64.decodeToString(req.getHeader(Constant.REQUEST_HEADER_GATEWAY_USER_INFO));
                AuthInfo authInfo = AuthInfoParseTool.parse(userInfoJson, loginType);
                request.setAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER, authInfo);
                chain.doFilter(request, response);
                return;
            }
            String accessToken = req.getHeader("accesstoken");
            if (StringUtils.isBlank(accessToken)) {
                chain.doFilter(request, response);
                return;
            }
            AuthInfo authInfo = RequestUtils.verifyToken(redisClient, accessToken);
            if (authInfo != null) {
                request.setAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER, authInfo);
            }
        } catch (Exception e) {
            log.error("解析请求头异常[" + Constant.REQUEST_ATTRIBUTE_CURRENT_USER + "]", e);
        }
        chain.doFilter(request, response);
    }
}
