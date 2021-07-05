package com.imooc.mall.exception;

/**
 * 异常枚举
 */
public enum ImoocMallExceptionEnum {

    NEED_USER_NAME(10001,"用户名不能为空"),
    NEED_PASSWORD(10002, "密码不能为空"),
    PASSWORD_TOO_SHORT(10003,"密码长度太短"),
    USERNAME_EXIST(10004,"用户名已存在"),
    INSERT_FAIL(10005,"插入失败，请稍后重试");

    private Integer code;

    private String msg;

    ImoocMallExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}