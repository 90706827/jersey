package com.jangni.jersey.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName RestResponse
 * @Description RestFul接口返回结果
 * @Author Mr.Jangni
 * @Date 2019/3/25 19:43
 * @Version 1.0
 **/
@Data
public class RestResponse<T> implements Serializable {

    private String code;
    private T data;
    private String desc;
    //自定义状态不参与JSON
    @JsonIgnore
    private RespCode respCode;
    private int status = 200;
    /**
     * Author Mr.Jangni
     * Description 没有返回数据的构造方法
     * Date 2019/3/27 0:31
     * Param [respCode]
     * Return  
     **/
    public RestResponse(RespCode respCode) {
        this.respCode = respCode;
        this.code = respCode.getCode();
        this.desc = respCode.getDesc();
    }

    /**
     * Author Mr.Jangni
     * Description 有返回数据的构造方法
     * Date 2019/3/27 0:31
     * Param [respCode, data]
     * Return  
     **/
    RestResponse(RespCode respCode, T data) {
        this.respCode = respCode;
        this.code = respCode.getCode();
        this.desc = respCode.getDesc();
        this.data = data;
    }
}
