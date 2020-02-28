package com.andersenlab.aadamovich.dao.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@Configuration
@Import(SettingsConfig.class)
public class HibernateConfig {

    private final SettingsConfig settingsConfig;

    public HibernateConfig(SettingsConfig settingsConfig) {
        this.settingsConfig = settingsConfig;
    }

    @Bean
    public DataSource dataSource() {
        final DataSourceSettings datasourceSettings = settingsConfig.dataSourceSettings();

        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(datasourceSettings.getUrl());
        hikariDataSource.setUsername(datasourceSettings.getUsernameDB());
        hikariDataSource.setPassword(datasourceSettings.getPasswordDB());
        hikariDataSource.setDriverClassName(datasourceSettings.getDriver());
        hikariDataSource.setMaximumPoolSize(20);
        return hikariDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        final LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.andersenlab.aadamovich.dao.entity");
        sessionFactoryBean.setHibernateProperties(settingsConfig.hibernateProperties());
        return sessionFactoryBean;
    }
}
