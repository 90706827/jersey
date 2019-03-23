package com.jangni.jersey.config;

import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @ClassName DataConfig
 * @Description 数据库配置
 * @Author Mr.Jangni
 * @Date 2019/3/22 19:11
 * @Version 1.0
 **/
@Configuration
@ComponentScan(value = "com.jangni.jersey.dao")
@EnableTransactionManagement(proxyTargetClass = true)
public class DataConfig {
    @Autowired
    CustomProps props;

    @Bean
    public DataSource dataSource() throws Exception {
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        Properties properties = new Properties();
        properties.setProperty("driverClassName", props.getDriverClassName());
        properties.setProperty("url", props.getUrl());
        properties.setProperty("username", props.getUsername());
        properties.setProperty("password", props.getPassword());
        properties.setProperty("maxActive", props.getMaxActive());
        properties.setProperty("maxIdle", props.getMaxIdle());
        properties.setProperty("defaultAutoCommit", props.getDefaultAutoCommit());
        properties.setProperty("timeBetweenEvictionRunsMillis", props.getTimeBetweenEvictionRunsMillis());
        properties.setProperty("minEvictableIdleTimeMillis", props.getMinEvictableIdleTimeMillis());
        properties.setProperty("testOnBorrow", props.getTestOnBorrow());
        properties.setProperty("validationQuery", props.getValidationQuery());
        properties.setProperty("maxWait", props.getMaxWait());
        properties.setProperty("validationQueryTimeout", props.getValidationQueryTimeout());
        properties.setProperty("validationInterval", props.getValidationInterval());
        return dataSourceFactory.createDataSource(properties);
    }


    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSourceTransactionManager transactionManager) {
        return new JdbcTemplate(transactionManager.getDataSource());
    }
}