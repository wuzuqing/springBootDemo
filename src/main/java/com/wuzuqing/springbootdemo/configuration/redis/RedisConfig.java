package com.wuzuqing.springbootdemo.configuration.redis;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

import static java.util.Collections.singletonMap;

/**
 * RedisConfig
 *
 * @author MT.LUO
 * 2018/8/6 23:18
 * @Description:
 */
@Slf4j
@Configuration
@EnableCaching
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        /* 默认配置， 默认超时时间为30s */
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration
                .ofSeconds(30L)).disableCachingNullValues();

        /* 配置test的超时时间为120s*/
        RedisCacheManager cacheManager = RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter
                (connectionFactory)).cacheDefaults(defaultCacheConfig).withInitialCacheConfigurations(singletonMap
                ("test", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(120L))
                        .disableCachingNullValues())).transactionAware().build();

        return cacheManager;
    }

    @Bean
    public RedisTemplate<String, Integer> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setExposeConnection(true);
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.afterPropertiesSet();


        /*StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();*/
        return redisTemplate;
    }

}