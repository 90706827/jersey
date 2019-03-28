package com.jangni.jersey.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 * @ClassName ResponseFilter
 * @Description 响应过滤 ContainerResponseFilter响应过滤器
 * @Author Mr.Jangni
 * @Date 2019/3/28 13:30
 * @Version 1.0
 **/
public class ResponseFilter implements ContainerResponseFilter {
    private final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        logger.info("Response Filter Test ----");
        response.getHeaders().add("X-Powered-By", "wolfcode.cn");
    }
}
