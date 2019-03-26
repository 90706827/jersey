package com.jangni.jersey.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.jangni.jersey.exec.ResponseExceptionHandler;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
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
        //注册Spring Filter
        register(RequestContextFilter.class);
        //自动扫描包
        packages("com.jangni.jersey.resource");

        //手动添加配置选项
        property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, "INFO");
        property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_SERVER, LoggingFeature.Verbosity.PAYLOAD_ANY);
        //自定义异常处理
        register(ResponseExceptionHandler.class);
        packages("com.jangni.jersey.exec");
        // 注册数据转换器，支持传参和返回信息json格式与bean之间的自动转换
//        register(JacksonJsonProvider.class);

        // 注册支持multipart-formdata格式的请求
//        register(org.glassfish.jersey.media.multipart.MultiPartFeature.class);

    }

}