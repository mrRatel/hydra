package com.ratel.hydra;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.ratel.hydra.**.mapper")
@EnableConfigurationProperties
@EnableCaching
public class HydraApplication {
    public static void main(String[] args) {
        MDC.put("TRACE_ID","-1");
        SpringApplication.run(HydraApplication.class, args);
    }
}
