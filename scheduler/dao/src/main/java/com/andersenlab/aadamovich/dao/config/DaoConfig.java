package com.andersenlab.aadamovich.dao.config;

import com.andersenlab.aadamovich.dao.event.DefaultEventDaoImpl;
import com.andersenlab.aadamovich.dao.event.EventBaseDao;
import com.andersenlab.aadamovich.dao.repository.EventRepository;
import com.andersenlab.aadamovich.dao.repository.UserRepository;
import com.andersenlab.aadamovich.dao.user.DefaultUserDaoImpl;
import com.andersenlab.aadamovich.dao.user.UserBaseDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan (basePackages = "com.andersenlab.aadamovich.dao.aspect")
@Import(HibernateConfig.class)
@EnableAspectJAutoProxy
public class DaoConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Bean
    public UserBaseDao userBaseDao() {
        return new DefaultUserDaoImpl(userRepository);
    }

    @Bean
    public EventBaseDao eventBaseDao() {
        return new DefaultEventDaoImpl(eventRepository);
    }
}
