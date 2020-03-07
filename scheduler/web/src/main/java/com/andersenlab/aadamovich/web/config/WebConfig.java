package com.andersenlab.aadamovich.web.config;

import com.andersenlab.aadamovich.service.config.ServiceConfig;
import com.andersenlab.aadamovich.web.controller.RootController;
import com.andersenlab.aadamovich.web.controller.SecurityForwardToJspController;
import com.andersenlab.aadamovich.web.controller.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {RootController.class, SecurityForwardToJspController.class})
public class WebConfig {

    private final ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public UserController userController() {
        return new UserController(serviceConfig.userService());
    }

    @Bean
    ViewResolver viewResolver () {
        final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
