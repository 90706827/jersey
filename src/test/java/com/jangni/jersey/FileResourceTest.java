package com.jangni.jersey;

import org.apache.catalina.WebResource;
import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.springframework.util.FileCopyUtils;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
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
        Client client = ClientBuilder.newClient(config);

        Form form = new Form();
        form.param("filename", "fdfs.jpg");

        Invocation.Builder resp = client
                .target("http://localhost:8081")
                .path("jersey")
                .path("downFile")
                .request();

        final byte[]  file = resp.post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE),byte[].class);
        FileCopyUtils.copy(file,new File("E:\\fdfs1.jpg"));
    }
    /**
     * 文件上传
     */
    public void upload(){
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config).register(MultiPartFeature.class);

        MultiPart multiPart = new MultiPart();
        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file",
                new File("E:\\fdfs.jpg"), MediaType.APPLICATION_OCTET_STREAM_TYPE);
        multiPart.bodyPart(fileDataBodyPart);

        Response resp = client
                .target("http://localhost:8081")
                .path("jersey")
                .path("upload")
                .property(ClientProperties.CONNECT_TIMEOUT,10000)
                .property(ClientProperties.READ_TIMEOUT,50000)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .header("Content-Type", MediaType.MULTIPART_FORM_DATA)
                .post( Entity.entity(multiPart, multiPart.getMediaType()));

        System.out.println(resp);
        if (resp.getStatus() == 200) {
            String body = resp.readEntity(String.class);
            System.out.println(body);
        } else {
            String body = resp.readEntity(String.class);
            System.out.println(body);
        }
    }

    public void download() throws IOException {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        Form form = new Form();
        form.param("filename", "fdfs.jpg");

        Invocation.Builder resp = client
                .target("http://localhost:8081")
                .path("jersey")
                .path("down")
                .request();

        final byte[]  file = resp.post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE),byte[].class);
        FileCopyUtils.copy(file,new File("E:\\fdfs1.jpg"));
    }
}