package com.alpha.classpie.dto.safeimpl;


import com.alpha.classpie.dao.TaskMapper;

import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.pojo.user.*;
import com.alpha.classpie.service.inf.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/9
 */
@Component
public class DataWrapper {

    @Resource(name = "defaultCourseService")
    CourseService courseService;

    @Autowired
    TaskMapper taskMapper;

    public CourseWrapper doCourseWrapper(Course course, int userId){
       return null;
    }

    public UserSafeWrapper doUserSafeWrap(User user){
        UserSafeWrapper userSafeWrapper = new UserSafeWrapper();
        BeanUtils.copyProperties(user,userSafeWrapper);
        return userSafeWrapper;
    }
    public  TeacherSafeWrapper doTeacherSafeWrap(Teacher teacher){
        TeacherSafeWrapper teacherSafeWrapper = new TeacherSafeWrapper();
        BeanUtils.copyProperties(teacher,teacherSafeWrapper);
        return teacherSafeWrapper;
    }

    public StudentSafeWrapper doUserSafeWrap(Student user){
        StudentSafeWrapper userSafeWrapper = new StudentSafeWrapper();
        BeanUtils.copyProperties(user,userSafeWrapper);
        return userSafeWrapper;
    }
}
