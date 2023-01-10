package com.noodles.refer.optLog.support.aop;

import com.noodles.refer.optLog.annotation.OptLog;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author Noodles
 * @since 2023/1/6 16:04
 */
public class OptLogPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return method.getAnnotation(OptLog.class) != null;
    }
}
