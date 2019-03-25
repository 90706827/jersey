package com.jangni.jersey.resource;

import com.jangni.jersey.service.TranListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
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
public class JerseyResource {
    @Autowired
    TranListService tranListService;

    @POST
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public void message(String context,
                        @HeaderParam("type") String type,
                        @Suspended AsyncResponse asyncResponse) {
        System.out.println(type+context);
        int sleep = (int) (Math.random() * 1000+1000);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", sleep);
        map.put("codeMsg", type);
        tranListService.save();
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        asyncResponse.resume(map);
    }

}