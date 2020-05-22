package com.ratel.hydra.common.aspect;

import com.alibaba.fastjson.JSON;
import com.ratel.hydra.common.utils.IpUtil;
import com.ratel.hydra.common.utils.ThreadLocalUtil;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.AccessLogAdd;
import com.ratel.hydra.system.service.AccessLogService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Aspect
@Component
public class AccessLogAspect {
    @Autowired
    private AccessLogService service;

    @Pointcut("execution(* com.ratel.hydra..*.controller.*.*(..))")
    public void accessLogPointcut(){};

    @Before("accessLogPointcut()")
    public void before(JoinPoint point) {
        AccessLogAdd accessLog = constructLog(point);
        service.add(accessLog);
    }

    private AccessLogAdd constructLog(JoinPoint point){
        MethodSignature method = (MethodSignature) point.getSignature();
        HttpServletRequest req = getHttpServletRequest();
        AccessLogAdd accessLog = new AccessLogAdd()
                .setAssessUrl(getUrl(req))
                .setIp(getIP(req))
                .setOperationContent(getOperationContent(method))
                .setOperationParam(getOperationParam(point))
                .setSourceUrl("")
                .setUserId(getUserId());
        log.info(accessLog.getOperationContent());
        return accessLog;
    }

    private HttpServletRequest getHttpServletRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        return (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
    }

    private String getUrl(HttpServletRequest request){
        return request.getRequestURL().toString();
    }

    private String getIP(HttpServletRequest request){
        return IpUtil.getIpAddr(request);
    }

    private String getOperationParam(JoinPoint point){
        Object[] args = point.getArgs();
        if (args == null){
            return "";
        }
        if (args.length == 1){
            return JSON.toJSONString(args[0]);
        }
        return JSON.toJSONString(args);
    }

    private String getOperationContent(MethodSignature method){
        ApiOperation annotation = method.getMethod().getAnnotation(ApiOperation.class);
        if (annotation == null){
            return method.getName();
        }
        return annotation.value();
    }

    private String getSourceUrl(HttpServletRequest request){
        return "";
    }

    private Long getUserId(){
        User userInfo = ThreadLocalUtil.getUserInfo();

        Map map = new HashMap() { {

        }};


        return userInfo == null ? -1L :userInfo.getId();
    }
}
