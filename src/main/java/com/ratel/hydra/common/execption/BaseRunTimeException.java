package com.ratel.hydra.common.execption;

import com.ratel.hydra.common.constant.ExceptionEnum;

/**
 * @author ratel
 * @date 2020-05-21
 */
public class BaseBusinessException extends RuntimeException{
    private ExceptionEnum en;

    public ExceptionEnum getEn() {
        return en;
    }

    public BaseBusinessException() {
    }

    public BaseBusinessException(ExceptionEnum en, String msg) {
        super(String.format(en.getMsg(),msg));
        this.en = en;
    }

    public BaseBusinessException(ExceptionEnum en) {
        this.en = en;
    }

     /**
      * @Description 自定义异常 不需要打印堆栈信息
      * @Author      ratel
      * @Date        2020-08-07
      * @return      java.lang.Throwable
      **/
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
