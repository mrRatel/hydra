package com.ratel.hydra.common.constant;

/**
 * @author ratel
 * @date 2020-05-21
 */
public enum  ExceptionEnum {
    SYS1000("系统异常"),
    SYS1001("参数异常"),
    AUTH1000("未知账号异常"),
    AUTH1001("密码不正确"),
    AUTH1002("账号已被锁定"),
    AUTH1003("认证失败"),
    AUTH1004("未知账号异常"),
    AUTH1005("用户名不存在"),

    ;

    private String msg;


    public String getMsg() {
        return msg;
    }

    ExceptionEnum(String msg) {
        this.msg = msg;
    }}