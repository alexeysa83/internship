package com.andersenlab.aadamovich.service.config;

import com.andersenlab.aadamovich.dao.config.DaoConfig;
import com.andersenlab.aadamovich.service.event.DefaultEventServiceImpl;
import com.andersenlab.aadamovich.service.event.EventService;
import com.andersenlab.aadamovich.service.security.AuthenticationService;
import com.andersenlab.aadamovich.service.user.DefaultUserServiceImpl;
import com.andersenlab.aadamovich.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Import(DaoConfig.class)
public class ServiceConfig {

    private final DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Bean
    public UserService userService() {
        return new DefaultUserServiceImpl(daoConfig.userBaseDao(), passwordEncoder());
    }

    @Bean
    public EventService eventService() {
        return new DefaultEventServiceImpl(daoConfig.eventBaseDao());
    }

    @Bean
    public AuthenticationService authenticationService() {
        return new AuthenticationService(daoConfig.userBaseDao());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
