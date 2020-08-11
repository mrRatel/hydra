package com.ratel.hydra.common.configure;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.lang.reflect.Method;

import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;

/**
 * Redis 配置类
 * @author ratel
 * @date 2020/7/14
 */
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * @Description 自定义缓存 key 生成策略
     *              这里仅用作测试，实际情况不建议使用超长 Key
     * @Author      ratel
     * @Date        2020/7/14
     * @return      org.springframework.cache.interceptor.KeyGenerator
     **/
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(o.getClass().getSimpleName());
//            sb.append(method.getName());
//            for (Object object : objects) {
//                sb.append(object.toString());
//            }
//            return sb.toString();
            return JSON.toJSONString(objects);
        };
    }

    /**
     * @Description 缓存管理器
     * @Author      ratel
     * @Date        2020/7/14
     * @return      org.springframework.cache.CacheManager
     **/
    @Bean("ICacheManager")
    @Override
    public CacheManager cacheManager(){
        RedisCacheConfiguration cacheConfiguration =
                defaultCacheConfig()
                        .disableCachingNullValues()
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(cacheConfiguration).build();
    }

    /**
     * @Description Redis 模板类 功能类似 JDBC template 可以直接操作Redis
     * @Author      ratel
     * @Date        2020/7/14
     * @param       factory
     * @return      org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.String>
     **/
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        setSerializer(template);// 设置序列化工具
        template.afterPropertiesSet();
        return template;
    }

    /**
     * @Description 配置序列化策略
     * @Author      ratel
     * @Date        2020/7/14
     * @param       template
     * @return      void
     **/
    private void setSerializer(StringRedisTemplate template) {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }
}
