package com.andersenlab.aadamovich.web.config;

import com.andersenlab.aadamovich.service.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(ServiceConfig.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ServiceConfig serviceConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/grand_user").hasAnyRole("ADMIN", "GRAND_USER")
                .antMatchers("/users/**", "/main","/login", "/errors/403").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .usernameParameter("login")
                .and().exceptionHandling().accessDeniedPage("/errors/403")
                .and().rememberMe(withDefaults())
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(serviceConfig.authenticationService())
                .passwordEncoder(serviceConfig.passwordEncoder());
    }
}
