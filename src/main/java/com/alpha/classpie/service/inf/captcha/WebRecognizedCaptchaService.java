package com.alpha.classpie.service.inf.captcha;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import java.io.IOException;


public interface WebRecognizedCaptchaService extends CaptchaService<Integer> {
    public void getWebMathCaptcha(@Min(1) int width, @Min(1) int height, String virtualId, HttpServletResponse httpServletResponse) throws IOException ;
}
