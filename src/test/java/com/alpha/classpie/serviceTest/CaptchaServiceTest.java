package com.alpha.classpie.serviceTest;

import com.alpha.classpie.service.inf.safe.CaptchaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
public class CaptchaServiceTest {

    @Resource(name = "defaultCaptchaService")
    CaptchaService captchaService;

    @Test
    public void sendCaptchaServiceTest(){
        Assertions.assertDoesNotThrow(()->{captchaService.sendRegisterEmailCaptcha("258001289@qq.com");});
    }
}
