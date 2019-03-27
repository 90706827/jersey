package com.jangni.jersey.resource;

import com.jangni.jersey.core.RespCode;
import com.jangni.jersey.core.RestResponse;
import com.jangni.jersey.exec.ResponseExecption;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @ClassName FileResource
 * @Description 文件上传下载
 * @Author Mr.Jangni
 * @Date 2019/3/27 23:38
 * @Version 1.0
 **/
@Component
@Path("/")
public class FileResource {

    @GET
    @Path("/down")
    public void down(@QueryParam("filename") String filename,
                     @Context HttpServletResponse response,
                     @Context ServletContext ctx) throws Exception {
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        response.setHeader("Cache-Control", "no-cache");
        File f = new File(ctx.getRealPath("/upload"), filename);
        FileUtils.copyFile(f, response.getOutputStream());
    }

    @GET
    @Path("/downFile/{filename}")
    public Response downFile(@PathParam("filename") String filename,
                             @Context ServletContext ctx) throws Exception {
        File f = new File(ctx.getRealPath("/upload"), filename);
        if (!f.exists()) {
            throw new ResponseExecption("文件不存在", new RestResponse(RespCode.FILE_NOT_FILE_ERROR));
        } else {
            return Response.ok(f).header("Content-disposition", "attachment;filename=" + filename)
                    .header("Cache-Control", "no-cache").build();
        }
    }

    @GET
    @Path("/downImage/{filename}")
    public Response downImage(@PathParam("filename") String filename,
                              @Context ServletContext ctx) throws Exception {
        File f = new File(ctx.getRealPath("/upload"), filename);
        if (!f.exists()) {
            throw new ResponseExecption("文件不存在", new RestResponse(RespCode.FILE_NOT_FILE_ERROR));
        } else {
            return Response.ok(new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    output.write(FileUtils.readFileToByteArray(f));
                }
            }).header("Content-disposition", "attachment;filename=" + filename)
                    .header("Cache-Control", "no-cache").build();
        }
    }

    @POST
    @Path("/upload")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void upload(@FormDataParam("file") InputStream fileInputStream,
                       @FormDataParam("file") FormDataContentDisposition disposition,
                       @Context ServletContext ctx,
                       @Suspended AsyncResponse asyncResponse) throws Exception {

        //获取文件名；
        System.out.println("文件名称: " + disposition.getFileName());
        //获取字段名称，即<input type="file" name="xxx"）中的xxx
        System.out.println("Input name: " + disposition.getName());
        //获取该段content-disposition的内容长度
        System.out.println("文件大小: " + disposition.getSize());
        //获取该段content-disposition的类型，比如form-data
        System.out.println("文件类型: " + disposition.getType());
        //获取本次请求的请求值，比如{name=file, filename=3.jpg}
        System.out.println("文件内容: " + disposition.getParameters());

        File upload = new File(ctx.getRealPath("/upload"),
                UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(disposition.getFileName()));
        try {
            FileUtils.copyInputStreamToFile(fileInputStream, upload);
        } catch (IOException e) {
            throw new ResponseExecption("上传文件失败", new RestResponse(RespCode.UPLOAD_ERROR));
        }
        asyncResponse.resume(new RestResponse<String>("upload success!"));
    }
}