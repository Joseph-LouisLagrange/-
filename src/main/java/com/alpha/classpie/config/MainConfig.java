package com.alpha.classpie.config;

import com.alpha.classpie.service.impl.captcha.SMSRemoteCaptchaService;
import com.alpha.classpie.service.inf.captcha.RemoteCaptchaService;
import com.alpha.classpie.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.util.concurrent.TimeUnit;


@Configuration
public class MainConfig {

    @Bean("defaultPasswordEncoder")
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean("webLogger")
    public Logger webLogger(){
        return LoggerFactory.getLogger("Web访问");
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
