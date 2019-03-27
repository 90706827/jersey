package com.jangni.jersey;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileResourceTest
 * @Description file
 * @Author Mr.Jangni
 * @Date 2019/3/28 0:24
 * @Version 1.0
 **/
public class FileResourceTest {

    public static void main(String[] args) throws IOException {
        ClientConfig config = new ClientConfig();
        String filepath = "E:\\fdfs.jpg";
        File file = new File(filepath);
        byte[] bb = FileUtils.readFileToByteArray(file);
        Client client = ClientBuilder.newClient(config);
        Response resp = client
                .target("http://localhost:8081")
                .path("jersey")
                .path("upload")
                .request()
                .accept(MediaType.APPLICATION_JSON)
//                .header("Content-Type", MediaType.MULTIPART_FORM_DATA)
                .post(Entity.entity(bb, MediaType.MULTIPART_FORM_DATA));

        System.out.println(resp);
        if (resp.getStatus() == 200) {
            String body = resp.readEntity(String.class);
            System.out.println(body);
        } else {
            String body = resp.readEntity(String.class);
            System.out.println(body);
        }



    }
    public  byte[]  download() throws Exception {
        ClientConfig config = new ClientConfig();
        Client client =  ClientBuilder.newClient(config);
        final WebTarget webTarget = client.target("http://localhost:8081");
        final WebTarget pathTarget = webTarget.path("in-resource").path("download");
        final Form form = new Form();
        form.param("filename", "HQGT-WS.zip");//要下载下来的文件名
        final byte[] invocationBuilder = pathTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), byte[].class);
        return invocationBuilder;
    }
}