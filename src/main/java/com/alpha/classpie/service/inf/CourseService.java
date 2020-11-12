package com.alpha.classpie.service.inf;

import com.alpha.classpie.example.CourseExample;
import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.util.PageParam;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/9
 */
public interface CourseService extends BaseService<Course, CourseExample> {
    public Course createCourse(Course course,int userId);
    public List<Course> getTeachCourses(int userId);
    public List<Course> getLearnCourses(int userId);
    public boolean deleteCourse(int courseId,int userId,String password);
    public Course enterCourse(String courseCode,int userId);
    public Course editCourse(Course course , int courseId);
    public boolean archiveCourse(int courseId);
    public boolean archiveOwnCourse(int userId,int courseId);
    public List<Course> getCourses(PageParam pageParam);
    public List<Course> getAllCourses(int userId);
    public long getCourseMemberCount(int courseId);
    public long getStudentCountInCourse(int courseId);
    public long getTeacherCountInCourse(int courseId);
    public int getOrder(int courseId,int userId);
}
