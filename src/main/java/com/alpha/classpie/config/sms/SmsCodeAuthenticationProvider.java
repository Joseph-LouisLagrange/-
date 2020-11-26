package com.alpha.classpie.config.sms;

import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.inf.UserService;
import com.alpha.classpie.service.inf.captcha.RemoteCaptchaService;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author 杨能
 * @create 2020/11/7
 */
@Component("smsCodeAuthenticationProvider")
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    @Resource(name = "defaultUserDetailsService")
    UserDetailsService userDetailsService;

    @Resource(name = "smsLoginRemoteCaptchaService")
    RemoteCaptchaService loginRemoteCaptchaService;

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(SmsCodeAuthenticationToken.class, authentication,
                () -> messages.getMessage("SmsCodeAuthenticationProvider.onlySupports",
                        "Only SmsCodeAuthenticationToken is supported"));
        SmsCodeAuthenticationToken smsCodeAuthenticationToken= (SmsCodeAuthenticationToken) authentication;
        String telephone=smsCodeAuthenticationToken.getPrincipal();
        Integer captcha=smsCodeAuthenticationToken.getCredentials();
        if(!loginRemoteCaptchaService.checkCaptcha(telephone,captcha)){
            throw new InternalAuthenticationServiceException("验证码错误");
        }else {
            UserDetails userDetails = userDetailsService.loadUserByUsername(telephone);
            SmsCodeAuthenticationToken smsSuccessCodeAuthenticationToken = new SmsCodeAuthenticationToken(telephone, captcha, userDetails.getAuthorities());
            smsSuccessCodeAuthenticationToken.setDetails(userDetails);
            return smsSuccessCodeAuthenticationToken;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
