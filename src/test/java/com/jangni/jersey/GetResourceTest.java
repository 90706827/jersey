package com.jangni.jersey;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @ClassName GetResourceTest
 * @Description Get Test
 * @Author Mr.Jangni
 * @Date 2019/3/27 12:40
 * @Version 1.0
 **/
public class GetResourceTest {

    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        Response resp = client
                .target("http://localhost:8081")
                .path("jersey")
                .path("get")
                .property(ClientProperties.CONNECT_TIMEOUT,10000)
                .property(ClientProperties.READ_TIMEOUT,50000)
                .path("655cd5e4c8e645208437187f482fcdbe")
//                GET URL 参数
                .queryParam("tranNo", "655cd5e4c8e645208437187f482fcdbe")
                .request()
                .acceptEncoding("UTF-8")
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
