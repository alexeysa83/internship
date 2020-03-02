package com.andersenlab.aadamovich.service.config;

import com.andersenlab.aadamovich.dao.config.DaoConfig;
import com.andersenlab.aadamovich.service.event.DefaultEventServiceImpl;
import com.andersenlab.aadamovich.service.event.EventService;
import com.andersenlab.aadamovich.service.user.DefaultUserServiceImpl;
import com.andersenlab.aadamovich.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DaoConfig.class)
public class ServiceConfig {

    private final DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Bean
    public UserService userService() {
        return new DefaultUserServiceImpl(daoConfig.userBaseDao());
    }

    @Bean
    public EventService eventService() {
        return new DefaultEventServiceImpl(daoConfig.eventBaseDao());
    }
}
