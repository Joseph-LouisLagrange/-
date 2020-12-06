package com.alpha.classpie.config;

import com.alpha.classpie.service.impl.captcha.EmailRemoteCaptchaService;
import com.alpha.classpie.service.impl.captcha.SMSRemoteCaptchaService;
import com.alpha.classpie.service.impl.captcha.WebCaptchaServiceImpl;
import com.alpha.classpie.service.inf.notice.MailService;
import com.alpha.classpie.service.inf.captcha.RemoteCaptchaService;
import com.alpha.classpie.service.inf.captcha.WebRecognizedCaptchaService;
import com.alpha.classpie.util.AlibabaSMSUtil;
import com.alpha.classpie.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨能
 * @create 2020/11/18
 */
@Configuration

public class CaptchaConfig {
    @Value("${captcha.SMS.expireTimeout}")
    long smsExpireTimeout;
    @Value("${captcha.SMS.timeUnit}")
    TimeUnit smsTimeUnit;

    @Value("${captcha.web.expireTimeout}")
    long webExpireTimeout;
    @Value("${captcha.web.timeUnit}")
    TimeUnit webTimeUnit;

    @Value("${captcha.mail.expireTimeout}")
    long mailExpireTimeout;
    @Value("${captcha.mail.timeUnit}")
    TimeUnit mailTimeUnit;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AlibabaSMSUtil alibabaSMSUtil;

    @Resource(name = "defaultMailService")
    MailService mailService;

    @Bean("smsLoginRemoteCaptchaService")
    public RemoteCaptchaService smsLoginRemoteCaptchaService(){
        return new SMSRemoteCaptchaService("短信登陆",redisUtil,smsExpireTimeout,smsTimeUnit,alibabaSMSUtil);
    }

    @Bean("smsRegisterRemoteCaptchaService")
    public RemoteCaptchaService smsRegisterRemoteCaptchaService(){
        return new SMSRemoteCaptchaService("短信注册",redisUtil,smsExpireTimeout,smsTimeUnit,alibabaSMSUtil);
    }
    @Bean("smsBindRemoteCaptchaService")
    public RemoteCaptchaService smsBindRemoteCaptchaService(){
        return new SMSRemoteCaptchaService("短信绑定",redisUtil,smsExpireTimeout,smsTimeUnit,alibabaSMSUtil);
    }
    @Bean("emailRegisterRemoteCaptchaService")
    public RemoteCaptchaService emailRegisterRemoteCaptchaService(){
        return new EmailRemoteCaptchaService("邮箱注册",redisUtil,mailExpireTimeout,mailTimeUnit,mailService);
    }
    @Bean("emailBindRemoteCaptchaService")
    public RemoteCaptchaService emailBindRemoteCaptchaService(){
        return new EmailRemoteCaptchaService("邮箱绑定",redisUtil,mailExpireTimeout,mailTimeUnit,mailService);
    }
    @Bean("loginWebRecognizedCaptchaService")
    public WebRecognizedCaptchaService loginWebRecognizedCaptchaService(){
        return new WebCaptchaServiceImpl("登陆图形码",redisUtil,webExpireTimeout,webTimeUnit);
    }
    @Bean("registerWebRecognizedCaptchaService")
    public WebRecognizedCaptchaService registerWebRecognizedCaptchaService(){
        return new WebCaptchaServiceImpl("注册图形码",redisUtil,webExpireTimeout,webTimeUnit);
    }
    @Bean("bindUsernameWebRecognizedCaptchaService")
    public WebRecognizedCaptchaService bindUsernameWebRecognizedCaptchaService(){
        return new WebCaptchaServiceImpl("绑定账号图形码",redisUtil,webExpireTimeout,webTimeUnit);
    }
}
