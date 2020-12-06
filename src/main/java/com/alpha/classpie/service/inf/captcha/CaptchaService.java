package com.alpha.classpie.service.inf.captcha;

import javax.validation.constraints.Email;

/**
 * @author 杨能
 * @create 2020/11/18
 */
public interface CaptchaService<T> {
    public long getCaptchaExpireSeconds(String key);
    public boolean checkCaptcha(String key,T code);
    public void deleteCaptcha(String key);
    public boolean hasCaptchaKey(String key);
}
