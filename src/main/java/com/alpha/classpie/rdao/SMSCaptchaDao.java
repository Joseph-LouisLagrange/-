package com.alpha.classpie.rdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨能
 * @create 2020/11/3
 */
@Validated
@Repository("smsCaptchaDao")
public class SMSCaptchaDao extends RedisCaptchaDao<Integer> {
    @Value("${captcha.SMS.expireTimeout}")
    long expireTimeout;
    @Value("${captcha.SMS.timeUnit}")
    TimeUnit timeUnit;
    @Override
    public Integer getCode(@NotNull String sessionId, String captchaId) {
        return (Integer) redisTemplate.opsForValue().get(aggregate(sessionId, captchaId));
    }

    public void setCaptcha(@NotNull String phone, String captchaId, Integer code) {
        super.setCaptcha(phone, captchaId, code);
    }

    @Override
    public boolean hasCaptcha(@NotNull String phone, String captchaId) {
        return super.hasCaptcha(phone, captchaId);
    }

    @Override
    public long getExpireTimeout() {
        return expireTimeout;
    }

    public void setExpireTimeout(long expireTimeout) {
        this.expireTimeout = expireTimeout;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
