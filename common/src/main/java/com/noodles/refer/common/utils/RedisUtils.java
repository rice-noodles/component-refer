package com.noodles.refer.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 需要放到能够被spring扫描到的地方
 * 需要引入redis相关的依赖
 *
 * @author Noodles
 * @since 2022/11/28 14:30
 */
@Component
public class RedisUtils implements ApplicationContextAware {

    private static RedisTemplate<Object, Object> redisTemplate;

    public static void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static void set(String key, Object value, long expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static Long increment(String key, Long value) {
        return redisTemplate.opsForValue().increment(key, value);
    }

    public static Long increment(String key, long expire, TimeUnit timeUnit) {
        Long result = redisTemplate.opsForValue().increment(key);
        expire(key, expire, timeUnit);
        return result;
    }

    public static Long increment(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    public static Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public static Boolean expire(String key, long expire, TimeUnit timeUnit) {
        return redisTemplate.expire(key, expire, timeUnit);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate<Object, Object>) applicationContext.getBean("redisTemplate");

        // 下面序列化，解决乱码问题
        Jackson2JsonRedisSerializer<Object> jsonSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jsonSerializer.setObjectMapper(new ObjectMapper());
        // String 的 序列化
        StringRedisSerializer strSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(strSerializer);
        redisTemplate.setHashKeySerializer(strSerializer);
        redisTemplate.setHashValueSerializer(jsonSerializer);
        redisTemplate.setValueSerializer(strSerializer);
        redisTemplate.afterPropertiesSet();
    }

}
