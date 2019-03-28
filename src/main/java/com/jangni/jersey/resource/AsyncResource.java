package com.jangni.jersey.resource;

import com.jangni.jersey.core.JobContext;
import com.jangni.jersey.entity.TranList;
import com.jangni.jersey.service.TranListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AsyncResource
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/3/22 0:39
 * @Version 1.0
 **/
@Component
@Path("/")
public class AsyncResource {
    public static final long TIMEOUT = 20;
    private Logger logger = LoggerFactory.getLogger(AsyncResource.class);
    final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    /**
     * //异步资源方法需要@Suspended注解和AsyncResponse参数
     *
     * @param asyncResponse
     * @param tranNo
     */
    @GET
    @Path("/tran")
    @Produces(MediaType.APPLICATION_JSON)
    public void asyncBatchSave(@QueryParam("tranNo") String tranNo,
                               @Suspended final AsyncResponse asyncResponse) {
        JobContext context = new JobContext();
        context.setAsyncResponse(asyncResponse);
        context.setTranNo(tranNo);
        //该方法用于定义回调
        configResponse(context);
        final BatchRunner batchTask = new BatchRunner(context);
        threadPool.submit(batchTask);
    }

    /**
     * 回调方法  当请你去处理完成之后,CompletionCallback实例的onComplete()方法将会被回调,实现onComplete()方法,
     * 可以监听请求处理完成事件并实现相关业务流程。
     */
    private void configResponse(final JobContext context) {
        context.getAsyncResponse().register((CompletionCallback) throwable -> {
            if (throwable == null) {
                logger.info("CompletionCallback-onComplete: OK");
            } else {
                logger.info("CompletionCallback-onComplete: ERROR: " + throwable.getMessage());
            }
        });

        //
        context.getAsyncResponse().register((ConnectionCallback) disconnected -> {
            //Status.GONE=410
            logger.info("ConnectionCallback-onDisconnect");
            /**
             * 当请求--响应模型的连接断开的时候,CompletionCallback实例的onDisconnect()方法会被回调。
             * 实现这个方法可以监听连接断开事件并实现相关业务,比如主动唤醒AsyncRespurce实例并设置状态码HTTP为410、
             * 客户端请求资源不可用(Response.status(Response.Status.GONE)来完成响应工作。
             */
            disconnected.resume(Response.status(Response.Status.GONE).entity("disconnect!").build());
        });

        context.getAsyncResponse().setTimeoutHandler(new TimeoutHandler() {

            //TimeoutHandler是JAX-RS2定义的超时处理接口,用于处理异步响应类超时事件,
            // 当预期的超时时间到达之后,TimeoutHandler实例的handleTimeout()方法就会被调用。
            // 实现这个方法可以监听超时时间并处理相关业务。
            // 并设置状态码为503、服务器端服务不可用(Response.Status.SERVICE_UNAVAILABLE)  T
            // imeoutHandler的实现可以作为AsyncResource的setTimeoutHandler()方法的参数来配置。
            // AsyncResource的setTimeout()方法用于设置超时时间,默认永不超时。
            @Override
            public void handleTimeout(AsyncResponse asyncResponse) {
                //Status.SERVICE_UNAVAILABLE=503
                logger.info("TIMEOUT");
                asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Operation time out.").build());
            }
        });
        context.getAsyncResponse().setTimeout(TIMEOUT, TimeUnit.SECONDS);
    }

    class BatchRunner implements Runnable {
        @Autowired
        TranListService tranListService;
        private final JobContext context;

        public BatchRunner(JobContext context) {
            this.context = context;
        }

        @Override
        public void run() {
            TranList tranList = tranListService.getTranListByTranNo(context.getTranNo());
            context.getAsyncResponse().resume(tranList);
        }
    }
}