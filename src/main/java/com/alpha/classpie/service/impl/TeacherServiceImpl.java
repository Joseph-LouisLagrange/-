package com.alpha.classpie.service.impl;

import com.alpha.classpie.dao.*;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.pojo.user.Teacher;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.pojo.user.UserRole;
import com.alpha.classpie.service.inf.CaptchaService;
import com.alpha.classpie.type.UserNameType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author 杨能
 * @create 2020/11/5
 */
@Service("teacherServiceImpl")
@Validated
public class TeacherServiceImpl extends SimpleUserService {

    @Autowired
    TeacherCustomMajorMapper teacherCustomMajorMapper;

    @Autowired
    TeacherMajorMapper teacherMajorMapper;

    @Autowired
    TeacherTeachCourseMapper teacherTeachCourseMapper;

    @Autowired
    TeacherDepartmentMapper teacherDepartmentMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    CaptchaService captchaService;

    private Teacher toTeacher(@NotNull User user){
        Teacher teacher=new Teacher();
        BeanUtils.copyProperties(user,teacher);
        Integer id = teacher.getId();
        teacher.setMajors(teacherMajorMapper.getMajorsByUserId(id));
        teacher.setTeacherTeachCourses(teacherTeachCourseMapper.getTeacherTeachCourseByUserId(id));
        teacher.setTeacherDepartments(teacherDepartmentMapper.getDepartmentsByUserId(id));
        teacher.setTeacherCustomMajors(teacherCustomMajorMapper.getTeacherCustomMajorsByUserId(id));
        return teacher;
    }


    @Override
    public Teacher loginByAccountNumber(String accountNumber, String password) {
        return login(UserNameType.ACCOUNT,accountNumber,password);
    }

    @Override
    public Teacher loginByEmailNumber(String emailNumber, String password) {
        return login(UserNameType.EMAIL,emailNumber,password);
    }

    @Override
    public Teacher loginByTelephoneNumber(String telephoneNumber, String password) {
        return login(UserNameType.PHONE,telephoneNumber,password);
    }

    @Override
    public Teacher loginBySMSVerificationCode(String telephoneNumber, int verificationCode) throws ExceptionDto {
        User user = super.loginBySMSVerificationCode(telephoneNumber, verificationCode);
        if(user==null) {
            return null;
        }else {
            return toTeacher(user);
        }
    }

    @Override
    public Teacher login(UserNameType userNameType, String userName, String password) {
        User user = super.login(userNameType, userName, password);
        if(user==null) {
            return null;
        }
        return toTeacher(user);
    }

    @Override
    public Teacher getUserById(int id) {
        return toTeacher(userMapper.selectByPrimaryKey(id));
    }

    private Teacher insert(User user){
        Teacher teacher= (Teacher) user;
        userRoleMapper.insert(new UserRole(teacher.getId(),2));
        return teacher;
    }

    @Override
    public User smsRegister(User user, int verificationCode) {
        User u = super.smsRegister(user, verificationCode);
        return insert(u);
    }

    @Override
    public User emailRegister(User user, int verificationCode) {
        User u = super.emailRegister(user, verificationCode);
        return insert(u);
    }
}
