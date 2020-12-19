package com.alpha.classpie.controller;

import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.pojo.user.Student;
import com.alpha.classpie.pojo.user.Teacher;
import com.alpha.classpie.service.inf.CourseService;
import com.alpha.classpie.service.inf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource(name = "defaultCourseService")
    CourseService courseService;

    @Autowired
    DataWrapper dataWrapper;

    @Autowired
    UserService userService;

    @RequestMapping("/createCourse")
    @PreAuthorize("hasRole('老师')")
    public Course createCourse(@RequestBody Course course){
        int userId = UserController.getUserId();
        return dataWrapper.doCourseWrapper(courseService.createCourse(course,userId),userId);
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/enterCourse")
    public Course enterCourse(@RequestParam(name = "courseCode") String courseCode){
        int id = UserController.getUserId();
        return dataWrapper.doCourseWrapper(courseService.enterCourse(courseCode,id),id);
    }

    @PreAuthorize("hasRole('老师')")
    @RequestMapping("/archiveCourse")
    public boolean updateArchiveState(@RequestParam("courseId")int courseId,@RequestParam("archiveState") Boolean archiveState){
        return courseService.updateArchiveState(courseId,archiveState);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping("/archiveOwnCourse")
    public boolean archiveOwnCourse(@RequestParam("courseId")int courseId,@RequestParam("archiveState") Boolean archiveState){
        return courseService.updateOwnArchiveState(courseId,UserController.getUserId(),archiveState);
    }

    @PreAuthorize("hasRole('老师')")
    @RequestMapping("/editCourse")
    public Course editCourse(@RequestBody Course course){
        return dataWrapper.doCourseWrapper(courseService.editCourse(course,course.getId()),UserController.getUserId());
    }

    @PreAuthorize("hasRole('老师')")
    @RequestMapping("/flushCourseCode")
    public String flushCourseCode(@RequestParam(name = "courseId") int courseId){
        return courseService.flushCourseCode(courseId);
    }

    @PreAuthorize("hasRole('老师')")
    @RequestMapping("/getTeachCourses")
    public List<Course> getTeachCourses(){
        int id = UserController.getUserId();
        return courseService.getTeachCourses(id)
                .stream().map(course -> dataWrapper.doCourseWrapper(course,id))
                .collect(Collectors.toList());
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/getLearnCourses")
    public List<Course> getLearnCourses(){
        int id = UserController.getUserId();
        return courseService.getLearnCourses(id)
                .stream().map(course -> dataWrapper.doCourseWrapper(course,id))
                .collect(Collectors.toList());
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/exitCourse")
    public boolean exitCourse(@RequestParam(name = "courseId") int courseId,@RequestParam(name = "password")String password){
        if(!userService.checkPassword(UserController.getUserId(),password)) return false;
        return courseService.exitCourse(courseId,UserController.getUserId());
    }

    @PreAuthorize("hasRole('老师')")
    @RequestMapping("/deleteCourse")
    public boolean deleteCourse(@RequestParam(name = "courseId")int courseId,@RequestParam(name = "password")String password){
        return courseService.deleteCourse(courseId,UserController.getUserId(),password);
    }

    @RequestMapping("/getTeachers")
    public List<Teacher> getTeachers(@RequestParam(name = "courseId")int courseId){
        return courseService.getTeachers(courseId).stream().map(dataWrapper::doTeacherSafeWrap).collect(Collectors.toList());
    }

    @RequestMapping("/getStudents")
    public List<Student> getStudents(@RequestParam(name = "courseId")int courseId){
        return courseService.getStudents(courseId).stream().map(dataWrapper::doStudentSafeWrap).collect(Collectors.toList());
    }

    @RequestMapping("/stopCourseCode")
    public boolean stopCourseCode(@RequestParam("courseId")int courseId){
        return courseService.stopCourseCode(courseId);
    }

    @RequestMapping("/transfer")
    public boolean transferCourse(@RequestParam("courseId")int courseId,@RequestParam("userId")int userId,@RequestParam("password") String password){
        if(userService.checkPassword(UserController.getUserId(),password)){
            return courseService.transferCourse(UserController.getUserId(),courseId,userId);
        }
        return false;
    }

    @RequestMapping("/sort")
    public boolean sort(@RequestBody List<Integer> courseIds){
        return courseService.sort(UserController.getUserId(),courseIds);
    }

    @RequestMapping("/hasCourse")
    public boolean hasCourse(@RequestParam("courseId")int courseId){
        return courseService.hasInCourse(UserController.getUserId(),courseId);
    }

    @RequestMapping("/getCourse")
    public Course getCourse(@RequestParam("courseId")int courseId){
        if(courseService.hasInCourse(UserController.getUserId(),courseId)){
            return dataWrapper.doCourseWrapper(courseService.getCourseById(courseId),UserController.getUserId());
        }
        return null;
    }
}
