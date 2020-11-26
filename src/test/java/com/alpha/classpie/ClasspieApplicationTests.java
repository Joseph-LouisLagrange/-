package com.alpha.classpie;


import com.alpha.classpie.dao.*;
import com.alpha.classpie.example.TaskNoticeExample;
import com.alpha.classpie.service.inf.safe.CaptchaService;
import com.alpha.classpie.service.inf.notice.MailService;


import com.alpha.classpie.util.RedisDaoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ClasspieApplicationTests {

    @Resource(name = "defaultPasswordEncoder")
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;


    @Autowired
    CaptchaService captchaService;

    @Autowired
    MailService mailService;

    @Resource(name = "defaultUserDetailsService")
    UserDetailsService userDetailsService;


    @Autowired
    RedisDaoUtil redisDaoUtil;

    @Autowired
    TaskNoticeMapper taskNoticeMapper;

    @Test
    public void test() throws Exception {
        taskNoticeMapper.selectByExample(new TaskNoticeExample()).forEach(System.out::println);
    }

}
