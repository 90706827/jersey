package com.jangni.jersey.core;

import lombok.Data;

import javax.ws.rs.container.AsyncResponse;

/**
 * @ClassName Context
 * @Description 上下文信息
 * @Author Mr.Jangni
 * @Date 2019/3/28 17:20
 * @Version 1.0
 **/
@Data
public class JobContext {

    private AsyncResponse asyncResponse;

    private String tranNo;
}
