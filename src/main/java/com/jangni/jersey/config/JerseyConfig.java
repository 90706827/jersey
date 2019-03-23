package com.jangni.jersey.config;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.process.Endpoint;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * @ClassName JerseyConfig
 * @Description Jersey config
 * @Author Mr.Jangni
 * @Date 2019/3/21 21:28
 * @Version 1.0
 **/
@Component
@ApplicationPath("jersey")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        //提供手动注册组件
        register(RequestContextFilter.class);
        //自动扫描包
        packages("com.jangni.jersey.resource");
       //手动添加配置选项
        property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER,"INFO");
        property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_SERVER,LoggingFeature.Verbosity.PAYLOAD_ANY);
    }

}