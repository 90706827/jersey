package com.jangni.jersey;


import com.jangni.jersey.resource.AsyncResource;
import org.apache.http.client.config.RequestConfig;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
public class AsyncResourceTest  {

    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        Response resp = client
                .target("http://localhost:8081")
                .property(ClientProperties.CONNECT_TIMEOUT,10000)
                .property(ClientProperties.READ_TIMEOUT,50000)
                .path("jersey")
                .path("tran")
                .queryParam("tranNo","655cd5e4c8e645208437187f482fcdbe")
                .request()
                .accept(MediaType.APPLICATION_JSON)
//                添加头参数
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .get();

        System.out.println(resp);
        if (resp.getStatus() == 200) {
            String body = resp.readEntity(String.class);
            System.out.println(body);
        } else {
            String body = resp.readEntity(String.class);
            System.out.println(body);
        }
//        client.close();
    }

}