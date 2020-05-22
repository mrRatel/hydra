package com.ratel.hydra.common.handler;

import com.ratel.hydra.common.execption.SystemException;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.vo.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public WebResult exceptionHandler(Exception e){
//        WebResultFactory.failed()
        log.error("系统异常",e);
        return WebResultFactory.failed(null,null);
    }

    @ExceptionHandler(SystemException.class)
    public WebResult systemExceptionHandler(SystemException e){
        log.error(e.getMessage(),e);
        return WebResultFactory.failed(e.getEn().toString(),e.getMessage());
    }
}
