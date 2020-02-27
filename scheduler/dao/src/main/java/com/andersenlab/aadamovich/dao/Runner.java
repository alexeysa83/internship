package com.andersenlab.aadamovich.dao;

import com.andersenlab.aadamovich.dao.config.DaoConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
        for (String bean : context.getBeanDefinitionNames()) {
            System.out.println(bean);
        }
    }
}
