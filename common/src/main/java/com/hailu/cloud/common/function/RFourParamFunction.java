package com.hailu.cloud.common.function;

/**
 * @author xuzhijie
 */
@FunctionalInterface
public interface RFourParamFunction<P1, P2, P3, P4, R> {

    R apply(P1 param1, P2 param2, P3 param3, P4 param4);

}
