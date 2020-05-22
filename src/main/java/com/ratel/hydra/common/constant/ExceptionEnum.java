package com.ratel.hydra.common.constant;

/**
 * @author ratel
 * @date 2020-05-21
 */
public enum  ExceptionEnum {
    SYS_ERR("系统异常");

    private String msg;


    public String getMsg() {
        return msg;
    }

    ExceptionEnum(String msg) {
        this.msg = msg;
    }}