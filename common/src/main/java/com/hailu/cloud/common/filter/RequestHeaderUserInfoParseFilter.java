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

    /**
     * 解析网关传过来的数据
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            // 如果请求头数据已经解析过，直接转成对应的登录用户信息
            String headerLoginType = req.getHeader(Constant.JWT_LOGIN_TYPE);
            String gatewayUserInfo = req.getHeader(Constant.REQUEST_HEADER_GATEWAY_USER_INFO);
            if (StringUtils.isNotBlank(headerLoginType) && StringUtils.isNotBlank(gatewayUserInfo)) {
                int loginType = Integer.parseInt(headerLoginType);
                String userInfoJson = Base64.decodeToString(gatewayUserInfo);
                AuthInfo authInfo = AuthInfoParseTool.parse(userInfoJson, loginType);
                request.setAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER, authInfo);
                chain.doFilter(request, response);
                return;
            }
            // 如果有携带Access-token，从redis读取用户信息初始化
            String accessToken = req.getHeader(Constant.REQUEST_HEADER_ACCESS_TOKEN);
            if (StringUtils.isNotBlank(accessToken)) {
                AuthInfo authInfo = RequestUtils.verifyToken(redisClient, accessToken);
                if (authInfo != null) {
                    request.setAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER, authInfo);
                    chain.doFilter(request, response);
                    return;
                } else {
                    log.error("解析请求头异常[" + Constant.REQUEST_HEADER_ACCESS_TOKEN + "]");
                }
            }
        } catch (Exception e) {
            log.error("解析请求头异常[" + Constant.REQUEST_ATTRIBUTE_CURRENT_USER + "]", e);
        }
        chain.doFilter(request, response);
    }
}
