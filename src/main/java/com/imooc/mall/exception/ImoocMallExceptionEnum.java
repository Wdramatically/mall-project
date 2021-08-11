package com.imooc.mall.exception;

/**
 * 异常枚举
 */
public enum ImoocMallExceptionEnum {

    NEED_USER_NAME(10001,"用户名不能为空"),
    NEED_PASSWORD(10002, "密码不能为空"),
    PASSWORD_TOO_SHORT(10003,"密码长度太短"),
    USERNAME_EXIST(10004,"用户名已存在"),
    INSERT_FAIL(10005,"插入失败，请稍后重试"),
    WRONG_PASSWORD(10006,"密码错误"),
    NEED_LOGIN(10007,"用户尚未登录"),
    UPDATE_FAIL(10008,"更新失败"),
    NEED_ADMIN(10009,"无管理员权限"),
    PARAM_NAME_EXIST(10010, "参数值已存在"),
    CREATE_FAILED(10011, "新增失败"),
    REQUEST_PARAM_ERROR(10012, "参数错误"),
    SYSTEM_ERROR(20000,"系统异常");

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
