package com.hailu.cloud.common.interceptor;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.utils.RequestUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * feign调用时自动填充请求头
 *
 * @author xuzhijie
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = RequestUtils.getRequest();
        requestTemplate.header(Constant.JWT_LOGIN_TYPE, request.getHeader(Constant.JWT_LOGIN_TYPE));
        requestTemplate.header(Constant.REQUEST_HEADER_GATEWAY_USER_INFO, request.getHeader(Constant.REQUEST_HEADER_GATEWAY_USER_INFO));
    }

}
