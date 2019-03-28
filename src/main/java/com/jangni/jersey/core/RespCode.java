package com.jangni.jersey.core;

/**
 * @ClassName RespCode
 * @Description 自定义返回码
 * @Author Mr.Jangni
 * @Date 2019/3/26 20:37
 * @Version 1.0
 **/
public enum RespCode {
    // 交易状态码
    OK("200", "交易成功"),
    URL_ERROR("404", "请求URL异常"),
    PARAM_ERROR("401", "请求参数异常"),
    UPLOAD_ERROR("501", "上传文件失败"),
    FILE_NOT_FILE_ERROR("502", "文件不存在"),
    SYS_ERROR("599", "系统异常");

    private final String code;
    private final String desc;

    private RespCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "响应码：" + code + "响应描述" + desc;
    }
}
