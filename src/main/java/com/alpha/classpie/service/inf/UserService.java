package com.alpha.classpie.service.inf;

import com.alpha.classpie.dto.DepartmentTree;
import com.alpha.classpie.dto.EducationTree;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.pojo.other.Department;
import com.alpha.classpie.pojo.user.*;
import com.alpha.classpie.type.UserNameType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * @author 杨能
 * @create 2020/11/1
 */
@Service
public interface UserService{
    public User smsTeacherRegister(Teacher teacher, int verificationCode);
    public User emailTeacherRegister(Teacher teacher, int verificationCode);
    public User teacherRegister(Teacher teacher, String registerUsername, int verificationCode);
    public User smsStudentRegister(Student student,int verificationCode);
    public User emailStudentRegister(Student student,int verificationCode);
    public User studentRegister(Student student,String registerUsername,int verificationCode);
    //username password 登陆
    public User login(String userName, String password);
    //使用id获取user
    public User getUserById(int id);
    //批量获取
    public List<User> getUsersByIds(List<Integer> ids);
    public List<Student> getStudentByIds(List<Integer> ids);
    //使用用户名获取user
    public User getUserByUsername(String username);
    //使用username转到id
    public Integer getUserIdByUsername(String username);
    //检查密码
    public boolean checkPassword(int userId,String password);
    //计算是否有用户名
    public boolean hasUsername(String username);

    //以下为个人资料编辑接口
    public boolean addDepartment(int departmentId,int userId);
    public boolean deleteDepartment(int departmentId,int userId);
    public boolean addMajor(int majorId,int userId);
    public boolean deleteMajor(int majorId,int userId);
    public boolean addTeacherCustomMajor(TeacherCustomMajor teacherCustomMajor);
    public boolean deleteTeacherCustomMajor(TeacherCustomMajor teacherCustomMajor);
    public boolean addTeachCourse(String teachCourse,int userId);
    public boolean deleteTeachCourse(String teachCourse,int userId);
    public User roleToStudent(User user);
    public User roleToTeacher(User user);
    public User updateUser(User user,int id);
    public boolean updateEmail(int userId,String password,String email);
    public boolean updateTelephone(int userId,String password,String telephone);
    public boolean unbindTelephone(int userId,String password);
    public boolean unbindEmail(int userId,String password);
    public boolean bindTelephone(int userId, @NotNull String telephone, int verificationCode);
    public boolean bindEmail(int userId,@NotNull String email,int verificationCode);
    public Student getStudentByUserId(int userId);
}
