package com.ratel.hydra.common.execption;

import com.ratel.hydra.common.constant.ExceptionEnum;

/**
 * @author ratel
 * @date 2020-05-21
 */
public class BaseException extends RuntimeException{
    private ExceptionEnum en;

    public ExceptionEnum getEn() {
        return en;
    }

    public BaseException() {
    }

    public BaseException(ExceptionEnum en,String msg) {
        super(String.format(en.getMsg(),msg));
        this.en = en;
    }

    public BaseException(ExceptionEnum en) {
        this.en = en;
    }
}
