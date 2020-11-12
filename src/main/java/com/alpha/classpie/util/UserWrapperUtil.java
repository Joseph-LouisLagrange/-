package com.alpha.classpie.util;

import com.alpha.classpie.dao.*;
import com.alpha.classpie.pojo.user.Student;
import com.alpha.classpie.pojo.user.Teacher;
import com.alpha.classpie.pojo.user.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/5
 */
@Component
public class UserWrapperUtil {
    @Autowired
    TeacherMajorMapper teacherMajorMapper;

    @Autowired
    TeacherCustomMajorMapper teacherCustomMajorMapper;

    @Autowired
    TeacherTeachCourseMapper teacherTeachCourseMapper;

    @Autowired
    TeacherDepartmentMapper teacherDepartmentMapper;

    @Autowired
    StudentMapper studentMapper;

    public  Teacher toTeacher(User user){
        Teacher teacher=new Teacher();
        BeanUtils.copyProperties(user,teacher);
        Integer userId = user.getId();
        teacher.setMajors(teacherMajorMapper.getMajorsByUserId(userId));
        teacher.setTeacherCustomMajors(teacherCustomMajorMapper.getTeacherCustomMajorsByUserId(userId));
        teacher.setTeacherDepartments(teacherDepartmentMapper.getDepartmentsByUserId(userId));
        teacher.setTeacherTeachCourses(teacherTeachCourseMapper.getTeacherTeachCourseByUserId(userId));
        return teacher;
    }

    public Student toStudent(User user){
        Student student=new Student();
        BeanUtils.copyProperties(user,student);
        String studentId = studentMapper.selectByPrimaryKey(user.getId()).getStudentId();
        student.setStudentId(studentId);
        return student;
    }
}
