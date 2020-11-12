package com.alpha.classpie.controller;


import com.aliyuncs.exceptions.ClientException;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.pojo.other.SMSResponse;
import com.alpha.classpie.rdao.VirtualSession;
import com.alpha.classpie.service.inf.CaptchaService;

import com.alpha.classpie.type.UserNameType;
import com.alpha.classpie.util.FormatRecognitionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import java.io.IOException;

/**
 * @author 杨能
 * @create 2020/11/1
 */
@RestController
public class CaptchaController {

    @Resource(name = "defaultCaptchaService")
    CaptchaService captchaService;

    @Autowired
    VirtualSession virtualSession;

    static final String registerUsername="registerUsername";

    static final String loginUsername="loginUsername";

    @PreAuthorize("permitAll()")
    @GetMapping("register/mathCaptcha")
    public void registerMathCaptcha(@RequestParam(name = "width",required = false ,defaultValue = "200") int width
            , @RequestParam(name = "height",required = false,defaultValue = "170") int height
            , HttpServletResponse httpServletResponse
            , @RequestParam(name = "virtualId") String virtualId) throws IOException {
            captchaService.getRegisterChineseWebMathCaptcha(width,height,virtualId,httpServletResponse);
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping("/register/checkCaptcha")
    public boolean checkRegisterCaptcha(@RequestParam(name = "registerCaptcha") int registerCaptcha,
                                        @RequestParam(name = "username")String username,
                                        @RequestParam(name = "virtualId") String virtualId){
        Object attribute = virtualSession.getAttribute(virtualId,registerUsername,String.class);
        if(attribute==null){
            throw new ExceptionDto("非法验证","当前的账号并未被发送验证码","重新进行注册");
        }
        if(!registerUsername.equals(username)){
            throw new ExceptionDto("验证失败","当前的账号与被发送验证码的账号不符","重新进行注册");
        }
        UserNameType userNameType = FormatRecognitionUtil.identifyUserName(username);
        if(UserNameType.EMAIL==userNameType){
            return captchaService.checkRegisterEmailCaptcha(username, registerCaptcha);
        }else if(UserNameType.PHONE==userNameType){
           return captchaService.checkLoginSMSCaptcha(username,registerCaptcha);
        }else {
            throw new ExceptionDto("验证失败","账号格式无法识别","检查目标[ "+username+" ]格式或者重新输入");
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("login/mathCaptcha")
    public void loginMathCaptcha(
            @RequestParam(name = "width",required = false ,defaultValue = "200") int width
            , @RequestParam(name = "height",required = false,defaultValue = "170") int height
            , HttpServletResponse httpServletResponse
            , @RequestParam(name = "virtualId") String virtualId) throws IOException {
        captchaService.getLoginChineseWebMathCaptcha(width,height,virtualId,httpServletResponse);
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping("/register/sendCaptcha")
    public long sendRegisterCaptcha(@RequestParam(name = "username") String username,
                                    @RequestParam(name = "webCaptchaResult") int webCaptchaResult,
                                    @RequestParam(name = "virtualId") String virtualId) throws ClientException {
        //判定图形验证码的数值
        boolean result = captchaService.checkRegisterChineseWebMathCaptcha(virtualId, webCaptchaResult);
        if(!result){
            throw new ExceptionDto(1,"验证失败","图形验证码错误","重新填写验证码");
        }
        long captchaExpire=-1;
        UserNameType userNameType = FormatRecognitionUtil.identifyUserName(username);
        if(UserNameType.EMAIL==userNameType){
            //邮箱类型
            boolean b = captchaService.sendRegisterEmailCaptcha(username);
            if(b){
                captchaExpire=captchaService.getRegisterEmailCaptchaExpireSeconds(username);
            }
        }else if(UserNameType.PHONE==userNameType){
            //电话类型
            SMSResponse smsResponse = captchaService.sendRegisterSMSCaptcha(username);
            if(smsResponse!=SMSResponse.REFUSE) {
                captchaExpire = captchaService.getRegisterSMSCaptchaExpireSeconds(username);
            }
        }else {
            throw new ExceptionDto("验证码发送失败","格式无法识别","检查目标[ "+username+" ]格式或者重新输入");
        }
        if(captchaExpire!=-1){
            virtualSession.setAttributeForever(virtualId,registerUsername,username);
        }
        //记录当前用户真在使用的注册方式，以及注册账号
        return captchaExpire;
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping("/login/sendCaptcha")
    public long sendLoginCaptcha(@RequestParam(name = "telephone") String telephone
                                ,@RequestParam(name = "webCaptchaResult") int webCaptchaResult
                                ,@RequestParam(name = "virtualId")String virtualId) throws ClientException {
        if(FormatRecognitionUtil.identifyUserName(telephone)!=UserNameType.PHONE){
            throw new ExceptionDto("验证码发送失败","电话格式无法识别","检查目标[ "+telephone+" ]格式或者重新输入");
        }
        boolean result = captchaService.checkLoginChineseWebMathCaptcha(virtualId, webCaptchaResult);
        if(!result){
            throw new ExceptionDto(1,"验证失败","图形验证码错误","重新填写验证码");
        }
        SMSResponse smsResponse = captchaService.sendLoginSMSCaptcha(telephone);
        if(smsResponse==SMSResponse.SUCCESS){
            virtualSession.setAttributeForever(virtualId,loginUsername,telephone);
            return captchaService.getLoginSMSCaptchaExpireSeconds(telephone);
        }
        return 0;
    }
}
