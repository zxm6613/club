package com.zxm.club.auth.domain.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * RedisUtil工具类
 */
@Component
@Slf4j
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    private static final String CACHE_KEY_SEPARATOR = ".";

    /**
     * 构建缓存key
     * 将传入的多个字符串以"."连接生成一个 Redis 的 key
     *
     * @param strObjs 多个字符串
     * @return 拼接后的字符串作为 Redis 的 key
     */
    public String buildKey(String... strObjs) {
        return Stream.of(strObjs).collect(Collectors.joining(CACHE_KEY_SEPARATOR));
    }

    /**
     * 判断是否存在指定的 key
     *
     * @param key Redis 中的 key
     * @return 如果 key 存在则返回 true，否则返回 false
     */
    public boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除指定的 key
     *
     * @param key 要删除的 Redis key
     * @return 删除成功返回 true，删除失败返回 false
     */
    public boolean del(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 设置指定的 key 的值为指定的 value
     *
     * @param key   Redis key
     * @param value Redis value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 如果 key 不存在，则设置 key 的值为 value，并指定过期时间
     *
     * @param key      Redis key
     * @param value    Redis value
     * @param time     过期时间
     * @param timeUnit 过期时间的单位
     * @return 如果设置成功返回 true，如果 key 已存在返回 false
     */
    public boolean setNx(String key, String value, Long time, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
    }

    /**
     * 获取指定 key 的值
     *
     * @param key Redis key
     * @return Redis 中存储的字符串值
     */
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 向有序集合中添加元素
     *
     * @param key   Redis key
     * @param value 添加的元素
     * @param score 元素的分数（排序依据）
     * @return 如果成功添加返回 true，否则返回 false
     */
    public Boolean zAdd(String key, String value, Long score) {
        return redisTemplate.opsForZSet().add(key, value, Double.valueOf(String.valueOf(score)));
    }

    /**
     * 获取有序集合的元素数量
     *
     * @param key Redis key
     * @return 有序集合中元素的数量
     */
    public Long countZset(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 获取有序集合中指定范围内的元素
     *
     * @param key   Redis key
     * @param start 起始位置（从 0 开始）
     * @param end   结束位置
     * @return 指定范围内的元素集合
     */
    public Set<String> rangeZset(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 从有序集合中移除指定的元素
     *
     * @param key   Redis key
     * @param value 要移除的元素
     * @return 被移除的元素数量
     */
    public Long removeZset(String key, Object value) {
        return redisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * 从有序集合中移除一组元素
     *
     * @param key   Redis key
     * @param value 要移除的元素集合
     */
    public void removeZsetList(String key, Set<String> value) {
        value.stream().forEach((val) -> redisTemplate.opsForZSet().remove(key, val));
    }

    /**
     * 获取有序集合中指定元素的分数
     *
     * @param key   Redis key
     * @param value 元素
     * @return 元素的分数
     */
    public Double score(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 获取有序集合中分数在指定范围内的元素
     *
     * @param key   Redis key
     * @param start 分数范围的起始值
     * @param end   分数范围的结束值
     * @return 在指定分数范围内的元素集合
     */
    public Set<String> rangeByScore(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScore(key, Double.valueOf(String.valueOf(start)), Double.valueOf(String.valueOf(end)));
    }

    /**
     * 为有序集合中的指定元素增加分数
     *
     * @param key   Redis key
     * @param obj   要增加分数的元素
     * @param score 要增加的分数
     * @return 增加分数后的新分数
     */
    public Object addScore(String key, Object obj, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, obj, score);
    }

    /**
     * 获取有序集合中指定元素的排名
     *
     * @param key   Redis key
     * @param obj   元素
     * @return 元素的排名（从 0 开始）
     */
    public Object rank(String key, Object obj) {
        return redisTemplate.opsForZSet().rank(key, obj);
    }
}