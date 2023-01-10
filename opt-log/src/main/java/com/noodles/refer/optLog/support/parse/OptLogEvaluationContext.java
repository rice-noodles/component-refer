package com.noodles.refer.optLog.support.parse;

import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @author Noodles
 * @since 2023/1/6 9:59
 */
public class OptLogEvaluationContext extends StandardEvaluationContext {

    public OptLogEvaluationContext(Method method, Object[] arguments, ParameterNameDiscoverer parameterNameDiscoverer) {
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        if (parameterNames != null) {
            for (int i = 0; i < parameterNames.length; i++) {
                setVariable(parameterNames[i], arguments[i]);
            }
        }
    }

}
