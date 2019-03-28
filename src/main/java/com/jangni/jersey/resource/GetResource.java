package com.jangni.jersey.resource;

import com.jangni.jersey.core.RespCode;
import com.jangni.jersey.core.RestResponse;
import com.jangni.jersey.entity.TranList;
import com.jangni.jersey.exec.ResponseExecption;
import com.jangni.jersey.service.TranListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;

/**
 * @ClassName GetResource
 * @Description Get
 * @Author Mr.Jangni
 * @Date 2019/3/27 12:31
 * @Version 1.0
 **/
@Component
@Path("/")
public class GetResource {
    @Autowired
    TranListService tranListService;

    @GET
    @Path("/get/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void message(String context, @PathParam("id") String id,
                        @QueryParam("tranNo") String tranNo,
                        @Suspended AsyncResponse asyncResponse) throws Throwable {
        System.out.println(id);
        if (StringUtils.isEmpty(tranNo)) {
            throw new ResponseExecption("参数错误tranNo：", new RestResponse(RespCode.PARAM_ERROR));
        }
        TranList tranList = tranListService.getTranListByTranNo(tranNo);
        asyncResponse.resume(new RestResponse<TranList>(tranList));
    }

}
