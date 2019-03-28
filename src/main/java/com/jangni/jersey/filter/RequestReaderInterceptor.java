package com.jangni.jersey.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * @ClassName RequestReaderInterceptor
 * @Description 请求拦截器
 * @Author Mr.Jangni
 * @Date 2019/3/28 15:00
 * @Version 1.0
 **/
public class RequestReaderInterceptor implements ReaderInterceptor {

    private final Logger logger = LoggerFactory.getLogger(RequestReaderInterceptor.class);

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        logger.info("Request Reader Interceptor ----");
        return context.proceed();
    }
}
