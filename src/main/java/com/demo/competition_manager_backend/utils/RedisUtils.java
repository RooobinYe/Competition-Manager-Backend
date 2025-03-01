package com.demo.competition_manager_backend.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis缓存工具类
 */
@Component
public class RedisUtils {

    private final RedisTemplate<String, Object> redisTemplate;
    
    @Value("${app.cache.redis.prefix:CompetitionManagerBackend}")
    private String prefix;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置缓存
     *
     * @param key      键
     * @param value    值
     * @param expire   过期时间
     * @param timeUnit 时间单位
     */
    public void set(String key, Object value, long expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(prefix + ":" + key, value, expire, timeUnit);
    }

    /**
     * 设置缓存（默认时间单位为秒）
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间（秒）
     */
    public void set(String key, Object value, long expire) {
        set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 设置永久缓存
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(prefix + ":" + key, value);
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(prefix + ":" + key);
    }

    /**
     * 获取缓存并转换为指定类型
     *
     * @param key   键
     * @param clazz 类型
     * @return 转换后的值
     * @throws IllegalArgumentException 类型转换失败时抛出
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) throws IllegalArgumentException {
        Object value = redisTemplate.opsForValue().get(prefix + ":" + key);
        if (value == null) {
            return null;
        }
        if (clazz.isAssignableFrom(value.getClass())) {
            return (T) value;
        }
        throw new IllegalArgumentException("Class " + value.getClass().getName() + " is not a subclass of " + clazz.getName());
    }

    /**
     * 删除缓存
     *
     * @param key 键
     */
    public void remove(String key) {
        redisTemplate.delete(prefix + ":" + key);
    }

    /**
     * 判断缓存是否存在
     *
     * @param key 键
     * @return true存在 false不存在
     */
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(prefix + ":" + key));
    }

    /**
     * 获取缓存过期时间
     *
     * @param key 键
     * @return 过期时间（秒）
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(prefix + ":" + key, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存过期时间
     *
     * @param key    键
     * @param expire 过期时间（秒）
     */
    public void expire(String key, long expire) {
        redisTemplate.expire(prefix + ":" + key, expire, TimeUnit.SECONDS);
    }
} 