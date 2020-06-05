package com.ratel.hydra.common.configure;

import com.ratel.hydra.common.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Slf4j
@Configuration
public class SnowflakeIdWorkerConfiguration {

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker(){
        return new SnowflakeIdWorker(1,1);
    }
}
