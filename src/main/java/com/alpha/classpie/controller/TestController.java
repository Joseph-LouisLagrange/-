package com.alpha.classpie.controller;

import com.alpha.classpie.annotation.NoDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/test")
public class TestController {
    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/testAuthenticated")
    public String testAuthenticated(){
        return "完全认证的";
    }

    @NoDto
    @RequestMapping("/testAnonymous")
    @PreAuthorize("isAnonymous()")
    public String testAnonymous(){
        return "是匿名用户";
    }

    @PreAuthorize("hasRole('老师')")
    @RequestMapping("/testTeacherRole")
    public String testTeacherRole(){
        return "我是老师";
    }

    @PreAuthorize("hasRole('学生')")
    @RequestMapping("/testStudentRole")
    public String testStudentRole(){
        return "我是学生";
    }
}
