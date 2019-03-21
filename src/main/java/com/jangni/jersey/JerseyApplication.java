package com.jangni.jersey;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JerseyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JerseyApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean jersetServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/jersey/*");
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
        return registration;
    }

}
