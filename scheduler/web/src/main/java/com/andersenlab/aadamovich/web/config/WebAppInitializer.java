package com.andersenlab.aadamovich.web.config;

import com.andersenlab.aadamovich.dao.config.DaoConfig;
import com.andersenlab.aadamovich.service.config.ServiceConfig;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootConfig.class,
                WebSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                DaoConfig.class,
                ServiceConfig.class,
                WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        final DelegatingFilterProxy delegateFilterProxy = new DelegatingFilterProxy();
        delegateFilterProxy.setTargetBeanName("springSecurityFilterChain");
        return new Filter[]{delegateFilterProxy};
    }
}
