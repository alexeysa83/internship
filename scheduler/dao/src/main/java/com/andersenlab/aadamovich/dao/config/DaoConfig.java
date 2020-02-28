package com.andersenlab.aadamovich.dao.config;

import com.andersenlab.aadamovich.dao.aspect.aspectj.LoggingConfigAspectJ;
import com.andersenlab.aadamovich.dao.aspect.spring_aop.LoggingClassMethodsBeanPostProcessor;
import com.andersenlab.aadamovich.dao.event.DefaultEventDaoImpl;
import com.andersenlab.aadamovich.dao.event.EventBaseDao;
import com.andersenlab.aadamovich.dao.user.DefaultUserDaoImpl;
import com.andersenlab.aadamovich.dao.user.UserBaseDao;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@Import(HibernateConfig.class)
@EnableAspectJAutoProxy
public class DaoConfig {

    @Bean
    public UserBaseDao userBaseDao(SessionFactory factory) {
        return new DefaultUserDaoImpl(factory);
    }

    @Bean
    public EventBaseDao eventBaseDao(SessionFactory factory) {
        return new DefaultEventDaoImpl(factory);
    }

    //    AOP Beans
    @Bean
    public LoggingConfigAspectJ loggingConfigAspectJ() {
        return new LoggingConfigAspectJ();
    }

    @Bean
    public LoggingClassMethodsBeanPostProcessor loggingClassMethodsBeanPostProcessor() {
        return new LoggingClassMethodsBeanPostProcessor();
    }
}
