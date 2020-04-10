package com.jangni.jersey.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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

}