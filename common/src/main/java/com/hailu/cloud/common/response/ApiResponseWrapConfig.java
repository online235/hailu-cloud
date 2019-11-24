package com.hailu.cloud.common.response;

import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局处理Controller返回结果
 *
 * @Author xuzhijie
 * @Date 2019/11/7 18:55
 */
@Configuration
public class ApiResponseWrapConfig implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    private RedisStandAloneClient redisStandAloneClient;

    @Override
    public void afterPropertiesSet() {
        List<HandlerMethodReturnValueHandler> list = handlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newList = new ArrayList<>();
        if (null != list) {
            for (HandlerMethodReturnValueHandler valueHandler : list) {
                if (valueHandler instanceof RequestResponseBodyMethodProcessor) {
                    GlobalApiResponseHandler proxy = new GlobalApiResponseHandler(valueHandler, redisStandAloneClient);
                    newList.add(proxy);
                } else {
                    newList.add(valueHandler);
                }
            }
        }

        handlerAdapter.setReturnValueHandlers(newList);
    }
}
