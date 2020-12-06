package com.alpha.classpie.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alpha.classpie.dao.*;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.example.StudentExample;
import com.alpha.classpie.example.UserExample;
import com.alpha.classpie.example.UserRoleExample;
import com.alpha.classpie.pojo.user.*;
import com.alpha.classpie.service.inf.UserService;
import com.alpha.classpie.service.inf.captcha.RemoteCaptchaService;
import com.alpha.classpie.type.UserNameType;
import com.alpha.classpie.util.FormatRecognitionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/1
 */

@Transactional
@Service("defaultUserService")
public class UserServiceImpl implements UserService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Resource(name = "emailRegisterRemoteCaptchaService")
    RemoteCaptchaService emailRegisterRemoteCaptchaService;

    @Resource(name = "smsRegisterRemoteCaptchaService")
    RemoteCaptchaService smsRegisterRemoteCaptchaService;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    TeacherDepartmentMapper teacherDepartmentMapper;

    @Autowired
    TeacherMajorMapper teacherMajorMapper;

    @Autowired
    TeacherCustomMajorMapper teacherCustomMajorMapper;

    @Autowired
    TeacherTeachCourseMapper teacherTeachCourseMapper;

    @Resource(name ="smsBindRemoteCaptchaService")
    RemoteCaptchaService smsBindRemoteCaptchaService;
    @Resource(name ="emailBindRemoteCaptchaService")
    RemoteCaptchaService emailBindRemoteCaptchaService;


    @Resource(name = "defaultPasswordEncoder")
    PasswordEncoder passwordEncoder;

    public User wrap(User user){
        //随机账号
        user.setAccountNumber(RandomUtil.randomString(9));
        String password = user.getPassword();
        //密码安全编码
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }


    protected User smsUserRegister(User user,List<Role> roles,int verificationCode)  {
        if(smsRegisterRemoteCaptchaService.checkCaptcha(user.getTelephoneNumber(),verificationCode)){
            User wrap = wrap(user);
            userMapper.insertSelective(wrap);
            for(Role role: roles){
                userRoleMapper.insert(new UserRole(wrap.getId(),role.getId()));
            }
            smsRegisterRemoteCaptchaService.deleteCaptcha(user.getTelephoneNumber());
            return user;
        }
        return null;
    }


    protected User emailUserRegister(User user,List<Role> roles,int verificationCode) {
        if(emailRegisterRemoteCaptchaService.checkCaptcha(user.getEmailNumber(),verificationCode)){
            User wrap = wrap(user);
            userMapper.insertSelective(wrap);
            for(Role role: roles){
                userRoleMapper.insert(new UserRole(wrap.getId(),role.getId()));
            }
            emailRegisterRemoteCaptchaService.deleteCaptcha(user.getEmailNumber());
            return user;
        }
        return null;
    }


    @Override
    public User smsTeacherRegister(Teacher teacher, int verificationCode) {
        return this.smsUserRegister(teacher,Collections.singletonList(Role.TEACHER),verificationCode);
    }

    @Override
    public User emailTeacherRegister(Teacher teacher, int verificationCode) {
        return this.emailUserRegister(teacher,Collections.singletonList(Role.TEACHER),verificationCode);
    }

    @Override
    public User teacherRegister(Teacher teacher, String registerUsername, int verificationCode) {
        UserNameType userNameType = FormatRecognitionUtil.identifyUserName(registerUsername);
        if(userNameType==UserNameType.EMAIL){
            return this.emailTeacherRegister(teacher, verificationCode);
        }else if(userNameType==UserNameType.PHONE){
            return this.smsTeacherRegister(teacher, verificationCode);
        }else {
            throw new ExceptionDto("格式错误","注册的账号格式无法识别","重新注册");
        }
    }

    @Override
    public User smsStudentRegister(Student student, int verificationCode) {
        User user = this.smsUserRegister(student, Collections.singletonList(Role.STUDENT),verificationCode);
        if(user!=null){
            //添加学号
            studentMapper.insert(student);
        }
        return user;
    }

    @Override
    public User emailStudentRegister(Student student, int verificationCode) {
        User user = this.emailUserRegister(student,Collections.singletonList(Role.STUDENT),verificationCode);
        if(user!=null){
            //添加学号
            studentMapper.insert(student);
        }
        return user;
    }

    @Override
    public User studentRegister(Student student, String registerUsername, int verificationCode) {
        UserNameType userNameType = FormatRecognitionUtil.identifyUserName(registerUsername);
        if(userNameType==UserNameType.EMAIL){
            return this.emailStudentRegister(student, verificationCode);
        }else if(userNameType==UserNameType.PHONE){
            return this.smsStudentRegister(student, verificationCode);
        }else {
            throw new ExceptionDto("格式错误","注册的账号格式无法识别","重新注册");
        }
    }


    @Override
    public User login(String username, String password) {
        User user = getUserByUsername(username);
        if(user!=null && passwordEncoder.matches(password,user.getPassword())){
            //检验密码
            return user;
        }
        return null;
    }

    public User getUserByUsername(String username) {
        List<User> users = userMapper.selectByExample(getUserExampleByUsername(username));
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }


    @Override
    @Cacheable(value = "user",key = "#id")
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUsersByIds(List<Integer> ids){
        if (!ids.isEmpty())
            return userMapper.getUsersByIds(ids);
        return Collections.emptyList();
    }

    @Override
    public List<Student> getStudentByIds(List<Integer> ids) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andUserIdIn(ids);
        return studentMapper.selectByExample(studentExample);
    }

    private UserExample getUserExampleByUsername(String username){
        UserNameType userNameType = FormatRecognitionUtil.identifyUserName(username);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(userNameType == UserNameType.ACCOUNT){
            criteria.andAccountNumberEqualTo(username);
        }else if(userNameType == UserNameType.EMAIL){
            criteria.andEmailNumberEqualTo(username);
        }else if(userNameType==UserNameType.PHONE){
            criteria.andTelephoneNumberEqualTo(username);
        }else {
            //未知的username格式
            throw new ExceptionDto("username格式未知","无法识别该username:["+username+"]","重新输入账号");
        }
        return userExample;
    }

    @Override
    public Integer getUserIdByUsername(String username) {
        return userMapper.getUserId(getUserExampleByUsername(username));
    }

    @Override
    public boolean checkPassword(int userId,String password) {
        User user = getUserById(userId);
        Assert.notNull(user,"userId不存在["+userId+"]");
        return passwordEncoder.matches(password,user.getPassword());
    }

    @Override
    public boolean hasUsername(String username) {
        return getUserIdByUsername(username)!=null;
    }


    //@CacheEvict(value = "user",key = "#userId",beforeInvocation = false)
    @Override
    public boolean addDepartment(int departmentId, int userId) {
        return teacherDepartmentMapper.insert(new TeacherDepartment(userId,departmentId)) > 0;
    }

    @Override
    public boolean deleteDepartment(int departmentId, int userId) {
        return teacherDepartmentMapper.deleteByPrimaryKey(new TeacherDepartment(userId,departmentId))>0;
    }

    //@CacheEvict(value = "user",key = "#userId",beforeInvocation = false)
    @Override
    public boolean addMajor(int majorId, int userId) {
        return teacherMajorMapper.insert(new TeacherMajor(userId,majorId))>0;
    }

    @Override
    public boolean deleteMajor(int majorId, int userId) {
        return teacherMajorMapper.deleteByPrimaryKey(new TeacherMajor(userId,majorId))>0;
    }

   // @CacheEvict(value = "user",key = "#teacherCustomMajor.userId",beforeInvocation = false)
    @Override
    public boolean addTeacherCustomMajor(TeacherCustomMajor teacherCustomMajor) {
        return teacherCustomMajorMapper.insert(teacherCustomMajor)>0;
    }

    @Override
    public boolean deleteTeacherCustomMajor(TeacherCustomMajor teacherCustomMajor) {
        return teacherCustomMajorMapper.deleteByPrimaryKey(teacherCustomMajor)>0;
    }

    @Override
    public boolean addTeachCourse(String teachCourse, int userId) {
       return teacherTeachCourseMapper.insert(new TeacherTeachCourse(userId,teachCourse))>0;
    }

    @Override
    public boolean deleteTeachCourse(String teachCourse, int userId) {
        return teacherTeachCourseMapper.deleteByPrimaryKey(new TeacherTeachCourse(userId,teachCourse))>0;
    }


    @CachePut(value = "user",key = "#user.id")
    @Override
    public User roleToStudent(User user) {
        Integer id = user.getId();
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(id);
        //更新角色
        userRoleMapper.updateByExample(new UserRole(id,Role.STUDENT.getId()),userRoleExample);
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andUserIdEqualTo(id);
        if(studentMapper.countByExample(studentExample)==0) {
            //添加 null学号
            studentMapper.insert(new Student(id, null));
        }
        return getUserById(id);
    }

    @CachePut(value = "user",key = "#user.id")
    @Override
    public User roleToTeacher(User user) {
        Integer id = user.getId();
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(id);
        userRoleMapper.updateByExample(new UserRole(id,Role.TEACHER.getId()),userRoleExample);
        return getUserById(id);
    }


    @CachePut(value = "user",key = "#id")
    @Override
    public User updateUser(User user, int id) {
        user.setId(id);
        String password = user.getPassword();
        //密码安全编码
        if(password!=null)
            user.setPassword(passwordEncoder.encode(password));
        userMapper.updateByPrimaryKeySelective(user);
        return user;
    }

    @CacheEvict(value = "user",key = "#userId")
    @Override
    public boolean updateEmail(int userId, String password, String email) {
        if(checkPassword(userId, password)){
            User user = new User();
            user.setId(userId);
            user.setEmailNumber(email);
           return userMapper.updateByPrimaryKeySelective(user)>0;
        }
        return false;
    }

    @CacheEvict(value = "user",key = "#userId")
    @Override
    public boolean updateTelephone(int userId, String password, String telephone) {
        if(checkPassword(userId, password)){
            User user = new User();
            user.setId(userId);
            user.setTelephoneNumber(telephone);
            return userMapper.updateByPrimaryKeySelective(user)>0;
        }
        return false;
    }


    protected boolean setSafeTelephone(int userId, String telephone, String password){
        if(!checkPassword(userId,password)){
            return false;
        }else {
            return setTelephone(userId, telephone)!=null;
        }
    }

    protected boolean setSafeEmail(int userId, String email,String password){
        if(!checkPassword(userId,password)){
            return false;
        }else {
            return setEmail(userId, email)!=null;
        }
    }


    public User setTelephone(int userId, String telephone){
        User user = getUserById(userId);
        if(user==null) return null;
        user.setTelephoneNumber(telephone);
        userMapper.updateByPrimaryKey(user);
        return user;
    }

    public User setEmail(int userId,String email){
        User user = getUserById(userId);
        if(user==null) return null;
        user.setEmailNumber(email);
        userMapper.updateByPrimaryKey(user);
        return user;
    }


    @CacheEvict(value = "user",key = "#userId")
    @Override
    public boolean unbindTelephone(int userId, String password) {
       return setSafeTelephone(userId,null,password);
    }


    @CacheEvict(value = "user",key = "#userId")
    @Override
    public boolean unbindEmail(int userId, String password) {
       return setSafeEmail(userId,null, password);
    }


    @CacheEvict(value = "user",key = "#userId")
    @Override
    public boolean bindTelephone(int userId,@NotNull String telephone, int verificationCode) {
        //校验验证码
        if(smsBindRemoteCaptchaService.checkCaptcha(telephone,verificationCode)){
            smsBindRemoteCaptchaService.deleteCaptcha(telephone);
            //绑定
            return setTelephone(userId, telephone)!=null;
        }
        return false;
    }

    @CacheEvict(value = "user",key = "#userId")
    @Override
    public boolean bindEmail(int userId, @NotNull String email, int verificationCode) {
        if(emailBindRemoteCaptchaService.checkCaptcha(email,verificationCode)){
            emailBindRemoteCaptchaService.deleteCaptcha(email);
            return setEmail(userId, email)!=null;
        }
        return false;
    }

    @Override
    public Student getStudentByUserId(int userId) {
        return studentMapper.selectByPrimaryKey(userId);
    }
}

