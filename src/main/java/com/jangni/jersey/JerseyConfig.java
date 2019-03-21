package com.jangni.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.process.Endpoint;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

/**
 * @ClassName JerseyConfig
 * @Description Jersey config
 * @Author Mr.Jangni
 * @Date 2019/3/21 21:28
 * @Version 1.0
 **/
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(RequestContextFilter.class);
        //配置restful package.
        packages("com.jangni");


    }

}