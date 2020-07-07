package com.ratel.hydra.common.configure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author ratel
 * @date 2020-07-07
 */
@Slf4j
@Configuration
@EnableAsync
public class ThreadPoolConfiguartion {

    /**
     * @Description spring @Asyn注解 公用线程池
     * @Author ratel
     * @Date 2020-07-07
     **/
    @Bean
    public ThreadPoolTaskExecutor initCommonThreadPool() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(2);//核心线程数
        threadPool.setMaxPoolSize(4);//最大线程数
        threadPool.setKeepAliveSeconds(60);//线程存活时间
        threadPool.setQueueCapacity(10000);
        threadPool.setAllowCoreThreadTimeOut(false);
        threadPool.setThreadNamePrefix("common-thread-");//线程池前缀
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//拒绝策略， 调用者执行
        return threadPool;
    }

    public ThreadPoolExecutor initThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, //核心线程数
                4, //最大线程数
                60, //线程存活时间
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10000), //阻塞队列
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy() //拒绝策略， 调用者执行
        );
        return threadPoolExecutor;
    }
}
