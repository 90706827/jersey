package com.jangni.jersey;

import com.jangni.jersey.module.Users;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @ClassName FromResourceTest
 * @Description From
 * @Author Mr.Jangni
 * @Date 2019/3/27 23:10
 * @Version 1.0
 **/
public class FromResourceTest {
    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        Form form = new Form();
        form.param("name", "lisi");
        form.param("password", "abcd");

        Client client = ClientBuilder.newClient(config);
        Response resp = client
                .target("http://localhost:8081")
                .path("jersey")
                .path("form")
//                .path("bean")
                .request()
                .acceptEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED));

        System.out.println(resp);
        if (resp.getStatus() == 200) {
            String body = resp.readEntity(String.class);
            System.out.println(body);
        } else {
            String body = resp.readEntity(String.class);
            System.out.println(body);
        }
//        resp.readEntity 后client自动关闭
//        client.close();
    }
}