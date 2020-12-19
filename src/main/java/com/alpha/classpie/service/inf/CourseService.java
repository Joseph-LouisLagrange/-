package com.alpha.classpie.service.inf;

import com.alpha.classpie.example.CourseExample;
import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.pojo.user.Role;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.util.PageParam;

import java.util.List;


public interface CourseService extends BaseService<Course, CourseExample> {
    public Course createCourse(Course course,int userId);
    public List<User> getTeachers(int courseId);
    public List<User> getStudents(int courseId);
    public List<User> getStudents(int courseId, List<Integer> excludeId);
    public boolean exitCourse(int courseId,int userId);
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
    public Course getCourseById(int courseId);
    public String flushCourseCode(int courseId);
    public boolean updateArchiveState(int courseId,boolean archiveState);
    public boolean updateOwnArchiveState(int courseId, int userId,boolean archiveState);
    public boolean stopCourseCode(int courseId);
    public boolean transferCourse(int userId,int courseId,int toUserId);
    public boolean sort(int userId, List<Integer> courseIds);
    public boolean hasInCourse(int userId,int courseId);
    public Role getRoleInCourse(int courseId,int userId);
}
