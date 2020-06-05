package com.ratel.hydra.common.constant;


import com.ratel.hydra.common.execption.SystemException;

public enum ExcelTypeEnum {
    LOGIN_LOG("登录日志"),
    ACCESS_LOG( "访问日志");

    private String name;

    ExcelTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ExcelTypeEnum getEnum(String type){
        ExcelTypeEnum[] values = values();
        for (ExcelTypeEnum value : values) {
            if (value.getName().equals(type)) {
                return value;
            }
        }
        throw new SystemException(ExceptionEnum.SYS1005,type);
    }
}
