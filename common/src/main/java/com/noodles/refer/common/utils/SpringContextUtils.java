package com.noodles.refer.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要放到能够被spring扫描到的地方
 *
 * @author Noodles
 * @since 2023/1/5 17:19
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return context.getBean(clazz);
    }

    public static <T> List<T> getBeansOfType(Class<T> clazz) {
        return new ArrayList<>(context.getBeansOfType(clazz).values());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

}
