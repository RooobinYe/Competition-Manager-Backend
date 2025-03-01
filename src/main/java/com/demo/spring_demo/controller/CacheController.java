package com.demo.spring_demo.controller;

import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Redis缓存操作示例控制器
 */
@RestController
@RequestMapping("/api/cache")
public class CacheController {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 设置缓存
     */
    @PostMapping("/set")
    public ApiResponse<Object> setCache(@RequestParam String key, @RequestParam String value, @RequestParam(required = false, defaultValue = "600") long expireTime) {
        try {
            redisUtils.set(key, value, expireTime);
            Map<String, Object> data = new HashMap<>();
            data.put("key", key);
            data.put("value", value);
            data.put("expireTime", expireTime);
            return ApiResponse.success(data);
        } catch (Exception e) {
            return ApiResponse.error(500, "设置缓存失败: " + e.getMessage());
        }
    }

    /**
     * 获取缓存
     */
    @GetMapping("/get")
    public ApiResponse<Object> getCache(@RequestParam String key) {
        Object value = redisUtils.get(key);
        if (value != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("key", key);
            data.put("value", value);
            data.put("expireTime", redisUtils.getExpire(key));
            return ApiResponse.success(data);
        }
        return ApiResponse.error(404, "缓存不存在");
    }

    /**
     * 删除缓存
     */
    @DeleteMapping("/delete")
    public ApiResponse<Object> deleteCache(@RequestParam String key) {
        redisUtils.remove(key);
        return ApiResponse.success(null);
    }

    /**
     * 检查缓存是否存在
     */
    @GetMapping("/exists")
    public ApiResponse<Object> existsCache(@RequestParam String key) {
        boolean exists = redisUtils.hasKey(key);
        Map<String, Object> data = new HashMap<>();
        data.put("key", key);
        data.put("exists", exists);
        return ApiResponse.success(data);
    }
} 