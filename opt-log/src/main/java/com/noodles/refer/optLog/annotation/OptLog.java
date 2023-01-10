package com.noodles.refer.optLog.annotation;

import java.lang.annotation.*;

/**
 * @author Noodles
 * @since 2023/1/5 14:13
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OptLog {
    String content();

    String bizNo() default "";

    String operator() default "";
}
