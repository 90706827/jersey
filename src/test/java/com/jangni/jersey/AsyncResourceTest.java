package com.jangni.jersey;


import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName AsyncResourceTest
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/3/22 1:01
 * @Version 1.0
 **/
public class AsyncResourceTest extends JerseyTest {
    private final static Logger logger = LoggerFactory.getLogger(AsyncResourceTest.class);
    private static final int COUNT = 2;

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(AsyncResource.class);
    }

    @Test
    public void testAsyncBatchSave() throws InterruptedException, ExecutionException {
        String books = "books";
        try {
            //…初始化bookList
            final Entity<String> booksEntity = Entity.entity(books, MediaType.APPLICATION_XML_TYPE);
            final Invocation.Builder request = target("http://localhost:8080/jersey/books").request();
            //请求方法的调用使用AsyncInvoker实例
            final AsyncInvoker async = request.async();
            //使用post()方法提交异步请求
            final Future<String> responseFuture = async.post(booksEntity, String.class);
            //在AsyncInvoker接口的post()方法中，定义一个InvocationCallback接口的实例，实现REST调用的回调
            /*final Future<String> responseFuture = async.post(booksEntity, new InvocationCallback<String>() {
                @Override
                public void completed(String result) {
                    LOGGER.debug(“On Completed: ” + result);
                }

                @Override
                public void failed(Throwable throwable) {
                    LOGGER.debug(“On Failed: ” + throwable.getMessage());
                    throwable.printStackTrace();
                }

            });*/

            //第一次响应返回
            logger.debug("First response @" + System.currentTimeMillis());
            String result = null;
            try {
                //…可以以非阻塞方式处理其他业务
                //异步获取服务器的最终响应
                result = responseFuture.get(AsyncResource.TIMEOUT + 1, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                //…
            } finally {
                //…
            }
        } finally {
            logger.debug("<-**Test Batch Save");
        }
    }
}