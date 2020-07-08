package com.ratel.hydra.common.Interceptor;

import com.ratel.hydra.common.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 请求追踪 拦截器
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Component
public class TraceInterceptor implements HandlerInterceptor {

    /** 雪花ID */
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    private static final ReentrantLock lock = new ReentrantLock();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("TRACE_ID", snowflakeIdWorker.nextId() + "");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.clear();
    }

    public TraceInterceptor(SnowflakeIdWorker snowflakeIdWorker) {
        this.snowflakeIdWorker = snowflakeIdWorker;
    }
}
