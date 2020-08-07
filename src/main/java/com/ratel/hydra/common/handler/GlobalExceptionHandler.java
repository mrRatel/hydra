package com.ratel.hydra.common.handler;

import com.ratel.hydra.common.constant.ExceptionEnum;
import com.ratel.hydra.common.execption.SystemBusinessException;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.properties.ViewUrlProperty;
import com.ratel.hydra.common.pojo.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.ratel.hydra.common.constant.ExceptionEnum.*;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Description 全局异常
     * @Author      ratel
     * @Date        2020-08-07
     * @param       e
     * @return      com.ratel.hydra.common.pojo.WebResult
     **/
    @ExceptionHandler(Exception.class)
    public WebResult exceptionHandler(Exception e){
//        WebResultFactory.failed()
        log.error("系统异常",e);
        return WebResultFactory.failed(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(), ViewUrlProperty.ERROR);

    }

    /**
     * @Description 业务异常
     * @Author      ratel
     * @Date        2020-08-07
     * @param       e
     * @return      com.ratel.hydra.common.pojo.WebResult
     **/
    @ExceptionHandler(SystemBusinessException.class)
    public WebResult systemExceptionHandler(SystemBusinessException e){
        if (!ExceptionEnum.AUTH1007.equals(e.getEn())) {
            log.error(e.getMessage(), e);
        }
        return WebResultFactory.failed(e.getMessage(),e.getEn().toString());
    }

    /**
     * @Description 无效账号异常
     * @Author      ratel
     * @Date        2020-08-07
     * @param       e
     * @return      com.ratel.hydra.common.pojo.WebResult
     **/
    @ExceptionHandler(UnknownAccountException.class)
    public WebResult unknownAccountExceptionHandler(UnknownAccountException e){
        log.warn(AUTH1001.getMsg());
        return WebResultFactory.failed(AUTH1000.getMsg(),AUTH1000.toString());
    }

    /**
     * @Description 身份认证失败
     * @Author      ratel
     * @Date        2020-08-07
     * @param       e
     * @return      com.ratel.hydra.common.pojo.WebResult
     **/
    @ExceptionHandler(IncorrectCredentialsException.class)
    public WebResult incorrectCredentialsExceptionHandler(IncorrectCredentialsException e){
        log.warn(AUTH1001.getMsg());
        return WebResultFactory.failed(AUTH1001.getMsg(),AUTH1001.toString());
    }


    /**
     * @Description 账户已被锁定
     * @Author      ratel
     * @Date        2020-08-07
     * @param       e
     * @return      com.ratel.hydra.common.pojo.WebResult
     **/
    @ExceptionHandler(LockedAccountException.class)
    public WebResult lockedAccountExceptionExceptionHandler(LockedAccountException e){
        log.warn(AUTH1002.getMsg());
        return WebResultFactory.failed(AUTH1002.getMsg(),AUTH1002.toString());
    }

    /**
     * @Description 身份认证失败
     * @Author      ratel
     * @Date        2020-08-07
     * @param       e
     * @return      com.ratel.hydra.common.pojo.WebResult
     **/
    @ExceptionHandler(AuthenticationException.class)
    public WebResult authenticationExceptionExceptionHandler(AuthenticationException e){
        log.warn(AUTH1003.getMsg());
        log.error(AUTH1003.getMsg(),e);
        return WebResultFactory.failed(AUTH1003.getMsg(),AUTH1003.toString());
    }

    /**
     * @Description 无效参数
     * @Author      ratel
     * @Date        2020-08-07
     * @param       e
     * @return      com.ratel.hydra.common.pojo.WebResult
     **/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        String errMsg = "";
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldErro : e.getBindingResult().getFieldErrors()) {
            sb.append("【").append(fieldErro.getField()).append(":").append(fieldErro.getDefaultMessage()).append("】");
        }
        return WebResultFactory.failed(sb.toString(),SYS1001.toString());
    }

    /**
     * @Description 非法参数
     * @Author      ratel
     * @Date        2020-08-07
     * @param       e
     * @return      com.ratel.hydra.common.pojo.WebResult
     **/
    @ExceptionHandler(IllegalArgumentException.class)
    public WebResult illegalArgumentExceptionExceptionHandler(IllegalArgumentException e){
        log.info(e.getMessage());
        log.error("IllegalArgumentException",e);
        return WebResultFactory.failed(e.getMessage(),"");
    }

    /**
     * @Description 不支持的请求方法
     * @Author      ratel
     * @Date        2020-08-07
     * @param       e
     * @return      com.ratel.hydra.common.pojo.WebResult
     **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public WebResult httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e){
        log.info(e.getMessage());
        return WebResultFactory.failed(e.getMessage(),"405");
    }

    /**
     * @Description 未授权
     * @Author      ratel
     * @Date        2020-08-07
     * @param       e
     * @return      com.ratel.hydra.common.pojo.WebResult
     **/
    @ExceptionHandler(UnauthorizedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
    public WebResult UnauthorizedExceptionHandler(UnauthorizedException e){
        return WebResultFactory.failed(e.getMessage(),HttpStatus.FORBIDDEN.value(),ViewUrlProperty.UNAUTHORIZED);
    }
}
