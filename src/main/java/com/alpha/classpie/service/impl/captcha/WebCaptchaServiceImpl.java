package com.alpha.classpie.service.impl.captcha;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;

import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.service.inf.captcha.AbstractCaptchaService;
import com.alpha.classpie.service.inf.captcha.WebRecognizedCaptchaService;
import com.alpha.classpie.util.MathGenerator;

import com.alpha.classpie.util.RedisUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨能
 * @create 2020/11/18
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class WebCaptchaServiceImpl extends AbstractCaptchaService<String,Integer> implements WebRecognizedCaptchaService {
    MathGenerator mathGenerator=new MathGenerator();//数字生成器

    public WebCaptchaServiceImpl() {
    }

    public WebCaptchaServiceImpl(@NotNull String prefix, RedisUtil redisUtil, long expireTimeout, TimeUnit timeUnit) {
        super(prefix, redisUtil, expireTimeout, timeUnit);
    }

    @Override
    public void getWebMathCaptcha(@Min(1) int width, @Min(1) int height, String virtualId, HttpServletResponse httpServletResponse) throws IOException {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(width, height, 4, 4);
        circleCaptcha.setBackground(Color.WHITE);
        circleCaptcha.setFont(new Font(null,Font.PLAIN,width/6));
        circleCaptcha.setGenerator(mathGenerator);
        circleCaptcha.createCode();
        circleCaptcha.write(httpServletResponse.getOutputStream());
        String code = circleCaptcha.getCode();
        httpServletResponse.flushBuffer();
        //存入redis
        super.saveCaptcha(virtualId,code);
    }

    @Override
    public boolean checkCaptcha(String key, Integer number) {
        String captcha = super.getCaptcha(key, String.class);
        System.out.print(captcha);
        if(captcha==null){
            throw new ExceptionDto("图形码过期","图形码失去使用效用","重新刷新页面获取图形码");
        }
        return mathGenerator.verify(captcha, String.valueOf(number));
    }
}
