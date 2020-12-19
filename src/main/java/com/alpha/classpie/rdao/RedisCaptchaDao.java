package com.alpha.classpie.rdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Repository
public abstract class RedisCaptchaDao<T> implements CaptchaDao<T>{


    @Autowired
    protected RedisTemplate<String,Object> redisTemplate;


    protected String aggregate(String sessionId,String captchaId){
        return captchaId+":"+sessionId;
    }

    @Override
    public void setCaptcha(@NotNull String sessionId, String captchaId,T code) {
        String captchaKey=aggregate(sessionId, captchaId);
        redisTemplate.opsForValue().set(captchaKey,code);
        redisTemplate.expire(captchaKey,getExpireTimeout(),getTimeUnit());
    }


    @Override
    public void removeCaptcha(@NotNull String sessionId, String captchaId) {
        redisTemplate.delete(aggregate(sessionId, captchaId));
    }

    @Override
    public boolean hasCaptcha(@NotNull String sessionId, String captchaId) {
        return redisTemplate.hasKey(aggregate(sessionId, captchaId));
    }

    @Override
    public long getExpireSeconds(@NotNull String sessionId, String captchaId) {
        return redisTemplate.getExpire(aggregate(sessionId, captchaId),TimeUnit.SECONDS);
    }
}
