package com.hailu.cloud.common.fill.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhijie
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface DictName {

    /**
     * 关联类的哪个属性
     *
     * @return
     */
    String joinField();

    /**
     * 字典code
     *
     * @return
     */
    String code();

}
