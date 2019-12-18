package com.hailu.cloud.common.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.Callable;

/**
 * hystrix使用线程池隔离，feign之间传递request上下文策略
 *
 * @author xuzhijie
 */
public class RequestContextHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        return new RequestAttributeAwareCallable<>(callable, RequestContextHolder.getRequestAttributes());
    }

    static class RequestAttributeAwareCallable<T> implements Callable<T> {

        private final Callable<T> delegate;
        private final RequestAttributes requestAttributes;

        public RequestAttributeAwareCallable(Callable<T> callable, RequestAttributes requestAttributes) {
            this.delegate = callable;
            this.requestAttributes = requestAttributes;
        }

        @Override
        public T call() throws Exception {
            try {
                RequestContextHolder.setRequestAttributes(requestAttributes);
                return delegate.call();
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        }
    }
}