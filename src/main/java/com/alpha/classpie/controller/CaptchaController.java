package com.alpha.classpie.controller;


import com.alpha.classpie.dto.exception.ExceptionDto;

import com.alpha.classpie.service.inf.safe.UsernameSafeService;
import com.alpha.classpie.service.inf.captcha.RemoteCaptchaService;
import com.alpha.classpie.service.inf.captcha.WebRecognizedCaptchaService;
import com.alpha.classpie.type.UserNameType;
import com.alpha.classpie.util.FormatRecognitionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 杨能
 * @create 2020/11/1
 */
@RestController
public class CaptchaController {


    @Resource(name = "loginWebRecognizedCaptchaService")
    WebRecognizedCaptchaService loginWebRecognizedCaptchaService;
    @Resource(name = "registerWebRecognizedCaptchaService")
    WebRecognizedCaptchaService registerWebRecognizedCaptchaService;
    @Resource(name = "bindUsernameWebRecognizedCaptchaService")
    WebRecognizedCaptchaService bindUsernameWebRecognizedCaptchaService;
    @Resource(name ="smsLoginRemoteCaptchaService")
    RemoteCaptchaService smsLoginRemoteCaptchaService;

    @Resource(name ="smsRegisterRemoteCaptchaService")
    RemoteCaptchaService smsRegisterRemoteCaptchaService;
    @Resource(name ="smsBindRemoteCaptchaService")
    RemoteCaptchaService smsBindRemoteCaptchaService;
    @Resource(name ="emailRegisterRemoteCaptchaService")
    RemoteCaptchaService emailRegisterRemoteCaptchaService;
    @Resource(name ="emailBindRemoteCaptchaService")
    RemoteCaptchaService emailBindRemoteCaptchaService;

    @Autowired
    UsernameSafeService usernameSafeService;

    @PreAuthorize("permitAll()")
    @GetMapping("register/mathCaptcha")
    public void registerMathCaptcha(@RequestParam(name = "width",required = false ,defaultValue = "200") int width
            , @RequestParam(name = "height",required = false,defaultValue = "170") int height
            , HttpServletResponse httpServletResponse
            , @RequestParam(name = "virtualId") String virtualId) throws IOException {
            registerWebRecognizedCaptchaService.getWebMathCaptcha(width, height, virtualId, httpServletResponse);
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping("/register/checkCaptcha")
    public boolean checkRegisterCaptcha(@RequestParam(name = "registerCaptcha") int registerCaptcha,
                                        @RequestParam(name = "username")String username,
                                        @RequestParam(name = "virtualId") String virtualId){
        usernameSafeService.checkRegisterUsernameSafe(virtualId, username);
        UserNameType userNameType = FormatRecognitionUtil.identifyUserName(username);
        if(userNameType==UserNameType.EMAIL){
            return emailRegisterRemoteCaptchaService.checkCaptcha(username,registerCaptcha);
        }else if(userNameType==UserNameType.PHONE){
            return smsRegisterRemoteCaptchaService.checkCaptcha(username,registerCaptcha);
        }else {
          return false;
        }
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping("/register/sendCaptcha")
    public long sendRegisterCaptcha(@RequestParam(name = "username") String username,
                                    @RequestParam(name = "webCaptchaResult") int webCaptchaResult,
                                    @RequestParam(name = "virtualId") String virtualId) throws Exception {
        //判定图形验证码的数值
        boolean result = registerWebRecognizedCaptchaService.checkCaptcha(virtualId,webCaptchaResult);
        long captchaExpire=-1;
        if(!result){
            return captchaExpire;
        }else {
            //删除图形码
            registerWebRecognizedCaptchaService.deleteCaptcha(virtualId);
        }
        UserNameType userNameType = FormatRecognitionUtil.identifyUserName(username);
        if(UserNameType.EMAIL==userNameType){
            //邮箱类型
            if(!emailRegisterRemoteCaptchaService.hasCaptchaKey(username)){
                emailRegisterRemoteCaptchaService.sendCaptcha(username);
            }
            captchaExpire=emailRegisterRemoteCaptchaService.getCaptchaExpireSeconds(username);
        }else if(UserNameType.PHONE==userNameType){
            //电话类型
            if(!smsRegisterRemoteCaptchaService.hasCaptchaKey(username)){
                smsRegisterRemoteCaptchaService.sendCaptcha(username);
            }
            captchaExpire =smsRegisterRemoteCaptchaService.getCaptchaExpireSeconds(username);
        }else {
            throw new ExceptionDto("验证码发送失败","格式无法识别","检查目标[ "+username+" ]格式或者重新输入");
        }
        //记录当前用户真在使用的注册方式，以及注册账号
        usernameSafeService.doRegisterUsernameSafe(virtualId, username);
        return captchaExpire;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("login/mathCaptcha")
    public void loginMathCaptcha(
            @RequestParam(name = "width",required = false ,defaultValue = "200") int width
            , @RequestParam(name = "height",required = false,defaultValue = "170") int height
            , HttpServletResponse httpServletResponse
            , @RequestParam(name = "virtualId") String virtualId) throws IOException {
        loginWebRecognizedCaptchaService.getWebMathCaptcha(width, height, virtualId, httpServletResponse);
    }


    @PreAuthorize("isAnonymous()")
    @RequestMapping("/login/sendCaptcha")
    public long sendLoginCaptcha(@RequestParam(name = "telephone") String telephone
                                ,@RequestParam(name = "webCaptchaResult") int webCaptchaResult
                                ,@RequestParam(name = "virtualId")String virtualId) throws Exception {
        if(FormatRecognitionUtil.identifyUserName(telephone)!=UserNameType.PHONE){
            throw new ExceptionDto("验证码发送失败","电话格式无法识别","检查目标[ "+telephone+" ]格式或者重新输入");
        }
        boolean result = loginWebRecognizedCaptchaService.checkCaptcha(virtualId,webCaptchaResult);
        if(!result){
            return -1;
        }else {
            //删除图形码
            loginWebRecognizedCaptchaService.deleteCaptcha(virtualId);
        }
        if(!smsLoginRemoteCaptchaService.hasCaptchaKey(telephone)){
            usernameSafeService.doLoginUsernameSafe(virtualId,telephone);
            smsLoginRemoteCaptchaService.sendCaptcha(telephone);
        }
        return smsLoginRemoteCaptchaService.getCaptchaExpireSeconds(telephone);
    }

    @RequestMapping("bindUsername/mathCaptcha")
    public void bindTelephoneMathCaptcha(@RequestParam(name = "width",required = false ,defaultValue = "200") int width
            , @RequestParam(name = "height",required = false,defaultValue = "170") int height
            , HttpServletResponse httpServletResponse) throws IOException {
        bindUsernameWebRecognizedCaptchaService.getWebMathCaptcha(width,height,String.valueOf(UserController.getUserId()),httpServletResponse);
    }


    @RequestMapping("bindTelephone/sendCaptcha")
    public long sendBindTelephoneCaptcha(
            @RequestParam(name = "telephone") String telephone
            ,@RequestParam(name = "webCaptchaResult",required = false) Integer webCaptchaResult) throws Exception {
//            if(!bindUsernameWebRecognizedCaptchaService.checkCaptcha(String.valueOf(UserController.getUserId()),webCaptchaResult)){
//                return -1;
//            }else {
//                //删除图形码缓存
//                bindUsernameWebRecognizedCaptchaService.deleteCaptcha(telephone);
//            }
            if(!smsBindRemoteCaptchaService.hasCaptchaKey(telephone)){
                //发送验证码
                smsBindRemoteCaptchaService.sendCaptcha(telephone);
            }
            return smsBindRemoteCaptchaService.getCaptchaExpireSeconds(telephone);
    }

    @RequestMapping("bindEmail/sendCaptcha")
    public long bindEmailMathCaptcha(
            @RequestParam(name = "webCaptchaResult",required = false) Integer webCaptchaResult
            , @RequestParam(name = "emailAddress") String emailAddress) throws Exception {
//        if(!bindUsernameWebRecognizedCaptchaService.checkCaptcha(String.valueOf(UserController.getUserId()),webCaptchaResult)){
//            return -1;
//        }else {
//            //删除图形码缓存
//            bindUsernameWebRecognizedCaptchaService.deleteCaptcha(emailAddress);
//        }
        if(!emailBindRemoteCaptchaService.hasCaptchaKey(emailAddress)){
            //发送验证码
            emailBindRemoteCaptchaService.sendCaptcha(emailAddress);
        }
        return emailBindRemoteCaptchaService.getCaptchaExpireSeconds(emailAddress);
    }
}
