package com.jangni.jersey.exec;

import com.jangni.jersey.core.RestResponse;

/**
 * @ClassName ResponseExecption
 * @Description 统一异常
 * @Author Mr.Jangni
 * @Date 2019/3/26 20:12
 * @Version 1.0
 **/
public class ResponseExecption extends Exception {

    /**
     * Author Mr.Jangni
     * Description 自定已返回实体类
     * Date 2019/3/27 0:20
     * Param
     * Return
     **/
    private RestResponse restResponse;

    public ResponseExecption() {

    }

    public ResponseExecption(String message, RestResponse restResponse) {
        super(message);
        this.restResponse = restResponse;
    }

    public ResponseExecption(String message, RestResponse restResponse, Throwable cause) {
        super(message, cause);
        this.restResponse = restResponse;
    }

    public RestResponse getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(RestResponse restResponse) {
        this.restResponse = restResponse;
    }
}