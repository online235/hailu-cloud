package com.hailu.cloud.gateway.config;

import com.hailu.cloud.gateway.exception.GatewayGlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * @author xuzhijie
 */
@Slf4j
@Configuration
public class ExceptionConfig {

    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(
            ObjectProvider<List<ViewResolver>> viewResolversProvider,
            ServerCodecConfigurer serverCodecConfigurer) {

        GatewayGlobalExceptionHandler globalExceptionHandler = new GatewayGlobalExceptionHandler();
        globalExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        globalExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        globalExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return globalExceptionHandler;
    }
}