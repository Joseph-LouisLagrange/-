package com.alpha.classpie.controller;

import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.service.inf.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/11
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource(name = "defaultCourseService")
    CourseService courseService;

    @RequestMapping("/createCourse")
    @PreAuthorize("hasRole('老师')")
    public Course createCourse(Course course){
       return courseService.createCourse(course,UserController.getUser().getId());
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/enterCourse")
    public Course enterCourse(String courseCode){
        return courseService.enterCourse(courseCode,UserController.getUser().getId());
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/getTeachCourses")
    public List<Course> getTeachCourses(){
        return courseService.getTeachCourses(UserController.getUser().getId());
    }

    @PreAuthorize("hasRole('老师')")
    @RequestMapping("/getLearnCourses")
    public List<Course> getLearnCourses(){
        return courseService.getLearnCourses(UserController.getUser().getId());
    }



}
