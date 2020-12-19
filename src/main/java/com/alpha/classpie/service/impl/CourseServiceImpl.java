package com.alpha.classpie.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alpha.classpie.dao.CourseMapper;

import com.alpha.classpie.dao.UserCourseMapper;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.example.CourseExample;

import com.alpha.classpie.example.UserCourseExample;
import com.alpha.classpie.pojo.UserCourse;
import com.alpha.classpie.pojo.UserCourseKey;
import com.alpha.classpie.pojo.course.Course;
import com.alpha.classpie.pojo.user.Role;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.inf.CourseService;
import com.alpha.classpie.service.inf.UserService;
import com.alpha.classpie.util.PageParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service("defaultCourseService")
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    UserCourseMapper userCourseMapper;

    @Resource(name = "defaultUserService")
    UserService userService;


    @Resource(name = "defaultPasswordEncoder")
    PasswordEncoder passwordEncoder;


    private int getNextOrder(int userId){
        Integer order = userCourseMapper.getMaxOrderByUserId(userId);
        if(order==null){
            return 0;
        }else {
            return order+1;
        }
    }

    private String createCourseCode(){
        return RandomUtil.randomString(6);
    }

    @Override
    public Course createCourse(Course course, int userId) {
        course.setCode(createCourseCode());
        course.setAdminTeacherId(userId);
        course.setIsArchive(false);
        course.setTheme(1);
        courseMapper.insert(course);
        internalEnterCourse(Role.TEACHER,course.getId(),userId);
        return course;
    }

    public  List<User> getUsers(int courseId, List<Role> roles,List<Integer> excludeId){
        UserCourseExample userCourseExample = new UserCourseExample();
        UserCourseExample.Criteria criteria = userCourseExample.createCriteria()
                .andCourseIdEqualTo(courseId)
                .andRoleIdIn(roles.stream().map(Role::getId).collect(Collectors.toList()));
        if(!excludeId.isEmpty()){
            criteria.andUserIdNotIn(excludeId);
        }
        List<UserCourse> userCourses = userCourseMapper.selectByExample(userCourseExample);
        List<Integer> usersId = userCourses.stream().map(UserCourseKey::getUserId).collect(Collectors.toList());
        if(usersId.size()==0) return Collections.emptyList();
        return userService.getUsersByIds(usersId);
    }


    @Override
    public List<User> getTeachers(int courseId) {
        return getUsers(courseId,Arrays.asList(Role.TEACHER,Role.TEACHING_ASSISTANT),Collections.emptyList());
    }

    @Override
    public List<User> getStudents(int courseId) {
        return getUsers(courseId, Collections.singletonList(Role.STUDENT),Collections.emptyList());
    }

    @Override
    public List<User> getStudents(int courseId, List<Integer> excludeId) {
        return getUsers(courseId,Collections.singletonList(Role.STUDENT),excludeId);
    }

    @Override
    public boolean exitCourse(int courseId,int userId) {
        return userCourseMapper.deleteByPrimaryKey(new UserCourseKey(userId, courseId))>0;
    }

    @Override
    public List<Course> getTeachCourses(int userId) {
        return getCourses(userId,Role.TEACHER);
    }

    private List<Course> getCourses(int userId,Role role){
        return courseMapper.getCourses(userId,role.getId());
    }

    @Override
    public List<Course> getLearnCourses(int userId) {
        return getCourses(userId,Role.STUDENT);
    }

    @Override
    public boolean deleteCourse(int courseId, int userId, String password) {
        Assert.isTrue(courseMapper.selectByPrimaryKey(courseId).getAdminTeacherId().equals(userId),"非法身份");
        if(!userService.checkPassword(userId,password)){
            return false;
        }
        return courseMapper.deleteByPrimaryKey(courseId) > 0;
    }

    private boolean internalEnterCourse(Role role,int courseId,int userId){
        return userCourseMapper.insertSelective(new UserCourse(userId,courseId, role,false,getNextOrder(userId),null))>0;
    }

    @Override
    public Course enterCourse(String courseCode, int userId) {
        Course course=courseMapper.getCourseByCode(courseCode);
        if(internalEnterCourse(Role.STUDENT,course.getId(),userId)){
            return course;
        }else {
            return null;
        }
    }

    @Override
    public Course editCourse(Course course, int courseId) {
        course.setId(courseId);
        courseMapper.updateByPrimaryKey(course);
        return course;
    }



    @Override
    public boolean archiveCourse(int courseId) {
        return courseMapper.updateArchive(true,courseId)>0;
    }

    @Override
    public boolean archiveOwnCourse(int userId, int courseId) {
        return userCourseMapper.updateArchive(true,userId,courseId)>0;
    }

    @Override
    public List<Course> getCourses(PageParam pageParam) {
        return null;
    }

    @Override
    public List<Course> getAllCourses(int userId) {
        return userCourseMapper.getCoursesByUserId(userId);
    }

    @Override
    public long getCourseMemberCount(int courseId) {
        return getTeacherCountInCourse(courseId)+getStudentCountInCourse(courseId);
    }

    @Override
    public long getStudentCountInCourse(int courseId) {
        UserCourseExample userCourseExample = new UserCourseExample();
        userCourseExample.createCriteria().andCourseIdEqualTo(courseId).andRoleIdEqualTo(Role.STUDENT.getId());
        return userCourseMapper.countByExample(userCourseExample);
    }

    @Override
    public long getTeacherCountInCourse(int courseId) {
        UserCourseExample userCourseExample = new UserCourseExample();
        userCourseExample.createCriteria().andCourseIdEqualTo(courseId).andRoleIdIn(Arrays.asList(Role.TEACHER.getId(),Role.TEACHING_ASSISTANT.getId()));
        return userCourseMapper.countByExample(userCourseExample);
    }

    @Override
    public int getOrder(int courseId, int userId) {
        UserCourse userCourse = userCourseMapper.selectByPrimaryKey(new UserCourseKey(userId, courseId));
        Assert.notNull(userCourse,"未拥有课程");
        return userCourse.getCourseOrder();
    }

    @Override
    public Course getCourseById(int courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    @Override
    public List<Course> list(CourseExample example) {
        return courseMapper.selectByExample(example);
    }

    public String flushCourseCode(int courseId){
        String courseCode = createCourseCode();
        if(courseMapper.updateCourseCode(courseId,courseCode)>0){
            return courseCode;
        }else{
            return null;
        }
    }

    @Override
    public boolean updateArchiveState(int courseId, boolean archiveState) {
        return courseMapper.updateArchive(archiveState,courseId)>0;
    }

    @Override
    public boolean updateOwnArchiveState(int courseId, int userId,boolean archiveState) {
        return userCourseMapper.updateArchive(archiveState,userId,courseId)>0;
    }

    @Override
    public boolean stopCourseCode(int courseId) {
        Course course = courseMapper.selectByPrimaryKey(courseId);
        course.setCode(null);
        return courseMapper.updateByPrimaryKey(course)>0;
    }

    @Override
    public boolean transferCourse(int userId, int courseId,int toUserId) {
        User user = userService.getUserById(toUserId);
        if(!this.getCourseById(courseId).getAdminTeacherId().equals(userId)){
            throw new ExceptionDto("权限拒绝","自身不具备转课权限","获取课程转让权限");
        }
        if(!user.getRoles().contains(Role.TEACHER)){
            throw new ExceptionDto("权限拒绝",user.getName()+" 不具备教授课程的权限","重新选择转让目标");
        }
        Course course = new Course();
        course.setId(courseId);
        course.setAdminTeacherId(toUserId);//设置管理员
        courseMapper.updateByPrimaryKeySelective(course);//更新
        return this.exitCourse(courseId,userId);//退课
    }

    @Override
    public boolean sort(int userId,List<Integer> courseIds) {
        UserCourseExample userCourseExample = new UserCourseExample();
        userCourseExample.setOrderByClause("course_order ASC");
        userCourseExample.createCriteria().andUserIdEqualTo(userId);
        List<UserCourse> userCourses = userCourseMapper.selectByExample(userCourseExample);
        return false;
    }

    @Override
    public boolean hasInCourse(int userId, int courseId) {
        UserCourse userCourse = userCourseMapper.selectByPrimaryKey(new UserCourseKey(userId, courseId));
        return userCourse!=null;
    }

    @Override
    public Role getRoleInCourse(int courseId, int userId) {
        UserCourse userCourse = userCourseMapper.selectByPrimaryKey(new UserCourseKey(userId, courseId));
        return userCourse.getRole();
    }
}
