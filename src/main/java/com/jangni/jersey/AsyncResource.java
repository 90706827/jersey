package com.jangni.jersey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName AsyncResource
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/3/22 0:39
 * @Version 1.0
 **/
@Path("books")
public class AsyncResource {
    public static final long TIMEOUT = 120;
    private Logger logger = LoggerFactory.getLogger(AsyncResource.class);

    public AsyncResource() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    public void asyncBatchSave(
            //异步资源方法需要@Suspended注解和AsyncResponse参数
            @Suspended final AsyncResponse asyncResponse,
            final String books) {
        //设置超时和回调
        configResponse(asyncResponse);
        //处理业务
        final BatchRunner batchTask = new BatchRunner(books);
        Future<String> bookIdsFuture =
                Executors.newSingleThreadExecutor().submit(batchTask);
        String ids;
        try {
            //获取结果
            ids = bookIdsFuture.get();
            //唤醒请求线程，将resume方法的参数作为返回值响应给客户端
            asyncResponse.resume(ids);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }


    private void configResponse(final AsyncResponse asyncResponse) {
        //处理完成的回调
        asyncResponse.register(new CompletionCallback() {
            @Override
            public void onComplete(Throwable throwable) {
                if (throwable == null) {
                    logger.info("CompletionCallback-onComplete: OK");
                } else {
                    logger.info("CompletionCallback-onComplete: ERROR:"
                            + throwable.getMessage());
                }
            }
        });
        //连接断开的回调
//        asyncResponse.register(new ConnectionCallback() {
//            @Override
//            public void onDisconnect(AsyncResponse disconnected) {
//                //Status.GONE=410
//                logger.info("ConnectionCallback-onDisconnect");
//                disconnected.resume(Response.status(Response.Status.GONE)
//                        .entity("disconnect!").build());
//            }
//        });

        //超时的回调，当超时时，主动唤醒AsyncResource实例并设置HTTP状态码
//        asyncResponse.setTimeoutHandler(new TimeoutHandler() {
//            @Override
//            public void handleTimeout(AsyncResponse asyncResponse) {
//                //Status.SERVICE_UNAVAILABLE=503
//                logger.info("TIMEOUT");
//                asyncResponse.resume(Response.status(
//                        Response.Status.SERVICE_UNAVAILABLE)
//                        .entity("Operation time out.").build());
//            }
//        });
//        asyncResponse.setTimeout(TIMEOUT, TimeUnit.SECONDS);
    }
}