package com.andersenlab.aadamovich.dao.config;

import com.andersenlab.aadamovich.dao.event.DefaultEventDaoImpl;
import com.andersenlab.aadamovich.dao.event.EventBaseDao;
import com.andersenlab.aadamovich.dao.user.DefaultUserDaoImpl;
import com.andersenlab.aadamovich.dao.user.UserBaseDao;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan (basePackages = "com.andersenlab.aadamovich.dao.aspect")
@Import(HibernateConfig.class)
@EnableAspectJAutoProxy
public class DaoConfig {

    private final SessionFactory factory;

    public DaoConfig(SessionFactory factory) {
        this.factory = factory;
    }

    @Bean
    public UserBaseDao userBaseDao() {
        return new DefaultUserDaoImpl(factory);
    }

    @Bean
    public EventBaseDao eventBaseDao() {
        return new DefaultEventDaoImpl(factory);
    }
}
