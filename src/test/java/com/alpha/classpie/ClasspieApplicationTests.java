package com.alpha.classpie;


import cn.hutool.core.util.RandomUtil;
import com.alpha.classpie.dao.*;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.example.*;
import com.alpha.classpie.pojo.user.TeacherCustomMajor;
import com.alpha.classpie.rdao.EmailCaptchaDao;
import com.alpha.classpie.service.impl.UserDetailsImpl;
import com.alpha.classpie.service.inf.CaptchaService;
import com.alpha.classpie.service.inf.MailService;
import com.alpha.classpie.util.AlibabaSMSUtil;


import com.google.gson.Gson;
import org.apache.catalina.connector.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.File;

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

    @Resource(name = "defaultUserDetails")
    UserDetailsService userDetailsService;

    @Test
    public void test(){
        userDetailsService.loadUserByUsername("158gyxdf");
    }

}
