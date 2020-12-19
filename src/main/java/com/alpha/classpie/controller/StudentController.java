package com.alpha.classpie.controller;

import com.alpha.classpie.dto.StudentRegisterDto;
import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.user.Student;

import com.alpha.classpie.pojo.user.Teacher;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.inf.UserService;
import com.alpha.classpie.service.inf.safe.UsernameSafeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource(name = "defaultUserService")
    UserService userService;

    @Autowired
    UsernameSafeService usernameSafeService;

    @Autowired
    DataWrapper dataWrapper;

    @PreAuthorize("permitAll()")
    @RequestMapping("/register")
    public boolean register(@RequestBody StudentRegisterDto registerDto){
        String registerUsername = registerDto.getRegisterUsername();
        usernameSafeService.checkRegisterUsernameSafe(registerDto.getVirtualId(),registerUsername);
        Student student = registerDto.getStudent();
        return userService.studentRegister(student,registerUsername,registerDto.getCaptcha())!=null;
    }

    @PreAuthorize("hasRole('学生')")
    @RequestMapping("/getMe")
    public Student getMe(){
        User user = UserController.getUser();
        return  dataWrapper.doStudentSafeWrap(user);
    }

    @PreAuthorize("hasRole('学生')")
    @RequestMapping("/roleToTeacher")
    public Teacher roleToTeacher(){
        User user = UserController.getUser();
        return dataWrapper.doTeacherSafeWrap(userService.roleToTeacher(user));
    }
}
