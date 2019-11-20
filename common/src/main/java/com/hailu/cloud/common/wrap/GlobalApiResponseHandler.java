package com.hailu.cloud.common.wrap;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.fill.DictLoader;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 处理Controller返回结果
 *
 * @author xuzhijie
 */
public class GlobalApiResponseHandler implements HandlerMethodReturnValueHandler {
    private HandlerMethodReturnValueHandler proxyObject;

    private DictLoader dictLoader;

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
        }
        proxyObject.handleReturnValue(ApiResponse.result(returnValue), returnType, mavContainer, webRequest);
    }

}