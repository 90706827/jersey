package com.jangni.jersey.exec;

import com.jangni.jersey.core.RespCode;
import com.jangni.jersey.core.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @ClassName ResponseExceptionHandler
 * @Description 全局异常处理
 * @Author Mr.Jangni
 * @Date 2019/3/26 20:20
 * @Version 1.0
 **/
@Provider
public class ResponseExceptionHandler implements ExceptionMapper<Exception> {

    private static Logger logger = LoggerFactory.getLogger(ResponseExceptionHandler.class);

    @Override
    public Response toResponse(Exception e) {
        //根据自定义类型判断并作出相应的处理
        if (e instanceof ResponseExecption) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(((ResponseExecption) e).getRestResponse()).type(MediaType.APPLICATION_JSON).build();
        } else if (e instanceof NotFoundException) {

            logger.error("客户端请求路径错误:", e);
            RestResponse rest = new RestResponse(RespCode.URL_ERROR);
            return Response.status(Response.Status.NOT_FOUND).entity(rest).type(MediaType.APPLICATION_JSON).build();
        } else {
            logger.error("未知异常:", e);
            RestResponse rest = new RestResponse(RespCode.SYS_ERROR);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).type(MediaType.APPLICATION_JSON).build();
        }

    }
}