package com.alpha.classpie.service.impl.captcha;

import cn.hutool.core.util.RandomUtil;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.service.inf.notice.MailService;
import com.alpha.classpie.service.inf.captcha.AbstractCaptchaService;
import com.alpha.classpie.service.inf.captcha.RemoteCaptchaService;
import com.alpha.classpie.util.RedisUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;


@EqualsAndHashCode(callSuper = true)
@Data
public class EmailRemoteCaptchaService extends AbstractCaptchaService<Integer,Integer> implements RemoteCaptchaService {

    MailService mailService=null;



    public EmailRemoteCaptchaService(@NotNull String prefix, RedisUtil redisUtil, long expireTimeout, TimeUnit timeUnit, MailService mailService) {
        super(prefix, redisUtil, expireTimeout, timeUnit);
        this.mailService = mailService;
    }

    @Override
    public boolean sendCaptcha(String target) throws Exception {
        int code = RandomUtil.randomInt(1000, 9999);

        this.mailService.sendCaptcha(target,code);
        //存入缓存
        super.saveCaptcha(target,code);
        return true;
    }

    @Override
    public boolean checkCaptcha(String key, Integer code) {
        Integer captcha = super.getCaptcha(key, Integer.class);
        if(captcha==null){
            throw new ExceptionDto("验证码过期","email发送的验证码失去使用效用","重新发送新的email验证码");
        }
        return captcha.equals(code);
    }
}
