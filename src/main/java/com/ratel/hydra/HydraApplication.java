package com.ratel.hydra;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan("com.ratel.hydra.**.mapper")
@EnableConfigurationProperties
public class HydraApplication {

    public static void main(String[] args) {
        SpringApplication.run(HydraApplication.class, args);
    }

}
