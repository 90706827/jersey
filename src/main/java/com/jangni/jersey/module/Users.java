package com.jangni.jersey.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.ws.rs.FormParam;

/**
 * @ClassName Users
 * @Description From bean
 * @Author Mr.Jangni
 * @Date 2019/3/27 23:03
 * @Version 1.0
 **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private int id;
    @FormParam("name")
    private String name;
    @FormParam("password")
    private String password;
}