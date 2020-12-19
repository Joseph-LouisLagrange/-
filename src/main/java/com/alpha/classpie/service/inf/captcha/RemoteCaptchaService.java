package com.alpha.classpie.service.inf.captcha;


public interface RemoteCaptchaService extends CaptchaService<Integer>{
    public boolean sendCaptcha(String target) throws Exception;
}
