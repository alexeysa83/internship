package com.andersenlab.aadamovich.dao.aspect.spring_aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoggingClassMethodsBeanPostProcessor implements BeanPostProcessor {

    private final Logger logger = LogManager.getLogger(LoggingClassMethodsBeanPostProcessor.class);

    private Map<String, Class> annotatedClasses = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        final Class<?> currentClass = bean.getClass();
        if (currentClass.isAnnotationPresent(LoggingClassMethods.class)) {
            annotatedClasses.put(beanName, currentClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        final Class currentClass = annotatedClasses.get(beanName);
        if (currentClass != null) {
            final ProxyFactory proxyFactory = new ProxyFactory();
            proxyFactory.addAdvice((MethodInterceptor) invocation -> {
                final String className = currentClass.getSimpleName();
                final String methodName = invocation.getMethod().getName();
                final LocalTime currentTime = LocalTime.now();
                logger.info("CLASS: " + className + " --> METHOD: " + methodName + " --> TIME: " + currentTime);
                return invocation.proceed();
            });
            proxyFactory.setTarget(bean);
            return proxyFactory.getProxy();
        }
        return bean;
    }


}
