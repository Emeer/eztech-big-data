package com.eztech.spring.bigdata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
@ComponentScan("com.eztech.spring.bigdata.controller")
public class RedisCacheConfiguration extends CachingConfigurerSupport {

    private
    @Value("${spring.redis.host}")
    String redisHost;
    private
    @Value("${spring.redis.port}")
    int redisPort;


    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        // Defaults
        redisConnectionFactory.setHostName(redisHost);
        redisConnectionFactory.setPort(redisPort);
        redisConnectionFactory.setUsePool(true);
        return redisConnectionFactory;
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(cf);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // Number of seconds before expiration. Defaults to unlimited (0)
        cacheManager.setDefaultExpiration(300);
        return cacheManager;
    }
}
