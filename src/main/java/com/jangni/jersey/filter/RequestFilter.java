package com.jangni.jersey.filter;

import org.glassfish.jersey.server.ContainerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RequestFilter
 * @Description 请求过滤 ContainerRequestFilter请求过滤接口
 * @Author Mr.Jangni
 * @Date 2019/3/28 13:29
 * @Version 1.0
 **/
public class RequestFilter implements ContainerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        logger.info("Request Filter Test ----");
        logger.info(request.getMethod());
//        logger.info(request.getMediaType().getType());
        logger.info(request.getUriInfo().getRequestUriBuilder().toString());
        logger.info(request.getHeaders().toString());
    }
}
