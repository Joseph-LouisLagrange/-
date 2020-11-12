package com.alpha.classpie.service.impl;

import com.alpha.classpie.dao.CourseMapper;

import com.alpha.classpie.dao.UserCourseMapper;
import com.alpha.classpie.example.CourseExample;

import com.alpha.classpie.example.UserCourseExample;
import com.alpha.classpie.pojo.UserCourse;
import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.pojo.user.Role;
import com.alpha.classpie.service.inf.CourseService;
import com.alpha.classpie.util.PageParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/9
 */
@Service("defaultCourseService")
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    UserCourseMapper userCourseMapper;

    private int getNextOrder(int userId){
        Integer order = userCourseMapper.getMaxOrderByUserId(userId);
        if(order==null){
            return 0;
        }else {
            return order+1;
        }
    }

    @Override
    public Course createCourse(Course course, int userId) {
        courseMapper.insert(course);
        userCourseMapper.insertSelective(new UserCourse(userId,course.getId(), Role.TEACHER,false,getNextOrder(userId),null));
        return course;
    }

    @Override
    public List<Course> getTeachCourses(int userId) {
        UserCourseExample userCourseExample = new UserCourseExample();
        userCourseExample.createCriteria().andUserIdEqualTo(userId);
        return null;
    }

    private List<Course> getCourses(int userId,Role role){
        return null;
    }

    @Override
    public List<Course> getLearnCourses(int userId) {
        return null;
    }

    @Override
    public boolean deleteCourse(int courseId, int userId, String password) {
        return false;
    }

    @Override
    public Course enterCourse(String courseCode, int userId) {
        return null;
    }

    @Override
    public Course editCourse(Course course, int courseId) {
        return null;
    }

    @Override
    public boolean archiveCourse(int courseId) {
        return false;
    }

    @Override
    public boolean archiveOwnCourse(int userId, int courseId) {
        return false;
    }

    @Override
    public List<Course> getCourses(PageParam pageParam) {
        return null;
    }

    @Override
    public List<Course> getAllCourses(int userId) {
        return null;
    }

    @Override
    public long getCourseMemberCount(int courseId) {
        return 0;
    }

    @Override
    public long getStudentCountInCourse(int courseId) {
        return 0;
    }

    @Override
    public long getTeacherCountInCourse(int courseId) {
        return 0;
    }

    @Override
    public int getOrder(int courseId, int userId) {
        return 0;
    }

    @Override
    public List<Course> list(PageParam pageParam, CourseExample example) {
        return null;
    }
}
