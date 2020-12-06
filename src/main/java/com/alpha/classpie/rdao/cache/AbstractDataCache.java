package com.alpha.classpie.rdao.cache;

import com.alpha.classpie.util.RedisDaoUtil;
import com.alpha.classpie.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 杨能
 * @create 2020/12/2
 */
@Component
public abstract class AbstractDataCache<T> implements DataCache<T> {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Override
    public void set(String key, T data) {

    }

    @Override
    public T get(String key) {
        return null;
    }

    @Override
    public void remove(String key) {

    }
}
