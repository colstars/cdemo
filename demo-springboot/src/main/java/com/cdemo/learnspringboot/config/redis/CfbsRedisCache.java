package com.cdemo.learnspringboot.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheElement;
import org.springframework.data.redis.cache.RedisCacheKey;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: star
 * @time: 2021/3/17 23:59
 */
public class CfbsRedisCache extends RedisCache {

    private final static Logger LOGGER = LoggerFactory.getLogger(CfbsRedisCache.class);

    private final byte[] prefix;
    private final RedisOperations<?, ?> redisOperations;
    private final Long expiration;

    public CfbsRedisCache(String name, RedisOperations<?, ?> redisOperations, long expiration) {
        super(name, "cfbs:".getBytes(StandardCharsets.UTF_8), redisOperations, expiration);
        this.prefix = "cfbs:".getBytes(StandardCharsets.UTF_8);
        this.redisOperations = redisOperations;
        this.expiration = expiration;

    }

    public CfbsRedisCache(String name, byte[] prefix, RedisOperations<?, ?> redisOperations, long expiration) {
        super(name, prefix, redisOperations, expiration);
        this.prefix = prefix;
        this.redisOperations = redisOperations;
        this.expiration = expiration;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        try {
//            return super.get(key, type);
            ValueWrapper wrapper = get(new RedisCacheKey(key)
                    .withKeySerializer(redisOperations.getKeySerializer()));
            return wrapper == null ? null : (T) wrapper.get();
        } catch (Exception e) {
            LOGGER.error("cache get error", e);
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        try {
            super.put(key, value);
        } catch (Exception e) {
            LOGGER.error("cache get error", e);
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        try {
            return super.putIfAbsent(key, value);
        } catch (Exception e) {
            LOGGER.error("cache get error", e);
        }
        return null;
    }

    @Override
    public void evict(Object key) {
        try {
            super.evict(key);
        } catch (Exception e) {
            LOGGER.error("cache get error", e);
        }
    }

    public void put(Object key, Object value, long ttl) {
        try {
            LOGGER.info("CfbsRedisCache.put begin : key is {}, value is {}, ttl is {}", key, value, ttl);
            Assert.notNull(key, "key is necessary");
            Assert.notNull(key, "value is necessary");
            Assert.notNull(key, "ttl is necessary");

            final RedisCacheKey redisCacheKey = new RedisCacheKey(key).withKeySerializer(redisOperations.getKeySerializer());
            super.put(new RedisCacheElement(redisCacheKey, value).expireAfter(ttl));
        } catch (Exception e) {
            LOGGER.info("CfbsRedisCache.put error : key is {}", key, e);
        }
        LOGGER.info("CfbsRedisCache.put end : key is {} success", key);
    }

    public void putIfAbsent(Object key, Object value, long ttl) {
        try {
            LOGGER.info("CfbsRedisCache.putIfAbsent begin : key is {}, value is {}, ttl is {}", key, value, ttl);
            Assert.notNull(key, "key is necessary");
            Assert.notNull(key, "value is necessary");
            Assert.notNull(key, "ttl is necessary");

            final RedisCacheKey redisCacheKey = new RedisCacheKey(key).usePrefix(prefix).withKeySerializer(redisOperations.getKeySerializer());
            super.putIfAbsent(new RedisCacheElement(redisCacheKey, value).expireAfter(ttl));
        } catch (Exception e) {
            LOGGER.info("CfbsRedisCache.putIfAbsent error : key is {}", key, e);
        }
        LOGGER.info("CfbsRedisCache.putIfAbsent end : key is {} success", key);
    }
}


