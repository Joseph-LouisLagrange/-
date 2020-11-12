package com.alpha.classpie.rdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨能
 * @create 2020/11/3
 */
@Repository("webCaptchaDao")
public class WebCaptchaDao extends RedisCaptchaDao<String> {
    @Value("${captcha.web.expireTimeout}")
    long expireTimeout;
    @Value("${captcha.web.timeUnit}")
    TimeUnit timeUnit;
    @Override
    public String getCode(@NotNull String sessionId, String captchaId) {
        return (String) redisTemplate.opsForValue().get(aggregate(sessionId, captchaId));
    }

    public void setWebCaptcha(@NotNull String sessionId, String captchaId, String code) {
        super.setCaptcha(sessionId, captchaId, code);
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
