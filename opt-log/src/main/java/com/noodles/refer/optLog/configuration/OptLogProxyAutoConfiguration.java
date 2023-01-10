package com.noodles.refer.optLog.configuration;

import com.noodles.refer.optLog.support.aop.OptLogBeanFactoryPointAdvisor;
import com.noodles.refer.optLog.support.aop.OptLogInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Noodles
 * @since 2023/1/6 16:23
 */
@Slf4j
@Configuration
public class OptLogProxyAutoConfiguration {

    @Bean
    public OptLogBeanFactoryPointAdvisor optLogBeanFactoryPointAdvisor() {
        return new OptLogBeanFactoryPointAdvisor(optLogInterceptor());
    }

    @Bean
    public OptLogInterceptor optLogInterceptor() {
        return new OptLogInterceptor();
    }

}
