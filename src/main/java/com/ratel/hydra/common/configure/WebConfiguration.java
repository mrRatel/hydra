package com.ratel.hydra.common.configure;

import com.ratel.hydra.common.Interceptor.AccessLogInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
//@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private AccessLogInterceptor accessLogInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("addInterceptors ==>",accessLogInterceptor.toString());
        registry.addInterceptor(accessLogInterceptor).addPathPatterns("/**");
    }
}
