package com.alpha.classpie.controller;

import com.alpha.classpie.dto.StudentRegisterDto;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.pojo.user.Student;
import com.alpha.classpie.service.impl.StudentServiceImpl;
import com.alpha.classpie.type.UserNameType;
import com.alpha.classpie.util.FormatRecognitionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author 杨能
 * @create 2020/11/4
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @PreAuthorize("permitAll()")
    @RequestMapping("/register")
    public boolean register(@RequestBody StudentRegisterDto registerDto){
        String registerUsername = registerDto.getRegisterUsername();
        System.out.println(registerUsername);
//
//        if(!username.equals(registerUsername)){
//            throw new ExceptionDto("注册失败","当前的注册账号与被发送验证码的账号不符","重新进行注册");
//        }
        Student student = registerDto.getStudent();
        UserNameType userNameType = FormatRecognitionUtil.identifyUserName(registerUsername);
        System.out.println(userNameType);
        if(userNameType==UserNameType.EMAIL){
            return studentService.emailRegister(student, registerDto.getCaptcha())!=null;
        }
        if(userNameType==UserNameType.PHONE){
            return studentService.smsRegister(student,registerDto.getCaptcha())!=null;
        }
        return false;
    }
}
