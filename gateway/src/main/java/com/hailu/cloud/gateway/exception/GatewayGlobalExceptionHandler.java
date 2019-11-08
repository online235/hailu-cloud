package com.hailu.cloud.gateway.exception;

import com.hailu.cloud.common.response.ApiResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.ConnectException;
import java.util.Collections;
import java.util.List;


/**
 * @author xuzhijie
 */
@Slf4j
public class GatewayGlobalExceptionHandler implements ErrorWebExceptionHandler {

    /**
     * MessageReader
     */
    @Setter
    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();

    /**
     * MessageWriter
     */
    @Setter
    private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();

    /**
     * ViewResolvers
     */
    @Setter
    private List<ViewResolver> viewResolvers = Collections.emptyList();

    /**
     * 存储处理异常后的信息
     */
    private ThreadLocal<ApiResponse> exceptionHandlerResult = new ThreadLocal<>();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        /**
         * 按照异常类型进行处理
         */
        ApiResponse apiResponse;
        if (ex instanceof NotFoundException) {
            apiResponse = ApiResponse.notFound();
        } else if (ex instanceof ResponseStatusException) {
            HttpStatus httpStatus = ((ResponseStatusException) ex).getStatus();
            switch (httpStatus) {
                case NOT_FOUND:
                    apiResponse = ApiResponse.notFound();
                    break;
                case INTERNAL_SERVER_ERROR:
                    apiResponse = ApiResponse.serverError();
                    break;
                case UNAUTHORIZED:
                    apiResponse = ApiResponse.unAuthorized();
                    break;
                case METHOD_NOT_ALLOWED:
                    apiResponse = ApiResponse.methodNotAllowed();
                    break;
                default:
                    apiResponse = ApiResponse.requestTimeout();
                    break;
            }
        } else if (ex instanceof ConnectException) {
            apiResponse = ApiResponse.requestTimeout();
        } else {
            apiResponse = ApiResponse.serverError();
        }

        /**
         * 错误记录
         */
        ServerHttpRequest request = exchange.getRequest();
        log.error("[网关全局异常处理]异常请求路径:{},记录异常信息:{}", request.getPath(), ex.getMessage());
        /**
         * 参考AbstractErrorWebExceptionHandler
         */
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }
        exceptionHandlerResult.set(apiResponse);
        ServerRequest newRequest = ServerRequest.create(exchange, this.messageReaders);
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse).route(newRequest)
                .switchIfEmpty(Mono.error(ex))
                .flatMap(handler -> handler.handle(newRequest))
                .flatMap(response -> {
                    exceptionHandlerResult.remove();
                    return write(exchange, response);
                });

    }

    /**
     * 参考DefaultErrorWebExceptionHandler
     *
     * @param request
     * @return
     */
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        ApiResponse result = exceptionHandlerResult.get();
        return ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(result));
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     *
     * @param exchange
     * @param response
     * @return
     */
    private Mono<? extends Void> write(ServerWebExchange exchange,
                                       ServerResponse response) {
        exchange.getResponse().getHeaders()
                .setContentType(response.headers().getContentType());
        return response.writeTo(exchange, new ResponseContext());
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    private class ResponseContext implements ServerResponse.Context {

        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return GatewayGlobalExceptionHandler.this.messageWriters;
        }

        @Override
        public List<ViewResolver> viewResolvers() {
            return GatewayGlobalExceptionHandler.this.viewResolvers;
        }

    }
}
