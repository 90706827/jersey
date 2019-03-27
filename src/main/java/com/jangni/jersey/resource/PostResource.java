package com.jangni.jersey.resource;

import com.jangni.jersey.core.RespCode;
import com.jangni.jersey.core.RestResponse;
import com.jangni.jersey.exec.ResponseExecption;
import com.jangni.jersey.service.TranListService;
import org.apache.catalina.filters.RestCsrfPreventionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestEndpoint
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/3/21 21:36
 * @Version 1.0
 **/
@Component
@Path("/")
public class PostResource {
    @Autowired
    TranListService tranListService;

    @POST
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public void message(String context,
                        @HeaderParam("token") String token,
                        @Suspended AsyncResponse asyncResponse) throws Throwable {
        System.out.println(token+context);
        int sleep = (int) (Math.random() * 1000+1000);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", sleep);
        map.put("codeMsg", token);
        if(StringUtils.isEmpty(token)){
            throw new ResponseExecption("type参数为空",new RestResponse(RespCode.PARAM_ERROR));
        }
        tranListService.save();
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        asyncResponse.resume(new RestResponse<Map<String,Object>>(map));
    }

}