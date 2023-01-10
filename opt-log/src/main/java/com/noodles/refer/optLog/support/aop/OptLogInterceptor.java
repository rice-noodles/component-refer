package com.noodles.refer.optLog.support.aop;

import com.noodles.refer.optLog.annotation.OptLog;
import com.noodles.refer.optLog.entity.OptLogOps;
import com.noodles.refer.optLog.service.OptLogService;
import com.noodles.refer.optLog.support.parse.OptLogExpressionEvaluator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author Noodles
 * @since 2023/1/6 16:00
 */
public class OptLogInterceptor implements MethodInterceptor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        MethodExecuteResult methodExecuteResult = new MethodExecuteResult(true, null, null);
        Object proceed = null;
        try {
            proceed = methodInvocation.proceed();
        } catch (Exception e) {
            methodExecuteResult = new MethodExecuteResult(false, e.getMessage(), e);
        }
        OptLogService<?> optLogService;
        try {
            optLogService = applicationContext.getBean(OptLogService.class);
        } catch (Exception e) {
            throw new RuntimeException("a configured OptLogService is not found");
        }
        Method method = methodInvocation.getMethod();
        OptLog annotation = method.getAnnotation(OptLog.class);

        OptLogExpressionEvaluator evaluator = new OptLogExpressionEvaluator();
        String[] contents = {annotation.bizNo(), annotation.operator(), annotation.content()};
        evaluator.parseTemplate(method, methodInvocation.getArguments(), contents);
        OptLogOps optLogOps = buildOptLogOps(contents[0], contents[1], contents[2], methodExecuteResult);
        optLogService.save(optLogOps);

        if (methodExecuteResult.throwable != null) {
            throw methodExecuteResult.throwable;
        }
        return proceed;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private OptLogOps buildOptLogOps(String bizNo, String operator, String content, MethodExecuteResult methodExecuteResult) {
        return OptLogOps.builder()
                .bizNo(bizNo)
                .operator(operator)
                .content(content)
                .isSuccess(methodExecuteResult.isSuccess)
                .remark(methodExecuteResult.remark)
                .createTime(LocalDateTime.now())
                .build();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    static class MethodExecuteResult {
        Boolean isSuccess;
        String remark;
        Throwable throwable;
    }

}
