package com.ratel.hydra.common.configure;

import com.ratel.hydra.common.Interceptor.TraceInterceptor;
import com.ratel.hydra.common.properties.ShiroProperty;
import com.ratel.hydra.common.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private TraceInterceptor traceInterceptor;
    @Autowired
    private ShiroProperty shiroProperty;
    @Autowired
    private ResourceProperties resourceProperties;
    /**
     * @Description 请求追踪拦截器，id 通过雪花ID 生成
     * @Author      ratel
     * @Date        2020-07-07
     * @param       snowflakeIdWorker
     * @return      com.ratel.hydra.common.Interceptor.TraceInterceptor
     **/
//    @Bean
    public TraceInterceptor traceInterceptor(SnowflakeIdWorker snowflakeIdWorker){
        traceInterceptor = new TraceInterceptor(snowflakeIdWorker);
        return traceInterceptor;
    }

    /**
     * @Description 添加拦截器
     * @Author      ratel
     * @Date        2020-07-07
     * @param       registry
     * @return      void
     **/
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        List<String> exclude = shiroProperty.getExclude();
        log.debug("TraceInterceptor exclude url {}",exclude.toString());
        registry.addInterceptor(traceInterceptor).excludePathPatterns(exclude).addPathPatterns("/**");
    }

    /**
     * @Description 解决静态资源404
     * @Author      ratel
     * @Date        2020-07-07
     * @param       registry
     * @return      void
     **/
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //添加静态资源路径由静态资源处理器处理
        registry.addResourceHandler("/**").addResourceLocations(resourceProperties.getStaticLocations());
        super.addResourceHandlers(registry);
    }
}
