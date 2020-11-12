package com.alpha.classpie.rdao;

import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨能
 * @create 2020/11/2
 */
@Repository
public interface CaptchaDao<T> {
    public long getExpireSeconds(@NotNull String sessionId,String captchaId);
    public void setCaptcha(@NotNull String sessionId, String captchaId, T code);
    public T getCode(@NotNull String sessionId, String captchaId);
    public boolean hasCaptcha(@NotNull String sessionId,String captchaId);
    public void removeCaptcha(@NotNull String sessionId,String captchaId);
    public long getExpireTimeout();
    public TimeUnit getTimeUnit();
}
