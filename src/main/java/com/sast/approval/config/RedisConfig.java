package com.sast.approval.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.NON_FINAL;

@Configuration
@EnableCaching // 启用缓存支持
public class RedisConfig {

    /**
     * 创建一个支持Java 8日期时间类型的ObjectMapper，并启用类型信息
     */
    private ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 添加Java 8日期时间支持
        objectMapper.registerModule(new JavaTimeModule());
        // 启用类型信息，确保反序列化时能够恢复为正确的类型
        objectMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                NON_FINAL,
                PROPERTY
        );
        return objectMapper;
    }

    /**
     * 配置缓存管理器
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        // default config, ttl 600 seconds
        // TODO: 是否针对不同的数据采用随机的过期时间 避免缓存雪崩？
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(600))
                // 使用StringRedisSerializer来序列化和反序列化redis的key值
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 使用支持类型信息的GenericJackson2JsonRedisSerializer
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(createObjectMapper())))
                // 禁用空值
                .disableCachingNullValues();

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .build();
    }

    /**
     * 配置RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        // 使用支持类型信息的GenericJackson2JsonRedisSerializer
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer(createObjectMapper()));
        
        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        // Hash的value也采用支持类型信息的GenericJackson2JsonRedisSerializer
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(createObjectMapper()));
        
        template.afterPropertiesSet();
        return template;
    }
} 