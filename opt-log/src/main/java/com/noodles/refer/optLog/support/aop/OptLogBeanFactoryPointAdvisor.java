package com.noodles.refer.optLog.support.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * @author Noodles
 * @since 2023/1/6 16:20
 */
public class OptLogBeanFactoryPointAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private static final long serialVersionUID = -8562754809398816935L;

    private final OptLogPointcut pointcut = new OptLogPointcut();

    public OptLogBeanFactoryPointAdvisor() {
    }

    public OptLogBeanFactoryPointAdvisor(Advice advice) {
        setAdvice(advice);
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

}
