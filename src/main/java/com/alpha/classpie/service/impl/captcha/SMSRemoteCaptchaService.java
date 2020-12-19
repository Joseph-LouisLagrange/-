package com.alpha.classpie.service.impl.captcha;

import cn.hutool.core.util.RandomUtil;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.pojo.other.SMSResponse;
import com.alpha.classpie.service.inf.captcha.AbstractCaptchaService;
import com.alpha.classpie.service.inf.captcha.RemoteCaptchaService;
import com.alpha.classpie.util.AlibabaSMSUtil;
import com.alpha.classpie.util.RedisUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;


@EqualsAndHashCode(callSuper = true)
@Data
public class SMSRemoteCaptchaService extends AbstractCaptchaService<Integer,Integer> implements RemoteCaptchaService {

    AlibabaSMSUtil alibabaSMSUtil;


    public SMSRemoteCaptchaService(@NotNull String prefix, RedisUtil redisUtil, long expireTimeout, TimeUnit timeUnit, AlibabaSMSUtil alibabaSMSUtil) {
        super(prefix, redisUtil, expireTimeout, timeUnit);
        this.alibabaSMSUtil = alibabaSMSUtil;
    }

    @Override
    public boolean sendCaptcha(String target) throws Exception {
        int code = RandomUtil.randomInt(1000, 9999);
        alibabaSMSUtil.sendCaptcha(code, target);
        //保存下来
        saveCaptcha(target,code);
        return true;
    }

    @Override
    public boolean checkCaptcha(String key, Integer code) {
        Integer captcha = super.getCaptcha(key, Integer.class);
        if(captcha==null){
            throw new ExceptionDto("验证码过期","手机发送的验证码失去使用效用","重新发送新的手机验证码");
        }
        return captcha.equals(code);
    }
}
