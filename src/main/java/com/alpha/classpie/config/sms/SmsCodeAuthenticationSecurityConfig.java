package com.alpha.classpie.config.sms;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;



@Component("smsCodeAuthenticationSecurityConfig")
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Resource(name = "smsCodeAuthenticationProvider")
    AuthenticationProvider smsCodeAuthenticationProvider;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setPostOnly(false);
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(new ForwardAuthenticationSuccessHandler("/user/loginSuccess"));
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(new ForwardAuthenticationFailureHandler("/user/loginFail"));
        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

