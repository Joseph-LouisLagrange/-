package com.alpha.classpie.rdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨能
 * @create 2020/11/8
 */
@Validated
@Repository("emailCaptchaDao")
public class EmailCaptchaDao extends RedisCaptchaDao<Integer> {

    @Value("${captcha.mail.expireTimeout}")
    long expireTimeout;
    @Value("${captcha.mail.timeUnit}")
    TimeUnit timeUnit;

    @Override
    public Integer getCode(@NotNull String emailAddress, String captchaId) {
        return (Integer) redisTemplate.opsForValue().get(aggregate(emailAddress, captchaId));
    }

    public void setCaptcha(@NotNull String emailAddress, String captchaId, Integer code) {
        super.setCaptcha(emailAddress, captchaId, code);
    }

    @Override
    public boolean hasCaptcha(@NotNull String emailAddress, String captchaId) {
        return super.hasCaptcha(emailAddress, captchaId);
    }

    @Override
    public long getExpireTimeout() {
        return this.expireTimeout;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return this.timeUnit;
    }
}
