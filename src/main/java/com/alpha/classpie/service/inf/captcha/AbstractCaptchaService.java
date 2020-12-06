package com.alpha.classpie.service.inf.captcha;

import com.alpha.classpie.util.RedisUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨能
 * @create 2020/11/18
 */
@Data
@NoArgsConstructor
public abstract class AbstractCaptchaService<K,T> implements CaptchaService<T> {
    protected String prefix=null;//缓存名
    protected RedisUtil redisUtil;//redis工具
    protected long expireTimeout;
    protected TimeUnit timeUnit;
    public AbstractCaptchaService(@NotNull final String prefix, RedisUtil redisUtil,long expireTimeout, TimeUnit timeUnit){
        this.prefix=prefix;
        this.expireTimeout=expireTimeout;
        this.timeUnit=timeUnit;
        this.redisUtil=redisUtil;
    }

    protected void saveCaptcha(String key,K captcha){
        redisUtil.set(aggregate(key),captcha);
        redisUtil.expire(aggregate(key),expireTimeout,timeUnit);
    }

    @Override
    public long getCaptchaExpireSeconds(String key) {
        return redisUtil.getExpire(aggregate(key), TimeUnit.SECONDS);
    }

    @Override
    public void deleteCaptcha(String key) {
        redisUtil.delete(aggregate(key));
    }

    protected K getCaptcha(String key,Class<K> kClass){
        return redisUtil.get(aggregate(key),kClass);
    }

    @Override
    public boolean hasCaptchaKey(String key) {
        return redisUtil.hasKey(aggregate(key));
    }

    protected String aggregate(String key){
        return prefix+":"+key;
    }
}
