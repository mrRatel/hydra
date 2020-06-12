package com.ratel.hydra.common.handler;

import com.ratel.hydra.common.constant.ExceptionEnum;
import com.ratel.hydra.common.execption.SystemException;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.vo.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.annotation.HttpConstraint;

import static com.ratel.hydra.common.constant.ExceptionEnum.*;

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
        if (!ExceptionEnum.AUTH1007.equals(e.getEn())) {
            log.error(e.getMessage(), e);
        }
        return WebResultFactory.failed(e.getMessage(),e.getEn().toString());
    }

    @ExceptionHandler(UnknownAccountException.class)
    public WebResult unknownAccountExceptionHandler(UnknownAccountException e){
        log.warn(AUTH1001.getMsg());
        return WebResultFactory.failed(AUTH1000.getMsg(),AUTH1000.toString());
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public WebResult incorrectCredentialsExceptionHandler(IncorrectCredentialsException e){
        log.warn(AUTH1001.getMsg());
        return WebResultFactory.failed(AUTH1001.getMsg(),AUTH1001.toString());
    }


    @ExceptionHandler(LockedAccountException.class)
    public WebResult lockedAccountExceptionExceptionHandler(LockedAccountException e){
        log.warn(AUTH1002.getMsg());
        return WebResultFactory.failed(AUTH1002.getMsg(),AUTH1002.toString());
    }

    @ExceptionHandler(AuthenticationException.class)
    public WebResult authenticationExceptionExceptionHandler(AuthenticationException e){
        log.warn(AUTH1003.getMsg());
        return WebResultFactory.failed(AUTH1003.getMsg(),AUTH1003.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        String errMsg = "";
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldErro : e.getBindingResult().getFieldErrors()) {
            sb.append("【").append(fieldErro.getField()).append(":").append(fieldErro.getDefaultMessage()).append("】");
        }
        return WebResultFactory.failed(sb.toString(),SYS1001.toString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public WebResult illegalArgumentExceptionExceptionHandler(IllegalArgumentException e){
        log.info(e.getMessage());
        return WebResultFactory.failed(e.getMessage(),"");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public WebResult httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e){
        log.info(e.getMessage());
        return WebResultFactory.failed(e.getMessage(),"405");
    }
}
