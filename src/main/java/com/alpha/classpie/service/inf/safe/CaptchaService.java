package com.alpha.classpie.service.inf.safe;


import com.aliyuncs.exceptions.ClientException;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.pojo.other.SMSResponse;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.io.IOException;


@Validated
@Service
public interface CaptchaService {
    public long getLoginSMSCaptchaExpireSeconds(String phone);

    public long getRegisterSMSCaptchaExpireSeconds(String phone);

    public long getRegisterEmailCaptchaExpireSeconds(String emailAddress);

    public long getPhoneBindSMSCaptchaExpireSeconds(String phone);

    public long getEmailBindCaptchaExpireSeconds(String emailAddress);;

    public SMSResponse sendRegisterSMSCaptcha(String phone) throws ClientException;

    public boolean sendRegisterEmailCaptcha(@Email String emailAddress);

    public SMSResponse sendLoginSMSCaptcha(String phone) throws ClientException;

    public SMSResponse sendPhoneBindSMSCaptcha(String phone) throws ClientException;

    public boolean sendEmailBindCaptcha(String email);

    public void getRegisterChineseWebMathCaptcha(@Min(1) int width, @Min(1) int height, String virtualId, HttpServletResponse httpServletResponse) throws IOException;

    public void getBindChineseWebMathCaptcha(@Min(1) int width, @Min(1) int height, int userId, HttpServletResponse httpServletResponse) throws IOException;

    public void getLoginChineseWebMathCaptcha(@Min(1) int width, @Min(1) int height, String virtualId, HttpServletResponse httpServletResponse) throws IOException;

    public boolean checkRegisterSMSCaptcha(String phone, int code) throws ExceptionDto;

    public boolean checkRegisterEmailCaptcha(@Email String emailAddress,int code);

    public boolean checkRegisterChineseWebMathCaptcha(String virtualId, int number) throws ExceptionDto;

    public boolean checkBindChineseWebMathCaptcha(int userId, int number);

    public boolean checkPhoneBindSMSCaptcha(String phone,int code);

    public boolean checkEmailBindCaptcha(String emailAddress,int code);

    public boolean checkLoginChineseWebMathCaptcha(String virtualId, int number) throws ExceptionDto;

    public boolean checkLoginSMSCaptcha(String phone,int code) throws ExceptionDto;

    public void deleteRegisterSMSCaptcha(String phone);

    public void deleteRegisterEmailCaptcha(@Email String email);

    public void deleteRegisterChineseWebMathCaptcha(HttpSession session);

    public void deleteBindChineseWebMathCaptcha(int userId);

    public void deleteLoginSMSCaptcha(String phone);

    public void deletePhoneBindSMSCaptcha(String phone);

    public void deleteEmailBindCaptcha(String address);
}
