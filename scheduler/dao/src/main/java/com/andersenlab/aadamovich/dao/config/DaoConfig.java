package com.andersenlab.aadamovich.dao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(HibernateConfig.class)
public class DaoConfig {
}
