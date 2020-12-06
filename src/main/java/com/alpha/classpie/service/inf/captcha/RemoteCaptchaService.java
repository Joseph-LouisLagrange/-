package com.alpha.classpie.service.inf.captcha;

/**
 * @author 杨能
 * @create 2020/11/18
 */
public interface RemoteCaptchaService extends CaptchaService<Integer>{
    public boolean sendCaptcha(String target) throws Exception;
}
