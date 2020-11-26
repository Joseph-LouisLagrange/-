package com.alpha.classpie.controller;

import com.alpha.classpie.dto.TeacherRegisterDto;
import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.user.Student;
import com.alpha.classpie.pojo.user.Teacher;

import com.alpha.classpie.pojo.user.TeacherCustomMajor;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.inf.UserService;
import com.alpha.classpie.service.inf.safe.UsernameSafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author 杨能
 * @create 2020/11/8
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource(name = "defaultUserService")
    UserService userService;

    @Autowired
    DataWrapper dataWrapper;

    @Autowired
    UsernameSafeService usernameSafeService;
    @RequestMapping("/register")
    public boolean register(@Valid @RequestBody TeacherRegisterDto registerDto){
        String registerUsername = registerDto.getRegisterUsername();
        usernameSafeService.checkRegisterUsernameSafe(registerDto.getVirtualId(),registerUsername);
        Teacher teacher = registerDto.getTeacher();
        return userService.teacherRegister(teacher,registerUsername,registerDto.getCaptcha())!=null;
    }

    @PreAuthorize("hasRole('老师')")
    @RequestMapping("/getMe")
    public Teacher getMe(){
        User user = UserController.getUser();
        return dataWrapper.doTeacherSafeWrap(user);
    }

    @PreAuthorize("hasRole('老师')")
    @RequestMapping("/roleToStudent")
    public Student roleToStudent(HttpServletResponse response){
        User user = UserController.getUser();
        return dataWrapper.doStudentSafeWrap(userService.roleToStudent(user));
    }

    @RequestMapping("/deleteTeacherCustomMajor")
    public boolean deleteTeacherCustomMajor(@RequestParam(name = "customMajor") String customMajor){
        return userService.deleteTeacherCustomMajor(new TeacherCustomMajor(UserController.getUserId(),customMajor));
    }

    @RequestMapping("/deleteDepartment")
    public boolean deleteDepartment(@RequestParam(name = "departmentId")int departmentId){
        return userService.deleteDepartment(departmentId,UserController.getUserId());
    }

    @RequestMapping("/deleteMajor")
    public boolean deleteMajor(@RequestParam(name = "majorId")int majorId){
        return userService.deleteMajor(majorId,UserController.getUserId());
    }


    @PreAuthorize("hasRole('老师')")
    @RequestMapping("/addTeacherCustomMajor")
    public boolean addTeacherCustomMajor(@RequestParam(name = "customMajor") String customMajor){
        return userService.addTeacherCustomMajor(new TeacherCustomMajor(UserController.getUserId(),customMajor));
    }

    @PreAuthorize("hasRole('老师')")
    @RequestMapping("/addDepartment")
    public boolean addDepartment(@RequestParam(name = "departmentId")int departmentId){
        return userService.addDepartment(departmentId,UserController.getUserId());
    }

    @RequestMapping("/addMajor")
    public boolean addMajor(@RequestParam(name = "majorId")int majorId){
        return userService.addMajor(majorId,UserController.getUserId());
    }

    @RequestMapping("/addTeachCourse")
    public boolean addTeachCourse(@RequestParam(name = "teachCourse")String teachCourse){
       return userService.addTeachCourse(teachCourse,UserController.getUserId());
    }

    @RequestMapping("/deleteTeachCourse")
    public boolean deleteTeachCourse(@RequestParam(name = "teachCourse")String teachCourse){
        return userService.deleteTeachCourse(teachCourse,UserController.getUserId());
    }
}
