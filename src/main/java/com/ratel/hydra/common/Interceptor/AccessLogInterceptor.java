package com.ratel.hydra.common.Interceptor;

import com.alibaba.fastjson.JSON;
import com.ratel.hydra.common.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpMethod.POST;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
//@Component
public class AccessLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("请求IP:{},请求URL:{},请求参数:{}", IpUtil.getIpAddr(request),request.getRequestURL());
        String queryString = request.getQueryString();
            log.info("queryString ==> " + queryString);
        if (request.getMethod().equals(POST)) {
        }

        if(handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod)handler;
            Object bean = h.getBean();
            log.info(JSON.toJSONString(bean));
//            MethodParameter[] methodParameters = h.getMethodParameters();
//            for (MethodParameter methodParameter : methodParameters) {
//                log.info(JSON.toJSONString(methodParameter));
//            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
