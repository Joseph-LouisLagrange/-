package com.alpha.classpie.service.impl;

import com.alpha.classpie.dao.StudentMapper;
import com.alpha.classpie.dao.UserRoleMapper;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.pojo.user.Student;
import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.pojo.user.UserRole;
import com.alpha.classpie.service.inf.CaptchaService;
import com.alpha.classpie.type.UserNameType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author 杨能
 * @create 2020/11/5
 */

@Validated
@Service("studentService")
@Transactional
public class StudentServiceImpl extends SimpleUserService {

    private Student toStudent(@NotNull User user){
        Student student=new Student();
        String studentId = studentMapper.selectByPrimaryKey(user.getId()).getStudentId();
        BeanUtils.copyProperties(user,student);
        student.setStudentId(studentId);
        return student;
    }

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    CaptchaService captchaService;

    @Autowired
    UserRoleMapper userRoleMapper;



    @Override
    public Student loginByAccountNumber(String accountNumber, String password) {
        return login(UserNameType.ACCOUNT,accountNumber,password);
    }

    @Override
    public Student loginByEmailNumber(String emailNumber, String password) {
        return login(UserNameType.EMAIL,emailNumber,password);
    }

    @Override
    public Student loginByTelephoneNumber(String telephoneNumber, String password) {
        return login(UserNameType.PHONE,telephoneNumber,password);
    }

    @Override
    public Student loginBySMSVerificationCode(String telephoneNumber, int verificationCode) throws ExceptionDto {
        User user = super.loginBySMSVerificationCode(telephoneNumber, verificationCode);
        return toStudent(user);
    }

    @Override
    public Student login(UserNameType userNameType, String userName, String password) {
        User user = login(userNameType, userName, password);
        if(user==null){
            return null;
        }else {
            return toStudent(user);
        }
    }

    @Override
    public Student getUserById(int id) {
        return toStudent(userMapper.selectByPrimaryKey(id));
    }


    @Override
    public Student emailRegister(User user, int verificationCode) {
        User u = super.emailRegister(user, verificationCode);
        return insert(u);
    }

    private Student insert(User user){
        if(user==null){
            return null;
        }
        Student student= (Student) user;
        userRoleMapper.insert(new UserRole(user.getId(),2));
        student.setId(user.getId());
        studentMapper.insertSelective(student);
        return student;
    }

    @Override
    public Student smsRegister(User user, int verificationCode) {
        User u = super.smsRegister(user, verificationCode);
        return insert(u);
    }
}
