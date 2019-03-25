package com.jangni.jersey.core;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * @ClassName RestResponse
 * @Description RestFul接口返回结果
 * @Author Mr.Jangni
 * @Date 2019/3/25 19:43
 * @Version 1.0
 **/
public class RestResponse<T> implements Serializable {
    private int status;
    private int code;
    private String message;
    private Throwable throwable;
    private T data;

    public RestResponse(int status, int code, String message, Throwable throwable, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.throwable = throwable;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof RestResponse) {
            RestResponse re = (RestResponse) o;
            return getStatus() == re.getStatus() &&
                    getCode() == re.getCode() &&
                    ObjectUtils.nullSafeEquals(getMessage(), re.getMessage()) &&
                    ObjectUtils.nullSafeEquals(getThrowable(), re.getThrowable()) &&
                    ObjectUtils.nullSafeEquals(getData(), re.getData());
        }

        return false;
    }

    @Override
    public int hashCode() {
        // noinspection ThrowableResultOfMethodCallIgnored
        return ObjectUtils.nullSafeHashCode(new Object[] {
                getStatus(), getCode(), getMessage(),getThrowable(), getData()
        });
    }

    public static class Builder<T> {

        private HttpStatus status;
        private int code;
        private String message;
        private Throwable throwable;
        private T data;

        public Builder() {}

        public Builder setStatus(int statusCode) {
            this.status = HttpStatus.valueOf(statusCode);
            return this;
        }

        public Builder setStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder setCode(int code) {
            this.code = code;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setThrowable(Throwable throwable) {
            this.throwable = throwable;
            return this;
        }

        public Builder setData(T data) {
            this.data = data;
            return this;
        }

        public RestResponse<T> build() {
            if (this.status == null) {
                this.status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return new RestResponse(this.status.value(), this.code, this.message,this.throwable, this.data);
        }
    }
}
