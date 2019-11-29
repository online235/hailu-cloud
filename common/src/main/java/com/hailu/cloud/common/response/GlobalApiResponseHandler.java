package com.hailu.cloud.common.response;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.fill.DictLoader;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.regex.Pattern;

/**
 * 处理Controller返回结果
 *
 * @author xuzhijie
 */
@Slf4j
public class GlobalApiResponseHandler implements HandlerMethodReturnValueHandler {
    private HandlerMethodReturnValueHandler proxyObject;

    private DictLoader dictLoader;

    private static final Pattern WEIXIN_CALLBACK_PATTERN = Pattern.compile("^/api/v1/.*/callback");

    public GlobalApiResponseHandler(HandlerMethodReturnValueHandler proxyObject, RedisStandAloneClient redisClient) {
        this.proxyObject = proxyObject;
        dictLoader = new DictLoader((code, value) -> {
            String dictKey = Constant.REDIS_KEY_DICT_CACHE + code;
            return redisClient.handle(true, jedis -> jedis.hget(dictKey, value));
        });
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return proxyObject.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(
            Object returnValue,
            MethodParameter returnType,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest) throws Exception {

        if (returnValue != null) {
            // 注入字典
            dictLoader.load(returnValue);
            log.info("返回参数："+returnValue.toString());
        }
        //回调不封装参数
        if(!WEIXIN_CALLBACK_PATTERN.matcher(webRequest.getContextPath()).matches()){
            proxyObject.handleReturnValue(ApiResponse.result(returnValue), returnType, mavContainer, webRequest);
        }else {
            proxyObject.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        }
    }

}