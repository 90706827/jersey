package com.jangni.jersey.resource;

import com.jangni.jersey.core.RestResponse;
import com.jangni.jersey.module.Users;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

/**
 * @ClassName FromResource
 * @Description From
 * @Author Mr.Jangni
 * @Date 2019/3/27 22:51
 * @Version 1.0
 **/
@Component
@Path("/")
public class FromResource {


    @POST
    @Path("/bean")
    @Produces(MediaType.APPLICATION_JSON)
    public void bean(@BeanParam Users users,
                     @Suspended AsyncResponse asyncResponse) {

        users.setId(10001);
        asyncResponse.resume(new RestResponse<Users>(users));
    }

    @POST
    @Path("/form")
    @Produces(MediaType.APPLICATION_JSON)
    public void from(@DefaultValue("zhangsan") @FormParam("name") String name,
                     @DefaultValue("12345678") @FormParam("password") String password,
                     @Suspended AsyncResponse asyncResponse) {

        asyncResponse.resume(new RestResponse<Users>(new Users(10002,name, password)));
    }
}