package com.ratel.hydra;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.ratel.hydra.**.mapper")
@EnableConfigurationProperties
@EnableTransactionManagement(order = 2000)
public class HydraApplication {

    public static void main(String[] args) {
        MDC.put("TRACE_ID","-1");
        SpringApplication.run(HydraApplication.class, args);
    }

}
