package com.alpha.classpie.controller;

import com.alpha.classpie.dto.TeacherRegisterDto;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.pojo.user.Student;
import com.alpha.classpie.pojo.user.Teacher;
import com.alpha.classpie.service.impl.TeacherServiceImpl;
import com.alpha.classpie.type.UserNameType;
import com.alpha.classpie.util.FormatRecognitionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author 杨能
 * @create 2020/11/8
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherServiceImpl teacherService;
    @RequestMapping("/register")
    public boolean register(@RequestBody TeacherRegisterDto registerDto){
        String registerUsername = registerDto.getRegisterUsername();
        HttpSession session = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
        String username= (String) session.getAttribute(CaptchaController.registerUsername);
        if(!username.equals(registerUsername)){
            throw new ExceptionDto("注册失败","当前的注册账号与被发送验证码的账号不符","重新进行注册");
        }
        Teacher teacher = registerDto.getTeacher();
        UserNameType userNameType = FormatRecognitionUtil.identifyUserName(registerUsername);
        if(userNameType==UserNameType.EMAIL){
            return teacherService.emailRegister(teacher, registerDto.getCaptcha())!=null;
        }
        if(userNameType==UserNameType.PHONE){
            return teacherService.smsRegister(teacher,registerDto.getCaptcha())!=null;
        }
        return false;
    }



}
