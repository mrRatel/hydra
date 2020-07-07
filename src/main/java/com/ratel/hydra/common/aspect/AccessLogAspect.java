package com.ratel.hydra.common.aspect;

import com.alibaba.fastjson.JSON;
import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.common.utils.IpUtil;
import com.ratel.hydra.common.utils.ThreadLocalUtil;
import com.ratel.hydra.common.utils.WebUtil;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.AccessLogAdd;
import com.ratel.hydra.system.service.AccessLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Pointcut("@annotation(com.ratel.hydra.common.annotation.OperatingInfo)")
    public void accessLogAnnoPointcut(){};

    @Before("accessLogAnnoPointcut()")
    public void before(JoinPoint point) {
        AccessLogAdd accessLog = constructLog(point);
        service.asyncAdd(accessLog);
        log.debug("==>>日志切面：{}",JSON.toJSONString(accessLog));
    }

    private AccessLogAdd constructLog(JoinPoint point){
        MethodSignature method = (MethodSignature) point.getSignature();
        HttpServletRequest req = getHttpServletRequest();
        String ip = getIP(req);
        OperatingInfo annotation = point.getTarget().getClass().getAnnotation(OperatingInfo.class);

        AccessLogAdd accessLog = new AccessLogAdd()
                .setAssessUrl(getUrl(req))
                .setIp(ip)
                .setOperationContent(annotation == null ? getOperationContent(method) : getOperationContent(method) + annotation.tag())
                .setOperationParam(getOperationParam(point))
                .setSourceUrl(getSourceUrl(req))
                .setUserId(getUserId())
                .setLocation(IpUtil.getCityInfo(ip))
                .setSourceUrl(WebUtil.getReferer(req))
                .setOperatingSystem(WebUtil.getOperatingSystem(req))
                .setAccessDevice(WebUtil.getBrowers(req));
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
        List<Object> list = new ArrayList<>(args.length);
        if (args.length == 1 && !WebUtil.isWebObject(args[0])){
            return JSON.toJSONString(args[0]);
        }

        for (Object arg : args) {
            if (!WebUtil.isWebObject(arg)){
                list.add(arg);
            }
        }
        return JSON.toJSONString(list);
    }

    private String getOperationContent(MethodSignature method){
        OperatingInfo annotation = method.getMethod().getAnnotation(OperatingInfo.class);
        if (annotation == null){
            return method.getName();
        }
        String content = annotation.operation();
        return content;
    }

    private String getSourceUrl(HttpServletRequest request){
        return "";
    }

    private Long getUserId(){
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        return principal == null ? -1L : ((User)principal).getId();
    }
}
