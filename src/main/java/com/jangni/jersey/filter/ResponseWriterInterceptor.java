package com.jangni.jersey.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @ClassName ResponseWriterInterceptor
 * @Description 响应拦截器
 * @Author Mr.Jangni
 * @Date 2019/3/28 14:56
 * @Version 1.0
 **/
public class ResponseWriterInterceptor implements WriterInterceptor {

    private final Logger logger = LoggerFactory.getLogger(ResponseWriterInterceptor.class);

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        logger.info("Response Writer Interceptor ------");
        context.proceed();
    }

}
