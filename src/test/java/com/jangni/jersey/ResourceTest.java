package com.jangni.jersey;

import com.jangni.jersey.resource.AsyncResource;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.*;

/**
 * @ClassName TestEndpointTest
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/3/21 23:10
 * @Version 1.0
 **/
public class ResourceTest implements Callable<Response> {
    private Long count = 0L;
    ResourceTest(Long count){
        this.count = count;
    }

    public static void main(String[] args) {

        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        // 防止频繁回收核心线程，最低保留4个
        pool.setCorePoolSize(4);
        pool.setQueueCapacity(0);
        // 允许核心线程池超时后回收
        pool.setAllowCoreThreadTimeOut(true);
        pool.setThreadNamePrefix("thread-async-biz-");
        pool.afterPropertiesSet();

        Long count = 1L;
        for (int i=0;i<1;i++){
            pool.submit(new ResourceTest(count++));
        }
        pool.shutdown();
    }

    @Override
    public Response call() throws Exception {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        Response r = client
                .target("http://localhost:8080/jersey/hello")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .header("type", count)
                .post(Entity.entity("<xml><root>abce</root></xml>", "text/xml; charset=utf-8"));

        System.out.println(r);
        if (r.getStatus() == 200) {
            String body = r.readEntity(String.class);
            System.out.println(body);
        }
        client.close();
        return r;
    }


    public static void asyncPost() throws InterruptedException, ExecutionException, TimeoutException {
        ClientConfig config = new ClientConfig();
        final Client client =ClientBuilder.newClient(config);
        WebTarget webTarget = client.target("http://localhost:8080/jersey/hello");
        final AsyncInvoker async = webTarget.request().header("type","1234").async();
       for (int i =0 ;i<=10; i++){

           Entity<String> entity = Entity.entity("<xml><root>abce</root></xml>",MediaType.APPLICATION_JSON);
            final Future<String> responseFuture = async.post(entity, String.class);
            String result = responseFuture.get(AsyncResource.TIMEOUT + 1, TimeUnit.SECONDS);
            System.out.println(result);
        }
    }
}