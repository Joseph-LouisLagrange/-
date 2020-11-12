package com.alpha.classpie.serviceTest;

import com.alpha.classpie.service.inf.CaptchaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;

import javax.annotation.Resource;

/**
 * @author 杨能
 * @create 2020/11/5
 */
@SpringBootTest
public class CaptchaServiceTest {

    @Resource(name = "defaultCaptchaService")
    CaptchaService captchaService;

    @Test
    public void sendCaptchaServiceTest(){
        Assertions.assertDoesNotThrow(()->{captchaService.sendRegisterEmailCaptcha("258001289@qq.com");});
    }
}
